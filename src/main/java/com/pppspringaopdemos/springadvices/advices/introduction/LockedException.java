package com.pppspringaopdemos.springadvices.advices.introduction;

public class LockedException extends RuntimeException {
    public LockedException() {
        super("Object is locked. No modifications are allowed.");
    }
}