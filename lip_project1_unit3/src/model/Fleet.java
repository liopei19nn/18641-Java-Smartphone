package model;

import java.util.LinkedHashMap;

import exception.AutoException;
import exception.CustomExceptionEnum;
import util.FileIO;

/**
 * @author Li Pei
 * @andrew_id lip
 */
public class Fleet {

	// a list of automobile
	private static LinkedHashMap<String, Automobile> autoList;

	/*
	 * Fleet()
	 * 
	 * constructor
	 */
	public Fleet() {
		autoList = new LinkedHashMap<String, Automobile>();
	}
	
	
	/*
	 * setAutomobile()
	 * 
	 * add a automobile in list by object or file
	 * */
	public void setAutomobile(Automobile auto) {
		autoList.put(auto.getName(), auto);
	}

	public void setAutomobile(String fileName) throws AutoException {
		Automobile auto = new FileIO().readInAutomotive(fileName);

		autoList.put(auto.getName(), auto);
	}
	
	/*
	 * getAutomobile()
	 * 
	 * get a automobile in list
	 * */
	public Automobile getAutomobile(String modelName) throws AutoException {
		Automobile auto = autoList.get(modelName);
		if (auto == null) {
			throw new AutoException(CustomExceptionEnum.CarModelNotFound);
		}else{
			return autoList.get(modelName);
		}
	}
	
	/*
	 * updateOptionSetName()
	 * 
	 * update option set name 
	 * 
	 * exception : CarModelNotFound, OptionSetNotFound
	 * */
	public void updateOptionSetName(String ModelName, String OptionSetName,
			String newName) throws AutoException {
		Automobile auto = autoList.get(ModelName);
		if (auto != null) {
			auto.updateOptionSetName(OptionSetName, newName);
			autoList.put(ModelName, auto);
		} else {
			throw new AutoException(CustomExceptionEnum.CarModelNotFound);
		}
	}
	
	/*
	 * updateOptionPrice()
	 * 
	 * update option price 
	 * 
	 * exception : CarModelNotFound, OptionSetNotFound, OptionNotFound
	 * */
	public void updateOptionPrice(String ModelName, String Optionname,
			String Option, float newprice) throws AutoException {
		Automobile auto = autoList.get(ModelName);
		if (auto != null) {
			auto.updateOptionPrice(Optionname, Option, newprice);
			autoList.put(ModelName, auto);
		} else {
			throw new AutoException(CustomExceptionEnum.CarModelNotFound);
		}
	}
	
	/*
	 * deleteAutomobile()
	 * 
	 * delete an automobile in the list 
	 * */
	public void deleteAutomoble(String modelName) throws AutoException{
		Automobile auto = autoList.get(modelName);
		if (auto == null) {
			throw new AutoException(CustomExceptionEnum.CarModelNotFound);
		}else{
			autoList.remove(modelName);
		}
	}
	
	
    public void printAutomobile(String modelName) throws AutoException{
    		Automobile auto = autoList.get(modelName);
    		if (auto == null) {
    			throw new AutoException(CustomExceptionEnum.CarModelNotFound);
		}else{
			auto.print();
		}
    }

}
