package pl.dawidkaszuba.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Exercise {
    private Integer id;
    private String title;
    private String description;

    public Exercise(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Exercise(Integer id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public Exercise() {
    }

    public static Exercise findById(Connection connection, int id) throws SQLException {
        String sql = "SELECT * FROM exercises WHERE id=?";
        try(PreparedStatement preStmt = connection.prepareStatement(sql)) {
            preStmt.setInt(1,id);
            ResultSet rs = preStmt.executeQuery();
            if(rs.next()) {
                Exercise exercise = new Exercise();
                exercise.id = rs.getInt("id");
                exercise.title = rs.getString("title");
                exercise.description = rs.getString("description");

                return exercise;
            }

        }

        return null;
    }

    public static List<Exercise>  findAll(Connection connection) throws SQLException {
        String sql = "SELECT * FROM exercises;";
        List<Exercise> exercises = new ArrayList<>();

        try (Statement stat = connection.createStatement()) {
            ResultSet rs = stat.executeQuery(sql);
            while (rs.next()) {
                Exercise exercise = new Exercise();
                exercise.id = rs.getInt("id");
                exercise.title = rs.getString("title");
                exercise.description = rs.getString("description");
                exercises.add(exercise);

            }

        }
        return exercises;
    }

    public void delete(Connection connection) throws SQLException {
        if (id != null) {

            String sql = "DELETE FROM exercises WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, id);
                statement.execute();
            }

            id = null;
        }
    }

    public void save(Connection connection) throws SQLException {
        if (id == null) {

            String sql = "INSERT INTO exercises(title, description) VALUES(?, ?)";
            String[] generatedColumns = {"id"};

            try (PreparedStatement statement = connection.prepareStatement(sql, generatedColumns)) {
                statement.setString(1, title);
                statement.setString(2, description);
                statement.executeUpdate();

                ResultSet rs = statement.getGeneratedKeys();
                if (rs.next()) {
                    id = rs.getInt(1);
                }
            }
        } else {
            String sql = "UPDATE exercises SET title=?, description=? WHERE id=?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, title);
                statement.setString(2, description);
                statement.setInt(3, id);
                statement.execute();
            }
        }
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Exercise{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}