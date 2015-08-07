package model.person;

/**
 * This interface represents the friend interface which contain some useful methods.
 */
public interface Friend {
    /**
     * Get the username
     * @return
     */
    String getUserName();

    /**
     * Get the nickname.
     * @return
     */
    String getNickName();

    /**
     * Get the age.
     * @return
     */
    int getAge();

    /**
     * Get the gender.
     * @return
     */
    String getGender();

    /**
     * Get the height.
     * @return
     */
    int getHeight();

    /**
     * Get the weight.
     * @return
     */
    int getWeight();

    /**
     * Get the consumption of calories.
     * @return
     */
    int getCalConsumption();

    /**
     * Get the goal
     * @return
     */
    int getCalGoal();


}
