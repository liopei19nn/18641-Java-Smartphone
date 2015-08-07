/**
 * 
 */
package hw1CoinToss;

/**
 * @author Li Pei
 * @andrew_id lip 
 * Toss 20 times 
 */
public class Simulation1 {
	public static void main(String[] args) {
		System.out.println("Simulation1:Toss 20 Times");
		int tossTimes = 20; // total toss number
		
		int headsCount = 0; // total number of heads
		int tailscount = 0; // total number of tails

		String currentSideUp; // current side
		
		// new instance of coin
		Coin testCoin = new Coin();
		
		// display the initial facing up
		System.out.println("Initially Facing Up : " + testCoin.getSideUp());
		
		// toss 20 times, display the facing up side each time
		System.out.println("Toss " + tossTimes + " Times");
		for (int count = 0; count < tossTimes; count++) {
			testCoin.toss();
			currentSideUp = testCoin.getSideUp();
			System.out.println("Current Facing Up Side : " + currentSideUp);
			if (currentSideUp.equals("heads")) {
				headsCount++;
			} else {
				tailscount++;
			}
		}
		if(tossTimes == 0){
			System.out.println("No toss at all");
		}else if(tossTimes < 0){
			System.out.println("Illegal toss times");
		}else{
			System.out.println("Times of Tossing Heads : " + headsCount);
			System.out.println("Times of Tossing Tails : " + tailscount);
		}
	}
}
