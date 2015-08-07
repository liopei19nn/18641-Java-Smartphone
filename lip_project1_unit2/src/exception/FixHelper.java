package exception;

import util.FileIO;
import model.Automobile;
import model.Fleet;

/**
 * @author Li Pei
 * @andrew_id lip
 */
public class FixHelper {
	// default input car model : Focus
	private static final String DEFAULT_CAR_MODEL_FILE = "default_car_model.txt";
	// default load car model : NISSAN GTR
	private static final String DEFAULT_SAVED_CAR_MODEL = "Default_NISSAN_GTR.ser";

	// Fix File not found exception by loading default car model and set it into
	// input fleet
	public void FixFileNotFound(Fleet autoList) {
		Automobile auto = null;
		try {
			auto = new FileIO().readInAutomotive(DEFAULT_CAR_MODEL_FILE);
		} catch (AutoException e) {
			System.out.println("Default Model File Not Find Exception: "
					+ e.toString());
		}
		autoList.setAutomobile(auto);
	}

	// fix missing base price problem by set it to 0
	public String FixFileMissBasePrice() {
		return "0";
	}

	// fix missing option price problem by set it to 0
	public String[] FixFileMissOptionPrice(String[] input) {
		String[] output = new String[2];
		output[0] = input[0];
		output[1] = "0";
		return output;
	}

	// Fix File not found exception by loading default car model and set it into
	// input fleet
	public void SavedCarFileNotFound(Fleet autoList) {
		Automobile auto = null;
		try {
			auto = new FileIO().serializeInput(DEFAULT_SAVED_CAR_MODEL);
		} catch (AutoException e) {
			System.out.println("Default Model File Not Find Exception: "
					+ e.toString());
		}
		autoList.setAutomobile(auto);
	}

}
