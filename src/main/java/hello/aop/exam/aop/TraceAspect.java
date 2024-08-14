package hello.aop.exam.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Slf4j
@Aspect
public class TraceAspect {

    /**
     * hello.aop.exam.annotation.Trace는 직접 만든 어노테이션이다.
     * @annotation(hello.aop.exam.annotation.Trace) 포인트컷을 사용해서
     * @Trace가 붙은 메서드에 어드바이스를 적용한다
     * */
    @Before("@annotation(hello.aop.exam.annotation.Trace)")
    public void doTrace(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();//넘어가는 인수 정보들
        log.info("[trace] {} args={}", joinPoint.getSignature(), args);
        //[trace] void hello.aop.exam.ExamService.request(String) args=[data2]
    }
}
