package scale;

import exception.AutoException;
import model.Automobile;

/**
 * @author Li Pei
 * @andrew_id lip
 */
public class EditOption extends Thread {
	private Automobile editAuto;// Auto to edit
	private int editOptionEnum; // edit option
	private String[] args; // arguments for operation
	// args[0] : model name
	// args[1] : optionset name
	// args[2] : new option set name or option name
	// args[3] : new option price
	private int threadID; // this thread ID

	/*
	 * EditOption()
	 * 
	 * constructor
	 */
	public EditOption(int threadID, Automobile editAuto,
			EditOptionEnum editOptionEnum, String[] args) {
		this.editAuto = editAuto;
		this.editOptionEnum = editOptionEnum.getValue();
		this.args = args;
		this.threadID = threadID;
	}

	/*
	 * run()
	 * 
	 * thread function, use editOptionEnum to choose what to do
	 */
	@Override
	public void run() {
		switch (editOptionEnum) {
		case 1:

			ThreadUpdateOptionSetName();
			break;
		case 2:
			try {
				ThreadUpdateOptionPrice();
			} catch (NumberFormatException | AutoException ae) {
				System.out.println("Error -- " + ae.toString());
			}
			break;
		default:
			break;
		}
	}

	/*
	 * ThreadUpdateOptionSetName()
	 * 
	 * update option set name
	 */
	public void ThreadUpdateOptionSetName() {
		// A circle update option set name to test synchronization between
		// threads
		String[] ThreadOptionSetName = { args[1], "Op1", "Op2", "Op3", "Op4",
				"Op5", "Op6", "Op7", args[1] };

		synchronized (editAuto) { // comment this line to see insynchronized
			for (int i = 0; i < ThreadOptionSetName.length - 1; i++) {
				// wait for random time in for()
				randomWait();
				try {
					// update option set name
					// [i] is old name
					// [i+1] is new name
					editAuto.updateOptionSetName(ThreadOptionSetName[i],
							ThreadOptionSetName[i + 1]);
					System.out
							.println("Thread"
									+ threadID
									+ " : "
									+ "Change Option Set Name From "
									+ ThreadOptionSetName[i]
									+ " To "
									+ editAuto
											.getOptionSetName(ThreadOptionSetName[i + 1]));

				} catch (AutoException ae) {
					System.out.println("Thread" + threadID + " : "
							+ "Error -- " + ae.toString());
				}

			}
		}
	}

	/*
	 * ThreadUpdateOptionPrice()
	 * 
	 * update option price
	 */
	public void ThreadUpdateOptionPrice() throws NumberFormatException,
			AutoException {
		synchronized (editAuto) { // comment this line to see insynchronized
			for (int i = 1; i < 10; i++) {

				randomWait();
				// args1 is old-set-name, args2 is new args3 is new price
				editAuto.updateOptionPrice(args[1], args[2],
						i * Float.parseFloat(args[3]));
				System.out.println("Thread " + threadID + " " + args[1] + " "
						+ args[2] + " Price: "
						+ editAuto.getOptionPrice(args[1], args[2]));
			}
		}
	}

	/*
	 * wait for a random time
	 */
	void randomWait() {
		try {
			Thread.currentThread();
			Thread.sleep((long) (3000 * Math.random()));
		} catch (InterruptedException e) {
			System.out.println("Interrupted!");
		}
	}

}
