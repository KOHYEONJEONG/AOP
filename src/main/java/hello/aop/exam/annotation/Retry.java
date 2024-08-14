package hello.aop.exam.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Retry {
    // 재시도 AOP
    // @Retry 애노테이션이 있으면 예외가 발생했을 때 다시 시도해서 문제를 복구
    int value() default 3;//3번까지 재시도

    //RetryAspect.class
}
