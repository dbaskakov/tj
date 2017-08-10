package ru.javawebinar.topjava.service;


import org.junit.Test;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;
import static ru.javawebinar.topjava.UserTestData.USER_ID;

abstract public class AbstactMealServiceTest extends AbstractTest {
    @Test
    public void testDelete() throws Exception {
        mealService.delete(MEAL1_ID, USER_ID);
        MATCHER.assertCollectionEquals(Arrays.asList(MEAL6, MEAL5, MEAL4, MEAL3, MEAL2), mealService.getAll(USER_ID));
    }

    @Test
    public void testDeleteNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        mealService.delete(MEAL1_ID, 1);
    }

    @Test
    public void testCreate() throws Exception {
        Meal created = getCreated();
        mealService.create(created, USER_ID);
        MATCHER.assertCollectionEquals(Arrays.asList(created, MEAL6, MEAL5, MEAL4, MEAL3, MEAL2, MEAL1), mealService.getAll(USER_ID));
    }

    @Test
    public void testGet() throws Exception {
        Meal actual = mealService.get(ADMIN_MEAL_ID, ADMIN_ID);
        MATCHER.assertEquals(ADMIN_MEAL1, actual);
    }

    @Test
    public void testGetNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        mealService.get(MEAL1_ID, ADMIN_ID);
    }

    @Test
    public void testUpdate() throws Exception {
        Meal updated = getUpdated();
        mealService.update(updated, USER_ID);
        MATCHER.assertEquals(updated, mealService.get(MEAL1_ID, USER_ID));
    }

    @Test
    public void testUpdateNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        thrown.expectMessage("Not found entity with id=" + MEAL1_ID);
        mealService.update(MEAL1, ADMIN_ID);
    }

    @Test
    public void testGetAll() throws Exception {
        MATCHER.assertCollectionEquals(MEALS, mealService.getAll(USER_ID));
    }

    @Test
    public void testGetBetween() throws Exception {
        MATCHER.assertCollectionEquals(Arrays.asList(MEAL3, MEAL2, MEAL1),
                mealService.getBetweenDates(
                        LocalDate.of(2015, Month.MAY, 30),
                        LocalDate.of(2015, Month.MAY, 30), USER_ID));
    }
}