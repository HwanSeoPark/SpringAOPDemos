package com.pppspringaopdemos.pointcutapi;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.pppspringaopdemos.pointcutapi.config.AppConfig;
import com.pppspringaopdemos.pointcutapi.config.AppConfigForEnableAspectJAutoProxy;
import com.pppspringaopdemos.pointcutapi.service.AnotherService;
import com.pppspringaopdemos.pointcutapi.service.MyService;
import com.pppspringaopdemos.pointcutapi.service.NotificationService;
import com.pppspringaopdemos.pointcutapi.service.OrderService;
import com.pppspringaopdemos.pointcutapi.service.ProductService;
import com.pppspringaopdemos.pointcutapi.service.SimpleService;
import com.pppspringaopdemos.pointcutapi.service.TaskCaller;
import com.pppspringaopdemos.pointcutapi.service.UserAccountService;

public class SpringAOPExample {
	
	public static void execAppConfig() {
		AnnotationConfigApplicationContext context = 
				new AnnotationConfigApplicationContext(AppConfig.class);

        MyService myService = (MyService) context.getBean("myServiceProxy"); // 프록시 팩토리의 빈 전달
        AnotherService anotherService = (AnotherService) context.getBean("anotherServiceProxy");

        TaskCaller caller = context.getBean(TaskCaller.class);
        SimpleService service = context.getBean("simpleServiceProxy", SimpleService.class);

        OrderService orderService = (OrderService) context.getBean("orderServiceProxy");

        UserAccountService userAccountService = (UserAccountService) context.getBean("userAccountServiceProxy");

        ProductService productService = (ProductService) context.getBean("productServiceProxy");

        NotificationService notificationService = (NotificationService) context.getBean("notificationServiceProxy");

        // 메서드 실행 테스트
        myService.myMethod();              // AspectJ 포인트컷 및 커스텀 포인트컷 모두 적용
        myService.anotherMethod("test");   // 커스텀 포인트컷만 적용
        
        anotherService.differentMethod(123); // 커스텀 포인트컷만 적용

//        try {
//            myService.methodWithException(); // 예외 처리 어드바이스가 실행
//        } catch (Exception e) {
//            System.out.println("Exception handled in main");
//        }
        
        
        System.out.println("\n[1] TaskCaller.callTask()");
        caller.callTask();  // LoggingAdvice 동작

        System.out.println("\n[2] 직접 호출");
        service.performTask();  // LoggingAdvice 동작 안함
        

        System.out.println("\n--- createOrder() ---");
        String orderId = orderService.createOrder("pppspringaopdemos", "P1234", 2);

        System.out.println("\n--- cancelOrder() ---");
        orderService.cancelOrder(orderId);

        System.out.println("\n--- getOrderStatus() ---");
        orderService.getOrderStatus(orderId);  // 어드바이스 미적용 (어노테이션 없음)

        System.out.println("\n--- updatePassword ---");
        userAccountService.updatePassword("pppspringaopdemos");

        System.out.println("\n--- login ---");
        userAccountService.login("pppspringaopdemos");

        System.out.println("\n--- updateEmail ---");
        userAccountService.updateEmail("pppspringaopdemos");

        System.out.println("\n--- logout ---");
        userAccountService.logout("pppspringaopdemos");
        
        System.out.println("\n--- getProductById ---");
        productService.getProductById("A100");

        System.out.println("\n--- getAllProducts ---");
        productService.getAllProducts();

        System.out.println("\n--- updateStock ---");
        productService.updateStock("A100", 10);  // 이 메서드는 "get"으로 시작하지 않기 때문에 어드바이스 미적용

        System.out.println("\n--- setRecipient ---");
        notificationService.setRecipient("user@example.com");

        System.out.println("\n--- absquatulate ---");
        notificationService.absquatulate();

        System.out.println("\n--- sendNotification ---");
        notificationService.sendNotification("System maintenance at midnight.");
        context.close(); 
	}
	
	public static void execAppConfigForEnableAspectJAutoProxy() {
        AnnotationConfigApplicationContext context = 
        		new AnnotationConfigApplicationContext(AppConfigForEnableAspectJAutoProxy.class);

        // MyService 빈을 가져와서 메서드를 호출합니다.
        MyService myService = context.getBean(MyService.class);
        myService.myMethod();  // LoggingAdvice와 AspectJ 포인트컷이 적용됩니다.
        
        try {
            myService.methodWithException();  // ExceptionHandlingAdvice가 적용됩니다.
        } catch (Exception e) {
            System.out.println("Exception handled in main");
        }

        // AnotherService 빈을 가져와서 메서드를 호출합니다.
        AnotherService anotherService = context.getBean(AnotherService.class);
        anotherService.differentMethod(123);  // CustomPointcut이 적용됩니다.
        anotherService.performOperation("add");

        // 스프링 컨텍스트를 종료하여 리소스를 해제합니다.
        context.close();	
		
	}

	
	public static void main(String[] args) {
		execAppConfig();
		
//		execAppConfigForEnableAspectJAutoProxy();
    }
}