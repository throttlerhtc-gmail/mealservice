package ru.shlanlinec.graduation.mealservice.repository;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import ru.shlanlinec.graduation.mealservice.model.Dish;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static ru.shlanlinec.graduation.mealservice.util.DateTimeUtil.getEndExclusive;
import static ru.shlanlinec.graduation.mealservice.util.DateTimeUtil.getStartInclusive;

public interface DishRepository {
    //
    Dish save(Dish dish);

    //
    boolean delete(int id);

    //
    Dish get(int id);

    // for Cafe today
    List<Dish> getAll(int restaurantId);

    // ORDERED date desc
    default List<Dish> getBetweenInclusive(@Nullable LocalDate startDate, @Nullable LocalDate endDate, int restaurantId) {
        return getBetweenInclusive(getStartInclusive(startDate), getEndExclusive(endDate), restaurantId);
    }

    // ORDERED dateTime desc
    List<Dish> getBetweenInclusive(@NonNull LocalDateTime startDate, @NonNull LocalDateTime endDate, int restaurantId);

    default Dish getWithUser(int id, int userId) {
        throw new UnsupportedOperationException();
    }
}
