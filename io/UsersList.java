package io;

import java.io.Serializable;
import java.util.ArrayList;

public class UsersList implements Serializable {

    ArrayList<User> userslist = new ArrayList<User>();

    public void setUserslist(ArrayList<User> userslist) {
        this.userslist = userslist ;
    }

    public ArrayList<User> getUserslist() {
        return userslist ;
    }

    public void addToList(User user) {
        userslist.add(user);
    }

    public User getSpecificUser(String username) {
        for(User user : userslist) {
            if (user.getUsername().equals(username)){
                return user ;
            }
        }
        return null ;
    }
}
