package pl.dawidkaszuba.controller;

import pl.dawidkaszuba.dao.UserDao;
import pl.dawidkaszuba.model.DbUtil;
import pl.dawidkaszuba.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/ListOfUsers")
public class ListOfUsers extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            try {
                List<User> userList = UserDao.findAll(DbUtil.getConn());
                request.setAttribute("userList",userList);
                getServletContext().getRequestDispatcher("/WEB-INF/view/ListOfUsers.jsp").forward(request,response);
            } catch (SQLException e) {
                e.printStackTrace();
              //  response.getWriter().append("SQL ERROR");
            }



    }
}
