package ru.javawebinar.topjava.service.datajpa;

import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.UserTestData;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.service.AbstractUserServiceTest;

import java.util.Arrays;
import java.util.List;

import static ru.javawebinar.topjava.MealTestData.*;

/**
 * Created by d.baskakov on 10.08.2017.
 */

@ActiveProfiles("datajpa")
public class DataJpaUserServiceTest extends AbstractUserServiceTest {

    @Test
    public void getWithMeals()
    {
        User actual=service.getWithMeals(UserTestData.ADMIN_ID);
        List<Meal> mealsList=actual.getMealList();
        MATCHER.assertCollectionEquals(mealsList, Arrays.asList(ADMIN_MEAL1,ADMIN_MEAL2));
    }
}
