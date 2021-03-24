package jpabook.jpashop.repository;


import com.querydsl.jpa.impl.JPAQueryFactory;
import jpabook.jpashop.controller.dto.*;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static jpabook.jpashop.domain.item.QItem.*;
import static jpabook.jpashop.domain.item.QMovie.*;

@Repository
public class ItemQueryRepository{

    private final JPAQueryFactory queryFactory;

    public ItemQueryRepository(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    public List<ItemListDto> findItems() {
        return queryFactory
                .select(new QItemListDto(
                        item.id, item.name, item.price, item.stockQuantity))
                .from(item)
                .fetch();
    }

    public MovieForm findOne(Long itemId) {
        return queryFactory
                .select(new QMovieForm(
                        movie.id, movie.name, movie.price, movie.stockQuantity, movie.director, movie.actor))
                .from(movie)
                .where(movie.id.eq(itemId))
                .fetchOne();

    }

    public List<OrderMovieDto> findOrderDto() {
        return queryFactory
                .select(new QOrderMovieDto(movie.id, movie.name))
                .from(movie)
                .fetch();
    }
}
