package ru.shlanlinec.graduation.mealservice.model;

import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;
import org.hibernate.validator.constraints.Range;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.*;

//@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@NamedQueries({
        @NamedQuery(name = Dish.DELETE, query = "DELETE FROM Dish d WHERE d.id=:id"),
        @NamedQuery(name = Dish.BY_RESTAURANT, query = "SELECT DISTINCT d FROM Dish d LEFT JOIN FETCH d.roles WHERE d.email=?1"),
        @NamedQuery(name = Dish.ALL_SORTED, query = "SELECT d FROM Dish d ORDER BY d.name, d.email"),
})
@Entity
@Table(name = "DISHES", uniqueConstraints = {@UniqueConstraint(columnNames = {"DISH_NAME", "RESTAURANT_ID"}, name = "dishes_unique_restaurant_dish_date_idx")})
public class Dish extends AbstractNamedEntity {
    public static final String DELETE = "Dish.delete";
    public static final String BY_RESTAURANT = "Dish.getByRestaurant";
    public static final String ALL_SORTED = "Dish.getAllSorted";

    @Column(name = "PRICE", nullable = false)
    @Range(min = 10, max = 50000)
    private int price;

    @Column(name = "DATE", nullable = false, columnDefinition = "DATE DEFAULT CURDATE()")
    @NotNull
    private LocalDate date = LocalDate.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RESTAURANT_ID", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
//    @JsonIgnore
    private Restaurant restaurant;

    public Dish() {
    }

    public Dish(Dish d) {
        this(d.getId(), d.getName(), d.getRestaurant(), d.getPrice(), d.getDate());
    }

//    public Dish(Integer id, String name, Integer price, LocalDate date) {
//        this(id, name, price, LocalDate.now());
//    }

    public Dish(Integer id, String name, Restaurant restaurant, Integer price, LocalDate date) {
        super(id, name);
        this.restaurant = restaurant;
        this.price = price;
        this.date = date;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id=" + id +
                ", name=" + name +
                ", price=" + price +
                ", restaurant=" + restaurant +
                '}';
    }
}
