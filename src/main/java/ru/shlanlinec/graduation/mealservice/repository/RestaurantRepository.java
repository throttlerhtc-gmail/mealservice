package ru.shlanlinec.graduation.mealservice.repository;

import ru.shlanlinec.graduation.mealservice.model.Restaurant;
import java.util.List;

public interface RestaurantRepository {
    // null if updated vote do not belong to userId
    Restaurant save();

    // false if not present in db
    boolean delete();

    // null if not present
    Restaurant get(int id, int userId);

    // get all
    List<Restaurant> getAll();

}
