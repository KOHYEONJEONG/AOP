package hello.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Slf4j
@Aspect
public class AspectV3 { //어드바이스 추가

    /**
     * AOP 적용 전
     * 클라이언트 -> orderService.orderItem() -> orderRepository.save()
     * --------------------
     * AOP 적용 후  (+ orderService에는 doLod() -> doTransaction() 두가지 어드바이스가 적용되고)
     * 클라이언트 -> [doLog() -> doTransaction()] -> orderService.orderItem()
     *
     * orderRepository에는 doLog() 하나의 어드바이스만 적용된 것을 확인할 수 있다.
     * dooLog() -> orderRepository.save()
     * */

    //hello.aop.order 패키지와 하위 패키지
    @Pointcut("execution(* hello.aop.order..*(..))")
    private void allOrder(){} //pointcut signature

    //(AspectV2와 다른점) 클래스 이름 패턴이 '*Service'
    @Pointcut("execution(* *..*Service.*(..))")
    private void allService(){}

    @Around("allOrder()")
    public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("[log] {}", joinPoint.getSignature()); //join point 시그니처
        return joinPoint.proceed();
    }

    //어드바이저(포인트컷(적용위치) + 어드바이스(로직))
    //hello.aop.order 패키지와 하위 패키지 이면서 클래스 이름 패턴이 *Service
    @Around("allOrder() && allService()")//전 + 후 적용
    public Object doTransaction(ProceedingJoinPoint joinPoint) throws Throwable {

        try {
            log.info("[트랜잭션 시작] {}", joinPoint.getSignature());//함수 정보
            Object result = joinPoint.proceed(); //실제 타켓 호출
            log.info("[트랜잭션 커밋] {}", joinPoint.getSignature());
            return result;

        } catch (Exception e) {
            //예외 상황에 트랜잭션 커밋 대신에 트랜잭션 롤백이 호출되는 것을 확인할 수 있다.
            log.info("[트랜잭션 롤백] {}", joinPoint.getSignature());
            throw e;

        } finally {
            //무조건 호출됨.(예외가 터져도)
            log.info("[리소스 릴리즈] {}", joinPoint.getSignature());
        }
    }

}
