package ru.javawebinar.topjava;

import ru.javawebinar.topjava.matcher.BeanMatcher;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.Objects;

import static ru.javawebinar.topjava.model.BaseEntity.START_SEQ;

public class MealTestData {
    public static final int MEAL_ID = START_SEQ+2;

    public static final Meal meal1=new Meal(MEAL_ID, LocalDateTime.parse("2017-05-16T12:00:38"),"test1",500);
    public static final Meal meal2=new Meal(MEAL_ID+1, LocalDateTime.parse("2017-05-16T10:00:38"),"test2",500);
    public static final Meal NEWmeal=new Meal(LocalDateTime.parse("2017-07-16T10:00:38"),"testNEW",501);


    public static final BeanMatcher<Meal> MATCHER = new BeanMatcher<>(
            (expected, actual) -> expected == actual ||
                    (Objects.equals(expected.getCalories(),actual.getCalories())
                            &&Objects.equals(expected.getDescription(),actual.getDescription())
                            &&Objects.equals(expected.getDateTime(),actual.getDateTime())
                            &&Objects.equals(expected.getId(),actual.getId())
                            )
    );

}
