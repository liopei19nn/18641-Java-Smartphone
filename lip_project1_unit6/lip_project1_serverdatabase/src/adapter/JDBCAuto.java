/**
 *
 */
package adapter;

/**
 * @author Li Pei
 *
 * Andrew ID : lip
 */
public interface JDBCAuto {
	// build an automobile in database and linked hash map
	public void JDBC_buildAuto(String filename);
	// delete an automobile in database and linked hash map
	public void JDBC_deleteAuto(String AutoName);
	// update base price of an automobile in database and linked hash map
	public void JDBC_updateAutoBasePrice(String AutoName, float newPrice);
	// update option price of an automobile in database and linked hash map
	public void JDBC_updateAutoOptionPrice(String AutoName, String OptionSetName, String OptionName, float OptionPrice);

}
