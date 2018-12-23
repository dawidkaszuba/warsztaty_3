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

    public Exercise(Integer id) {
        this.id = id;
    }

    public Exercise() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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