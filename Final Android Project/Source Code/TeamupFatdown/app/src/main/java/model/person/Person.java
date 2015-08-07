package model.person;

import android.content.Context;

import java.util.ArrayList;

import model.activity.Sports;
import model.nutrition.Nutrition;

/**
 * This class represents the person.
 */
public class Person implements Friend {
    private static Person pPerson;
    private String userName;
    private int passWord;
    private String nickName;
    private int age;
    private String gender;
    private int height;
    private int weight;
    private int calConsumption;
    private int calGoal;
    private double BMR;
    private ArrayList<Friend> team;
    private ArrayList<Sports> sportsList;
    private ArrayList<Nutrition> nutritionList;
    private Friend rival;
    private String rivalName;
    private Context context;

    /**
     * Default Constructor
     */
    public Person() {
    }

    /**
     * Constructor
     *
     * @param context
     */
    public Person(Context context) {
        this.context = context;
        pPerson = this;
        nickName = "Team up Fat down";
        age = 20;
        height = 175;
        weight = 65;
        gender = "Male";
        rivalName = "nobody";
        calConsumption = 0;
        calGoal = 3000;
        if (gender.equals("Male")) {
            BMR = 66 + (13.7 * weight) + (5 * height) - (6.8 * age);
        } else {
            BMR = 655 + (9.6 * weight) + (1.8 * height) - (4.7 * age);
        }
        team = new ArrayList<>();
        sportsList = new ArrayList<>();
        nutritionList = new ArrayList<>();
    }

    public static Person get(Context c) {
        if (pPerson == null) {
            pPerson = new Person(c.getApplicationContext());
        }
        return pPerson;
    }

    @Override
    public String getUserName() {
        return userName;
    }

    /**
     * Set the username.
     *
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Get the password.
     *
     * @return
     */
    public int getPassWord() {
        return passWord;
    }

    /**
     * Set the password.
     *
     * @param passWord
     */
    public void setPassWord(int passWord) {
        this.passWord = passWord;
    }

    @Override
    public String getNickName() {
        return nickName;
    }

    /**
     * Set the nickname.
     *
     * @param nickName
     */
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @Override
    public int getAge() {
        return age;
    }

    /**
     * Set the age.
     *
     * @param age
     */
    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String getGender() {
        return gender;
    }

    /**
     * Set the gender.
     *
     * @param gender
     */
    public void setGender(String gender) {
        this.gender = gender;
        if (gender.equals("Male")) {
            BMR = 66 + (13.7 * weight) + (5 * height) - (6.8 * age);
        } else {
            BMR = 655 + (9.6 * weight) + (1.8 * height) - (4.7 * age);
        }
    }

    @Override
    public int getHeight() {
        return height;
    }

    /**
     * Set the height of user.
     *
     * @param height
     */
    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public int getWeight() {
        return weight;
    }

    /**
     * Set the weight of user.
     *
     * @param weight
     */
    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public int getCalConsumption() {
        return calConsumption;
    }

    /**
     * Set the calories consumption.
     *
     * @param calConsumption
     */
    public void setCalConsumption(int calConsumption) {
        this.calConsumption = calConsumption;
    }

    @Override
    public int getCalGoal() {
        return calGoal;
    }

    /**
     * Set the calories goal
     *
     * @param calGoal
     */
    public void setCalGoal(int calGoal) {
        this.calGoal = calGoal;
    }

    /**
     * Get the team of user.
     *
     * @return
     */
    public ArrayList<Friend> getTeam() {
        return team;
    }

    /**
     * Set the team.
     *
     * @param team
     */
    public void setTeam(ArrayList<Friend> team) {
        this.team = team;
    }

    /**
     * Get the list of sports activity.
     *
     * @return
     */
    public ArrayList<Sports> getSportsList() {
        return sportsList;
    }

    /**
     * Set the list of sports activity.
     *
     * @param sportsList
     */
    public void setSportsList(ArrayList<Sports> sportsList) {
        this.sportsList = sportsList;
    }

    /**
     * Get the list of nutrition.
     *
     * @return
     */
    public ArrayList<Nutrition> getNutritionList() {
        return nutritionList;
    }

    /**
     * Set the list of nutrition.
     *
     * @param nutritionList
     */
    public void setNutritionList(ArrayList<Nutrition> nutritionList) {
        this.nutritionList = nutritionList;
    }

    /**
     * Set the rival of user.
     *
     * @param f
     */
    public void setRival(Friend f) {
        this.rival = f;
    }

    /**
     * Get the rival of user.
     *
     * @return
     */
    public Friend getRival() {
        return this.rival;
    }

    /**
     * Set the rival's name.
     *
     * @param s
     */
    public void setRivalName(String s) {
        this.rivalName = s;
    }

    /**
     * Get the name of rival.
     * @return
     */
    public String getRivalName() {
        return this.rivalName;
    }

    /**
     * Update the calories consumption by sport.
     *
     * @param sport
     */
    public void updateCal(Sports sport) {
        calConsumption += sport.calculateCal(sport.getBasicCal(), weight, BMR, sport.getTime());
        sportsList.add(sport);
        if(calConsumption > Integer.MAX_VALUE){
            calConsumption = 0;
        }
    }

    /**
     * Update the calories consumption by nutrition.
     *
     * @param nutrition
     */
    public void updateCal(Nutrition nutrition) {
        calConsumption -= nutrition.calculateCal(nutrition.getCal(), nutrition.getQuantity());
        nutritionList.add(nutrition);
        if(calConsumption < Integer.MIN_VALUE){
            calConsumption = 0;
        }
    }
}
