package adapter;

/**
 * @author Li Pei
 * @andrew_id lip
 */
public interface UpdateAuto {
	// This function searches the Model for a given OptionSet and sets the name
	// of OptionSet to newName.
	public void updateOptionSetName(String ModelName, String OptionSetName,
			String newName);

	// This function searches the Model for a given OptionSet and Option name,
	// and sets the price to newPrice.
	public void updateOptionPrice(String ModelName, String Optionname,
			String Option, float newprice);
}
