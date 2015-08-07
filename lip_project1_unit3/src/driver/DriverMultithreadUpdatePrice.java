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
public class DriverMultithreadUpdatePrice {
	public static void main(String[] args) {
		BuildAuto autoBuilder = new BuildAuto();
		autoBuilder.buildAuto("Focus.txt");
		//model name, option set name, option name, new option price
		String[] args1 = {"Focus Wagon ZTW","Transmission","Automatic","1111"};
		String[] args2 = {"Focus Wagon ZTW","Transmission","Automatic","1111"};
		
		
		//edit auto to update option price
		autoBuilder.edit(EditOptionEnum.EditOptionPrice, args1);
		autoBuilder.edit(EditOptionEnum.EditOptionPrice, args2);


	}
}
