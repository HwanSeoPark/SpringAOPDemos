package com.pppspringaopdemos.adviceapiinspring.advices.introduction;

@SuppressWarnings("serial")
public class LockedException extends RuntimeException {
    public LockedException() {
        super("Object is locked. No modifications are allowed.");
    }
}