package exercise.controller;

import exercise.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import exercise.service.UserService;


@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UserService userService;

    @GetMapping(path = "")
    public Flux<User> getUsers() {
        return userService.findAll();
    }

    // BEGIN
    @GetMapping(path = "/{id}")
    public Mono<User> getUserById(@PathVariable(name = "id") long id) {
        return userService.findUserById(id);
    }

    @PostMapping(path = "")
    public Mono<User> createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PatchMapping(path = "/{id}")
    public Mono<User> updateUser(@PathVariable(name = "id") long id,
                                 @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    @DeleteMapping(path = "/{id}")
    public Mono<Void> deleteUser(@PathVariable(name = "id") long id) {
        return userService.deleteUser(id);
    }
    // END
}
