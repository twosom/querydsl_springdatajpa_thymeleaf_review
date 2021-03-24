package jpabook.jpashop.controller.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class OrderMemberDto {

    private Long id;
    private String name;

    @QueryProjection
    public OrderMemberDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
