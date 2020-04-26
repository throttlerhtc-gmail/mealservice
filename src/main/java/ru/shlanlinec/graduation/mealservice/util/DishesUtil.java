package ru.shlanlinec.graduation.mealservice.util;

import org.springframework.lang.Nullable;
import ru.shlanlinec.graduation.mealservice.model.Dish;
import ru.shlanlinec.graduation.mealservice.model.Vote;
import ru.shlanlinec.graduation.mealservice.to.DishTo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class DishesUtil {
    public static final int DEFAULT_CALORIES_PER_DAY = 2000;

    private DishesUtil() {
    }

    public static List<DishTo> getTos(Collection<Dish> meals, int caloriesPerDay) {
        return getFiltered(meals, caloriesPerDay, meal -> true);
    }

    public static List<DishTo> getFilteredTos(Collection<Dish> meals, int caloriesPerDay, @Nullable LocalTime startTime, @Nullable LocalTime endTime) {
        return getFiltered(meals, caloriesPerDay, meal -> Util.isBetweenInclusive(meal.getTime(), startTime, endTime));
    }

    private static List<DishTo> getFiltered(Collection<Dish> meals, int caloriesPerDay, Predicate<Dish> filter) {
        Map<LocalDate, Integer> caloriesSumByDate = meals.stream()
                .collect(
                        Collectors.groupingBy(Dish::getDate, Collectors.summingInt(Dish::getCalories))
//                      Collectors.toMap(Dish::getDate, Dish::getCalories, Integer::sum)
                );
        return meals.stream()
                .filter(filter)
                .map(meal -> createTo(meal, caloriesSumByDate.get(meal.getDate()) > caloriesPerDay))
                .collect(Collectors.toList());
    }

    public static DishTo createTo(Dish meal, boolean excess) {
        return new DishTo(meal.getId(), meal.getDateTime(), meal.getDescription(), meal.getCalories(), excess);
    }
}
