package hello.aop.exam.aop;

import hello.aop.exam.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Slf4j
@Aspect
public class RetryAspect {

    //@Around 어드바이스를 사용하는 이유는
    //재시도 할 때 언제 조인 포인트에 proceed를 호출할지를 지정할 수 있기 때문이다.
    @Around("@annotation(retry)")
    public Object doRetry(ProceedingJoinPoint joinPoint, Retry retry) throws Throwable {  //Retry retry로 해주면 Retry의 필드값을 꺼낼 수 있음.
        log.info("[retry] {} retry={}", joinPoint.getSignature(), retry);

        int maxRetry = retry.value();//4번까지 재시도
        
        Exception exceptionHolder = null;//만약 실패할경우 마지막에 터뜨릴 예외를 저장할 공간

        for (int retryCount = 1; retryCount <= maxRetry; retryCount++) {
            try {
                log.info("[retry] try count={}/{}", retryCount, maxRetry);
               // return joinPoint.proceed();

                //⏬Retry AOP가 for문을 돌면서 매번 joinPoint.proceed()로 save()를 다시 호출한다."
                Object result = joinPoint.proceed();

                log.info("[retry] Response SUCCESS");

                return result;
            } catch (Exception e) {
                //ExamService 로직에 5번째 예외가 발생하게 되는 로직이 있음
                //[retry] try count=1/4  <-- 정상응답 x
                //[retry] try count=2/4  <-- 재시도해서 정상 응답되는걸 확인함.

                exceptionHolder = e; //터진 예외를 가지고 있다가(리턴을 시키지 않고) 재시도 3번이후에도 터지면 30라인이 실행된다.
            }
        }
        throw exceptionHolder;
    }
}
