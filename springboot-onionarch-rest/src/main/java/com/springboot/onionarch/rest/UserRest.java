package com.springboot.onionarch.rest;


import com.springboot.onionarch.domain.User;
import com.springboot.onionarch.domain.UserRepository;
import com.springboot.onionarch.domain.UserService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserRest {
    private final UserRepository userRepository;
    private final UserService userService;

    @Inject
    public UserRest(final UserRepository userRepository, final UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<User> list() {
        Iterable<User> users = userRepository.findAll();
        List<User> ret = new ArrayList<>();
        users.forEach(ret::add);
        return ret;
    }

    @RequestMapping(method = RequestMethod.POST)
    public void create(@RequestBody User user) {
        userRepository.save(user);
    }

    @RequestMapping(value = "upp", method = RequestMethod.PUT)
    public void uppercase() {
        userService.uppercaseAllUserNames();
    }
}
