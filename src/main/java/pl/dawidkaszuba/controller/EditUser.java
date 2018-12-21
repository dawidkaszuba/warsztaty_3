package pl.dawidkaszuba.controller;

import pl.dawidkaszuba.model.DbUtil;
import pl.dawidkaszuba.model.User;
import pl.dawidkaszuba.model.UserGroup;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/EditUser")
public class EditUser extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String userEmail = request.getParameter("userEmail");
        int userGroupId = Integer.parseInt(request.getParameter("userGroupId"));
        int id=0;
        Cookie[] cookies = request.getCookies();
        for(Cookie cookie: cookies){
            if(cookie.getName().equals("id")){
                id = Integer.parseInt(cookie.getValue());
            }
        }

        User user= new User(id,userEmail,userName,userGroupId);
        try {
            user.save(DbUtil.getConn());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        response.sendRedirect("/ManageUsers");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        User user = new User(id);
        try {
            List<UserGroup> userGroups = UserGroup.findAll(DbUtil.getConn());
            request.setAttribute("userGroups",userGroups);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.setAttribute("user",user);
        getServletContext().getRequestDispatcher("/WEB-INF/view/EditUser.jsp").forward(request,response);



    }
}
