package ru.javawebinar.topjava.service.jdbc;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.ActiveDbProfileResolver;
import ru.javawebinar.topjava.service.AbstactMealServiceTest;

/**
 * Created by d.baskakov on 10.08.2017.
 */
@ActiveProfiles(profiles = "jdbc")
public class JdbcMealServiceTest extends AbstactMealServiceTest {
}
