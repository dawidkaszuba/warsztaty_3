package pl.dawidkaszuba.model;

import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class User {

    private Integer id;
    private String email;
    private String username;
    private String password;
    private int userGroupId;

    public User(String email, String username, String password, int userGroupId) {
        this.email = email;
        this.username = username;
        this.setPassword(password);
        this.userGroupId = userGroupId;
    }

    public User() {
    }

    public User(Integer id, String email, String username, String password, int userGroupId) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.setPassword(password);
        this.userGroupId = userGroupId;
    }

    public User(Integer id) {
        this.id = id;
    }

    public User(Integer id, String email, String username, int userGroupId) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.userGroupId = userGroupId;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public int getUserGroupId() {
        return userGroupId;
    }

    public void setUserGroupId(int userGroupId) {
        this.userGroupId = userGroupId;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", userGroupId=" + userGroupId +
                '}'+"\n";
    }


}