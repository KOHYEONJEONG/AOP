package hello.aop.internalcall;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CallServiceV0 {

    public void external() {
        log.info("call external");
        //흐름
        // 클라이언트 -> 프록시 호출 -> 어드바이스 호출(external()) -> 실제 external 호출(externaml())-> 실제 internal 호출(internal())
        internal(); //내부 메서드 호출(this.internal())  -- 어드바이스가 호출되지 않는것을 볼 수 있다.

        // 출력
        // aop=void hello.aop.internalcall.CallServiceV0.external()
        // call external
        // call internal  <-- 실제 대상만 호출됐고, 프록시가 호출되지 않았다.

        //어드바이스가 호출되려면? 대안1인 CallServiceV1을 보러가자.
    }

    public void internal() {
        log.info("call internal");
    }
}
