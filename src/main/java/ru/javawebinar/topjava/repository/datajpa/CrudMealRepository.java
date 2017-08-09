package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.List;

@Transactional(readOnly = true)
@SuppressWarnings("JpaQlInspection")
public interface CrudMealRepository extends JpaRepository<Meal, Integer> {
    @Override
    List<Meal> findAll(Sort sort);

    List<Meal> findAllByUserIdOrderByDateTimeDesc(Sort sort,int id);

//    @Transactional
//    Meal save(Meal meal,int userId);

    @Transactional
    Meal save(Meal meal);

    @Transactional
//    @Modifying
//    @Query("DELETE FROM Meal m where m.id=:id and m.user.id=:userId")
//    int deleteByIdAndUserId(@Param("id") int id,@Param("userId")int userId);
    int deleteByIdAndUserId(int id,int userId);



     Meal findByIdAndUserId(int id, int userId);

    List<Meal> findByUserIdAndDateTimeBetweenOrderByDateTimeDesc(int userId,LocalDateTime startDate, LocalDateTime endDate);
//    LocalDateTime startDate, LocalDateTime endDate, int userId);

    @Query("SELECT m from Meal m where m.user.id=:userId and m.dateTime between :startDate and :endDate order by m.dateTime DESC ")
    List<Meal> getBetween(@Param("startDate")LocalDateTime startDate,@Param("endDate") LocalDateTime endDate,@Param("userId") int userId);
}
