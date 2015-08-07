package adapter;

import scale.EditOption;
import scale.EditOptionEnum;
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
	private static int threadID = 0; // only for the use of thread method : edit

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
		try {
			autoList.printAutomobile(modelName);
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
		try {
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

	public void edit(EditOptionEnum editOptionEnum, String[] args) {
		Automobile auto = null;
		try {
			auto = autoList.getAutomobile(args[0]);// args 0 is model name
			EditOption edit = new EditOption(++threadID, auto, editOptionEnum,
					args);
			edit.start();
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
		Automobile auto = null;
		try {
			auto = autoList.getAutomobile(modelName);
		} catch (AutoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
