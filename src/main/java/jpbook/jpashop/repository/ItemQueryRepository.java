package jpbook.jpashop.repository;


import com.querydsl.jpa.impl.JPAQueryFactory;
import jpbook.jpashop.controller.dto.ItemListDto;
import jpbook.jpashop.controller.dto.MovieForm;
import jpbook.jpashop.controller.dto.QItemListDto;
import jpbook.jpashop.controller.dto.QMovieForm;
import jpbook.jpashop.domain.QMember;
import jpbook.jpashop.domain.item.QItem;
import jpbook.jpashop.domain.item.QMovie;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static jpbook.jpashop.domain.item.QItem.*;
import static jpbook.jpashop.domain.item.QMovie.*;

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
}
