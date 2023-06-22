package com.vkobilarz.rpgbot.controllers;

import com.vkobilarz.rpgbot.exceptions.ValidationException;
import com.vkobilarz.rpgbot.models.User;
import com.vkobilarz.rpgbot.repositories.UserRepository;
import com.vkobilarz.rpgbot.validators.IdValidation;
import com.vkobilarz.rpgbot.validators.UserValidation;
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
        UserValidation.validateThat()
                .isNameValid()
                .isUrlValid()
                .isEmailValid()
                .isPhoneNumberValid()
                .apply(user)
                .ifInvalidThrow(ValidationException::new);

        userRepository.createUser(user);

        return ResponseEntity
                .created(new URI("/user"))
                .build();
    }

    @RequestMapping(value = "{userId}", method = RequestMethod.PUT)
    public ResponseEntity<User> update(@PathVariable Long userId, @RequestBody User user) throws SQLException {
        String id = userId == null ? null : userId.toString();

        IdValidation.validateThat()
                .isRequired()
                .isNumericId()
                .apply(id)
                .ifInvalidThrow(ValidationException::new);

        UserValidation.validateThat()
                .isNameValid()
                .isUrlValid()
                .isEmailValid()
                .isPhoneNumberValid()
                .apply(user)
                .ifInvalidThrow(ValidationException::new);

        user.setId(userId);

        userRepository.updateUser(user);

        return ResponseEntity
                .ok()
                .build();
    }

    @RequestMapping(value = "{userId}", method = RequestMethod.DELETE)
    public ResponseEntity<User> delete(@PathVariable String userId) throws SQLException {
        IdValidation.validateThat()
                .isRequired()
                .isNumericId()
                .apply(userId)
                .ifInvalidThrow(ValidationException::new);

        userRepository.deleteUserById(Integer.parseInt(userId));

        return ResponseEntity.ok().build();
    }
}