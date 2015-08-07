/**
 * 
 */
package driver;


import scale.EditOptionEnum;
import adapter.BuildAuto;

/**
 * @author Li Pei
 * @andrew_id lip 
 */
public class DriverMultithreadingOptionset {
	public static void main(String[] args) {
		BuildAuto autoBuilder = new BuildAuto();
		autoBuilder.buildAuto("Focus.txt");
		
		// modelname, option set name, new option set name
		String[] args1 = {"Focus Wagon ZTW","Transmission","Thread"};
		String[] args2 = {"Focus Wagon ZTW","Transmission","Thread"};
		
		// edit auto to edit option set name
		autoBuilder.edit(EditOptionEnum.EditOptionSetName, args1);
		autoBuilder.edit(EditOptionEnum.EditOptionSetName, args2);
	}
	
	
}
