package communication;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import model.person.DatabasePerson;

/**
 *
 * @team Three Threads
 * @author Li Pei
 * @AndrewID : lip
 *
 */
public class SocketClient extends Thread implements SocketClientConstants {

    private Socket clientSocket;// client socket

    ObjectInputStream objectInputStream = null; // for all input
    ObjectOutputStream objectOutputStream = null; // for all output

    // save all users as string in this ArrayList
    ArrayList<String> allusers;
    // save the option string
    String option;
    // save the dbperson from constructor
    DatabasePerson person;
    // save the person you want to add indb
    String addoneperson;
    // save the status of server running
    ArrayList<String> statusStore;

    //ADD A PERSON INTO DATABASE
    public SocketClient(String addone, String opt, ArrayList<String> status) {
        this.addoneperson = addone;
        this.option = opt;
        this.statusStore = status;
    }


    //GET ALL PERSON FROM DATABASE
    public SocketClient(ArrayList<String> a, String opt, ArrayList<String> status) {
        this.allusers = a;
        this.option = opt;
        this.statusStore = status;
    }

    //GET ONE PERSON FROM DATABASE

    public SocketClient(DatabasePerson p, String opt, ArrayList<String> status) {
        this.person = p;
        this.option = opt;
        this.statusStore = status;
    }


    // run
    public void run() {
        if (openConnection()) {
            handleSession();
            closeSession();
        } else {
            statusStore.add("Open Error");
//            System.out.println("Connection Not Open !!!!!!!!!!!!!!!!");
        }
    }

    /*
     * openConnection()
     *
     * open input and output stream and client socket
     *
     * */
    private boolean openConnection() {
        try {
            clientSocket = new Socket(strHost, iPort); // connected to server
        } catch (Exception socketError) {

            socketError.printStackTrace();
            return false;
        }
        try {
            // open input and output stream
            objectInputStream = new ObjectInputStream(
                    clientSocket.getInputStream());

            objectOutputStream = new ObjectOutputStream(
                    clientSocket.getOutputStream());
        } catch (Exception e) {

            System.out
                    .println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!Unable to obtain stream to/from " + strHost);
        }
        return true;
    }


    private void handleSession() {
        String readin;
        try {
            // 0.0 read in the start
            readin = (String) objectInputStream.readObject();
            System.out.println(readin);


            if (option.equals("getall")) {
                // 0.1 write option to server
                objectOutputStream.writeObject(option);


                // 1.0 read all database person back
                ArrayList<String> storedp = (ArrayList) objectInputStream.readObject();

                // if there is no user in database, this ArrayList size is 0
                synchronized (allusers) {
                    for (String u : storedp) {
                        allusers.add(u);
                    }
                }

            }// end of get all

            else if (option.equals("getone")) {
                // 0.1 write option to server
                objectOutputStream.writeObject(option);

                synchronized (person) {
                    // 2.0 write a user name to it
                    String username = person.getUserName();
                    objectOutputStream.writeObject(username);

                    // 2.1 read a user in
                    // if the user did not exist in Database
                    // this string will be ""
                    String user = (String) objectInputStream.readObject();
                    if (user.equals("")) {
                        person.setUserName("");
                    } else {
                        person.generateFromString(user);
                    }
                }

            }// end of get one

            else if (option.equals("addone")) {
                // 0.1 write option to server
                objectOutputStream.writeObject(option);
                objectOutputStream.flush();


                //3.1 send the person to add to server
                objectOutputStream.writeObject(addoneperson);
                objectOutputStream.flush();


            }


            // 0.2 wirte finish to server to end a dialog thread
            String out = "finish";
            objectOutputStream.writeObject(out);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void closeSession() {
        try {
            clientSocket.close();
            objectOutputStream.close();
            objectInputStream.close();
            System.out.println("closed!");
        } catch (Exception e) {
            System.err.println("Error closing socket to " + strHost);
        }
    }
}// class SocketClient