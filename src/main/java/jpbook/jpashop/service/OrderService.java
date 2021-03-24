package jpbook.jpashop.service;

import jpbook.jpashop.domain.Delivery;
import jpbook.jpashop.domain.Member;
import jpbook.jpashop.domain.Order;
import jpbook.jpashop.domain.OrderItem;
import jpbook.jpashop.domain.item.Item;
import jpbook.jpashop.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    private final OrderQueryRepository orderQueryRepository;

    //주문
    @Transactional
    public Long order(Long memberId, Long itemId, int count) {

        //엔티티 조회
        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.findOne(itemId);

        //배송정보 생성
        Delivery delivery = new Delivery(member.getAddress());

        //주문 상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);
        Order order = Order.createOrder(member, delivery, orderItem);

        return orderRepository.save(order).getId();
    }

    //취소
    @Transactional
    public void cancelOrder(Long orderId) {
        //엔티티 조회
        Order order = orderRepository.findOne(orderId);
        //주문 취소
        order.cancel();
    }

    //검색
    public List<Order> findOrders(OrderSearch orderSearch) {
        return orderQueryRepository.findAll(orderSearch);
    }
}
