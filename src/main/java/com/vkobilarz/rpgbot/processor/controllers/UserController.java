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
import java.util.List;
import java.util.Optional;

@RequestMapping("/user")
@RestController
@AllArgsConstructor
public class UserController {
    private final UserRepository userRepository;
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<User>> getAll() {
        return ResponseEntity.ok(userRepository.findAll());
    }
    @RequestMapping(value = "{userId}", method = RequestMethod.GET)
    public ResponseEntity<User> get(@PathVariable Integer userId) {
        Optional<User> user = userRepository.findById(userId);

        if (!user.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user.get());
    }
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<User> create(@RequestBody User user) {
        try {
            userRepository.save(user);

            return ResponseEntity
                    .created(new URI("/user"))
                    .build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
