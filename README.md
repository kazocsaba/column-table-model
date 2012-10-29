Column Table Model
==================

Introduction
------------

`ColumnTableModel` is an extension of `AbstractTableModel` that reorganizes the data and functions that conceptually belong to a column.

Using `AbstractTableModel`, a simple table can be implemented like this:

	public class StringsTableModel extends AbstractTableModel {
		private final List<String> data=new ArrayList<>();
		
		@Override
		public int getColumnCount() {
			return 3;
		}
		@Override
		public Object getColumnName(int col) {
			switch (col) {
				case 0: return "Index";
				case 1: return "String";
				case 2: return "Hash";
				default: throw new IndexOutOfBoundsException();
			}
		}
		@Override
		public Object getValueAt(int row, int col) {
			switch (col) {
				case 0: return row+1;
				case 1: return data.get(row);
				case 2: return data.get(row).hashCode();
				default: throw new IndexOutOfBoundsException();
			}
		}
		@Override
		public Object getColumnClass(int col) {
			switch (col) {
				case 0: return Integer.class;
				case 1: return String.class;
				case 2: return Integer.class;
				default: throw new IndexOutOfBoundsException();
			}
		}
		@Override
		public boolean isCellEditable(int row, int col) {
			return col==1;
		}
		@Override
		public void setValueAt(Object value, int row, int col) {
			if (col==1) {
				data.set(row, (String)value);
				fireTableRowsUpdated(row, row);
			}
		}
	}

Especially when the model contains more columns, this code becomes hard to maintain. The columns are identified by their indices, and data belonging to a particular column is spread over several functions. For example, changing the order of the columns requires rewriting a lot of indices in the model functions; it is easy to make a mistake.

Example
-------

`ColumnTableModel` groups the code belonging to each column into a `Column` class. The following example class implements the same model as the above, but using `ColumnTableModel`:

	public class StringsTableModel extends ColumnTableModel {
		private final List<String> data=new ArrayList<>();
		
		public final Column indexColumn=new Column("Index", Integer.class) {
			@Override
			public Object get(int row) {
				return row+1;
			}
		};
		public final Column stringColumn=new Column("Value", String.class, true) {
			@Override
			public Object get(int row) {
				return data.get(row);
			}
			@Override
			public void set(Object value, int row) {
				data.set(row, (String)value);
				fireTableRowsUpdated(row, row);
			}
		};
		public final Column hashColumn=new Column("Hash", Integer.class) {
			@Override
			public Object get(int row) {
				return data.get(row).hashCode();
			}
		};
		
		public StringsTableModel() {
			addColumns(indexColumn, stringColumn, hashColumn);
		}

		@Override
		public int getRowCount() {
			return data.size();
		}
	}

Using
-----

The library resides in the central Maven repository with
group ID `hu.kazocsaba` and artifact ID `column-table-model`. If
you use a project management system which can fetch dependencies
from there, you can just add the library as a dependency. E.g.
in Maven:

	<dependency>
		<groupId>hu.kazocsaba</groupId>
		<artifactId>column-table-model</artifactId>
		<version>1.0.0</version>
	</dependency>

You can also browse the [online javadoc](http://kazocsaba.github.com/column-table-model/apidocs/index.html).
