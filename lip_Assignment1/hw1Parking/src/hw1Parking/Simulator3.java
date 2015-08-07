/**
 * 
 */
package hw1Parking;

/**
 * @author Li Pei
 * @andrew_id lip 
 * parked vehicle is out of the parking time purchased
 */
public class Simulator3 {
	public static void main(String[] args) {
		System.out.println("*********************");
		System.out.println("Simulation3 : parked vehicle is out of the parking time purchased");
		/*
		 * new instance of parking car
		 */
		
		ParkedCar parkedCar = new ParkedCar("FORD", "FOCUS", "BLACK", "15215",
				70);
		/*
		 * new instance of parking meter
		 */
		ParkingMeter parkingMeter = new ParkingMeter();
		parkingMeter.setPurchasedTime(60);
		
		System.out.println("Parked Car parking for " + parkedCar.getParkedMinutes()
				+" Minutes");
		System.out.println("Purchased parking for " + parkingMeter.getPurchasedTime()
				+" Minutes");
		System.out.println("*********************");
		
		
		/*
		 * new instance of policeOfficer
		 */
		PoliceOfficer policeOfficer = new PoliceOfficer("Master Yoda",
				"00003");

		/*
		 * if examine expire is true ,issue ticket else print legal parking and
		 * do nothing
		 */
		if (policeOfficer.examineParkingExpire(parkingMeter.getPurchasedTime(),
				parkedCar.getParkedMinutes())) {
			policeOfficer.issueTicket(parkedCar, parkingMeter);
		} else {
			System.out.println("Legal Parking.");
		}
	}
}
