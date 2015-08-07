/**
 * 
 */
package util;

import java.io.*;
import model.Automotive;

/**
 * @author Li Pei
 * @andrew_id lip
 */

public class FileIO {

	public Automotive readInAutomotive(String filename) {

		Automotive auto = null; // Automotive model to be returned
		BufferedReader br = null; // buffer reader
		String line; // store read-in line
		String[] baseInfo = new String[4]; // store make,model,base price and
											// option set size
		int OptionSetSize = 0; // size of option set
		String[] storeOptionSet; // temporarily store option set name and size
		String[] storeOptionString; // temporarily store option strings without
									// split
		String[] storeOptionDetail; // temporarily store option detail

		try {
			br = new BufferedReader(new FileReader(new File(filename)));

			// read in base info
			for (int i = 0; i < baseInfo.length; i++) {
				baseInfo[i] = br.readLine();
			}
			auto = new Automotive(baseInfo[0], baseInfo[1],
					Float.parseFloat(baseInfo[2]),
					Integer.parseInt(baseInfo[3]));

			// get option set size
			OptionSetSize = auto.getOptionSetListSize();
			

			for (int i = 0; i < OptionSetSize; i++) {
				// get option set information and initialize option set
				line = br.readLine();
				storeOptionSet = line.split(",");
				auto.setOptionSet(i, storeOptionSet[0],
						Integer.parseInt(storeOptionSet[1]));

				// get options and its price
				line = br.readLine();
				storeOptionString = line.split(";");
				for (int j = 0; j < storeOptionString.length; j++) {
					storeOptionDetail = storeOptionString[j].split(",");
					
					auto.setOption(i, j, storeOptionDetail[0],
							Float.parseFloat(storeOptionDetail[1]));
					
				}
			}

		} catch (IOException e) {
			// catch IOException
			System.out.println("Error -- " + e.toString());
		} finally {
			try {
				br.close();
			} catch (IOException brCloseException) {
				// catch IOExcetion for br.close()
				System.out.println("Error -- " + brCloseException.toString());
			}
		}
		// return the value
		return auto;

	}

	public void serializeOutput(Automotive auto) {
		ObjectOutputStream os = null;
		try {
			// write object to auto.ser file
			os = new ObjectOutputStream(new FileOutputStream("auto.ser"));
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

	public Automotive serializeInput(String filename) {
		ObjectInputStream is = null;
		Automotive auto = null;
		try {
			// get object from "filename"
			is = new ObjectInputStream(new FileInputStream(filename));
			auto = (Automotive) is.readObject();

		} catch (IOException e) {
			// catch IOExcetion
			System.out.println("Error -- " + e.toString());
			System.exit(1);

		} catch (ClassNotFoundException e) {
			// catch stream exception
			System.out.println("Error -- " + e.toString());
			System.exit(1);

		} finally {
			try {
				// close input stream anyhow
				is.close();
			} catch (IOException streamCloseException) {
				// catch stream close exception
				System.out.println("Error -- "
						+ streamCloseException.toString());
			}
		}
		return auto;

	}

}
