package ru.shlanlinec.graduation.mealservice.web;

import ru.shlanlinec.graduation.mealservice.model.AbstractBaseEntity;

import static ru.shlanlinec.graduation.mealservice.util.DishesUtil.DEFAULT_CALORIES_PER_DAY;

public class SecurityUtil {

    private static int id = AbstractBaseEntity.START_SEQ;

    private SecurityUtil() {
    }

    public static int authUserId() {
        return id;
    }

    public static void setAuthUserId(int id) {
        SecurityUtil.id = id;
    }

    public static int authUserCaloriesPerDay() {
        return DEFAULT_CALORIES_PER_DAY;
    }
}