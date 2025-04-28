package hello.aop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    
    //AOP는 사실 @Component로 등록 하는것 보다
    // 이렇게 하나의 파일에서 등록하는 게 보기 편하다.

//    ⏬예시
//    @Bean
//    public TimeTraceAop timeTraceAop(){
//        return new TimeTraceAop();
//    }
}
