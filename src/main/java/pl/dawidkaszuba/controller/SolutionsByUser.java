package pl.dawidkaszuba.controller;

import pl.dawidkaszuba.model.DbUtil;
import pl.dawidkaszuba.model.Solution;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/SolutionsByUser")
public class SolutionsByUser extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        try {
            List<Solution> solutionsList = Solution.loadAllByUserId(DbUtil.getConn(),id);
            request.setAttribute("solutionsList",solutionsList);
            getServletContext().getRequestDispatcher("/WEB-INF/SolutionsByUser.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
