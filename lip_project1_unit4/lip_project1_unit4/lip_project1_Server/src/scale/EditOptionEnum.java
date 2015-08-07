/**
 * 
 */
package scale;

/**
 * @author Li Pei
 * @andrew_id lip 
 */
public enum EditOptionEnum {
	// enumeration of all edit option you could choose
	EditOptionSetName(1),EditOptionPrice(2);
	private int value;

	
	private EditOptionEnum(int value) {
		this.setValue(value);
	}

	public int getValue() {
		return value;
	}

	private void setValue(int value) {
		this.value = value;
	}
}
