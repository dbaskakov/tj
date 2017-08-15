package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudMealRepository extends JpaRepository<Meal, Integer> {

    @Transactional
    @Override
    Meal save(Meal meal);

    Meal getByUserIdAndId(int userId, int id);

    List<Meal> findAllByUserIdOrderByDateTimeDesc(int userId, Sort sort);

    @Transactional
    int deleteByUserIdAndId(int userId, int id);

    List<Meal> getByUserIdAndDateTimeBetweenOrderByDateTimeDesc(int userId, LocalDateTime startDate, LocalDateTime endDate);


//        @Query("SELECT m from Meal m where m.id=:id and m.user.id=:userId")
    @Query("SELECT m from Meal m left join FETCH m.user where m.id=:id and m.user.id=:userId")
    Meal getMealWithUser(@Param("id") int id,@Param("userId")int userId);
}
