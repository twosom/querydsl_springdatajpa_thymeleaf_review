package jpbook.jpashop.domain;


import jpbook.jpashop.domain.item.Item;
import lombok.Getter;

import javax.persistence.*;

@Entity @Getter
public class OrderItem {

    @Id @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    private int orderPrice;
    private int count;
}
