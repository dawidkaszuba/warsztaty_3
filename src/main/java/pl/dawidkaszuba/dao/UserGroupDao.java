package pl.dawidkaszuba.dao;

import pl.dawidkaszuba.model.UserGroup;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserGroupDao {

    public static UserGroup findById(Connection connection, int id) throws SQLException {
        String sql = "SELECT * FROM user_groups WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                UserGroup userGroup = new UserGroup();
                userGroup.setId(id);
                userGroup.setName(rs.getString("name"));

                return userGroup;
            }
        }
        return null;
    }

    public static List<UserGroup> findAll(Connection connection) throws SQLException {

        List<UserGroup> userGroupList = new ArrayList<>();

        String sql = "SELECT * FROM user_groups;";
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                UserGroup userGroup = new UserGroup();
                userGroup.setId(rs.getInt("id"));
                userGroup.setName(rs.getString("name"));

                userGroupList.add(userGroup);
            }
        }

        return userGroupList;
    }

    public static void save(Connection connection, UserGroup userGroup) throws SQLException {
        if (userGroup.getId() == null) {

            String sql = "INSERT INTO user_groups(name) VALUES(?)";
            String[] generatedColumns = {"id"};

            try (PreparedStatement statement = connection.prepareStatement(sql, generatedColumns)) {
                statement.setString(1, userGroup.getName());

                statement.executeUpdate();

                ResultSet rs = statement.getGeneratedKeys();
                if (rs.next()) {
                    Integer id = rs.getInt(1);
                }
            }
        } else {
            String sql = "UPDATE user_groups SET name=? WHERE id=?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, userGroup.getName());
                statement.setInt(2, userGroup.getId());
                statement.execute();
            }
        }
    }

    public static void delete(Connection connection, UserGroup userGroup) throws SQLException {
        if (userGroup.getId() != null) {

            String sql = "DELETE FROM user_groups WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, userGroup.getId());
                statement.execute();
            }

            Integer id = null;
        }
    }

}
