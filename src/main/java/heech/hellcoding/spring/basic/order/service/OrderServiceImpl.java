package heech.hellcoding.spring.basic.order.service;

import heech.hellcoding.spring.basic.annotation.MainDiscountPolicy;
import heech.hellcoding.spring.basic.discount.policy.DiscountPolicy;
import heech.hellcoding.spring.basic.discount.policy.FixDiscountPolicy;
import heech.hellcoding.spring.basic.member.entity.Member;
import heech.hellcoding.spring.basic.order.entity.Order;
import heech.hellcoding.spring.basic.member.repository.MemberRepository;
import heech.hellcoding.spring.basic.member.repository.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository;
    //private final DiscountPolicy discountPolicy;
    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
