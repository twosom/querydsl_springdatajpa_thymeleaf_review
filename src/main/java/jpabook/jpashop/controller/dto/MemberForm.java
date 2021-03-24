package jpabook.jpashop.controller.dto;

import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

@Getter
@ToString(of = {"name", "city", "street", "zipcode"})
public class MemberForm {


    @NotEmpty(message = "회원 이름은 필수입니다.")
    private String name;

    private String city;
    private String street;
    private String zipcode;

}
