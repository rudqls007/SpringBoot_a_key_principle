package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberserviceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;


// 스프링 컨테이너에서 스프링 빈을 찾는 가장 기본적인 조회 방법
//ac.getBean(빈이름, 타입)
//ac.getBean(타입)
//조회 대상 스프링 빈이 없으면 예외 발생
//NoSuchBeanDefinitionException: No bean named 'xxxxx' available
class ApplicationContextBasicFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanByName(){
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        assertThat(memberService).isInstanceOf(MemberserviceImpl.class);
    }


    @Test
    @DisplayName("이름없이 타입으로만 조회")
    void findBeanByType(){
        MemberService memberService = ac.getBean(MemberService.class);
        assertThat(memberService).isInstanceOf(MemberserviceImpl.class);
    }

    // 역할과 구현으로 나뉘는데 역할이 중요함.
    @Test
    @DisplayName("구체 타입으로 조회")
    void findBeanByName2(){
        MemberserviceImpl memberService = ac.getBean("memberService", MemberserviceImpl.class);
        assertThat(memberService).isInstanceOf(MemberserviceImpl.class);
    }

    @Test
    @DisplayName(" 빈이름으로 조회X")
    void findBeanByNameX(){
//        ac.getBean("xxxxx", MemberService.class);
        assertThrows(NoSuchBeanDefinitionException.class,
                () -> ac.getBean("xxxxx", MemberService.class));
    }
}
