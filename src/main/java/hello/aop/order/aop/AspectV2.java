package hello.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Slf4j
@Aspect
public class AspectV2 {
    //포인트컷 쪼개기
    /**
     * AspectV1 과 다른 점은 분리이다.
     * - 이렇게 분리하면 하나의 포인트컷 표현식을 여러 어드바이스에서 함께 사용할 수 있다.
     *   - allOrder()이 포인트컷 시그니처이다.(이름 그대로 주문과 관련된 모든 기능을 대상으로 하는 포인트 컷이다.)
     *
     * */

    //hello.aop.order 패키지와 하위 패키지
    @Pointcut("execution(* hello.aop.order..*(..))")
    private void allOrder(){} //pointcut signature

    @Around("allOrder()")
    public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("[log] {}", joinPoint.getSignature()); //join point 시그니처
        return joinPoint.proceed();
    }

    //Pointcut
    //pointCut에 포인트컷 표현식을 사용한다.
    //메서드 이름과 파라미터를 합쳐서 포인트컷 시크니처라 한다.


}
