package jpbook.jpashop.repository;


import com.querydsl.jpa.impl.JPAQueryFactory;
import jpbook.jpashop.controller.dto.MemberListDto;
import jpbook.jpashop.controller.dto.QMemberListDto;
import jpbook.jpashop.domain.QMember;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static jpbook.jpashop.domain.QMember.*;

@Repository
public class MemberQueryRepository {

    private final JPAQueryFactory queryFactory;

    public MemberQueryRepository(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }


    public List<MemberListDto> findAll() {
        return queryFactory
                .select(new QMemberListDto(
                        member.id,
                        member.name,
                        member.address.city,
                        member.address.street,
                        member.address.zipcode))
                .from(member)
                .fetch();
    }

}
