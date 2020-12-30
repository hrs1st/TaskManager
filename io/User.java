package io;

import java.io.Serializable;

public class User implements Serializable {

    private String username;
    private String password;
    private String fileAddress;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
        fileAddress = username + ".dat" ;
    }

    public String getFileAddress() {
        return fileAddress ;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}