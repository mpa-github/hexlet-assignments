package exercise.controllers;

import io.javalin.http.Handler;
import java.util.List;
import java.util.Map;
import io.javalin.core.validation.Validator;
import io.javalin.core.validation.ValidationError;
import io.javalin.core.validation.JavalinValidation;
import org.apache.commons.validator.routines.EmailValidator;
import org.apache.commons.lang3.StringUtils;

import exercise.domain.User;
import exercise.domain.query.QUser;

public final class UserController {

    public static Handler listUsers = ctx -> {

        List<User> users = new QUser()
            .orderBy()
            .id.asc()
            .findList();

        ctx.attribute("users", users);
        ctx.render("users/index.html");
    };

    public static Handler showUser = ctx -> {
        long id = ctx.pathParamAsClass("id", Long.class).getOrDefault(null);

        User user = new QUser()
            .id.equalTo(id)
            .findOne();

        ctx.attribute("user", user);
        ctx.render("users/show.html");
    };

    public static Handler newUser = ctx -> {

        ctx.attribute("errors", Map.of());
        ctx.attribute("user", Map.of());
        ctx.render("users/new.html");
    };

    public static Handler createUser = ctx -> {
        // BEGIN
        String firstName = ctx.formParam("firstName");
        String lastName = ctx.formParam("lastName");
        String email = ctx.formParam("email");
        String password = ctx.formParam("password");
        Validator<String> firstNameValidator = ctx.formParamAsClass("firstName", String.class)
            .check(value -> !value.isEmpty(), "Имя не должно быть пустым");
        Validator<String> lastNameValidator = ctx.formParamAsClass("lastName", String.class)
            .check(value -> !value.isEmpty(), "Фамилия не должна быть пустая");
        Validator<String> emailValidator = ctx.formParamAsClass("email", String.class)
            .check(value -> EmailValidator.getInstance().isValid(value), "Неверный email");
        Validator<String> passwordValidator = ctx.formParamAsClass("password", String.class)
            .check(StringUtils::isNumeric, "Пароль должен состоять только из цифр")
            .check(value -> value.length() >= 4, "Минимальная длина пароля 4 цифры");

        Map<String, List<ValidationError<?>>> errors = JavalinValidation.collectErrors(
            firstNameValidator,
            lastNameValidator,
            emailValidator,
            passwordValidator
        );

        if (!errors.isEmpty()) {
            ctx.status(422);
            ctx.attribute("errors", errors);
            User invalidUser = new User(firstName, lastName, email, password);
            ctx.attribute("user", invalidUser);
            ctx.render("users/new.html");
            return;
        }

        User newUser = new User(firstName, lastName, email, password);
        newUser.save();

        ctx.sessionAttribute("flash", "Пользователь успешно создан");
        ctx.redirect("/users");
        // END
    };
}
