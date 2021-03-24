package jpabook.jpashop.service;


import jpabook.jpashop.api.MemberApiController;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    /* 회원 가입 */
    @Transactional
    public Long join(Member member) {
        validateDuplicateMember(member);    //중복 회원 검증
        return memberRepository.save(member).getId();
    }

    //회원 전체 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    //회원 단건 조회
    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }


    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다. member : " + findMembers.get(0).getName());
        }
    }

    @Transactional
    public void update(Long id, MemberApiController.UpdateMemberRequest request) {
        Member member = memberRepository.findOne(id);
        member.update(request);
    }
}
