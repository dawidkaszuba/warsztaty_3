package pl.dawidkaszuba.controller;

import pl.dawidkaszuba.model.DbUtil;
import pl.dawidkaszuba.model.User;
import pl.dawidkaszuba.model.UserGroup;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/DeleteUser")
public class DeleteUser extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        User user = new User(id);
        try {
            user.delete(DbUtil.getConn());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        response.sendRedirect("/ManageUsers");


    }
}
