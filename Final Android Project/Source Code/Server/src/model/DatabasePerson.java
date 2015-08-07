package model;
/**
 * 
 * @team Three Threads
 * @author Li Pei
 * @AndrewID : lip
 * 
 */
public class DatabasePerson{

   private String userName;
   private int passWord;
   private String nickName;
   private int age;
   private String gender;
   private int height;
   private int weight;
   private int calConsumption;
   private int calGoal;
   
   // for Person, rival is a Person
   // but in DatabasePerson, rival is 
   // a string of rival's user name
   private String rivalUsername;

   public DatabasePerson(){

   }



   public String getUserName() {
       return userName;
   }

   public void setUserName(String userName) {
       this.userName = userName;
   }

   public int getPassWord() {
       return passWord;
   }

   public void setPassWord(int passWord) {
       this.passWord = passWord;
   }

   public String getNickName() {
       return nickName;
   }

   public void setNickName(String nickName) {
       this.nickName = nickName;
   }

   public int getAge() {
       return age;
   }

   public void setAge(int age) {
       this.age = age;
   }

   public String getGender() {
       return gender;
   }

   public void setGender(String gender) {
       this.gender = gender;
   }

   public int getHeight() {
       return height;
   }

   public void setHeight(int height) {
       this.height = height;
   }

   public int getWeight() {
       return weight;
   }

   public void setWeight(int weight) {
       this.weight = weight;
   }

   public int getCalConsumption() {
       return calConsumption;
   }

   public void setCalConsumption(int calConsumption) {
       this.calConsumption = calConsumption;
   }

   public int getCalGoal() {
       return calGoal;
   }

   public void setCalGoal(int calGoal) {
       this.calGoal = calGoal;
   }

   public void setRival(String s){
       this.rivalUsername = s;
   }

   public String getRival(){
       return this.rivalUsername;
   }

   // transfer a DatabasePerson into String
   // like : 
   // username;password;nickname;age;gender;height;weight;calconsumption;calgoal;rivalname
   @Override
   public String toString(){

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

   
   // input string and turn the string into a DatabasePerson
   public void generateFromString(String s){
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
