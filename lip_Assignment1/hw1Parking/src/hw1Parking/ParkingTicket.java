/**
 * 
 */
package hw1Parking;

/**
 * @author Li Pei
 * @andrew_id lip
 */
public class ParkingTicket {
	/*
	 * instance variables of parking ticket
	 */
	private ParkedCar parkedCar;
	private double fine;
	private PoliceOfficer policeOfficer;
	private ParkingMeter parkingMeter;
	private static final double BASEFINE = 25.00; // base fine amount
	private static final double EXTRAFINEPERHOUR = 10.00; // fine per extra hour

	/*
	 * ParkingTicket() set the parking ticket information
	 * 
	 * @param : parkedCarInfo,policeOfficerInfo,parkingMitutes, purchasedMinutes
	 */
	public ParkingTicket(ParkedCar parkedCar, PoliceOfficer policeOfficer,
			ParkingMeter parkingMeter) {
		this.parkedCar = parkedCar;
		this.policeOfficer = policeOfficer;
		this.parkingMeter = parkingMeter;
	}

	/*
	 * reportCarInfo() report illegal parking car's information
	 * 
	 * @param parketCarInfo
	 */
	public void reportCarInfo() {
		System.out.println("Illegal Parking Car Infomation:");
		String parkedCarInfo = "Make: " + parkedCar.getMake() + "\nModel: "
				+ parkedCar.getModel() + "\nColor: " + parkedCar.getColor()
				+ "\nLicense Number: " + parkedCar.getLicenseNumber();
		System.out.println(parkedCarInfo);
	}

	/*
	 * reportOfficerInfo() report police officer's information
	 * 
	 * @param policeOfficerInfo
	 */
	public void reportOfficerInfo() {
		System.out.println("Ticket Issued Officer:");
		String officerInfo = "Name : " + policeOfficer.getName()
				+ "\nBadge Number : " + policeOfficer.getBadgeNumber();
		System.out.println(officerInfo);
	}

	/*
	 * reportFine() report fine
	 * 
	 * @param fine
	 */
	public void reportFine() {
		calculateFine();
		System.out.println("Illegal Parking Fine : $" + fine);
	}

	/*
	 * calculateFine() calculate the total fine
	 * 
	 * @param : fine
	 */
	private void calculateFine() {
		this.fine = BASEFINE;
		if (parkedCar.getParkedMinutes() - parkingMeter.getPurchasedTime() > 60) {
			this.fine += EXTRAFINEPERHOUR
					* ((parkedCar.getParkedMinutes() - parkingMeter
							.getPurchasedTime()) / 60);
		}
	}
}
