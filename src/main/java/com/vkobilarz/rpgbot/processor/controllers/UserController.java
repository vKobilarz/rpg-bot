package com.vkobilarz.rpgbot.processor.controllers;

import com.vkobilarz.rpgbot.core.models.User;
import com.vkobilarz.rpgbot.processor.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.List;

@RequestMapping("/user")
@RestController
@AllArgsConstructor
public class UserController {
    private final UserRepository userRepository;
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<User>> getAll() throws SQLException {
        return ResponseEntity.ok(userRepository.getUsers());
    }
    @RequestMapping(value = "{userId}", method = RequestMethod.GET)
    public ResponseEntity<User> get(@PathVariable int userId) {
        User user = userRepository.getUserById(userId);

        return ResponseEntity.ok(user);
    }
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<User> create(@RequestBody User user) throws URISyntaxException, SQLException {
        userRepository.createUser(user);

        return ResponseEntity
                .created(new URI("/user"))
                .build();
    }
}
