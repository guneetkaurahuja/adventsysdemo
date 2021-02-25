package com.adventsys.demo.model;

public class UserLogin {
    String emailid;

    //TODO this should be encrypted and not floated around in a string value
    String password;

    public UserLogin() {
    }

    public UserLogin(String emailid, String password) {
        this.emailid = emailid;
        this.password = password;
    }

    public String getEmailid() {
        return emailid;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
