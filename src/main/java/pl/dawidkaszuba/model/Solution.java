package pl.dawidkaszuba.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Solution {
    private Integer id;
    private String created;
    private String updated;
    private String description;
    private int exercise_id;
    private int userId;
    private int mark;
    private String comment;

    public Solution(int exercise_id, int userId) {
        this.exercise_id = exercise_id;
        this.userId = userId;
    }

    public Solution() {
    }

    public Integer getId() {
        return id;
    }

    public String getCreated() {
        return created;
    }

    public String getUpdated() {
        return updated;
    }

    public String getDescription() {
        return description;
    }

    public int getExerciseId() {
        return exercise_id;
    }

    public int getUserId() {
        return userId;
    }

    public void setCreated(String created) {


        this.created=created;
    }


    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getMark() {
        return mark;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setExerciseId(int exercise_id) {
        this.exercise_id = exercise_id;
    }

    public void setUserId(int users_id) {
        this.userId = users_id;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public static Solution findById(Connection connection, int id) throws SQLException {
        String sql = "SELECT * FROM solutions WHERE id=?";
        try(PreparedStatement preStmt = connection.prepareStatement(sql)) {
            preStmt.setInt(1,id);
            ResultSet rs = preStmt.executeQuery();
            if(rs.next()) {
                Solution solution = new Solution();
                solution.created = rs.getString("created");
                solution.updated = rs.getString("updated");
                solution.description = rs.getString("description");
                solution.exercise_id = rs.getInt("exercise_id");
                solution.userId = rs.getInt("user_id");

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
                solution.created = rs.getString("created");
                solution.updated = rs.getString("updated");
                solution.description = rs.getString("description");
                solution.exercise_id =rs.getInt("exercise_id");
                solution.userId = rs.getInt("user_id");
                solution.comment = rs.getString("comment");
                solutions.add(solution);

            }

        }
        return solutions;
    }

    public void delete(Connection connection) throws SQLException {
        if (id != null) {

            String sql = "DELETE FROM solutions WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, id);
                statement.execute();
            }

            id = null;
        }
    }

    public void save(Connection connection) throws SQLException {
        if (id == null) {

            String sql = "INSERT INTO solutions(exercise_id,user_id) VALUES(?,?)";
            String[] generatedColumns = {"id"};

            try (PreparedStatement statement = connection.prepareStatement(sql, generatedColumns)) {
                statement.setInt(1,exercise_id);
                statement.setInt(2,userId);

                statement.executeUpdate();

                ResultSet rs = statement.getGeneratedKeys();
                if (rs.next()) {
                    id = rs.getInt(1);
                }
            }
        } else {

            String sql = "UPDATE solutions SET updated=?, description=?, exercise_id=?, user_id=? WHERE id=?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {

                Timestamp timestamp = new Timestamp(new Date().getTime());
                statement.setString(1, String.valueOf(timestamp));
                statement.setString(2,description);
                statement.setInt(3,exercise_id);
                statement.setInt(4,userId);
                statement.setInt(5,id);
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
                solution.id = rs.getInt("id");
                solution.created = rs.getString("created");
                solution.updated = rs.getString("updated");
                solution.description = rs.getString("description");
                solution.exercise_id = rs.getInt("exercise_id");
                solution.userId = rs.getInt("user_id");
                solution.mark =rs.getInt("mark");
                solution.comment = rs.getString("comment");
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
                solution.created = rs.getString("created");
                solution.updated = rs.getString("updated");
                solution.description = rs.getString("description");
                solution.exercise_id = rs.getInt("exercise_id");
                solution.userId = rs.getInt("user_id");
                solution.mark =rs.getInt("mark");
                solution.comment = rs.getString("comment");
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
                solution.id = resultSet.getInt("id");
                solution.created = resultSet.getString("created");
                solution.updated = resultSet.getString("updated");
                solution.description = resultSet.getString("description");
                solution.exercise_id = resultSet.getInt("exercise_id");
                solution.userId = resultSet.getInt("user_id");
                solution.mark =resultSet.getInt("mark");
                solution.comment = resultSet.getString("comment");
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
                solution.id = resultSet.getInt("id");
                solution.created = resultSet.getString("created");
                solution.updated = resultSet.getString("updated");
                solution.description = resultSet.getString("description");
                solution.exercise_id = resultSet.getInt("exercise_id");
                solution.userId =  resultSet.getInt("user_id");
                solution.mark =resultSet.getInt("mark");
                solution.comment = resultSet.getString("comment");
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
                solution.id = resultSet.getInt("id");
                solution.created = resultSet.getString("created");
                solution.updated = resultSet.getString("updated");
                solution.description = resultSet.getString("description");
                solution.exercise_id = resultSet.getInt("exercise_id");
                solution.userId = resultSet.getInt("user_id");
                solution.mark = resultSet.getInt("mark");
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

    @Override
    public String toString() {
        return "Solution{" +
                "id=" + id +
                ", created='" + created + '\'' +
                ", updated='" + updated + '\'' +
                ", description='" + description + '\'' +
                ", exercise_id=" + exercise_id +
                ", users_id=" + userId +
                '}';
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
                    solution.created = rs.getString("created");
                    solution.updated = rs.getString("updated");
                    solution.description = rs.getString("description");
                    solution.exercise_id = rs.getInt("exercise_id");
                    solution.userId = rs.getInt("user_id");
                    solutions.add(solution);
                }
            }
        }
        return solutions;
    }
}
