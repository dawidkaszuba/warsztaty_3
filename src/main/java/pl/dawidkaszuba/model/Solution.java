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

    public void setId(Integer id) {
        this.id = id;
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


}
