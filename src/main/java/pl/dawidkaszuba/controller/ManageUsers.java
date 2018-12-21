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
import java.util.List;

@WebServlet("/ManageUsers")
public class ManageUsers extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String newUserName = request.getParameter("newUserName");
        String newUserEmail = request.getParameter("newUserEmail");
        int userGroupId = Integer.parseInt(request.getParameter("userGroupId"));
        String password = "password";

        User user = new User();
        user.setUsername(newUserName);
        user.setEmail(newUserEmail);
        user.setPassword(password);
        user.setUserGroupId(userGroupId);
        try {
            user.save(DbUtil.getConn());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        response.sendRedirect("/ManageUsers");


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            List<User> usersList = User.findAll(DbUtil.getConn());
            List<UserGroup> usersGroups = UserGroup.findAll(DbUtil.getConn());
            request.setAttribute("usersGroups",usersGroups);
            request.setAttribute("usersList",usersList);
            getServletContext().getRequestDispatcher("/WEB-INF/view/ManageUsers.jsp").forward(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
