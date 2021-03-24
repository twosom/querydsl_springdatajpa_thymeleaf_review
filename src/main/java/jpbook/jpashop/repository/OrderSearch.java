package jpbook.jpashop.repository;

import jpbook.jpashop.domain.OrderStatus;
import lombok.Getter;

@Getter
public class OrderSearch {

    private String username;
    private OrderStatus status;
}
