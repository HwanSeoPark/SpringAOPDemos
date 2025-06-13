package com.pppspringaopdemos.aspectjsupport.declaringadvice;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.ParameterNameDiscoverer;

import com.pppspringaopdemos.aspectjsupport.declaringadvice.aop.ExecutionCountingAspect;
import com.pppspringaopdemos.aspectjsupport.declaringadvice.config.AppConfig;
import com.pppspringaopdemos.aspectjsupport.declaringadvice.dao.AccountDao;
import com.pppspringaopdemos.aspectjsupport.declaringadvice.model.Account;
import com.pppspringaopdemos.aspectjsupport.declaringadvice.model.MyType;
import com.pppspringaopdemos.aspectjsupport.declaringadvice.model.UsageTracked;
import com.pppspringaopdemos.aspectjsupport.declaringadvice.service.AccountService;
import com.pppspringaopdemos.aspectjsupport.declaringadvice.service.MyService;
import com.pppspringaopdemos.aspectjsupport.declaringadvice.service.SampleService;
import com.pppspringaopdemos.aspectjsupport.declaringadvice.service.TransactionalService;

import org.springframework.core.DefaultParameterNameDiscoverer;

public class Main {

    public static void check(ApplicationContext context) {
        // 서비스 클래스 호출
        MyService myService = context.getBean(MyService.class);

        // 여러 스레드에서 메서드를 호출하여 스레드 안전한 카운팅 및 타이밍 측정 확인
        Runnable task = () -> myService.performTask();
        Thread thread1 = new Thread(task);
        Thread thread2 = new Thread(task);

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 최종 실행 횟수 출력
        ExecutionCountingAspect countingAspect = context.getBean(ExecutionCountingAspect.class);
        System.out.println("Total execution count: " + countingAspect.getExecutionCount());

    }

    public static void main(String[] args) throws NoSuchMethodException, SecurityException {
        ApplicationContext context = 
        		new AnnotationConfigApplicationContext(AppConfig.class);

//        check(context);


        SampleService sampleService = 
        		(SampleService) context.getBean(SampleService.class);
        AccountDao accountDao = context.getBean(AccountDao.class);

        AccountService accountService = 
        		context.getBean(AccountService.class);
        TransactionalService transactionalService = 
        		context.getBean(TransactionalService.class);

        Account validAccount = new Account();
        validAccount.setId("123");
        validAccount.setOwnerName("John Doe");
        validAccount.setBalance(1000.0);        
        
        accountDao.updateAccount(validAccount);        

        accountService.updateAccount(validAccount);
        
        Account retrievedAccount = accountService.getAccount("123");

        System.out.println("Account Owner: " + retrievedAccount.getOwnerName());


        transactionalService.performTransactionalOperation();

        UsageTracked usageTracked = (UsageTracked) accountService;
        System.out.println("AccountService usage count: " + usageTracked.getUseCount());
        
        accountService.deleteAccount("123");
        
        Account invalidAccount = new Account();
        invalidAccount.setId("678");
        invalidAccount.setOwnerName("Jane Doe");
        invalidAccount.setBalance(-500.0);
        try {
            accountDao.updateAccount(invalidAccount);
        } catch (IllegalArgumentException e) {
            System.out.println("Exception caught: " + e.getMessage());
        }        

        MyType myTypeInstance = new MyType("ExampleName");

        // 단일 제네릭 파라미터를 사용한 메서드 호출
        sampleService.sampleGenericMethod(myTypeInstance);

        // 제네릭 컬렉션 파라미터를 사용한 메서드 호출
        sampleService.sampleGenericCollectionMethod(Arrays.asList(myTypeInstance));
        
        
        // Method 객체 가져오기
        Method method = 
        		SampleService.class.getMethod("sampleGenericCollectionMethod", Collection.class);

        // ParameterNameDiscoverer 사용
        ParameterNameDiscoverer parameterNameDiscoverer = new DefaultParameterNameDiscoverer();
        String[] parameterNames = parameterNameDiscoverer.getParameterNames(method);

        // 파라미터 이름 출력
        // javac -parameters 설정이 되어야 파라미터 이름을 얻어 올 수 있음.
        if (parameterNames != null) {
            for (String paramName : parameterNames) {
                System.out.println("Parameter name: " + paramName);
            }
        } else {
            System.out.println("No parameter names found.");
        }

        // 메서드 호출
        List<MyType> myTypeList = Arrays.asList(new MyType("Test1"), new MyType("Test2"));
        sampleService.sampleGenericCollectionMethod(myTypeList);
        
        sampleService.sampleGenericMethod(new MyType("Test"), 10, "John");
        sampleService.sampleGenericMethod(new MyType("Example"));      
        sampleService.sampleGenericCollectionMethod(myTypeList);

        sampleService.sampleMethod("Test data");
        
        accountService = context.getBean(AccountService.class);
        accountService.findAccounts("doe");
        
        MyService myService = context.getBean(MyService.class);
        myService.performTask();

    }
}