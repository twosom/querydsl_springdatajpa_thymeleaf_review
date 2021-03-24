package jpbook.jpashop.controller.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class ItemListDto {

    private Long id;
    private String name;
    private int price;
    private int stockQuantity;

    @QueryProjection
    public ItemListDto(Long id, String name, int price, int stockQuantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }
}
