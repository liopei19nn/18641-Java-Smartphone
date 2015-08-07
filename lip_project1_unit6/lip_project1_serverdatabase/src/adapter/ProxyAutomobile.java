package adapter;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.*;

import scale.*;
import util.DatabaseIO;
import util.FileIO;
import exception.*;
import model.*;

/**
 * @author Li Pei
 * @andrew_id lip
 */
public abstract class ProxyAutomobile {
	// This class will contain all the implementation of any method declared in
	// the interface.

	// a list of Automobile

	private static int threadID = 0; // only for the use of thread method : edit

	private static LinkedHashMap<String, Automobile> autoList = new LinkedHashMap<String, Automobile>();
	
	// Three integer for insert distinct ID in database table
	private static int AutoID = 0; // record the current ID of car;
	private static int OptionSetID = 0; // record the current option set ID;
	private static int OptionID = 0; // record the current option ID;

	/*
	 * buildAuto()
	 * 
	 * build an automobile and set it into Fleet
	 * 
	 * exception : FileNotFound
	 */

	public void buildAuto(String fileName) {
		try {
			Automobile auto = new FileIO().readInAutomobile(fileName);

			autoList.put(auto.getName(), auto);
		} catch (AutoException ae) {
			fix(ae.getErrorNumber());
		}

	}
	
	/*
	 * JDBC_buildAuto()
	 * 
	 * build an automobile and set it into LHM and database
	 * 
	 * 
	 */

	public void JDBC_buildAuto(String filename) {
		Automobile auto = null;
		// read in an automobile from file
		try {
			if (filename.matches("[a-zA-Z0-9/]+.Properties")) {

				auto = new FileIO().readInAutoPropertyFile(filename);
			} else {
				auto = new FileIO().readInAutomobile(filename);
			}

		} catch (AutoException ae) {
			fix(ae.getErrorNumber());
		}
		
		// if already in the LHM, then return 
		if (autoList.get(auto.getName()) != null) {
			return;
		} else {
			autoList.put(auto.getName(), auto);
			
			// refresh the current ID for new input to make all ID in table distinct
			int[] idrecorder = new DatabaseIO().addToDatabase(auto, AutoID, OptionSetID, OptionID);
			AutoID++;
			OptionSetID = idrecorder[0];
			OptionID = idrecorder[1];
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
		Automobile auto = autoList.get(modelName);
		try {
			if (auto == null) {
				throw new AutoException(CustomExceptionEnum.CarModelNotFound);
			} else {
				auto.print();
			}
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
		Automobile auto = autoList.get(modelName);
		try {
			if (auto == null) {
				throw new AutoException(CustomExceptionEnum.CarModelNotFound);
			} else {
				autoList.remove(modelName);
			}
		} catch (AutoException ae) {
			System.out.println("Error -- " + ae.toString());
		}
	}

	/*
	 * JDBC_deleteAuto()
	 * 
	 * delete the according model in database and LHM
	 * 
	 */
	public void JDBC_deleteAuto(String AutoName) {
		if (autoList.get(AutoName) != null) {
			autoList.remove(AutoName);
			new DatabaseIO().deleteAutoInDatabase(AutoName);
			// if it also exist in LHM, delete it
		} else{
			System.out.println("Error -- " + AutoName + "Model Not Found in database!");
		}
	}

	/*
	 * updateOptionSetName()
	 * 
	 * update option set name
	 * 
	 * exception : CarModelNotFound, OptionSetNotFound
	 */

	public void updateOptionSetName(String ModelName, String OptionSetName, String newName) {
		Automobile auto = autoList.get(ModelName);
		try {

			if (auto != null) {
				auto.updateOptionSetName(OptionSetName, newName);
				autoList.put(ModelName, auto);
			} else {
				throw new AutoException(CustomExceptionEnum.CarModelNotFound);
			}

		} catch (AutoException ae) {
			System.out.println("Error -- " + ae.toString());
		}

	}

	public void edit(EditOptionEnum editOptionEnum, String[] args) {
		Automobile auto = autoList.get(args[0]); // args 0 is model name
		try {
			if (auto == null) {
				throw new AutoException(CustomExceptionEnum.CarModelNotFound);
			} else {
				EditOption edit = new EditOption(++threadID, auto, editOptionEnum, args);
				edit.start();
			}
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

	public void updateOptionPrice(String ModelName, String Optionname, String Option, float newprice) {
		Automobile auto = autoList.get(ModelName);
		try {
			if (auto != null) {
				auto.updateOptionPrice(Optionname, Option, newprice);
				autoList.put(ModelName, auto);
			} else {
				throw new AutoException(CustomExceptionEnum.CarModelNotFound);
			}
		} catch (AutoException ae) {
			System.out.println("Error -- " + ae.toString());
		}

	}

	/*
	 * JDBC_updateAutoBasePrice()
	 * 
	 * update the auto base price of automobile in database
	 * 
	 */
	public void JDBC_updateAutoBasePrice(String autoName, float newprice) {
		Automobile auto = autoList.get(autoName);
		if (auto != null) {
			auto.setBasePrice(newprice);
			new DatabaseIO().updateAutoBasePrice(autoName, newprice);
		}else{
			System.out.println("Error -- " + autoName + " not exist!");
		}
	}

	/*
	 * JDBC_updateAutoBasePrice()
	 * 
	 * update the auto's one option price in database
	 * 
	 */
	public void JDBC_updateAutoOptionPrice(String AutoName, String OptionSetName, String OptionName,
			float newOptionPrice) {
		Automobile auto = autoList.get(AutoName);
		if (auto != null) {
			new DatabaseIO().updateAutoOptionPrice(AutoName, OptionSetName, OptionName, newOptionPrice);
		}else{
			System.out.println("Error -- " + AutoName + " not eixst!");
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
		Automobile auto = autoList.get(modelName);
		try {
			if (auto == null) {
				throw new AutoException(CustomExceptionEnum.CarModelNotFound);
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
			autoList.put(auto.getName(), auto);
		} catch (AutoException ae) {
			fix(ae.getErrorNumber());
		}
	}

	/*
	 * loadCarModel()
	 * 
	 * build a car with input Properties object
	 * 
	 */
	public void buildAutoWithProperty(Properties carProperties) {
		Automobile auto = new FileIO().readInAutomobileProperty(carProperties);
		autoList.put(auto.getName(), auto);
	}

	/*
	 * buildAutoWithTxt(String fileName)
	 * 
	 * build a automobile with input .txt file
	 * 
	 */
	public void buildAutoWithTxt(String fileName) {
		try {
			Automobile auto = new FileIO().readInAutomobile(fileName);
			autoList.put(auto.getName(), auto);
		} catch (AutoException e) {
			e.printStackTrace();
		}
	}
	/*
	 * getAllModelList()
	 * 
	 * return all the available automobile in LinkedHashMap
	 * 
	 */

	public ArrayList<String> getAllModelList() {
		ArrayList<String> autoNameList = new ArrayList<String>();
		for (String key : autoList.keySet()) {
			autoNameList.add(key);
		}
		return autoNameList;
	}
	/*
	 * sendSelectedModel()
	 * 
	 * send the selected automobile model from server to client
	 * 
	 */

	public void sendSelectedModel(ObjectOutputStream objectOutputStream, String modelName) throws IOException {
		Automobile selectedAuto = autoList.get(modelName);
		objectOutputStream.writeObject(selectedAuto);
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
