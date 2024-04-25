package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class SatefulServiceTest {
    @Test
    void statefulServiceSingleton(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        SatefulService statefulService1 = ac.getBean(SatefulService.class);
        SatefulService statefulService2 = ac.getBean(SatefulService.class);

        //ThreadA : A사용자 10000원 주문
        int price1 = statefulService1.order("userA", 10000);
        //ThreadB : B사용자 20000원 주문
        int price2 = statefulService2.order("userB", 20000);

        //ThreadA : 사용자A 주문 금액 조회
//        int price = statefulService1.getPrice();
        System.out.println("price1 = " + price1);

//        Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);
    }

    static class TestConfig{
        @Bean
        public SatefulService satefulService(){
            return new SatefulService();
        }
    }
}