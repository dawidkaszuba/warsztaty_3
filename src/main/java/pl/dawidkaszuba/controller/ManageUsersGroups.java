package pl.dawidkaszuba.controller;

import pl.dawidkaszuba.dao.UserGroupDao;
import pl.dawidkaszuba.model.DbUtil;
import pl.dawidkaszuba.model.Exercise;
import pl.dawidkaszuba.model.UserGroup;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/ManageUsersGroups")
public class ManageUsersGroups extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String nameOfNewGroup = request.getParameter("newUserGroup");
        UserGroup userGroup = new UserGroup(nameOfNewGroup);

        try {
            UserGroupDao.save(DbUtil.getConn(),userGroup);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        response.sendRedirect("/ManageUsersGroups");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            List<UserGroup> userGroups = UserGroupDao.findAll(DbUtil.getConn());
            request.setAttribute("userGroups",userGroups);
            getServletContext().getRequestDispatcher("/WEB-INF/view/ManageUsersGroups.jsp").forward(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
