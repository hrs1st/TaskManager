package io ;

import storage.NormalTask;

import java.io.*;
import java.util.ArrayList;

public class FileManager implements Serializable {

    private User user ;

    public FileManager(User user) {
        this.user = user ;
    }

    public User getUser() {
        return user ;
    }

    public void writeToFile(ArrayList<NormalTask> tasks) {
        String fileAddress = user.getFileAddress();
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(fileAddress));
            objectOutputStream.writeObject(tasks);
            objectOutputStream.close();
        } catch (IOException exeption){exeption.printStackTrace();}
    }

    public ArrayList<NormalTask> readFromFile() {
        String fileAddress = user.getFileAddress();
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(fileAddress));
            ArrayList<NormalTask> tasks = (ArrayList<NormalTask>) objectInputStream.readObject() ;
            return tasks;

        } catch (IOException | ClassNotFoundException exception) {
            exception.printStackTrace();
            return null;
        }
    }

}