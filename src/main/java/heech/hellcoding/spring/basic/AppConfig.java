package heech.hellcoding.spring.basic;

import heech.hellcoding.spring.basic.discount.policy.DiscountPolicy;
import heech.hellcoding.spring.basic.discount.policy.FixDiscountPolicy;
import heech.hellcoding.spring.basic.discount.policy.RateDiscountPolicy;
import heech.hellcoding.spring.basic.member.repository.MemberRepository;
import heech.hellcoding.spring.basic.member.repository.MemoryMemberRepository;
import heech.hellcoding.spring.basic.member.service.MemberService;
import heech.hellcoding.spring.basic.member.service.MemberServiceImpl;
import heech.hellcoding.spring.basic.order.service.OrderService;
import heech.hellcoding.spring.basic.order.service.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
        //return new FixDiscountPolicy();
    }

}
