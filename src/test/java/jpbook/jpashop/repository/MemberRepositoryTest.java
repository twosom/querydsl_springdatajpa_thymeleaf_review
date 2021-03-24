package jpbook.jpashop.repository;

import jpbook.jpashop.domain.Address;
import jpbook.jpashop.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    EntityManager em;

    @Test
    void saveMember() throws Exception {
        //given
        Member member = new Member("memberA", new Address("city", "Street", "zipcode"));

        //when
        Long savedId = memberRepository.save(member).getId();
        Member findMember = memberRepository.findById(savedId)
                .orElseThrow(() -> new IllegalArgumentException("없는 사용자입니다. id = " + savedId));

        //then
        Assertions.assertThat(member).isEqualTo(findMember);

    }

    @Test
    void findOne() throws Exception {
        //given
        Member member = new Member("memberA", new Address("city", "Street", "zipcode"));

        //when
        Long savedId = memberRepository.save(member).getId();



        Member findMember = memberRepository.findOne(savedId);
        //then
        Assertions.assertThat(member).isEqualTo(findMember);
        Assertions.assertThat(member.getAddress()).isEqualTo(findMember.getAddress());

    }

}