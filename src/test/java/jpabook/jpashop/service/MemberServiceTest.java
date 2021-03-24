package jpabook.jpashop.service;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {


    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    EntityManager em;

    @Test
    void 회원가입() throws Exception {
        //given
        Member member = new Member("memberA", new Address("인천", "부평", "143"));

        //when
        Long savedId = memberService.join(member);

        em.flush();

        //then
        Member findMember = memberService.findOne(savedId);
        Assertions.assertThat(member).isEqualTo(findMember);

    }


    @Test
    void 중복_회원_예외() throws Exception {
        //given
        Member member1 = new Member("memberA", new Address("인천", "부평", "143"));

        Member member2 = new Member("memberA", new Address("인천", "부평", "143"));

        //when
        memberService.join(member1);
        assertThrows(IllegalStateException.class, () -> {
            memberService.join(member2);
        });

        //then


    }

}