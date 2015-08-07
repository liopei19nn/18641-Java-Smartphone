package model;

import java.io.Serializable;
import model.OptionSet.Option;

/**
 * @author Li Pei
 * @andrew_id lip
 */

public class Automotive implements Serializable {

	private static final long serialVersionUID = 4202216882376664509L;
	private String name; // name is the combination of make and model
	private String make;
	private String model;
	private float basePrice;
	private OptionSet[] optionSetList;

	/***** base information start here *****/
	/*
	 * Automotive() constructor [1]void [2](make,model,base price,
	 * optionSetSize)
	 */
	public Automotive() {

	}

	public Automotive(String make, String model, float basePrice,
			int OptionSetSize) {
		this.make = make;
		this.model = model;
		this.name = make + " " + model;
		this.basePrice = basePrice;
		this.optionSetList = new OptionSet[OptionSetSize];
	}

	/*
	 * getName() get the make and model of this automotive
	 */
	public String getName() {
		name = make + " " + model;
		return name;
	}
	

	/*
	 * getMake() get the make of this automotive
	 */
	public String getMake() {
		return make;
	}

	/*
	 * setMake() set the make of this automotive
	 */
	public void setMake(String make) {
		this.make = make;
	}

	/*
	 * getModel() get the model of this automotive
	 */
	public String getModel() {
		return model;
	}

	/*
	 * setModel() set the model of this automotive
	 */
	public void setModel(String model) {
		this.model = model;
	}

	/*
	 * getBasePrice()() get the base price of this automotive
	 */
	public float getBasePrice() {
		return basePrice;
	}

	/*
	 * setBasePrice()() set the base price of this automotive
	 */
	public void setBasePrice(float basePrice) {
		this.basePrice = basePrice;
	}

	/*
	 * printBaseInfo() print all the base infomation of the car
	 */
	public void printBaseInfo() {
		System.out.println(this.getName());
		System.out.println("Base Price :"
				+ String.format("%.2f", this.getBasePrice()));
		System.out.println();
	}

	/***** base information end here *****/

	/***** Option Set Start Here *****/

	/*
	 * initialize optionSet()
	 */

	/*
	 * get option set
	 * 
	 * [1] get option set size [2] get option set by index [3] get option set by
	 * name
	 */
	public int getOptionSetListSize() {
		return optionSetList.length;
	}

	protected OptionSet getOptionSet(int index) {
		if (index < optionSetList.length && index >= 0) {
			if (optionSetList[index] != null) {
				return optionSetList[index];
			}
		}
		return null;

	}

	protected OptionSet getOptionSet(String setName) {
		for (int i = 0; i < optionSetList.length; i++) {
			if (optionSetList[i] != null) {
				if (optionSetList[i].getOptionSetName().equals(setName)) {
					return optionSetList[i];
				}
			}
		}
		return null;
	}

	/*
	 * set option set
	 * 
	 * [1] set option set by finding the first empty slot of array [2] set
	 * option set by index
	 */
	public void setOptionSet(String setName, int optionSize) {
		for (int i = 0; i < optionSetList.length; i++) {
			if (optionSetList[i] == null) {
				optionSetList[i] = new OptionSet(setName, optionSize);
				return;
			}
		}
	}

	public void setOptionSet(int index, String setName, int optionSize) {
		optionSetList[index] = new OptionSet(setName, optionSize);
	}

	/*
	 * delete option set
	 * 
	 * [1] delete option set by index [2] delete option set by name
	 */

	public void deleteOptionSet(int index) {
		if (index < optionSetList.length && index >= 0) {
			if (optionSetList[index] != null) {
				optionSetList[index] = null;
			}
		}
	}

	public void deleteOptionSet(String setName) {
		for (int i = 0; i < optionSetList.length; i++) {
			if (optionSetList[i] != null) {
				if (optionSetList[i].getOptionSetName().equals(setName)) {
					optionSetList[i] = null;
				}
			}
		}
	}

	/*
	 * updateOptionSet() [1] update option set, searching by name [2] update
	 * option set, searching by index [3] update option set name, searching by
	 * name [4] update option set name, searching by index
	 */

	public void updateOptionSet(String setName, int optionSize) {
		for (int i = 0; i < optionSetList.length; i++) {
			if (optionSetList[i] != null) {
				if (optionSetList[i].getOptionSetName().equals(setName)) {
					optionSetList[i] = new OptionSet(setName, optionSize);
					return;
				}
			}
		}
	}

	public void updateOptionSet(int index, String setName, int optionSize) {
		if (index < optionSetList.length && index >= 0) {
			optionSetList[index] = new OptionSet(setName, optionSize);
		}
	}

	public void updateOptionSetName(String setName, String newSetName) {
		for (int i = 0; i < optionSetList.length; i++) {
			if (optionSetList[i] != null) {
				if (optionSetList[i].getOptionSetName().equals(setName)) {
					optionSetList[i].setOptionSetName(newSetName);
					return;
				}
			}
		}
	}

	public void updateOptionSetName(int index, String newSetName) {
		if (index < optionSetList.length && index >= 0) {
			if (optionSetList[index] != null) {
				optionSetList[index].setOptionSetName(newSetName);
			}
		}
	}

	/*
	 * printAllOptionSet()
	 * 
	 * print all option set name and their options
	 */

	public void printAllOptionSet() {
		for (int i = 0; i < optionSetList.length; i++) {
			if (optionSetList[i] != null) {
				System.out.println(optionSetList[i].getOptionSetName() + ":");
				optionSetList[i].printAllOptions();
				System.out.println();
			}
			else{
				System.out.println("Option Set Not Exist or Deleted");
				System.out.println();
			}
			
		}
	}

	/***** Option Set End Here *****/

	/***** Option Start Here *****/

	/*
	 * get option [1] get option by option set name and option name [2] get
	 * option by option set name and option index [3] get option price by option
	 * set name and option name [4] get option price by option set name and
	 * option index [5] get option by option set index and option name [6] get
	 * option by option set index and option index [7] get option price by
	 * option set index and option name [8] get option price by option set index
	 * and option index
	 */

	protected Option getOption(String setName, String optionName) {
		for (int i = 0; i < optionSetList.length; i++) {
			if (optionSetList[i] != null) {
				if (optionSetList[i].getOptionSetName().equals(setName)) {
					return optionSetList[i].getOption(optionName);
				}
			}
		}
		return null;
	}

	protected Option getOption(String setName, int optionIndex) {
		for (int i = 0; i < optionSetList.length; i++) {
			if (optionSetList[i] != null) {
				if (optionSetList[i].getOptionSetName().equals(setName)) {
					return optionSetList[i].getOption(optionIndex);
				}
			}
		}
		return null;
	}
	protected Option getOption(int setIndex, String optionName) {
		if (setIndex < optionSetList.length && setIndex >= 0) {
			if (optionSetList[setIndex] != null) {
				return optionSetList[setIndex].getOption(optionName);
			}
		}
		return null;
	}

	protected Option getOption(int setIndex, int optionIndex) {
		if (setIndex < optionSetList.length && setIndex >= 0) {
			if (optionSetList[setIndex] != null) {
				return optionSetList[setIndex].getOption(optionIndex);
			}
		}
		return null;
	}

	public float getOptionPrice(String setName, String optionName) {
		for (int i = 0; i < optionSetList.length; i++) {
			if (optionSetList[i] != null) {
				if (optionSetList[i].getOptionSetName().equals(setName)) {
					return optionSetList[i].getOption(optionName).getPrice();
				}
			}
		}
		return 0;
	}

	public float getOptionPrice(String setName, int optionIndex) {
		for (int i = 0; i < optionSetList.length; i++) {
			if (optionSetList[i] != null) {
				if (optionSetList[i].getOptionSetName().equals(setName)) {
					return optionSetList[i].getOption(optionIndex).getPrice();
				}
			}
		}
		return 0;
	}

	

	public float getOptionPrice(int setIndex, String optionName) {
		if (setIndex < optionSetList.length && setIndex >= 0) {
			if (optionSetList[setIndex] != null) {
				return optionSetList[setIndex].getOption(optionName).getPrice();
			}
		}
		return 0;
	}

	public float getOptionPrice(int setIndex, int optionIndex) {
		if (setIndex < optionSetList.length && setIndex >= 0) {
			if (optionSetList[setIndex] != null) {
				return optionSetList[setIndex].getOption(optionIndex)
						.getPrice();
			}
		}
		return 0;
	}

	/*
	 * set option [1] set option by option set name [2] set option by option set
	 * index and option index
	 */

	public void setOption(String setName, String optionName, float price) {
		if (getOptionSet(setName) != null) {
			getOptionSet(setName).setOption(optionName, price);
		}

	}

	public void setOption(int setIndex, int optionIndex, String optionName,
			float price) {
		if (getOptionSet(setIndex) != null) {
			optionSetList[setIndex].setOption(optionIndex, optionName, price);
		}
	}

	/*
	 * delete option [1] delete option by option set name and option name [2]
	 * delete option by option set index and option name [3] delete option by
	 * option set name and option index [4] delete option by option set index
	 * and option index
	 */

	public void deleteOption(String setName, String optionName) {
		for (int i = 0; i < optionSetList.length; i++) {
			if (optionSetList[i] != null) {
				if (optionSetList[i].getOptionSetName().equals(setName)) {
					optionSetList[i].deleteOption(optionName);
				}
			}
		}
	}

	public void deleteOption(int setIndex, String optionName) {
		if (setIndex < optionSetList.length && setIndex >= 0) {
			if (optionSetList[setIndex] != null) {
				optionSetList[setIndex].deleteOption(optionName);
			}
		}
	}

	public void deleteOption(String setName, int optionIndex) {
		for (int i = 0; i < optionSetList.length; i++) {
			if (optionSetList[i] != null) {
				if (optionSetList[i].getOptionSetName().equals(setName)) {
					optionSetList[i].deleteOption(optionIndex);
				}
			}
		}
	}

	public void deleteOption(int setIndex, int optionIndex) {
		if (setIndex < optionSetList.length && setIndex >= 0) {
			if (optionSetList[setIndex] != null) {
				optionSetList[setIndex].deleteOption(optionIndex);
			}
		}
	}

	/*
	 * update option [1] update option name by option set name and option name
	 * [2] update option name by option set name and option index [3] update
	 * option name by option set index and option name [4] update option name by
	 * option set index and option index [5] update option price by option set
	 * name and option name [6] update option price by option set name and
	 * option index [7] update option price by option set index and option name
	 * [8] update option price by option set index and option index
	 */

	public void updateOptionName(String setName, String optionName,
			String newOptionName) {
		if (getOptionSet(setName) != null) {
			getOptionSet(setName).updateOptionName(optionName, newOptionName);
		}
	}

	public void updateOptionName(String setName, int optionIndex,
			String newOptionName) {
		if (getOptionSet(setName) != null) {
			getOptionSet(setName).updateOptionName(optionIndex, newOptionName);
		}
	}

	public void updateOptionName(int setIndex, String optionName,
			String newOptionName) {
		if (getOptionSet(setIndex) != null) {
			getOptionSet(setIndex).updateOptionName(optionName, newOptionName);
		}
	}

	public void updateOptionName(int setIndex, int optionIndex,
			String newOptionName) {
		if (getOptionSet(setIndex) != null) {
			getOptionSet(setIndex).updateOptionName(optionIndex, newOptionName);
		}
	}

	public void updateOptionPrice(String setName, String optionName, float price) {
		if (getOptionSet(setName) != null) {
			getOptionSet(setName).updateOptionPrice(optionName, price);
		}
	}

	public void updateOptionPrice(String setName, int optionIndex, float price) {
		if (getOptionSet(setName) != null) {
			getOptionSet(setName).updateOptionPrice(optionIndex, price);
		}
	}

	public void updateOptionPrice(int setIndex, String optionName, float price) {
		if (getOptionSet(setIndex) != null) {
			getOptionSet(setIndex).updateOptionPrice(optionName, price);
		}
	}

	public void updateOptionPrice(int setIndex, int optionIndex, float price) {
		if (getOptionSet(setIndex) != null) {
			getOptionSet(setIndex).updateOptionPrice(optionIndex, price);
		}
	}

	/***** Option End Here *****/

	/*
	 * print() print all the option set and option
	 */

	public void print() {
		this.printBaseInfo();
		this.printAllOptionSet();
	}

}
