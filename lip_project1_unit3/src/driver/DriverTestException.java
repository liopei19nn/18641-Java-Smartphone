package driver;

import java.io.IOException;

import adapter.BuildAuto;

/**
 * @author Li Pei
 * @andrew_id lip
 */
public class DriverTestException {
	public static void main(String[] args) throws NumberFormatException,
			IOException {
		BuildAuto autoBuilder = new BuildAuto();
		System.out
				.println("***************test miss option price or base price*******************");
		autoBuilder.buildAuto("FlawFocus.txt");
		autoBuilder.deleteAuto("Focus Wagon ZTW");

		System.out
				.println("***************test cannot find file*******************");
		// Test Wrong file name, load default car
		autoBuilder.buildAuto("Focu.txt");

		autoBuilder.printAuto("Focus Wagon ZTW");

		System.out.println("***************test save*******************");
		// Test wrong save model name
		autoBuilder.saveCarModel("Fodus");

		System.out.println("***************test update*******************");
		// Test update wrong option set
		autoBuilder.updateOptionSetName("Focus Wagon ZTW", "olor",
				"!!!Changed Color!!!");
		// Test update wrong option
		autoBuilder.updateOptionPrice("Focus Wagon ZTW", "Transmission",
				"outomatic", (float) 9999.99);

		System.out.println("***************test delete*******************");

		autoBuilder.deleteAuto("Focus Wagon ZTW");
		// Test Wrong model name
		autoBuilder.printAuto("Focus Wagon ZTW");

		System.out.println("***************test load*******************");
		// Test Wrong load model
		autoBuilder.loadCarModel("Fodus");
		autoBuilder.printAuto("NISSAN GTR");
	}
}
