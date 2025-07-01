package com.pppspringaopdemos.pointcutapi.service;

public class NotificationService {

    public void setRecipient(String email) {
        System.out.println(">>> Setting recipient: " + email);
    }

    public void absquatulate() {
        System.out.println(">>> Notification system shutting down.");
    }

    public void sendNotification(String message) {
        System.out.println(">>> Sending notification: " + message);
    }
}