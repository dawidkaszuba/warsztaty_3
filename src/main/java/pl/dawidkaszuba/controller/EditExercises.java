package pl.dawidkaszuba.controller;

import pl.dawidkaszuba.dao.ExerciseDao;
import pl.dawidkaszuba.model.DbUtil;
import pl.dawidkaszuba.model.Exercise;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/EditExercises")
public class EditExercises extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Cookie[] cookies = request.getCookies();
        int id=0;
        for (Cookie cookie:cookies) {
            if(cookie.getName().equals("id")){
                id = Integer.parseInt(cookie.getValue());
            }

        }
        String title = request.getParameter("newTitle");
        String description = request.getParameter("newDescription");

        Exercise exercise = new Exercise(id,title,description);
        try {
            ExerciseDao.save(DbUtil.getConn(),exercise);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.sendRedirect("/ManageExercises");
    }



    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    getServletContext().getRequestDispatcher("/WEB-INF/view/EditExercise.jsp").forward(request,response);

    }
}
