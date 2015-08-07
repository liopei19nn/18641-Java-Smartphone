package exception;

/**
 * @author Li Pei
 * @andrew_id lip
 */
public enum CustomExceptionEnum {
	// enumeration of all exception
	FileNotFound(1), FileMissBasePrice(2), FileOptionPriceNotFound(3), CarModelNotFound(4),
	SavedCarFileNotFound(5), OptionSetNotFound(6), OptionNotFound(7);
	private int value;

	private CustomExceptionEnum(int value) {
		this.setValue(value);
	}

	public int getValue() {
		return value;
	}

	private void setValue(int value) {
		this.value = value;
	}
}
