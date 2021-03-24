package jpabook.jpashop.service;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderStatus;
import jpabook.jpashop.domain.item.Movie;
import jpabook.jpashop.exception.NotEnoughStockException;
import jpabook.jpashop.repository.OrderRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class OrderServiceTest {

    @Autowired OrderService orderService;
    @Autowired OrderRepository orderRepository;
    @Autowired EntityManager em;


    @Test
    void 상품주문() throws Exception {
        //given
        Member member = createMember("memberA", "인천", "부평구", "길주남로143");
        Movie movie = createMovie("TENET", 10_000, 10, "크리스토퍼 놀란", "워싱턴 존 데이비드");

        int orderCount = 8;

        //when
        Long orderId = orderService.order(member.getId(), movie.getId(), orderCount);

        //then
        Order getOrder = orderRepository.findOne(orderId);

        Assertions.assertThat(getOrder.getStatus()).isEqualTo(OrderStatus.ORDER);
        Assertions.assertThat(getOrder.getOrderItems().size()).isEqualTo(1);
        Assertions.assertThat(getOrder.getTotalPrice()).isEqualTo(movie.getPrice() * orderCount);
        Assertions.assertThat(movie.getStockQuantity()).isEqualTo(2);

    }


    @Test
    void 주문취소() throws Exception {
        //given
        Member member = createMember("memberA", "부평 ", "길주남로", "143");
        Movie movie = createMovie("TENET", 10_000, 10, "크리스토퍼 놀란", "워싱턴 존 데이비드");


        //when
        int orderCount = 2;

        Long orderId = orderService.order(member.getId(), movie.getId(), orderCount);
        orderService.cancelOrder(orderId);

        em.flush();
        em.clear();
        Order getOrder = orderRepository.findOne(orderId);

        //then
        Assertions.assertThat(getOrder.getStatus()).isEqualTo(OrderStatus.CANCEL);
        Assertions.assertThat(movie.getStockQuantity()).isEqualTo(10);

    }

    @Test
    void 상품주문_재고수량초과() throws Exception {
        //given
        Member member = createMember("memberA", "부평", "길주남로", "143");
        Movie movie = createMovie("TENET", 10_000, 10, "크리스토퍼 놀란", "워싱턴 존 데이비드");

        //when
        assertThrows(NotEnoughStockException.class, () ->
                orderService.order(member.getId(), movie.getId(), 15)
        );

        //then

    }


    private Movie createMovie(String itemName, int itemPrice, int stockQuantity, String director, String actor) {
        Movie movie = new Movie(itemName, itemPrice, stockQuantity, director, actor);
        em.persist(movie);
        return movie;
    }

    private Member createMember(String username, String city, String street, String zipcode) {
        Member member = new Member(username, new Address(city, street, zipcode));
        em.persist(member);
        return member;
    }

}