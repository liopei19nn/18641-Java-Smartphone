package adapter;

/**
 * @author Li Pei
 * @andrew_id lip
 */
public interface CreateAuto {
	// Given a text file name a method called BuildAuto can be written to build
	// an instance of Automobile. This method does not have to return the Auto
	// instance.
	public void buildAuto(String filename);

	// This function searches and prints the properties of a given Automobile.
	public void printAuto(String ModelName);
}
