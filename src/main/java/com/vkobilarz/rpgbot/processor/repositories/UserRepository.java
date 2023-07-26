package com.vkobilarz.rpgbot.processor.repositories;

import com.vkobilarz.rpgbot.processor.models.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.UUID;

@AllArgsConstructor
@Repository
public class UserRepository {

    private final ArrayList<User> users;

    public UserRepository() {
        users = new ArrayList<>();

        users.add(createMockedUser());
    }


    public User findUserById(UUID id) {
        return users.stream().filter(user -> user.getId().toString().equals(id.toString()))
                .findFirst()
                .orElse(null);
    }

    private User createMockedUser() {
        return User.builder()
                .id(UUID.fromString("08f49f35-2405-49b5-8c12-1c8dc767394d"))
                .build();
    }
}
