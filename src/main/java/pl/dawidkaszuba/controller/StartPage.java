package pl.dawidkaszuba.controller;

import pl.dawidkaszuba.dao.SolutionDao;
import pl.dawidkaszuba.model.Solution;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("")
public class StartPage extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int numberSolutions = Integer.parseInt(getServletContext().getInitParameter("number-solutions"));

        try {
            List<Solution> solutionList = SolutionDao.findAll(numberSolutions);
            request.setAttribute("solutionlist", solutionList);
            getServletContext().getRequestDispatcher("/WEB-INF/view/index.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
