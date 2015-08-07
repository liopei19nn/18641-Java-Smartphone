/**
 * 
 */
package prototype;

/**
 * @author Li Pei
 * @andrew_id lip
 */
public abstract class AbstractStatistics<P extends People> {
	/*
	 * findhigh() find the highest score
	 * 
	 * @param highscores[]
	 */
	public abstract void findhigh(P[] a);

	/*
	 * findlow() find the lowest scoreww
	 * 
	 * @param lowscores[]
	 */
	public abstract void findlow(P[] a);

	/*
	 * findavg() find the average score
	 * 
	 * @param avgscores[]
	 */
	public abstract void findavg(P[] a);
}
