package com.vkobilarz.rpgbot.controllers;

import com.vkobilarz.rpgbot.models.User;
import com.vkobilarz.rpgbot.repositories.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.List;

@RequestMapping("/user")
@RestController
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<User>> getAll() throws SQLException {
        return ResponseEntity.ok(userRepository.getUsers());
    }

    @RequestMapping(value = "{userId}", method = RequestMethod.GET)
    public ResponseEntity<User> get(@PathVariable Long userId) throws SQLException {
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

    @RequestMapping(value = "{userId}", method = RequestMethod.PUT)
    public ResponseEntity<User> update(@PathVariable Long userId, @RequestBody User user) throws SQLException {
        user.setId(userId);

        userRepository.updateUser(user);

        return ResponseEntity
                .ok()
                .build();
    }

    @RequestMapping(value = "{userId}", method = RequestMethod.DELETE)
    public ResponseEntity<User> delete(@PathVariable Integer userId) throws SQLException {
        userRepository.deleteUserById(userId);

        return ResponseEntity.ok().build();
    }
}