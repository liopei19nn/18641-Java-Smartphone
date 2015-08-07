package util;

import java.io.*;
import java.util.ArrayList;
import java.util.Properties;

import exception.AutoException;
import exception.CustomExceptionEnum;
import exception.CustomIOException;
import exception.FixHelper;
import model.Automobile;

/**
 * @author Li Pei
 * @andrew_id lip
 */

public class FileIO {
	// read in Automobile from Properties object
	public Automobile readInAutomobileProperty(Properties props){
		Automobile auto = null;
		auto = new Automobile(props.getProperty("CarMake"),
				props.getProperty("CarModel"), Float.parseFloat(props
						.getProperty("BasePrice")));

		String option = "Option";
		String optionValue = "OptionValue";
		String optionPrice = "OptionPrice";

		for (char optionNum = '1'; props.getProperty(option + optionNum) != null; optionNum++) {
			auto.setOptionSet(props.getProperty(option + optionNum));

			for (char optionValueNum = 'a'; props.getProperty(optionValue
					+ optionNum + optionValueNum) != null; optionValueNum++) {
				auto.setOption(
						props.getProperty(option + optionNum),
						props.getProperty(optionValue + optionNum
								+ optionValueNum),
						Float.parseFloat(props.getProperty(optionPrice
								+ optionNum + optionValueNum)));
			}

		}

		return auto;

	}
	
	// read in a automobile from .Properties file
	public Automobile readInAutomobile(String filename) throws AutoException{
		Properties props = new Properties();
		Automobile auto = null;
		try {
			FileInputStream in = new FileInputStream(filename);
			props.load(in);
			

			
			auto = new Automobile(props.getProperty("CarMake"), 
					props.getProperty("CarModel"), 
					Float.parseFloat(props.getProperty("BasePrice")));
			
			String option = "Option";
			String optionValue = "OptionValue";
			String optionPrice = "OptionPrice";
		
			
			
			
			
			for (char optionNum = '1'; props.getProperty(option + optionNum) != null; optionNum++) {
				auto.setOptionSet(props.getProperty(option + optionNum));
				 
				
				for (char optionValueNum = 'a';props.getProperty(optionValue + optionNum + optionValueNum) != null; optionValueNum++) {
					auto.setOption(props.getProperty(option + optionNum), 
							props.getProperty(optionValue + optionNum + optionValueNum), 
							Float.parseFloat(props.getProperty(optionPrice + optionNum + optionValueNum)));
				}
				 
				
			}
			
			
			
		} catch (FileNotFoundException e) {
			// if file not found, fix it by FixHelper outside
				throw new AutoException(CustomExceptionEnum.FileNotFound);
		} catch (IOException e){
			// catch IOException
						System.out.println("Error -- " + e.toString());
		}
		
		
		return auto;

	}
	// read in an automobile from .txt file
	public Automobile readInAutomobileTxt(String filename)
			throws AutoException {

		Automobile auto = null; // Automotive model to be returned
		BufferedReader br = null; // buffer reader
		String line; // store read-in line
		String[] baseInfo = new String[3]; // store make,model,base price

		String opsetName = null;

		String[] storeOptionString; // temporarily store option strings without
									// split
		String[] storeOptionDetail; // temporarily store option detail
		
		try {

			br = new BufferedReader(new FileReader(new File(filename)));

			// read in base info

			for (int i = 0; i < baseInfo.length; i++) {
				baseInfo[i] = br.readLine();
			}

			try {
				// if miss base price , set it innerly by FixHelper to 0
				if (baseInfo[2].length() == 0) {
					throw new CustomIOException(
							CustomExceptionEnum.FileMissBasePrice);
				}
			} catch (CustomIOException e) {
				FixHelper fixHelper = new FixHelper();
				baseInfo[2] = fixHelper.FixFileMissBasePrice();
			}

			auto = new Automobile(baseInfo[0], baseInfo[1],
					Float.parseFloat(baseInfo[2]));

			// get option set size
			while ((opsetName = br.readLine()) != null) {
				auto.setOptionSet(opsetName);
				// get options and its price
				line = br.readLine();
				storeOptionString = line.split(";");
				for (int i = 0; i < storeOptionString.length; i++) {
					storeOptionDetail = storeOptionString[i].split(",");
					try {
						// if the length of storeOptionDetail is not 2,
						// the option price is missing
						// handle it innerly by FixHelper to set price 0
						if (storeOptionDetail.length != 2) {
							throw new CustomIOException(
									CustomExceptionEnum.FileOptionPriceNotFound);
						}
					} catch (CustomIOException e) {
						FixHelper fixHelper = new FixHelper();
						storeOptionDetail = fixHelper
								.FixFileMissOptionPrice(storeOptionDetail);
					}

					auto.setOption(opsetName, storeOptionDetail[0],
							Float.parseFloat(storeOptionDetail[1]));
				}
			}

		} catch (FileNotFoundException fe) {
			// if file not found, fix it by FixHelper outside
			throw new AutoException(CustomExceptionEnum.FileNotFound);
		} catch (IOException e) {
			// catch IOException
			System.out.println("Error -- " + e.toString());
		} finally {
			try {
				if (br != null) {// if file not found, it will be null
					br.close();
				}
			} catch (IOException brCloseException) {
				// catch IOExcetion for br.close()
				System.out.println("Error -- " + brCloseException.toString());
			}
		}
		// return the value
		return auto;
	}
	// load an automobile from file
	public void serializeOutput(Automobile auto) {
		ObjectOutputStream os = null;
		try {
			// write object to auto.ser file

			// StringBuffer to build file name for output
			StringBuffer sb = new StringBuffer();
			sb.append(auto.getName());
			sb.append(".ser");
			os = new ObjectOutputStream(new FileOutputStream(sb.toString()));
			os.writeObject(auto);
			os.close();

		} catch (IOException e) {
			// catch stream close exception
			System.out.println("Error -- " + e.toString());
			System.exit(1);
		} finally {
			try {
				// close stream anyhow
				os.close();
			} catch (IOException streamCloseException) {
				// catch stream close exception
				System.out.println("Error -- "
						+ streamCloseException.toString());
			}
		}
	}
	// save a automobile in file
	public Automobile serializeInput(String filename)
			throws AutoException {
		ObjectInputStream is = null;
		Automobile auto = null;
		try {
			// get object from "filename"
			is = new ObjectInputStream(new FileInputStream(filename));
			auto = (Automobile) is.readObject();

		} catch (FileNotFoundException fe) {
			// if file not found, fix it outside by FixHelper
			throw new AutoException(CustomExceptionEnum.SavedCarFileNotFound);
		} catch (IOException e) {
			// catch IOExcetion
			System.out.println("Error -- " + e.toString());
			System.exit(1);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				// close input stream anyhow
				if (is != null) {
					is.close();
				}

			} catch (IOException streamCloseException) {
				// catch stream close exception
				System.out.println("Error -- "
						+ streamCloseException.toString());
			}
		}
		return auto;
	}
	
	// return a list of all available automobile file
	public String[] getAutoFileList(String fileName){
		BufferedReader br = null; // buffer reader
		ArrayList<String> autoArrayList = new ArrayList<String>();
		String file = null;
		try {
			br = new BufferedReader(new FileReader(new File(fileName)));
			while ((file = br.readLine()) != null) {
				autoArrayList.add(file);
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String[] autoNameList = new String[autoArrayList.size()];
		for (int i = 0; i < autoNameList.length; i++) {
			autoNameList[i] = autoArrayList.get(i);
		}
		return autoNameList;
	}
	
	
	
	

}
