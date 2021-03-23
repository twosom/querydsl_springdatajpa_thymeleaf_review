package jpbook.jpashop.domain.item;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Entity @DiscriminatorColumn(name = "dtype")
public abstract class Item {

    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;
}
