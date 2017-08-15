package ru.javawebinar.topjava.service.datajpa;

import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.UserTestData;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.service.AbstactMealServiceTest;

import static ru.javawebinar.topjava.MealTestData.ADMIN_MEAL_ID;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;

/**
 * Created by d.baskakov on 10.08.2017.
 */
@ActiveProfiles({"datajpa"})
public class DataJpaMealServiceTest extends AbstactMealServiceTest {

    @Test
    public  void getWithUser()
    {
        Meal actual = mealService.getWithUser(ADMIN_MEAL_ID, ADMIN_ID);
        User user=actual.getUser();
        System.out.println(user);
        UserTestData.MATCHER.assertEquals(user, UserTestData.ADMIN);
    }
}

