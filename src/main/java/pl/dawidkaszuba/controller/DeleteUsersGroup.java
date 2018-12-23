package pl.dawidkaszuba.controller;

import pl.dawidkaszuba.dao.UserGroupDao;
import pl.dawidkaszuba.model.DbUtil;
import pl.dawidkaszuba.model.UserGroup;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/DeleteUsersGroup")
public class DeleteUsersGroup extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        UserGroup userGroup = new UserGroup(id,name);
        try {
            UserGroupDao.delete(DbUtil.getConn(),userGroup);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            UserGroupDao.delete(DbUtil.getConn(),userGroup);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        response.sendRedirect("/ManageUsersGroups");

    }
}
