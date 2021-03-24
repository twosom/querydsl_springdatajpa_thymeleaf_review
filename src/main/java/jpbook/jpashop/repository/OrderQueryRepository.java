package jpbook.jpashop.repository;


import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jpbook.jpashop.domain.*;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import java.util.List;

import static jpbook.jpashop.domain.QDelivery.*;
import static jpbook.jpashop.domain.QMember.*;
import static jpbook.jpashop.domain.QOrder.*;
import static jpbook.jpashop.domain.QOrderItem.*;

@Repository
public class OrderQueryRepository {

    private final JPAQueryFactory queryFactory;

    public OrderQueryRepository(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    public List<Order> findAll(OrderSearch orderSearch) {
        return queryFactory
                .selectFrom(order)
                .join(order.member, member)
                .where(nameLike(orderSearch.getUsername()), statusEq(orderSearch.getStatus()))
                .fetch();

    }

    private BooleanExpression statusEq(OrderStatus status) {
        return status != null ? order.status.eq(status) : null;
    }

    private BooleanExpression nameLike(String username) {
        return StringUtils.hasText(username) ? order.member.name.like(username) : null;
    }
}
