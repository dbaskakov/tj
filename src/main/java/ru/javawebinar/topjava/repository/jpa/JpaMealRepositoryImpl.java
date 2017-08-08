package ru.javawebinar.topjava.repository.jpa;

import org.springframework.dao.support.DataAccessUtils;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaMealRepositoryImpl implements MealRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Meal save(Meal meal, int userId) {
        User ref = em.getReference(User.class, userId);
        meal.setUser(ref);
        if (meal.isNew())
        {
            em.persist(meal);
            return meal;
        } else
        {
            if (get(meal.getId(),userId)==null) return null;
            return em.merge(meal);
        }

//        "insert into Logger (redirect,user_id) VALUES (:insertLink,:id)"

//        Query query=em.createQuery("update  Meal m SET m.id=:id,m.dateTime=:datetime,m.calories=:calories,m.description=:description")
//               .setParameter("id",meal.getId())
//               .setParameter("datetime",meal.getDateTime())
//              .setParameter("calories",meal.getCalories())
//              .setParameter("description",meal.getDescription());
//        System.out.println("===============MEAL SAVED"+meal);
//       if (query.executeUpdate()!=0) return meal;else return null;

    }

    @Override
    @Transactional
    public boolean delete(int id, int userId) {
//        Query query=em.createQuery("DELETE FROM Meal m where m.user.id=:userid and m.id=:id");
//        return  query.setParameter("userid",userId).setParameter("id",id).executeUpdate()!=0;
        return em.createNamedQuery(Meal.DELETE).setParameter("userId",userId).setParameter("id",id).executeUpdate()!=0;
    }

    @Override
    public Meal get(int id, int userId) {
//        TypedQuery<Meal> mealTypedQuery=em.createQuery("SELECT m FROM Meal m where m.user.id=:userid and m.id=:id",Meal.class).setParameter("userid",userId).setParameter("id",id);
//        return DataAccessUtils.singleResult(mealTypedQuery.getResultList());
        return DataAccessUtils.singleResult(em.createNamedQuery(Meal.GET,Meal.class).setParameter("userId",userId).setParameter("id",id).getResultList());
    }

    @Override
    public List<Meal> getAll(int userId) {
//        TypedQuery<Meal> mealTypedQuery=em.createQuery("SELECT m FROM Meal m where m.user.id=:id ORDER BY m.dateTime DESC ",Meal.class).setParameter("id",userId);
//        return mealTypedQuery.getResultList();
        return em.createNamedQuery(Meal.GETALL,Meal.class).setParameter("userId",userId).getResultList();
    }

    @Override
    public List<Meal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
//        TypedQuery<Meal> mealTypedQuery=em.createQuery("SELECT m FROM Meal m WHERE m.user.id=:userId and m.dateTime BETWEEN :startDate and :endDate ORDER BY m.dateTime DESC ",Meal.class).setParameter("userId",userId).setParameter("startDate",startDate)
//                .setParameter("endDate",endDate);
//        return mealTypedQuery.getResultList();
        return em.createNamedQuery(Meal.GETBETWEEN,Meal.class)
                .setParameter("userId",userId)
                .setParameter("startDate",startDate)
                .setParameter("endDate",endDate)
                .getResultList();
    }
}