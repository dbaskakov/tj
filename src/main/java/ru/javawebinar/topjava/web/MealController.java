package ru.javawebinar.topjava.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.web.meal.MealRestController;

/**
 * Created by d.baskakov on 17.08.2017.
 */

@Controller
public class MealController {

    @Autowired
    MealRestController controller;

    @GetMapping(value = "/meals")
    public String getMeals(Model model)
    {
        model.addAttribute("meals",controller.getAll());
        return "meals";
    }

}
