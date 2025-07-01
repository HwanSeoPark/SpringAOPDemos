package com.pppspringaopdemos.adviceapiinspring.advices.introduction;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = 
        		new AnnotationConfigApplicationContext(AppConfig.class);

        MyTargetClass myObject = context.getBean(MyTargetClass.class);
        Lockable lockable = (Lockable) myObject; // 이 대입[할당]이 에러 없이 가능하려면
        										 // MytargetClass가
        										 // Lockable 인터페이스를 구현해야 함!
        										 // 근데 MyTargetClass는 Lockabe인터페이스를 구현하지 않았는데?
        										 // 가능한 이유는 
        									     // Introduction advice 기능을 통해 런타임때 
        										 // 인터페이스를 구현함
        
        System.out.println("Initially locked: " + lockable.locked());

        lockable.lock();
        System.out.println("Locked: " + lockable.locked());

        try {
            myObject.setName("New Name");
        } catch (LockedException ex) {
            System.out.println(ex.getMessage());
        }

        lockable.unlock();
        System.out.println("Unlocked: " + lockable.locked());

        myObject.setName("New Name");
        System.out.println("Name after unlocking: " + myObject.getName());
    }
}
