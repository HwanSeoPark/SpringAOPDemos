package com.pppspringaopdemos.pointcutapi.service;

public class UserAccountService {
    public void updatePassword(String username) {
        System.out.println(">>> Password updated for " + username);
    }

    public void updateEmail(String username) {
        System.out.println(">>> Email updated for " + username);
    }

    public void login(String username) {
        System.out.println(">>> User " + username + " logged in");
    }

    public void logout(String username) {
        System.out.println(">>> User " + username + " logged out");
    }
}