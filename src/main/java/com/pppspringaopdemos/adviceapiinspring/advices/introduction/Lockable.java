package com.pppspringaopdemos.adviceapiinspring.advices.introduction;

public interface Lockable {  
    void lock();  
    void unlock();  
    boolean locked();  
}