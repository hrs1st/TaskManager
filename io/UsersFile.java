package io;

import java.io.*;
import java.util.ArrayList;

public class UsersFile implements Serializable {

    public static void writeToUsersFile(ArrayList<User> users) {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("usersfile.dat"));
            objectOutputStream.writeObject(users);
            objectOutputStream.close();
        } catch (IOException exception){exception.printStackTrace();}
    }

    public static ArrayList<User> readFromUsersFile() {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("usersfile.dat"));
            ArrayList<User> users = (ArrayList<User>) objectInputStream.readObject() ;
            return users;
        } catch (IOException | ClassNotFoundException exception) {
            exception.printStackTrace();
            return null;
        }
    }

}
