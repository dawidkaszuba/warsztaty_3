package pl.dawidkaszuba.controller;

import pl.dawidkaszuba.dao.ExerciseDao;
import pl.dawidkaszuba.model.DbUtil;
import pl.dawidkaszuba.model.Exercise;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/ManageExercises")
public class ManageExercises extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String titleExercises = request.getParameter("titleExercises");
        String descriptionExercises = request.getParameter("descriptionExercises");

        Exercise exercise = new Exercise(titleExercises,descriptionExercises);
        try {
            ExerciseDao.save(DbUtil.getConn(),exercise);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        response.sendRedirect("/ManageExercises");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            List<Exercise> exercises = ExerciseDao.findAll(DbUtil.getConn());
            request.setAttribute("exercises",exercises);
            getServletContext().getRequestDispatcher("/WEB-INF/view/ManageExercises.jsp").forward(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
