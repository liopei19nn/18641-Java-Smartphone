package adapter;

import util.FileIO;
import exception.AutoException;
import exception.CustomExceptionEnum;
import exception.FixHelper;
import model.*;

/**
 * @author Li Pei
 * @andrew_id lip
 */
public abstract class ProxyAutomobile {
	// This class will contain all the implementation of any method declared in
	// the interface.

	// a list of Automobile
	private static Fleet autoList = new Fleet();

	/*
	 * buildAuto()
	 * 
	 * build an automobile and set it into Fleet
	 * 
	 * exception : FileNotFound
	 */

	public void buildAuto(String filename) {
		try {
			autoList.setAutomobile(filename);
		} catch (AutoException ae) {
			fix(ae.getErrorNumber());
		}

	}

	/*
	 * printAuto()
	 * 
	 * print the according model
	 * 
	 * exception : CarModelNotFound
	 */

	public void printAuto(String modelName) {
		Automobile auto = autoList.getAutomobile(modelName);
		try {
			if (auto == null) {
				throw new AutoException(CustomExceptionEnum.CarModelNotFound);
			}
			auto.print();
		} catch (AutoException ae) {
			System.out.println("Error -- " + ae.toString());
		}

	}

	/*
	 * deleteAuto()
	 * 
	 * delete the according model
	 * 
	 * exception : CarModelNotFound
	 */
	public void deleteAuto(String modelName) {

		Automobile auto = autoList.getAutomobile(modelName);
		try {
			if (auto == null) {
				throw new AutoException(CustomExceptionEnum.CarModelNotFound);
			}
			autoList.deleteAutomoble(modelName);
		} catch (AutoException ae) {
			System.out.println("Error -- " + ae.toString());
		}

	}

	/*
	 * updateOptionSetName()
	 * 
	 * update option set name
	 * 
	 * exception : CarModelNotFound, OptionSetNotFound
	 */

	public void updateOptionSetName(String ModelName, String OptionSetName,
			String newName) {
		try {
			autoList.updateOptionSetName(ModelName, OptionSetName, newName);
		} catch (AutoException ae) {
			System.out.println("Error -- " + ae.toString());
		}

	}

	/*
	 * updateOptionPrice()
	 * 
	 * update option set name
	 * 
	 * exception : CarModelNotFound, OptionSetNotFound， OptionNotFound
	 */

	public void updateOptionPrice(String ModelName, String Optionname,
			String Option, float newprice) {
		try {
			autoList.updateOptionPrice(ModelName, Optionname, Option, newprice);
		} catch (AutoException ae) {
			System.out.println("Error -- " + ae.toString());
		}
	}

	/*
	 * saveCarModel()
	 * 
	 * save a car model, the output should be "model name".ser
	 * 
	 * exception : CarModelNotFound
	 */

	public void saveCarModel(String modelName) {
		Automobile auto = autoList.getAutomobile(modelName);
		try {
			if (auto == null) {
				throw new AutoException(
						CustomExceptionEnum.SavedCarFileNotFound);
			}
			new FileIO().serializeOutput(auto);
		} catch (AutoException ae) {
			System.out.println("Error -- " + ae.toString());
		}
	}

	/*
	 * loadCarModel()
	 * 
	 * load a car model, the input file should be "model name".ser
	 * 
	 * if no match file, load the Default_NISSAN_GTR.ser
	 * 
	 * exception : SavedCarFileNotFound
	 */

	public void loadCarModel(String modelName) {
		StringBuffer sb = new StringBuffer();
		sb.append(modelName);
		sb.append(".ser");

		try {
			Automobile auto = new FileIO().serializeInput(sb.toString());
			autoList.setAutomobile(auto);
		} catch (AutoException ae) {
			fix(ae.getErrorNumber());
		}

	}

	/*
	 * fix()
	 * 
	 * fix problem by input error number
	 */

	public void fix(int errno) {

		FixHelper fixHelper = new FixHelper();

		switch (errno) {
		case 1:
			fixHelper.FixFileNotFound(autoList);
			break;
		case 5:
			fixHelper.SavedCarFileNotFound(autoList);
			break;
		default:
			break;
		}
	}

}
