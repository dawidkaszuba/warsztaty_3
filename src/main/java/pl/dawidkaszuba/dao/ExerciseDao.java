package pl.dawidkaszuba.dao;

import pl.dawidkaszuba.model.Exercise;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExerciseDao {

    public static Exercise findById(Connection connection, int id) throws SQLException {
        String sql = "SELECT * FROM exercises WHERE id=?";
        try(PreparedStatement preStmt = connection.prepareStatement(sql)) {
            preStmt.setInt(1,id);
            ResultSet rs = preStmt.executeQuery();
            if(rs.next()) {
                Exercise exercise = new Exercise();
                exercise.setId(rs.getInt("id"));
                exercise.setTitle(rs.getString("title"));
                exercise.setDescription(rs.getString("description"));

                return exercise;
            }

        }

        return null;
    }

    public static void delete(Connection connection, Exercise exercise) throws SQLException {
        if (exercise.getId() != null) {

            String sql = "DELETE FROM exercises WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, exercise.getId());
                statement.execute();
            }

            Integer id = null;
        }
    }

    public static void save(Connection connection,Exercise exercise) throws SQLException {
        if (exercise.getId() == null) {

            String sql = "INSERT INTO exercises(title, description) VALUES(?, ?)";
            String[] generatedColumns = {"id"};

            try (PreparedStatement statement = connection.prepareStatement(sql, generatedColumns)) {
                statement.setString(1, String.valueOf(exercise.getId()));
                statement.setString(2, exercise.getDescription());
                statement.executeUpdate();

                ResultSet rs = statement.getGeneratedKeys();
                if (rs.next()) {
                    Integer id = rs.getInt(1);
                }
            }
        } else {
            String sql = "UPDATE exercises SET title=?, description=? WHERE id=?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, exercise.getTitle());
                statement.setString(2, exercise.getDescription());
                statement.setInt(3, exercise.getId());
                statement.execute();
            }
        }
    }

    public static List<Exercise> findAll(Connection connection) throws SQLException {
        String sql = "SELECT * FROM exercises;";
        List<Exercise> exercises = new ArrayList<>();

        try (Statement stat = connection.createStatement()) {
            ResultSet rs = stat.executeQuery(sql);
            while (rs.next()) {
                Exercise exercise = new Exercise();
                exercise.setId(rs.getInt("id"));
                exercise.setTitle(rs.getString("title"));
                exercise.setDescription(rs.getString("description"));
                exercises.add(exercise);
            }
        }
        return exercises;
    }

}
