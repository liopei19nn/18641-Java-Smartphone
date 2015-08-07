package communication;

import model.person.DatabasePerson;
import model.person.Person;

/**
 *
 * @team Three Threads
 * @author Li Pei
 * @AndrewID : lip
 *
 */
public class proxyTransformPerson {

    // transfer a dbPerson to a person
    public static void dbPersonToPerson(DatabasePerson dbperson, Person person){
        person.setUserName(dbperson.getUserName());
        person.setPassWord(dbperson.getPassWord());
        person.setNickName(dbperson.getNickName());
        person.setAge(dbperson.getAge());
        person.setGender(dbperson.getGender());
        person.setHeight(dbperson.getHeight());
        person.setWeight(dbperson.getWeight());
        person.setCalConsumption(dbperson.getCalConsumption());
        person.setCalGoal(dbperson.getCalGoal());

        // Person should use this name to get a Person as rival in database
        person.setRivalName(dbperson.getRival());
    }
    // transfer a Person to a dbPerson
    public static void personTodbPerson(Person person,DatabasePerson dbperson){
        dbperson.setUserName(person.getUserName());
        dbperson.setPassWord(person.getPassWord());
        dbperson.setNickName(person.getNickName());
        dbperson.setAge(person.getAge());
        dbperson.setGender(person.getGender());
        dbperson.setHeight(person.getHeight());
        dbperson.setWeight(person.getWeight());
        dbperson.setCalConsumption(person.getCalConsumption());
        dbperson.setCalGoal(person.getCalGoal());


        // Person should use this name to get a Person as rival in database
        dbperson.setRival(person.getRivalName());
    }
}
