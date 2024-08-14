package hello.aop.member.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ClassAop {
    // 생성이유 : 클래스에 붙이는 AOP
    // @Target(ElementType.TYPE) : 클래스에 붙이는 에노테이션
    // @Retention(RetentionPolicy.RUNTIME) : 실제 실행할떄까지 애노테이션이 살아있음

}
