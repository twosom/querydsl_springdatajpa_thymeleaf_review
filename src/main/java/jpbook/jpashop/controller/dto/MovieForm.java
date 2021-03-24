package jpbook.jpashop.controller.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class MovieForm {

    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    private String director;
    private String actor;

    @QueryProjection
    public MovieForm(Long id, String name, int price, int stockQuantity, String director, String actor) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.director = director;
        this.actor = actor;
    }
}
