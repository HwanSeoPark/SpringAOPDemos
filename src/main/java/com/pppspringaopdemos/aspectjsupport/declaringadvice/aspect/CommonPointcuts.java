package com.pppspringaopdemos.aspectjsupport.declaringadvice.aspect;

import org.aspectj.lang.annotation.Pointcut;

public class CommonPointcuts {

    @Pointcut("within(com.pppspringaopdemos.aspectjsupport.declaringadvice.service..*)")
    public void inDataAccessLayer() {}
}