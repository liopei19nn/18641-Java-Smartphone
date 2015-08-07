package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import exception.AutoException;
import exception.CustomExceptionEnum;

/**
 * @author Li Pei
 * @andrew_id lip
 */
class OptionSet implements Serializable {

	private static final long serialVersionUID = -3902872759509912055L;
	private ArrayList<Option> options;
	private String name;
	private Option choiceOption;

	/**
	 * Optionset()
	 * 
	 * Option set constructor
	 * 
	 */
	protected OptionSet(String name) {
		this.name = name;
		options = new ArrayList<Option>();
	}

	/*
	 * getOptionSetName()
	 * 
	 * return the name of this option set
	 */

	protected String getOptionSetName() {
		return name;
	}

	/*
	 * setOptionSetName()
	 * 
	 * set the name of this option set
	 */
	protected void setOptionSetName(String name) {
		this.name = name;
	}

	/*
	 * get Option
	 * 
	 * [1] get option list size
	 * 
	 * [2] get option by name
	 * 
	 * [3] get option and option price in linkedhashmap for servlet configuration
	 */

	protected int getOptionListSize() {
		return options.size();
	}
	

	protected Option getOption(String optionName) {
		for (Option op : options) {
			if (op.getOptionName().equals(optionName)) {
				return op;
			}
		}
		return null;
	}
	
	protected LinkedHashMap<String, Float> getAllOptionLHM() {
		LinkedHashMap<String, Float> optionsetmap = new LinkedHashMap<String,Float>();
		for(Option op : options){
			optionsetmap.put(op.getOptionName(), op.getPrice());
		}
		return optionsetmap;
	}
	
	//used for car configuration
	protected Option getOption(int index){
		return options.get(index);
	}

	/*
	 * set option
	 * 
	 * set option by option name
	 */

	protected void setOption(String optionName, float price) {
		options.add(new Option(optionName, price));
	}

	/*
	 * delete option
	 * 
	 * delete option by name
	 */

	protected void deleteOption(String optionName) {
		for (Option op : options) {
			if (op.getOptionName().equals(optionName)) {
				options.remove(op);
				return;
			}
		}
	}

	/*
	 * updateOption()
	 * 
	 * update option price by option name
	 */

	protected void updateOptionPrice(String optionName, float price)
			throws AutoException {
		if (getOption(optionName) == null) {
			throw new AutoException(CustomExceptionEnum.OptionNotFound);
		} else {
			getOption(optionName).setPrice(price);
		}
	}

	/*
	 * printAllOptions() to print all the option in order
	 */
	protected void printAllOptions() {
		Option op = null;
		for (int i = 0; i < options.size(); i++) {
			op = options.get(i);
			System.out.println(i + ". " + op.getOptionName() + ":Price "
					+ String.format("%.2f", op.getPrice()));
		}
		
		

	}

	/*
	 * setChoice()
	 * 
	 * set the choice for this option set
	 */
	protected void setChoice(String optionName) {
		choiceOption = getOption(optionName);
	}
	
	// used for car configuration
	protected void setChoice(int index) {
		choiceOption = getOption(index);
	}
	

	/*
	 * getChoiceName()
	 * 
	 * get the choice name for this option set
	 */
	protected String getChoiceName() {
		return choiceOption.getOptionName();
	}

	/*
	 * setChoicePrice()
	 * 
	 * get the choice price for this option set
	 */
	protected float getChoicePrice() {
		return choiceOption.getPrice();
	}

	/*
	 * inner class Option option with option name and price
	 */
	protected class Option implements Serializable {
		private static final long serialVersionUID = -911426289875231387L;

		private String name;
		private float price;

		/*
		 * Option()
		 * 
		 * Constructor with name and price
		 */

		protected Option(String name, float price) {
			this.name = name;
			this.price = price;
		}

		/*
		 * setOptionName() set the option name
		 */
		protected void setOptionName(String option) {
			this.name = option;
		}

		/*
		 * getOptionName() get the option name
		 */
		protected String getOptionName() {
			return name;
		}

		/*
		 * getOptionPrice() set the option name
		 */
		protected void setPrice(float price) {
			this.price = price;
		}

		/*
		 * getOptionPrice() get the option name
		 */
		protected float getPrice() {
			return price;
		}

	}

}
