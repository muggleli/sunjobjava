package com.pojo;

public class Userinfo {
    static  String username;
    static  String password;

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        Userinfo.username = username;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        Userinfo.password = password;
    }
}
