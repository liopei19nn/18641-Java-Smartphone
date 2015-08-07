package driver;

import adapter.BuildAuto;

/**
 * @author Li Pei
 * @andrew_id lip
 */
public class DriverMultiAutomobile {
	public static void main(String[] args) {
		BuildAuto autoBuilder = new BuildAuto();
		autoBuilder.buildAuto("Focus.txt");
		autoBuilder.buildAuto("Nissan.txt");
		autoBuilder.printAuto("NISSAN GTR");

		autoBuilder.saveCarModel("NISSAN GTR");

		System.out.println("**********************************");
		autoBuilder.deleteAuto("NISSAN GTR");
		autoBuilder.printAuto("NISSAN GTR");

		System.out.println("**********************************");
		autoBuilder.loadCarModel("NISSAN GTR");
		autoBuilder.printAuto("NISSAN GTR");
	}
}
