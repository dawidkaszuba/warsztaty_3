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

@WebServlet("/DeleteExercise")
public class DeleteExercise extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        Exercise exercise = new Exercise(id);
        try {
            ExerciseDao.delete(DbUtil.getConn(), exercise);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.sendRedirect("/ManageExercises");
    }
}
