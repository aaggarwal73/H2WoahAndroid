package com.h2woah.model;



/**
 * Represents a user for the app
 * Created by Sahaj Bhatt on 9/17/16.
 */
public class User {

    /** Can only get the username, setting username only happens during user
     * creation */
    private String email = "";

    /** Can only get the password, setting password only happens during user
     * creation */
    private String password = "";
    private String address = "";


    /** Authority level of user
     * Can only get the user level, setting level only happens during user
     * creation */
    private UserLevel userLevel;

    /**
     * gets the email
     * @return String containing the email
     */
    public String getEmail() { return email; }

    /**
     * gets the password
     * @return String containing the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Gets the user level
     * @return UserLevel containing the user's level
     */
    public UserLevel getUserLevel() { return userLevel; }

    /**
     * gets the address
     * @return String containing the address
     */
    public String getAddress() {
        return address;
    }


    /**
     * Main constructor
     * @param uname     Username
     * @param pass      Password
     * @param level     Authority level
     * @param address   Address
     */
    public User(String uname, String pass, UserLevel level, String address) {
        email = uname;
        password= pass;
        userLevel = level;
        this.address = address;
    }

    @Override
    public String toString() {
        String level = UserLevel.toString(userLevel);
        return email + ":" + password + ":" + level +
                ":" + address + "\n";
    }


}
