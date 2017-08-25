package ru.javawebinar.topjava.web;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.web.meal.MealRestController;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

/**
 * Created by d.baskakov on 17.08.2017.
 */


@Controller
@RequestMapping(value = "/meals")
public class MealController extends MealRestController {

    public MealController(MealService service) {
        super(service);
    }


    @GetMapping
    public String getAllMeals(Model model)
    {
        model.addAttribute("meals",getAll());
        return "meals";
    }

    @GetMapping(value = "/create")
    public String create(Model model)
    {
        final Meal meal =new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", 1000);
        model.addAttribute("meal",meal);
        return "mealForm";
    }

    @GetMapping(value = "/update/{id}")
    public String update(Model model, @PathVariable("id") int id)
    {
        model.addAttribute("meal",get(id));
        return "mealForm";
    }

    @GetMapping(value = "/delete/{id}")
    public String delete1(@PathVariable("id") int id)
    {
        super.delete(id);
        return "redirect:/meals";
    }

//    @PostMapping
//    public String save(HttpServletRequest request)
//    {
//        Meal meal = new Meal(
//                LocalDateTime.parse(request.getParameter("dateTime")),
//                request.getParameter("description"),
//                Integer.parseInt(request.getParameter("calories")));
//        if (request.getParameter("id").isEmpty()) {
//           create(meal);
//        } else {
//            update(meal,Integer.parseInt(request.getParameter("id")));
//        }
//        return "redirect:meals";
//    }

    @PostMapping
    public String save(@ModelAttribute Meal meal)
    {
        if (meal.getId()==null) {
           create(meal);
        } else {
            update(meal,meal.getId());
        }
        return "redirect:meals";
    }


//    @PostMapping(value = "/filter")
//    public String filter(Model model,HttpServletRequest request)
//    {
//        LocalDate startDate = parseLocalDate(request.getParameter("startDate"));
//        LocalDate endDate = parseLocalDate(request.getParameter("endDate"));
//        LocalTime startTime = parseLocalTime(request.getParameter("startTime"));
//        LocalTime endTime = parseLocalTime(request.getParameter("endTime"));
//        model.addAttribute("meals",getBetween(startDate, startTime, endDate, endTime));
//        return "meals";
//    }
    @PostMapping(value = "/filter")
    public String filter(Model model,
                         @RequestParam(name = "startDate",required = false)@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate startDate,
                         @RequestParam(name = "endDate",required = false)@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate endDate,
                         @RequestParam(name = "startTime",required = false)@DateTimeFormat(iso = DateTimeFormat.ISO.TIME)LocalTime startTime,
                         @RequestParam(name = "endTime",required = false)@DateTimeFormat(iso = DateTimeFormat.ISO.TIME)LocalTime endTime)
    {
        model.addAttribute("meals",getBetween(startDate, startTime, endDate, endTime));
        return "meals";
    }


}
