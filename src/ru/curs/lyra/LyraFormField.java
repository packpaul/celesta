package ru.curs.lyra;

/**
 * Lyra form field metadata.
 */
public class LyraFormField {
	private LyraFieldType type;
	private boolean editable;
	private boolean visible;
	private String caption;
	private String lookup;

	/**
	 * Field type.
	 */
	public LyraFieldType getType() {
		return type;
	}

	/**
	 * Sets field type.
	 * 
	 * @param type
	 *            the type to set
	 */
	public void setType(LyraFieldType type) {
		this.type = type;
	}

	/**
	 * Is the field editable?
	 */
	public boolean isEditable() {
		return editable;
	}

	/**
	 * 
	 * Sets editable property.
	 * 
	 * @param editable
	 *            editable property.
	 */
	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	/**
	 * Is the field visible?
	 */
	public boolean isVisible() {
		return visible;
	}

	/**
	 * Sets visible property.
	 * 
	 * @param visible
	 *            the visible to set
	 */
	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	/**
	 * Caption of the field.
	 */
	public String getCaption() {
		return caption;
	}

	/**
	 * Sets new caption.
	 * 
	 * @param caption
	 *            the caption to set
	 */
	public void setCaption(String caption) {
		this.caption = caption;
	}

	/**
	 * Lookup procedure.
	 */
	public String getLookup() {
		return lookup;
	}

	/**
	 * Sets lookup procedure.
	 * 
	 * @param lookup
	 *            the lookup procedure to set
	 */
	public void setLookup(String lookup) {
		this.lookup = lookup;
	}

}
