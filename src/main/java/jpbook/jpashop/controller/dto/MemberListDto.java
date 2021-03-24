package jpbook.jpashop.controller.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class MemberListDto {

    private Long id;
    private String name;
    private String city;
    private String street;
    private String zipcode;


    @QueryProjection
    public MemberListDto(Long id, String name, String city, String street, String zipcode) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
