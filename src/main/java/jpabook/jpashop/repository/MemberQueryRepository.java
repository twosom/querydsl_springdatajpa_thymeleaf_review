package jpabook.jpashop.repository;


import com.querydsl.jpa.impl.JPAQueryFactory;
import jpabook.jpashop.controller.dto.MemberListDto;
import jpabook.jpashop.controller.dto.OrderMemberDto;
import jpabook.jpashop.controller.dto.QMemberListDto;
import jpabook.jpashop.controller.dto.QOrderMemberDto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static jpabook.jpashop.domain.QMember.*;

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

    public List<OrderMemberDto> findOrderDto() {
        return queryFactory
                .select(new QOrderMemberDto(member.id, member.name))
                .from(member)
                .fetch();
    }
}
