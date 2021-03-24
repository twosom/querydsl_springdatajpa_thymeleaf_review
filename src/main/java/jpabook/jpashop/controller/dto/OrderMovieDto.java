package jpabook.jpashop.controller.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class OrderMovieDto {

    private Long id;
    private String name;


    @QueryProjection
    public OrderMovieDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
