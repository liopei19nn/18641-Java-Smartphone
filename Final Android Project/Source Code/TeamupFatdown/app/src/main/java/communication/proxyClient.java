package communication;

import java.util.ArrayList;

import model.person.DatabasePerson;
import model.person.Friend;
import model.person.Person;

/**
 *
 * @team Three Threads
 * @author Li Pei
 * @AndrewID : lip
 *
 */
public class proxyClient {
    // command to Socket Client
    private static final String OPERATION_ADDONE = "addone";
    private static final String OPERATION_GETONE = "getone";
    private static final String OPERATION_GETALL = "getall";

    // save the Person transfered in with Constructor
    private Person person;

    // used when you get all from database
    private ArrayList<Friend> allusers;

    // constructor to get a person in
    public proxyClient(Person p) {
        this.person = p;

    }

    /*
    * addOneToDB()
    *
    * use thie method to add a person into database
    *
    * The Person is transformed to a DatabasePerson, and
    * a DatabasePerson will be transfered to a string to socket client
    *
    * */
    public String addOneToDB() {

        // person to DBperson
        DatabasePerson dbPerson = new DatabasePerson(person.getUserName(), person.getPassWord());
        proxyTransformPerson.personTodbPerson(person, dbPerson);

        // status to save if the server is not connected
        ArrayList<String> status = new ArrayList<String>();
        SocketClient client = new SocketClient(dbPerson.toString(), OPERATION_ADDONE, status);
        // start this client
        client.start();


        // waiting for the client thread
        while (client.isAlive()) {
            // wait for the communication
        }

        // if status size is not 0
        // it means the status was add a string
        // indicate the server is not running
        if (status.size() != 0) {
            return "No Internet Access";
        }
        return "Success";
    }// End of addonetodb

    /*
    * getOneFromDB()
    *
    * get one person from Database
    * and set all the property to the person
    * transfered in the constructur
    *
    * */
    public String getOneFromDB() {

        //Person to DB person
        DatabasePerson dbPerson = new DatabasePerson(person.getUserName(), person.getPassWord());

        // save internet status here
        ArrayList<String> status = new ArrayList<>();

        // start communication thread
        SocketClient client = new SocketClient(dbPerson, OPERATION_GETONE, status);
        client.start();


        // waiting for the communication thread ends
        while (client.isAlive()) {

        }

        // if status size is not 0
        // it means the status was add a string
        // indicate the server is not running
        if (status.size() != 0) {
            return "No Internet Access";
        }

        // transfer a DB person to person
        proxyTransformPerson.dbPersonToPerson(dbPerson, person);

        // if the user name is not exist
        // socket client will return a Person Username is ""
        if (person.getUserName().equals("")) {
            return "Username not exist";
        }

        return "success";
    }



    /*
    * getOneFromDB()
    *
    * return all the user from DB, if the username is not
    * the same as person from constructor, it is regarded as
    * a friend
    *
    * */

    public String getAllFromDB() {

        // save all users in database
        ArrayList<String> storeUsers = new ArrayList<>();

        // save server status
        ArrayList<String> status = new ArrayList<>();

        // store all friends of the Person
        allusers = new ArrayList<>();

        // run the communication thread
        SocketClient client = new SocketClient(storeUsers, OPERATION_GETALL, status);
        client.start();

        // waiting for the communication thread ends
        while (client.isAlive()) {

        }

        // if status size is not 0
        // it means the status was add a string
        // indicate the server is not running
        if (status.size() != 0) {
            return "No Internet Access";
        }


        //save all users to
        // 1 update the Person you transfer in as device user
        // 2 get all others as friends
        for (String f : storeUsers) {
            DatabasePerson dbPerson = new DatabasePerson();
            dbPerson.generateFromString(f);

            // if the user is user himself
            if (dbPerson.getUserName().equals(person.getUserName())) {
                proxyTransformPerson.dbPersonToPerson(dbPerson, person);
            }

            // if the user is not himself
            else {
                Person friend = new Person();

                proxyTransformPerson.dbPersonToPerson(dbPerson, friend);
                allusers.add(friend);
            }
        }


        // if there is no user in database
        if (allusers.size() == 0) {
            return "No User In Database";
        }


        // set the team for the Person you want to get TEAM
        person.setTeam(allusers);

        return "success";
    }
}
