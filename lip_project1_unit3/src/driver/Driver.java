package driver;

import java.io.IOException;
import adapter.BuildAuto;

/**
 * @author Li Pei
 * @andrew_id lip
 */
public class Driver {
	public static void main(String[] args) throws NumberFormatException,
			IOException {
		BuildAuto autoBuilder = new BuildAuto();
		autoBuilder.buildAuto("Focus.txt");
		autoBuilder.printAuto("Focus Wagon ZTW");

		System.out.println("***************test update*******************");

		autoBuilder.updateOptionSetName("Focus Wagon ZTW", "Color",
				"!!!Changed Color!!!");
		autoBuilder.updateOptionPrice("Focus Wagon ZTW", "Transmission",
				"Automatic", (float) 9999.99);

		autoBuilder.printAuto("Focus Wagon ZTW");

		System.out.println("***************test delete*******************");
		autoBuilder.deleteAuto("Focus Wagon ZTW");
		autoBuilder.printAuto("Focus Wagon ZTW");
	}

}
