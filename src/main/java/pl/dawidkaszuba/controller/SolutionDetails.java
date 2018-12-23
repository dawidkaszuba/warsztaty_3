package pl.dawidkaszuba.controller;

import pl.dawidkaszuba.dao.SolutionDao;
import pl.dawidkaszuba.model.DbUtil;
import pl.dawidkaszuba.model.Solution;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/SolutionDetails")
public class SolutionDetails extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        int id = Integer.parseInt(request.getParameter("id"));

        try {
            Solution solution = SolutionDao.findById(DbUtil.getConn(),id);
            request.setAttribute("solution",solution);
            getServletContext().getRequestDispatcher("/WEB-INF/view/SolutionDetails.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
