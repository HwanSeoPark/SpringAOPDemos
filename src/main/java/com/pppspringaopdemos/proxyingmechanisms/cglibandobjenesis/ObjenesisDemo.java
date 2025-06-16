package com.pppspringaopdemos.proxyingmechanisms.cglibandobjenesis;

import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class ObjenesisDemo {
    public static void main(String[] args) {
        Objenesis objenesis = new ObjenesisStd();
        Target target = objenesis.newInstance(Target.class); // 생성자 호출 안 됨
        System.out.println("✅ 생성자 호출 없이 인스턴스 생성됨: " + target);
    }
}
