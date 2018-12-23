package pl.dawidkaszuba.dao;

import pl.dawidkaszuba.model.DbUtil;
import pl.dawidkaszuba.model.Solution;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SolutionDao {

    public static Solution findById(Connection connection, int id) throws SQLException {
        String sql = "SELECT * FROM solutions WHERE id=?";
        try(PreparedStatement preStmt = connection.prepareStatement(sql)) {
            preStmt.setInt(1,id);
            ResultSet rs = preStmt.executeQuery();
            if(rs.next()) {
                Solution solution = new Solution();
                solution.setCreated(rs.getString("created"));
                solution.setUpdated(rs.getString("updated"));
                solution.setDescription(rs.getString("description"));
                solution.setExerciseId(rs.getInt("exercise_id"));
                solution.setUserId(rs.getInt("user_id"));

                return solution;
            }
        }
        return null;
    }

    public static List<Solution> findAll(Connection connection) throws SQLException {
        String sql = "SELECT * FROM solutions;";
        List<Solution> solutions = new ArrayList<>();

        try (Statement stat = connection.createStatement()) {
            ResultSet rs = stat.executeQuery(sql);
            while (rs.next()) {
                Solution solution = new Solution();
                solution.setCreated(rs.getString("created"));
                solution.setUpdated(rs.getString("updated"));
                solution.setDescription(rs.getString("description"));
                solution.setExerciseId(rs.getInt("exercise_id"));
                solution.setUserId(rs.getInt("user_id"));
                solution.setComment(rs.getString("comment"));
                solutions.add(solution);
            }
        }
        return solutions;
    }

    public static void delete(Connection connection, Solution solution) throws SQLException {
        if (solution.getId() != null) {

            String sql = "DELETE FROM solutions WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, solution.getId());
                statement.execute();
            }

            Integer id = null;
        }
    }

    public static void save(Connection connection,Solution solution) throws SQLException {
        if (solution.getId() == null) {

            String sql = "INSERT INTO solutions(exercise_id,user_id) VALUES(?,?)";
            String[] generatedColumns = {"id"};

            try (PreparedStatement statement = connection.prepareStatement(sql, generatedColumns)) {
                statement.setInt(1,solution.getExerciseId());
                statement.setInt(2,solution.getUserId());

                statement.executeUpdate();

                ResultSet rs = statement.getGeneratedKeys();
                if (rs.next()) {
                     Integer id = rs.getInt(1);
                }
            }
        } else {

            String sql = "UPDATE solutions SET updated=?, description=?, exercise_id=?, user_id=? WHERE id=?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {

                Timestamp timestamp = new Timestamp(new Date().getTime());
                statement.setString(1, String.valueOf(timestamp));
                statement.setString(2,solution.getDescription());
                statement.setInt(3,solution.getExerciseId());
                statement.setInt(4,solution.getUserId());
                statement.setInt(5,solution.getId());
                statement.execute();
            }
        }
    }
    public static List<Solution> loadAllByUserId(Connection connection, int id) throws SQLException{
        String sql = "SELECT * FROM solutions WHERE user_id=?";
        List<Solution> solutions = new ArrayList<>();

        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1,id);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()) {
                Solution solution = new Solution();
                solution.setId(rs.getInt("id"));
                solution.setCreated(rs.getString("created"));
                solution.setUpdated(rs.getString("updated"));
                solution.setDescription(rs.getString("description"));
                solution.setExerciseId(rs.getInt("exercise_id"));
                solution.setUserId(rs.getInt("user_id"));
                solution.setMark(rs.getInt("mark"));
                solution.setComment(rs.getString("comment"));
                solutions.add(solution);
            }
        }
        return solutions;
    }

    public static List<Solution> loadAllByExerciseId(Connection connection, int id) throws  SQLException{
        String sql = "SELECT * FROM solutions WHERE exercise_id=? ORDER BY created;";
        List<Solution> solutions = new ArrayList<>();

        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1,id);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                Solution solution = new Solution();
                solution.setCreated(rs.getString("created"));
                solution.setUpdated(rs.getString("updated"));
                solution.setDescription(rs.getString("description"));
                solution.setExerciseId(rs.getInt("exercise_id"));
                solution.setUserId(rs.getInt("user_id"));
                solution.setMark(rs.getInt("mark"));
                solution.setComment(rs.getString("comment"));
                solutions.add(solution);
            }
        }
        return solutions;
    }

    public static List<Solution> loadSolutionsByUserId(Connection connection, int id) {
        String sql = "SELECT * FROM solutions WHERE user_id=? ORDER BY created;";
        List<Solution> solutions = new ArrayList<>();
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Solution solution = new Solution();
                solution.setId(resultSet.getInt("id"));
                solution.setCreated(resultSet.getString("created"));
                solution.setUpdated(resultSet.getString("updated"));
                solution.setDescription(resultSet.getString("description"));
                solution.setUserId(resultSet.getInt("exercise_id"));
                solution.setUserId(resultSet.getInt("user_id"));
                solution.setMark(resultSet.getInt("mark"));
                solution.setComment(resultSet.getString("comment"));
                solutions.add(solution);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }

        return solutions;
    }
    public static List<Solution> loadSolutionsWithoutDescriptionByUserId(Connection connection,int id){
        List<Solution> solutions = new ArrayList<>();
        String sql = "SELECT * FROM solutions WHERE user_id=?";

        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Solution solution = new Solution();
                solution.setId(resultSet.getInt("id"));
                solution.setCreated(resultSet.getString("created"));
                solution.setUpdated(resultSet.getString("updated"));
                solution.setDescription(resultSet.getString("description"));
                solution.setExerciseId(resultSet.getInt("exercise_id"));
                solution.setUserId(resultSet.getInt("user_id"));
                solution.setMark(resultSet.getInt("mark"));
                solution.setComment(resultSet.getString("comment"));
                solutions.add(solution);
            }

        }catch(SQLException e){
            e.printStackTrace();
        }

        return solutions;
    }

    public static List<Solution> loadSolutionsWithoutMarkAndDescById(Connection connection, int id){
        List<Solution> solutions = new ArrayList<>();

        String sql = "SELECT * FROM solutions WHERE user_id =? AND mark IS NULL AND description IS NOT NULL;";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1,id);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                resultSet.previous();
                while(resultSet.next()){
                    Solution solution = new Solution();
                    solution.setId(resultSet.getInt("id"));
                    solution.setCreated(resultSet.getString("created"));
                    solution.setUpdated(resultSet.getString("updated"));
                    solution.setDescription(resultSet.getString("description"));
                    solution.setExerciseId(resultSet.getInt("exercise_id"));
                    solution.setUserId(resultSet.getInt("user_id"));
                    solution.setMark(resultSet.getInt("mark"));
                    solutions.add(solution);
                }
            }
            else{
                System.out.println("Brak zadań");
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }

        return solutions;
    }

    public static List<Solution> findAll(Integer limit) throws SQLException {
        List<Solution> solutions = new ArrayList<>();
        try (Connection connection = DbUtil.getConn()) {
            String sql = "SELECT * FROM solutions ORDER BY updated DESC";
            try (Statement statement = connection.createStatement()) {
                if (limit != null) {
                    statement.setFetchSize(limit);
                    statement.setMaxRows(limit);
                    statement.setFetchDirection(ResultSet.FETCH_FORWARD);
                }
                ResultSet rs = statement.executeQuery(sql);
                while (rs.next()) {
                    Solution solution = new Solution();
                    solution.setCreated(rs.getString("created"));
                    solution.setUpdated(rs.getString("updated"));
                    solution.setDescription(rs.getString("description"));
                    solution.setExerciseId(rs.getInt("exercise_id"));
                    solution.setUserId(rs.getInt("user_id"));
                    solutions.add(solution);
                }
            }
        }
        return solutions;
    }
}
