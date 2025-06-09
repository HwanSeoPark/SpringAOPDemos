package com.pppspringaopdemos.aspectjsupport.declaringadvice.model;

public interface UsageTracked {
    void incrementUseCount();
    int getUseCount();
}
