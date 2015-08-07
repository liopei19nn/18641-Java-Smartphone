/**
 * 
 */
package hw1Parking;

/**
 * @author Li Pei
 * @andrew_id lip 
 * Ticketing under 1 hour
 */
public class Simulator4 {
	public static void main(String[] args) {
		System.out.println("*********************");
		System.out.println("Simulation4 : Ticketing under 1 hour");
		/*
		 * new instance of parking car
		 */
		
		ParkedCar parkedCar = new ParkedCar("MAZDA", "3", "YELLOW", "15216",
				75);
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
		PoliceOfficer policeOfficer = new PoliceOfficer("Luke Skywalker",
				"00001");

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
