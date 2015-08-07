package model.activity;

/**
 * This class represents the sports activity.
 */
public abstract class Sports {

    /**
     * Get the basic calories reduction.
     * @return
     */
    public abstract double getBasicCal();

    /**
     * Get the duration of activity.
     * @return
     */
    public abstract int getTime();

    /**
     * Calculate the reduction of calories
     * @param basicCal
     * @param weight
     * @param BMR
     * @param time
     * @return
     */
    public int calculateCal(double basicCal, int weight, double BMR, int time) {
        double result = (basicCal * weight - BMR / 24) * ((double)time / 60);
        return (int)result;
    }
}
