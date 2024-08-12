package hello.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Slf4j
@Aspect //어드바이스 + 포인트컷을 모듈화 한 것
public class AspectV1 { // aop 관려해서 모아준다.

    /**
         * 어드바이스 : @Around <-- 부가기능
         * 포인트 컷 :  @Around("값") <-- "값"이 포이트 컷(적용할 위치)
         * 어드바이저 : doLog 메서드(하나의 어드바이스와 하나의 포인트 컷으로 구성)
     * */

    //hello.aop.order 패키지와 하위 패키지
    @Around("execution(* hello.aop.order..*(..))") //..는 hello.aop.order 패키지의 하위패키지
    public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {
        //[log] void hello.aop.order.OrderService.orderItem(String)
        log.info("[log] {}", joinPoint.getSignature()); //join point 시그니처 - 메소드의 정보가 찍힘.
        return joinPoint.proceed();
    }
}
