package com.pppspringaopdemos.proxyingmechanisms.understandingaopproxies.refactoring.avoidselfinvocation;


public class BarDelegateImpl implements BarDelegate {
    @Override
    public void bar() {
        System.out.println("[BarDelegateImpl] bar() 호출됨 (Advice 적용 대상)");
    }
}
