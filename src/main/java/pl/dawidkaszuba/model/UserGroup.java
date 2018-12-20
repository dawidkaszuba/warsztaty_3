package pl.dawidkaszuba.model;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserGroup {

    private Integer id;
    private String name;

    public UserGroup(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public UserGroup(String name) {
        this.name = name;
    }

    public UserGroup() {
    }

    public static UserGroup findById(Connection connection, int id) throws SQLException {
        String sql = "SELECT * FROM user_groups WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                UserGroup userGroup = new UserGroup();
                userGroup.id = id;
                userGroup.name = rs.getString("name");

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
                userGroup.id = rs.getInt("id");
                userGroup.name = rs.getString("name");

                userGroupList.add(userGroup);
            }
        }

        return userGroupList;
    }

    public Integer getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "UserGroup{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public void save(Connection connection) throws SQLException {
        if (id == null) {

            String sql = "INSERT INTO user_groups(name) VALUES(?)";
            String[] generatedColumns = {"id"};

            try (PreparedStatement statement = connection.prepareStatement(sql, generatedColumns)) {
                statement.setString(1, name);

                statement.executeUpdate();

                ResultSet rs = statement.getGeneratedKeys();
                if (rs.next()) {
                    id = rs.getInt(1);
                }
            }
        } else {
            String sql = "UPDATE user_groups SET name=? WHERE id=?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, name);
                statement.setInt(2, id);
                statement.execute();
            }
        }
    }

    public void delete(Connection connection) throws SQLException {
        if (id != null) {

            String sql = "DELETE FROM user_groups WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, id);
                statement.execute();
            }

            id = null;
        }
    }
}