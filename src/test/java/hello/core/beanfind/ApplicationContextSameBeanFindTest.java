package hello.core.beanfind;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepositoty;
import hello.core.member.MemoryMemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import
        org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.Map;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
class ApplicationContextSameBeanFindTest {
    AnnotationConfigApplicationContext ac = new
            AnnotationConfigApplicationContext(SameBeanConfig.class);
    @Test
    @DisplayName("타입으로 조회시 같은 타입이 둘 이상 있으면, 중복 오류가 발생한다")
    void findBeanByTypeDuplicate() {
        //MemberRepository bean = ac.getBean(MemberRepository.class);
        assertThrows(NoUniqueBeanDefinitionException.class, () ->
                ac.getBean(MemberRepositoty.class));
    }

    @Test
    @DisplayName("타입으로 조회시 같은 타입이 둘 이상 있으면, 빈 이름을 지정하면 된다")
    void findBeanByName() {
        MemberRepositoty memberRepository = ac.getBean("memberRepository1",
                MemberRepositoty.class);
        assertThat(memberRepository).isInstanceOf(MemberRepositoty.class);
    }
    @Test
    @DisplayName("특정 타입을 모두 조회하기")
    void findAllBeanByType() {
        Map<String, MemberRepositoty> beansOfType =
                ac.getBeansOfType(MemberRepositoty.class);
        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key + " value = " +
                    beansOfType.get(key));
        }
        System.out.println("beansOfType = " + beansOfType);
        assertThat(beansOfType.size()).isEqualTo(2);
    }
    @Configuration
    static class SameBeanConfig {
        @Bean
        public MemberRepositoty memberRepository1() {
            return new MemoryMemberRepository();
        }
        @Bean
        public MemberRepositoty memberRepository2() {
            return new MemoryMemberRepository();
        }
    }

    @Test
    @DisplayName("부모 타입으로 조회시, 자식이 둘 이상 있으면, 중복 오류가 발생한다")
    void findBeanByParentTypeDuplicate() {
        //DiscountPolicy bean = ac.getBean(DiscountPolicy.class);
        assertThrows(NoUniqueBeanDefinitionException.class, () ->
                ac.getBean(DiscountPolicy.class));
    }

    @Test
    @DisplayName("특정 하위 타입으로 조회")
    void findBeanBySubType() {
        RateDiscountPolicy bean = ac.getBean(RateDiscountPolicy.class);
        assertThat(bean).isInstanceOf(RateDiscountPolicy.class);
    }
}