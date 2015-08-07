/**
 * 
 */
package hw1Parking;

/**
 * @author Li Pei
 * @andrew_id lip 
 */
public class PoliceOfficer {
	/*
	 * instance variables of police officer
	 * 
	 * */
	private String name;
	private String badgeNumber;
	/*
	 * PoliceOfficer()
	 * set the police officer's information
	 * @param badgeNumber,name
	 * 
	 * */
	public PoliceOfficer(String name,String badgeNumber){
			this.name = name;
			this.badgeNumber = badgeNumber;
	}
	/*
	 * getName() : get the officer's name 
	 * 
	 * @param name
	 * 
	 * */
	public String getName(){
		return name;
	}
	/*
	 * setName() : set the officer's name 
	 * 
	 * @param name
	 * 
	 * */
	public void setName(String name){
			this.name = name;
	}
	/*
	 * getBadgeNumber() : get the officer's badge number 
	 * 
	 * @param badgeNumber
	 * 
	 * */
	public String getBadgeNumber(){
		return badgeNumber;
	}
	/*
	 * setBadgeNumber() : set the officer's badge number 
	 * 
	 * @param badgeNumber
	 * 
	 * */
	public void setBadgeNumber(String badgeNumber){
			this.badgeNumber = badgeNumber;
	}
	/*
	 * examineParkingExpire()
	 * examine if a car's parking time is expired
	 * 
	 * */
	public boolean examineParkingExpire(int purchasedTime, int parkedTime){
		if (purchasedTime >= parkedTime) {
			return false;
		}else {
			return true;
		}
	}
	/*
	 * issueTicket()
	 * issue fine ticket
	 * 
	 * */
	public void issueTicket(ParkedCar parkedCar, ParkingMeter parkingMeter){
		ParkingTicket parkingTicket = new ParkingTicket(parkedCar, this, parkingMeter);
		System.out.println("Illegal Parking Fine Ticket");
		System.out.println();
		parkingTicket.reportCarInfo();
		System.out.println();
		parkingTicket.reportFine();
		System.out.println();
		parkingTicket.reportOfficerInfo();
	}
}
