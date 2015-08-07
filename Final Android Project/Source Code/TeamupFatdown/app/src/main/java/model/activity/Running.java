package model.activity;

/**
 * This class represents the Running sports activity.
 */
public class Running extends Sports {
    private static final double BASICCAL = 10.5;
    private String name;
    private int time;

    public Running(String name, int time){
        this.name = name;
        this.time = time;
    }

    /**
     * Get the name of the activity.
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of activity.
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
