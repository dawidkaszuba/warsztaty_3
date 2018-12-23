package pl.dawidkaszuba.dao;

import pl.dawidkaszuba.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    public static void save(Connection connection, User user) throws SQLException {
        if (user.getId() == null) {

            String sql = "INSERT INTO users(email, username, password, user_group_id) VALUES(?, ?, ?, ?)";
            String[] generatedColumns = {"id"};

            try (PreparedStatement statement = connection.prepareStatement(sql, generatedColumns)) {
                statement.setString(1, user.getEmail());
                statement.setString(2, user.getUsername());
                statement.setString(3, user.getPassword());
                statement.setInt(4, user.getUserGroupId());
                statement.executeUpdate();

                ResultSet rs = statement.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                }
            }
        } else {
            String sql = "UPDATE users SET email=?, username=?, user_group_id=? WHERE id=?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, user.getEmail());
                statement.setString(2, user.getUsername());
                statement.setInt(3, user.getUserGroupId());
                statement.setInt(4, user.getId());
                statement.execute();
            }
        }
    }

    public static void delete(Connection connection, User user) throws SQLException {
        if (user.getId() != null) {

            String sql = "DELETE FROM users WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, user.getId());
                statement.execute();
            }

            Integer id = null;
        }
    }

    public static User findById(Connection connection, int id) throws SQLException {
        String sql = "SELECT * FROM users WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setId(id);
                user.setEmail(rs.getString("email"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setUserGroupId(rs.getInt("user_group_id"));
                return user;
            }
        }
        return null;
    }

    public static List<User> findAll(Connection connection) throws SQLException {

        List<User> users = new ArrayList<>();

        String sql = "SELECT * FROM users";
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setEmail(rs.getString("email"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setUserGroupId(rs.getInt("user_group_id"));
                users.add(user);
            }
        }

        return users;
    }

    public static List<User> loadAllByGroupId(Connection connection,int id) throws SQLException{
        String sql = "SELECT * FROM users JOIN user_groups ON users.user_group_id=user_groups.id WHERE user_groups.id=?;";
        List<User> users = new ArrayList<>();
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1,id);
            ResultSet rs =preparedStatement.executeQuery();
            while(rs.next()){
                User user =new User();
                user.setId(rs.getInt("id"));
                user.setEmail(rs.getString("email"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setUserGroupId(rs.getInt("user_group_id"));
                users.add(user);

            }
        }
        return users;
    }

}
