package com.vkobilarz.rpgbot.repositories;

import com.vkobilarz.rpgbot.models.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {
    @Value("${jdbc.connectionUrl}")
    private String connectionUrl;
    @Value("${jdbc.username}")
    private String username;
    @Value("${jdbc.password}")
    private String password;
    public UserRepository() throws ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
    }

    private Connection connectToDatabase() throws SQLException {
        return DriverManager.getConnection(connectionUrl, username, password);
    }

    private User createUserFromResultSet(ResultSet rs) throws SQLException {
        return new User(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getString("imageurl"),
                rs.getString("email"),
                rs.getString("phonenumber")
        );
    }

    public List<User> getUsers() throws SQLException {
        try (
            Connection connection = connectToDatabase();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from \"user\";")
        ) {
            List<User> users = new ArrayList<>();

            while (resultSet.next()) {
                User user = createUserFromResultSet(resultSet);
                users.add(user);
            }

            return users;
        }
    }

    public User getUserById(Long id) throws SQLException {
        try (
            Connection connection = connectToDatabase();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from \"user\" where id = ?;");
        ) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            User user = null;
            while (resultSet.next()) {
                user = createUserFromResultSet(resultSet);
            }

            return user;
        }
    }

    public void createUser(User user) throws SQLException {
        try (
            Connection connection = connectToDatabase();
            PreparedStatement preparedStatement = connection.prepareStatement("insert into \"user\" (id, name, imageurl, phonenumber, email) values (nextval('user_id'), ?, ?, ?, ?);");
        ) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getImageUrl());
            preparedStatement.setString(3, user.getPhoneNumber());
            preparedStatement.setString(4, user.getEmail());

            preparedStatement.executeUpdate();
        }
    }

    public void updateUser(User user) throws SQLException {
        try (
                Connection connection = connectToDatabase();
                PreparedStatement preparedStatement = connection.prepareStatement(
                "update \"user\" set "+
                    "  name = ?" +
                    ", imageurl = ?" +
                    ", phonenumber = ?" +
                    ", email = ?" +
                    "where id = ?" +
                    ";");
        ) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getImageUrl());
            preparedStatement.setString(3, user.getPhoneNumber());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setLong(5, user.getId());

            preparedStatement.executeUpdate();
        }
    }

    public void deleteUserById(Integer id) throws SQLException {

        try (
                Connection connection = connectToDatabase();
                PreparedStatement preparedStatement = connection.prepareStatement("delete from \"user\" where id = ?");
        ) {
            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();
        }
    }
}