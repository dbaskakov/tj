package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;
import static ru.javawebinar.topjava.UserTestData.USER_ID;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@Sql("classpath:db/populateDB.sql")
@RunWith(SpringRunner.class)
public class MealServiceImplTest {

    static {
        SLF4JBridgeHandler.install();
    }

    @Autowired
    MealService mealService;

    @Test
    public void get() throws Exception {
        Meal meal=mealService.get(MEAL_ID, USER_ID);
        MATCHER.assertEquals(meal, meal1);
    }

    @Test(expected = NotFoundException.class)
    public void getNotFound() throws Exception {
        mealService.delete(3,100);
    }

    @Test
    public void delete() throws Exception {
        mealService.delete(MEAL_ID, USER_ID);
        MATCHER.assertCollectionEquals(mealService.getAll(USER_ID), Collections.singletonList(meal2));
    }

    @Test(expected = NotFoundException.class)
    public void deleteNotFound() throws Exception {
        mealService.delete(MEAL_ID+10, USER_ID);
        mealService.delete(MEAL_ID,ADMIN_ID);
    }

    @Test
    public void getBetweenDateTimes() throws Exception {
        List<Meal> filter=mealService.getBetweenDateTimes(LocalDateTime.parse("2017-05-16T09:00:38"),LocalDateTime.parse("2017-05-16T11:00:38"),USER_ID);
        MATCHER.assertCollectionEquals(filter, Collections.singletonList(meal2));
    }

    @Test
    public void getAll() throws Exception {
        List<Meal> mealList=mealService.getAll(USER_ID);
        MATCHER.assertCollectionEquals(mealList,Arrays.asList(meal1,meal2));
    }

    @Test
    public void update() throws Exception {
        Meal updated=new Meal(meal1);
        updated.setDescription("UPDATED");
        mealService.update(updated, USER_ID);
        MATCHER.assertEquals(updated,mealService.get(MEAL_ID, USER_ID));
    }

    @Test(expected = NotFoundException.class)
    public void updateNotFound() throws Exception {
        Meal updated=new Meal(meal1);
        updated.setDescription("UPDATED");
        mealService.update(updated, ADMIN_ID);
    }

    @Test
    public void save() throws Exception {
        Meal created=mealService.save(NEWmeal, USER_ID);
        NEWmeal.setId(created.getId());
        MATCHER.assertCollectionEquals(mealService.getAll(USER_ID),Arrays.asList(NEWmeal,meal1,meal2));
    }

}