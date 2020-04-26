package ru.shlanlinec.graduation.mealservice.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@NamedQueries({
        @NamedQuery(name = Vote.ALL_SORTED, query = "SELECT v FROM Vote v WHERE v.user.id=:userId ORDER BY v.date DESC"),
        @NamedQuery(name = Vote.DELETE, query = "DELETE FROM Vote v WHERE v.id=:id AND v.user.id=:userId"),
        @NamedQuery(name = Vote.GET_BETWEEN, query = "SELECT v FROM Vote v " +
                "WHERE v.user.id=:userId AND v.date >= :startDate AND v.date < :endDate ORDER BY v.date DESC"),
        @NamedQuery(name = Vote.UPDATE, query = "UPDATE Vote v SET v.restaurant.id = :id where v.id=:id and v.user.id=:userId and v.date=:date")
//        @NamedQuery(name = Vote.UPDATE, query = "UPDATE Vote v SET v.date = :date, v.calories= :calories," +
//                "v.description=:desc where v.id=:id and v.user.id=:userId")
})
@Entity
@Table(name = "VOTES", uniqueConstraints = {@UniqueConstraint(columnNames = {"USER_ID", "DATE"}, name = "votes_unique_user_date_idx")})

public class Vote extends AbstractBaseEntity {
    public static final String ALL_SORTED = "Vote.getAll";
    public static final String DELETE = "Vote.delete";
    public static final String GET_BETWEEN = "Vote.getBetween";
    public static final String UPDATE = "Vote.update";

    @Column(name = "DATE", nullable = false)
    @NotNull
    private LocalDate date = LocalDate.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RESTAURANT_ID", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Restaurant restaurant;

    public Vote() {
    }

    public Vote(LocalDate date) {
        this(null, date);
    }

    public Vote(Integer id, LocalDate date) {
        super(id);
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "id=" + id +
                ", date=" + date +
//                ", description='" + description + '\'' +
//                ", calories=" + calories +
                '}';
    }

}
