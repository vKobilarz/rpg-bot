package com.vkobilarz.rpgbot.processor.repositories;

import com.vkobilarz.rpgbot.core.models.User;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Repository
public class UserRepository {
    @Value("${jdbc.connectionUrl}")
    private String connectionUrl;
    @Value("${jdbc.username}")
    private String username;
    @Value("${jdbc.password}")
    private String password;
    private final ArrayList<User> users;
    public UserRepository() throws ClassNotFoundException {
        Class.forName("org.postgresql.Driver");

        users = new ArrayList<>();
    }
    private Connection connectToDatabase() throws SQLException {
        return DriverManager.getConnection(connectionUrl, username, password);
    }
    private User createUserFromResultSet(ResultSet rs) throws SQLException {
        return new User(
                rs.getInt("id"),
                rs.getString("name")
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
    public void createUser(User user) throws SQLException {
        try (
                Connection connection = connectToDatabase();
                PreparedStatement preparedStatement = connection.prepareStatement("insert into \"user\" (id, name) values (nextval('user_id'), ?);");
        ) {
            preparedStatement.setString(1, user.getName());

            preparedStatement.executeUpdate();
        }
    }
    public User getUserById(int id) {
        try (
                Connection connection = connectToDatabase();
                PreparedStatement preparedStatement = connection.prepareStatement("select * from \"user\" where id = ?;");
        ) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            User user = null;
            while (resultSet.next()) {
                user = createUserFromResultSet(resultSet);
            }

            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
