package jpabook.jpashop.controller;

import jpabook.jpashop.controller.dto.OrderMemberDto;
import jpabook.jpashop.controller.dto.OrderMovieDto;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderStatus;
import jpabook.jpashop.repository.ItemQueryRepository;
import jpabook.jpashop.repository.MemberQueryRepository;
import jpabook.jpashop.repository.OrderQueryRepository;
import jpabook.jpashop.repository.OrderSearch;
import jpabook.jpashop.service.ItemService;
import jpabook.jpashop.service.MemberService;
import jpabook.jpashop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final MemberService memberService;
    private final ItemService itemService;

    private final MemberQueryRepository memberQueryRepository;
    private final ItemQueryRepository itemQueryRepository;
    private final OrderQueryRepository orderQueryRepository;


    @GetMapping("/order")
    public String createForm(Model model) {

        List<OrderMemberDto> members = memberQueryRepository.findOrderDto();
        List<OrderMovieDto> items = itemQueryRepository.findOrderDto();

        model.addAttribute("members", members);
        model.addAttribute("items", items);

        return "order/orderForm";
    }

    @PostMapping("/order")
    public String order(Long memberId, Long itemId, int count) {
        orderService.order(memberId, itemId, count);

        return "redirect:/orders";
    }

    @GetMapping("/orders")
    public String orderList(@ModelAttribute(value = "orderSearch") OrderSearch orderSearch, Model model) {
        List<Order> orders = orderQueryRepository.findAll(orderSearch);
        model.addAttribute("orders", orders);



        return "order/orderList";
    }

    @PostMapping("/orders/{orderId}/cancel")
    public String cancel(@PathVariable Long orderId) {
        orderService.cancelOrder(orderId);

        return "redirect:/orders";
    }
}
