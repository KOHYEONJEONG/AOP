package hello.aop.internalcall;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CallServiceV1 {

    private CallServiceV1 callServiceV1;

    /**
     * 생성자에 주입 시에는 오류가 난다
     * - 생성자 주입은 순환 사이클을 만들기 때문에 실패한다.
     * - 자기자신이 만들어지지 않은 상태에서 의존주입을 받을 수 없기 떄문이다.
     *
     * 대안으로는
     * setter를 통해 자기자신을 주입받게 하면된다.
     *
     * */
    @Autowired
    public void setCallServiceV1(CallServiceV1 callServiceV1) {
        this.callServiceV1 = callServiceV1;
    }

    public void external() {
        log.info("call external");
        //흐름
        // 클라이언트 -> 프록시 호출 -> 어드바이스 호출(external()) -> 실제 external 호출(externaml()) -> 어드바이스 호출(internal()) -> 실제 internal 호출
        callServiceV1.internal(); //외부 메서드 호출(의존관계 주입받은 프록시가 호출된 후 실제 대상이 호출된다.)
    }

    public void internal() {
        log.info("call internal");
    }
}
