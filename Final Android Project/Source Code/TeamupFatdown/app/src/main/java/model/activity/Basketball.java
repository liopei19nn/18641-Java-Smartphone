package model.activity;

/**
 * This class represents the Basketball sports activity.
 */
public class Basketball extends Sports {
    private static final double BASICCAL = 7.5;
    private String name;
    private int time;

    /**
     * Constructor
     *
     * @param name
     * @param time
     */
    public Basketball(String name, int time) {
        this.name = name;
        this.time = time;
    }

    /**
     * Get the name of the activity.
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of activity.
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getTime() {
        return time;
    }

    /**
     * Set the duration of activity.
     *
     * @param time
     */
    public void setTime(int time) {
        this.time = time;
    }

    @Override
    public double getBasicCal() {
        return BASICCAL;
    }
}
