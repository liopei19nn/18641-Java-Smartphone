/**
 * 
 */
package hw1Parking;

/**
 * @author Li Pei
 * @andrew_id lip 
 */
public class ParkingMeter {
	/*
	 * instance variable of purchased parking time
	 * 
	 * */
	
	private int purchasedMinutes = 0;
	/*
	 * ParkingMeter
	 * constructor with void input
	 * @param purchasedMinutes
	 * 
	 * */
	public ParkingMeter(){
		
	}
	/*
	 * ParkingMeter
	 * constructor with input
	 * @param purchasedMinutes
	 * 
	 * */
	public ParkingMeter(int minutes){
		if (minutes >= 0) {
			this.purchasedMinutes = minutes;
		}
	}
	
	/*
	 * getPurchasedTime()
	 * get purchased parking time
	 * 
	 * @param purchasedMinutes
	 * 
	 * */
	public int getPurchasedTime(){
		return purchasedMinutes;
	}
	/*
	 * setPurchasedTime()
	 * set purchased parking time
	 * 
	 * @param purchasedMinutes
	 * 
	 * */
	public void setPurchasedTime(int minutes){
		if (minutes >= 0) {
			this.purchasedMinutes = minutes;
		}
	}
}
