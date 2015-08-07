/**
 * 
 */
package model;

import java.io.Serializable;

/**
 * @author Li Pei
 * @andrew_id lip
 */
class OptionSet implements Serializable {
	
	private static final long serialVersionUID = -3902872759509912055L;
	private Option options[];
	private String name;

	/**
	 * Optionset()
	 * Option set constructor for 
	 * [1] (option set name, option number)
	 * [2] (void)
	 *  
	 */
	protected OptionSet(String name, int optionSize) {
		this.name = name;
		options = new Option[optionSize];
	}
	protected OptionSet() {

	}
	
	/*
	 * getOptionSetName()
	 * 
	 * return the name of this option set
	 * 
	 * 
	 * */

	protected String getOptionSetName() {
		return name;
	}
	/*
	 * setOptionSetName()
	 * 
	 * set the name of this optionset
	 * 
	 * 
	 * */
	protected void setOptionSetName(String name) {
		this.name = name;
	}
	
	
	
	/*
	 * get Option
	 * [1] get option list size
	 * [2] get option by name
	 * [3] get option by index
	 * 
	 */

	protected int getOptionListSize() {
		return options.length;
	}

	protected Option getOption(String optionName) {
		for (int i = 0; i < options.length; i++) {
			if (options[i] != null) {
				if (options[i].getOptionName().equals(optionName)) {
					return options[i];
				}
			}
		}
		return null;
	}

	protected Option getOption(int index) {
		if (index < options.length && index >= 0) {
			if (options[index] != null) {
				return options[index];
			}
		}
		return null;
	}
	
	

	/*
	 * set option
	 * [1] set option by finding the first empty slot of array
	 * [2] set option by index
	 * 
	 */

	protected void setOption(String optionName, float price) {
		for (int i = 0; i < options.length; i++) {
			if (options[i] == null) {
				options[i] = new Option(optionName, price);
				return;
			}
		}
	}

	protected void setOption(int index, String optionName, float price) {
		if (index < options.length && index >= 0) {
				options[index] = new Option(optionName, price);
		}
	}

	/*
	 * delete option
	 * [1] delete option by name
	 * [2] delete option by index
	 * 
	 */

	protected void deleteOption(String optionName) {
		for (int i = 0; i < options.length; i++) {
			if (options[i] != null) {
				if (options[i].getOptionName().equals(optionName)) {
					options[i] = null;
				}
			}
		}
	}
	protected void deleteOption(int index) {
		if (index < options.length && index >= 0) {
			if (options[index] != null) {
				options[index] = null;
			}
		}
	}

	/*
	 * updateOption()
	 * [1] update option price by option name
	 * [2] update option price by option index
	 * [3] update option name by option name
	 * [4] update option name by option index
	 *  
	 */
	
	protected void updateOptionPrice(String optionName,float price) {
		if (getOption(optionName) == null) {
			return;
		} else {
			getOption(optionName).setPrice(price);
		}
	}

	protected void updateOptionPrice(int index,float price) {
		if (getOption(index) == null) {
			return;
		} else {
			getOption(index).setPrice(price);
		}
	}
	protected void updateOptionName(String optionName,String newOptionName) {
		if (getOption(optionName) == null) {
			return;
		} else {
			getOption(optionName).setOptionName(newOptionName);
		}
	}

	protected void updateOptionName(int index,String newOptionName) {
		if (getOption(index) == null) {
			return;
		} else {
			getOption(index).setOptionName(newOptionName);
		}
	}
	
	/*
	 * printAllOptions()
	 * to print all the option in order
	 * 
	 * */
	protected void printAllOptions(){
		for (int i = 0; i < options.length; i++) {
			if (options[i] != null) {
				System.out.println(options[i].getOptionName()+":Price "+String.format("%.2f", options[i].getPrice()));
			}
			else{
				System.out.println("Option Not Exist or Deleted");
			}
			
		}
	}
	
	/*
	 * inner class Option
	 * option with option name and price
	 * 
	 * */
	protected class Option implements Serializable {
		private static final long serialVersionUID = -2298308737543534460L;
		private String name;
		private float price;
		/*
		 * Option()
		 * [1] empty constructor
		 * [2] Constructor with name and price
		 * 
		 * */
		protected Option() {

		}

		protected Option(String name, float price) {
			this.name = name;
			this.price = price;
		}
		/*
		 * setOptionName()
		 * set the option name
		 * 
		 * */
		protected void setOptionName(String option) {
			this.name = option;
		}
		/*
		 * getOptionName()
		 * get the option name
		 * 
		 * */
		protected String getOptionName() {
			return name;
		}
		/*
		 * getOptionPrice()
		 * set the option name
		 * 
		 * */
		protected void setPrice(float price) {
			this.price = price;
		}
		/*
		 * getOptionPrice()
		 * get the option name
		 * 
		 * */
		protected float getPrice() {
			return price;
		}

	}

}
