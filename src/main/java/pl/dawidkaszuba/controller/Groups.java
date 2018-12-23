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
import java.util.List;

@WebServlet("/groups")
public class Groups extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            List<UserGroup> groupsList = UserGroupDao.findAll(DbUtil.getConn());
            request.setAttribute("groupList",groupsList);
            getServletContext().getRequestDispatcher("/WEB-INF/view/Groups.jsp").forward(request,response);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
