package hello.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

@Slf4j
@Aspect
public class AspectV6Advice {
    /**
     * 5.2.7버전 부터 동일한 @Aspect 안에서 동일한 조인포인트의 우선순위를 정했다.
     * 실행순서 : @Around, @Before, @After,@AfterReturning, @AfterThrowing
     * 어드바이스가 적용되는 순서는 이렇게 적용되지만, 호출 순서와 리턴 순서는 반대라는 점
     *
     * 💥주의
     * @Around : joinPoint.proceed(); 을 무조건 해야한다.
     * @Before : 스프링이 다음 순서를 알고 있기 때문에 joinPoint.proceed(); 을 안해도 된다.
     * */

    //@Around : 메서드 호출 전/후에 수행, 조인 포인트 실행 여부 선택, 반환 값 변환, 예외 변환 등이 가능 (가장 강력한 어드바이스)
    @Around("hello.aop.order.aop.Pointcuts.orderAndService()")
    public Object doTransaction(ProceedingJoinPoint joinPoint) throws Throwable {

        try {
            //@Before
            log.info("[트랜잭션 시작] {}", joinPoint.getSignature());//타켓에 대한 정보, 조언되는 메서드에 대한 설명을 반환
            Object result = joinPoint.proceed();//💥@Around는 개발자가 직접 타겟의 항상 실행을 해줘야한다. / @Around에서만 proceed()가 가능하다.
            //@AfterReturning
            log.info("[트랜잭션 커밋] {}", joinPoint.getSignature());
            return result;

        } catch (Exception e) {
            //@AfterThrowing
            log.info("[트랜잭션 롤백] {}", joinPoint.getSignature());
            throw e;

        } finally {
            //@After
            log.info("[리소스 릴리즈] {}", joinPoint.getSignature());
        }
    }

    //@Before : 조인 포인트 실행 이전에 실행
    @Before("hello.aop.order.aop.Pointcuts.orderAndService()")
    public void doBefore(JoinPoint joinPoint) {
        log.info("[before] {}", joinPoint.getSignature());
    }

    //@After Retuning : 조인 포인트가 정상 완료 후 수행
    @AfterReturning(value = "hello.aop.order.aop.Pointcuts.orderAndService()", returning = "result")
    public void doReturn(JoinPoint joinPoint, Object result) { //returning = "result"과 Objects result 명칭을 같게 해줘야 리턴값을 받아옴.
        log.info("[return] {} return={}", joinPoint.getSignature(), result);
        //💥주의! return result; (void)를 하지 않기에 반환값을 변경할 수 없음.
    }

    //@After Throwing : 메서드가 예외를 던지는 경우 수행
    @AfterThrowing(value = "hello.aop.order.aop.Pointcuts.orderAndService()", throwing = "ex")
    public void doThrowing(JoinPoint joinPoint, Exception ex) {
        log.info("[ex] {} message={}", ex);
    }

    //@After : 조인 포인트가 정상 또는 예외에 관계없이 실행(finally)
    @After(value = "hello.aop.order.aop.Pointcuts.orderAndService()")
    public void doAfter(JoinPoint joinPoint) {
        log.info("[after] {}", joinPoint.getSignature());
    }

}
