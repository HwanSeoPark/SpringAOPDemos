package com.pppspringaopdemos.aspectjsupport.combindedpointcuts.pointcuts;

import org.aspectj.lang.annotation.Pointcut;

public class CommonPointcuts {

    // 웹 레이어의 모든 메서드를 매칭하는 포인트컷
    // 포인트컷 표현식에서 web..*의 의미는 다음과 같음:
    //
    // web: 패키지 이름을 의미.
    // ..: 현재 패키지와 그 하위 모든 패키지를 포함한다는 의미. 
	//     즉, web 패키지뿐만 아니라 web 패키지의 하위 패키지들도 모두 포함됨.
    // *: 해당 패키지 안에 있는 모든 클래스와 메서드를 의미함.
	//
    // 따라서, web..*는 web 패키지와 그 하위 모든 패키지에 있는 모든 클래스와 
	//       메서드가 포인트컷의 대상이 된다는 의미.
    // 예를 들어, com.pppspringaopdemos.aspectjsupport.combindedpointcuts.web 패키지와 
	// 그 하위에 있는 모든 클래스의 메서드들이 이 포인트컷에 포함됨.
    @Pointcut("within(com.pppspringaopdemos.aspectjsupport.combindedpointcuts.web..*)")
    public void inWebLayer() {}

    // 서비스 레이어의 모든 메서드를 매칭하는 포인트컷
    @Pointcut("within(com.pppspringaopdemos.aspectjsupport.combindedpointcuts.service..*)")
    public void inServiceLayer() {}

    // DAO 레이어의 모든 메서드를 매칭하는 포인트컷
    @Pointcut("within(com.pppspringaopdemos.aspectjsupport.combindedpointcuts.service.dao..*)")
    public void inDataAccessLayer() {}

    // 서비스 레이어에 있는 비즈니스 메서드
    // execution(...): 메서드 실행 시점에 포인트컷을 적용한다는 의미. 
    //                 이 포인트컷은 특정 메서드가 실행될 때 동작.
    // *: 리턴 타입을 나타냅니다. 여기서 *는 모든 리턴 타입을 의미. 
    //    즉, 어떤 리턴 타입의 메서드든지 포인트컷의 대상이 될 수 있음.
    // com.pppspringaopdemos.aspectjsupport.combindedpointcuts.service.*: 
    //          패키지와 클래스의 경로를 나타냄.
    //          여기서 com.pppspringaopdemos.aspectjsupport.combindedpointcuts.service.*는 
    //          service 패키지 내의 모든 클래스(*가 클래스 이름을 의미함)를 의미.
    // .*: 클래스 내의 모든 메서드를 의미. 첫 번째 *는 클래스 이름을 대체하고, 
    //     두 번째 *는 클래스 내의 모든 메서드를 대체.
    // (..): 메서드의 파라미터를 나타냅니다. (..)는 임의의 파라미터(개수 및 타입 불문)를 의미.
    //
    // 따라서, 이 포인트컷 표현식의 전체 의미는:
    //       com.pppspringaopdemos.aspectjsupport.combindedpointcuts.service 패키지 내의 
    //       모든 클래스의 모든 메서드가 어떤 리턴 타입이든,
    //       어떤 파라미터든 상관없이 실행될 때 이 포인트컷이 적용된다는 것.
    @Pointcut("execution(* com.pppspringaopdemos.aspectjsupport.combindedpointcuts.service.*.*(..))")
    public void businessService() {}

    // DAO 레이어에 있는 데이터 액세스 메서드
    @Pointcut("execution(* com.pppspringaopdemos.aspectjsupport.combindedpointcuts.service.dao.*.*(..))")
    public void dataAccessOperation() {}
}