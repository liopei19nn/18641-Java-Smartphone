package model.activity;

/**
 * This class represents the Baseball sports activity.
 */
public class Baseball extends Sports {
    private static final double BASICCAL = 5;
    private String name;
    private int time;

    /**
     * Constructor
     * @param name
     * @param time
     */
    public Baseball(String name, int time){
        this.name = name;
        this.time = time;
    }

    /**
     * Get the name
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name
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
     * Set the duration of the activity.
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
