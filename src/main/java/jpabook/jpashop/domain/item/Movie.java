package jpabook.jpashop.domain.item;


import jpabook.jpashop.controller.dto.MovieForm;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity @Getter
@DiscriminatorValue("M")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Movie extends Item{

    private String director;
    private String actor;

    public Movie(String name, int price, int stockQuantity) {
        super(name, price, stockQuantity);
    }

    public Movie(String name, int price, int stockQuantity, String director, String actor) {
        super(name, price, stockQuantity);
        this.director = director;
        this.actor = actor;
    }

    public Movie(MovieForm form) {
        super(form.getName(), form.getPrice(), form.getStockQuantity());
        this.director = form.getDirector();
        this.actor = form.getActor();
    }

    public void update(MovieForm form) {
        super.update(form);
        this.director = form.getDirector();
        this.actor = form.getActor();
    }
}
