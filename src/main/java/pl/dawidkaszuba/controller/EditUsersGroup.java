package pl.dawidkaszuba.controller;

import pl.dawidkaszuba.model.DbUtil;
import pl.dawidkaszuba.model.UserGroup;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.function.DoubleBinaryOperator;

@WebServlet("/EditUsersGroup")
public class EditUsersGroup extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Cookie[] cookies = request.getCookies();
        int id=0;
        for(Cookie cookie: cookies ) {
            if(cookie.getName().equals("id")) {
                id = Integer.parseInt(cookie.getValue());
            }
        }
        String newNameOfGroup = request.getParameter("newNameOfGroup");
        UserGroup userGroup = new UserGroup(id,newNameOfGroup);
        try {
            userGroup.save(DbUtil.getConn());
            response.sendRedirect("/ManageUsersGroups");

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        UserGroup userGroup = new UserGroup(id,name);

        request.setAttribute("userGroup",userGroup);
        getServletContext().getRequestDispatcher("/WEB-INF/view/EditUsersGroup.jsp").forward(request,response);



    }


}
