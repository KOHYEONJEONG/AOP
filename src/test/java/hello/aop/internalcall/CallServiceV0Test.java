package hello.aop.internalcall;

import hello.aop.internalcall.aop.CallLogAspect;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@Import(CallLogAspect.class)
@SpringBootTest //스프링 컨테이너를 실행하기 위해서 넣어줘야 함.
class CallServiceV0Test {

    //CallServiceV0는 aop 대상이기에 프록시한테 먹힌다.
    @Autowired CallServiceV0 callServiceV0;//callServiceV0는 프록시

    @Test
    void external() {
        callServiceV0.external();
    }

    @Test
    void internal() {
        callServiceV0.internal();
    }
}