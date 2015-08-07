package model.person;

/**
 * This class represents the object which
 */
public class DatabasePerson {
    private String userName;
    private int passWord;
    private String nickName = "EMPTY";
    private int age;
    private String gender = "EMPTY";
    private int height;
    private int weight;
    private int calConsumption;
    private int calGoal;
    private String rivalUsername = "EMPTY";

    /**
     * Default Constructor
     */
    public DatabasePerson() {

    }

    /**
     * Constructor2
     *
     * @param userName
     * @param p
     */
    public DatabasePerson(String userName, int p) {
        this.userName = userName;
        this.passWord = p;
    }

    /**
     * Get the username
     *
     * @return
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Set the username
     *
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Get the password
     *
     * @return
     */
    public int getPassWord() {
        return passWord;
    }

    /**
     * Set the password
     *
     * @param passWord
     */
    public void setPassWord(int passWord) {
        this.passWord = passWord;
    }

    /**
     * Get the nickname of user
     *
     * @return
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * Set the nickname of user
     *
     * @param nickName
     */
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    /**
     * Get the age of user
     *
     * @return
     */
    public int getAge() {
        return age;
    }

    /**
     * Set the age of user
     *
     * @param age
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Get the gender of user
     *
     * @return
     */
    public String getGender() {
        return gender;
    }

    /**
     * Set the gender of user
     *
     * @param gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * Get the height of user
     *
     * @return
     */
    public int getHeight() {
        return height;
    }

    /**
     * Set the height of user
     *
     * @param height
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Get the weight of user
     *
     * @return
     */
    public int getWeight() {
        return weight;
    }

    /**
     * Set the weight of user
     *
     * @param weight
     */
    public void setWeight(int weight) {
        this.weight = weight;
    }

    /**
     * Get the calories consumption of user.
     *
     * @return
     */
    public int getCalConsumption() {
        return calConsumption;
    }

    /**
     * Set the calories consumption of user.
     *
     * @param calConsumption
     */
    public void setCalConsumption(int calConsumption) {
        this.calConsumption = calConsumption;
    }

    /**
     * Get the calories goal of user.
     *
     * @return
     */
    public int getCalGoal() {
        return calGoal;
    }

    /**
     * Set the caloreis goal.
     *
     * @param calGoal
     */
    public void setCalGoal(int calGoal) {
        this.calGoal = calGoal;
    }

    /**
     * Set the rival name of user.
     *
     * @param s
     */
    public void setRival(String s) {
        this.rivalUsername = s;
    }

    /**
     * Get the rival name
     *
     * @return
     */
    public String getRival() {
        return this.rivalUsername;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        sb.append(this.userName);
        sb.append(";");

        sb.append(this.passWord);
        sb.append(";");

        sb.append(this.nickName);
        sb.append(";");

        sb.append(this.age);
        sb.append(";");

        sb.append(this.gender);
        sb.append(";");

        sb.append(this.height);
        sb.append(";");

        sb.append(this.weight);
        sb.append(";");

        sb.append(this.calConsumption);
        sb.append(";");

        sb.append(this.calGoal);
        sb.append(";");

        sb.append(this.rivalUsername);

        return sb.toString();
    }

    /**
     * Input string and turn the string into a DatabasePerson
     *
     * @param s
     */
    public void generateFromString(String s) {
        String[] info = s.split(";");

        this.userName = info[0];

        this.passWord = Integer.parseInt(info[1]);

        this.nickName = info[2];

        this.age = Integer.parseInt(info[3]);

        this.gender = info[4];

        this.height = Integer.parseInt(info[5]);

        this.weight = Integer.parseInt(info[6]);

        this.calConsumption = Integer.parseInt(info[7]);

        this.calGoal = Integer.parseInt(info[8]);

        this.rivalUsername = info[9];
    }

}
