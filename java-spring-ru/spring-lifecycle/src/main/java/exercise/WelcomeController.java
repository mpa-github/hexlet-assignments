package exercise;

import exercise.daytimes.Daytime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// BEGIN
@RestController
public class WelcomeController {

    private Meal meal;
    private Daytime daytime;

    @Autowired
    public WelcomeController(Meal meal, Daytime daytime) {
        this.meal = meal;
        this.daytime = daytime;
    }

    @GetMapping(path = "/daytime")
    public String wishYouBonAppetit() {
        return String.format("It is %s now. Enjoy your %s", daytime.getName(), meal.getMealForDaytime(daytime.getName()));
    }
}
// END
