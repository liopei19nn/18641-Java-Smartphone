package model;

import java.io.Serializable;
import java.util.ArrayList;

import exception.AutoException;
import exception.CustomExceptionEnum;
import model.OptionSet.Option;

/**
 * @author Li Pei
 * @andrew_id lip
 */

public class Automobile implements Serializable {

	private static final long serialVersionUID = 4202216882376664509L;
	private String name; // name is the combination of make and model
	private String make;
	private String model;
	private float basePrice;
	private ArrayList<OptionSet> optionSets;
	private ArrayList<Option> choice;

	/***** base information start here *****/
	/*
	 * Automotive()
	 * 
	 * constructor (make,model,base price, optionSetSize)
	 */

	public Automobile(String make, String model, float basePrice) {
		this.make = make;
		this.model = model;
		this.name = make + " " + model;
		this.basePrice = basePrice;
		this.optionSets = new ArrayList<OptionSet>();
		this.choice = new ArrayList<Option>();
	}

	/*
	 * getName()
	 * 
	 * get the make and model of this automotive
	 */
	public String getName() {
		name = make + " " + model;
		return name;
	}

	/*
	 * getMake()
	 * 
	 * get the make of this automotive
	 */
	public String getMake() {
		return make;
	}

	/*
	 * setMake()
	 * 
	 * set the make of this automotive
	 */
	public void setMake(String make) {
		this.make = make;
	}

	/*
	 * getModel()
	 * 
	 * get the model of this automotive
	 */
	public String getModel() {
		return model;
	}

	/*
	 * setModel()
	 * 
	 * set the model of this automotive
	 */
	public void setModel(String model) {
		this.model = model;
	}

	/*
	 * getBasePrice()
	 * 
	 * get the base price of this automotive
	 */
	public float getBasePrice() {
		return basePrice;
	}

	/*
	 * setBasePrice()
	 * 
	 * set the base price of this automotive
	 */
	public void setBasePrice(float basePrice) {
		this.basePrice = basePrice;
	}

	/*
	 * printBaseInfo()
	 * 
	 * print all the base information of the car
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
	 * get option set
	 * 
	 * [1] get option set size
	 * 
	 * [2] get option set by name
	 */
	public int getOptionSetListSize() {
		return optionSets.size();
	}

	protected OptionSet getOptionSet(String setName) {
		for (OptionSet opset : optionSets) {
			if (opset.getOptionSetName().equals(setName)) {
				return opset;
			}
		}
		return null;
	}
	
	/*
	 * getOptionSetName(String)
	 * 
	 * get optionset name with optionsetname
	 * this is a method for test thread method edit optionset name
	 * */
	
	public String getOptionSetName(String optionSetName) throws AutoException {
		if (getOptionSet(optionSetName) == null) {
			throw new AutoException(CustomExceptionEnum.OptionSetNotFound);
		}
		else{
			return getOptionSet(optionSetName).getOptionSetName();
		}
		
	}
	
	

	/*
	 * set option set
	 * 
	 * set option set by name
	 */
	public void setOptionSet(String setName) {
		optionSets.add(new OptionSet(setName));
	}

	/*
	 * delete option set
	 * 
	 * delete option set by name
	 */

	public void deleteOptionSet(String setName) {

		for (OptionSet opset : optionSets) {
			if (opset.getOptionSetName().equals(setName)) {
				optionSets.remove(opset);
				return;
			}
		}
	}

	/*
	 * updateOptionSet()
	 * 
	 * update option set name, searching by name
	 */

	public void updateOptionSetName(String setName, String newSetName)
			throws AutoException {
		boolean updateFlag = false;
		for (OptionSet opset : optionSets) {
			if (opset.getOptionSetName().equals(setName)) {
				opset.setOptionSetName(newSetName);
				return;

			}
		}
		if (!updateFlag) {
			throw new AutoException(CustomExceptionEnum.OptionSetNotFound);
		}

	}
	
	

	/*
	 * printAllOptionSet()
	 * 
	 * print all option set name and their options
	 */
	
	public void printAllOptionSet() {
		for (OptionSet opset : optionSets) {
			System.out.println(opset.getOptionSetName() + ":");
			opset.printAllOptions();
			System.out.println();
		}
	}
	
	

	/***** Option Set End Here *****/

	/***** Option Start Here *****/

	/*
	 * get option
	 * 
	 * get option by option set name and option name
	 */

	protected Option getOption(String setName, String optionName) {
		if (getOptionSet(setName) != null) {
			return getOptionSet(setName).getOption(optionName);
		}
		return null;
	}

	public float getOptionPrice(String setName, String optionName) {
		if (getOptionSet(setName) != null) {
			return getOption(setName, optionName).getPrice();
		}
		return 0;
	}

	/*
	 * set option
	 * 
	 * set option by option set name
	 */

	public void setOption(String setName, String optionName, float price) {
		if (getOptionSet(setName) != null) {
			getOptionSet(setName).setOption(optionName, price);
		}

	}

	/*
	 * delete option
	 * 
	 * delete option by option set name and option name
	 */

	public void deleteOption(String setName, String optionName) {
		if (getOptionSet(setName) != null) {
			getOptionSet(setName).deleteOption(optionName);
		}
	}

	/*
	 * update option
	 * 
	 * update option price by option set name and option name
	 */

	public void updateOptionPrice(String setName, String optionName, float price)
			throws AutoException {
		if (getOptionSet(setName) != null) {
			getOptionSet(setName).updateOptionPrice(optionName, price);
		} else {
			throw new AutoException(CustomExceptionEnum.OptionSetNotFound);
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

	/***** Choice Starts Here *****/
	/*
	 * setOptionChoice()
	 * 
	 * set option choice and add it to choice list
	 */
	public void setOptionChoice(String setName, String optionName) {
		OptionSet opset = getOptionSet(setName);
		choice.add(opset.getOption(optionName));
		opset.setChoice(optionName);
	}

	/*
	 * getOptionChoice()
	 * 
	 * get option choice
	 */
	public String getOptionChoice(String setName) {
		return getOptionSet(setName).getChoiceName();
	}

	/*
	 * setOptionChoicePrice()
	 * 
	 * get option choice price
	 */
	public float getOptionChoicePrice(String setName) {
		return getOptionSet(setName).getChoicePrice();
	}

	/*
	 * getTotalPrice()
	 * 
	 * get total price for all choice selected
	 */
	public float getTotalPrice() {
		float sum = basePrice;
		for (Option op : choice) {
			sum += op.getPrice();
		}
		return sum;
	}
	
	/*
	 * printChoice()
	 * 
	 * print all choice selected
	 */
	public void printChoice() {
		for (Option op : choice) {
			System.out.println("Option : " + op.getOptionName() + "Price : "
					+ op.getPrice());
		}
	}
	
	/*
	 * printTotalPrice()
	 * 
	 * print price for all choice selected
	 */
	public void printTotalPrice() {
		System.out.println("Total price : " + getTotalPrice());
	}
	/***** Choice End Here *****/

}
