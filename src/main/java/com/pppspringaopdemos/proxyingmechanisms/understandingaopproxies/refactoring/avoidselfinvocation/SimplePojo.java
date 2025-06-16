package com.pppspringaopdemos.proxyingmechanisms.understandingaopproxies.refactoring.avoidselfinvocation;


public class SimplePojo implements Pojo {

    private final BarDelegate delegate;

    public SimplePojo(BarDelegate delegate) {
        this.delegate = delegate;
    }

    @Override
    public void foo() {
        System.out.println("[SimplePojo] foo() 호출됨");
        delegate.bar();  // ✅ 프록시를 통해 호출되도록 위임
    }
  

}
