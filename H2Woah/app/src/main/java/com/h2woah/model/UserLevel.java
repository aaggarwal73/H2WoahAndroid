package com.h2woah.model;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Represents level of power of user within the app
 * Created by Sahaj Bhatt on 9/17/16.
 */
public enum UserLevel {
    NORMAL, WORKER, MANAGER, ADMIN;


    /**
     * Transforms the enum into a list
     * @return  the final list
     */
    public static Collection<UserLevel> toList() {
        List<UserLevel> list = new ArrayList<UserLevel>();

        Collections.addAll(list, values());
        return list;
    }

    /**
     * Changes inputted string to a userlevel enum
     * @param level the string level passed in
     * @return UserLevel the user level created
     */
    public static UserLevel stringToUserLevel(String level) {
        if ((level instanceof String)) {
            switch (level) {
                case "NORMAL":
                    return UserLevel.NORMAL;
                case "WORKER":
                    return UserLevel.WORKER;
                case "MANAGER":
                    return UserLevel.MANAGER;
                case "ADMIN":
                    return UserLevel.ADMIN;
            }
        }
        return null;
    }
    public static String toString(UserLevel level) {
        switch (level) {
            case NORMAL:
                return "NORMAL";
            case WORKER:
                return "WORKER";
            case MANAGER:
                return "MANAGER";
            case ADMIN:
                return "ADMIN";
        }

        return null;
    }
}