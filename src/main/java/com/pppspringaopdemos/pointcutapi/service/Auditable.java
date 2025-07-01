package com.pppspringaopdemos.pointcutapi.service;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD) // 타겟은 메서드만 적용가능
@Retention(RetentionPolicy.RUNTIME)
public @interface Auditable { // 감시하다
    String value() default "";
}
