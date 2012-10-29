package hu.kazocsaba.columntable;

import java.util.Objects;

/**
 * Describes a column of data in a {@link ColumnTableModel}. This class encapsulates data and behavior that is commonly
 * associated with a column in a table, namely:
 * <ul>
 * <li>the name of the column</li>
 * <li>the class of the column</li>
 * <li>the getter and setter for the value at a row</li>
 * <li>whether cells in the column are editable</li>
 * </ul>
 * For the
 * common case, the default implementations of the functions for getting the name, class, and the editable property
 * return fields that can be set at construction time.
 *
 * @author Kazó Csaba
 */
public abstract class Column {
	private String name;
	private Class<?> clazz;
	private boolean editable;

	/**
	 * Creates a new instance with the specified name. The default class is {@code Object.class} and the editable
	 * property is {@code false}.
	 *
	 * @param name the column name
	 */
	public Column(String name) {
		this(name, Object.class, false);
	}

	/**
	 * Creates a new instance with the specified name and class. The editable property is set to {@code false}.
	 *
	 * @param name the column name
	 * @param clazz the column class
	 */
	public Column(String name, Class<?> clazz) {
		this(name, clazz, false);
	}

	/**
	 * Creates a new instance with the specified default properties.
	 *
	 * @param name the column name
	 * @param clazz the column class
	 * @param editable the editable property
	 */
	public Column(String name, Class<?> clazz, boolean editable) {
		this.name = name;
		this.clazz = Objects.requireNonNull(clazz);
		this.editable = editable;
	}

	/**
	 * Returns the value for the cell at the specified row.
	 *
	 * @param row the index of the row
	 * @return the value at the specified cell
	 */
	public abstract Object get(int row);

	/**
	 * Sets the value for the cell at the specified row. The default implementation does nothing.
	 *
	 * @param aValue the new value
	 * @param row the index of the row
	 */
	public void set(Object aValue, int row) {
	}

	/**
	 * Returns whether the cell at the specified row is editable. The default implementation returns the field that was
	 * set at construction time.
	 *
	 * @param row the index of the row
	 * @return whether the specified cell is editable
	 */
	public boolean isEditable(int row) {
		return editable;
	}

	/**
	 * Returns the class of the values in this column. The default implementation returns the value supplied at
	 * construction time.
	 *
	 * @return the column class
	 */
	public Class<?> getColumnClass() {
		return clazz;
	}

	/**
	 * Returns the name of this column. The default implementation returns the name supplied at construction time.
	 *
	 * @return the column name
	 */
	public String getName() {
		return name;
	}
}
