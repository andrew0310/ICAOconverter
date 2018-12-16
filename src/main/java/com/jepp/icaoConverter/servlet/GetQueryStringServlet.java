package com.jepp.icaoConverter.servlet;

import com.jepp.icaoConverter.dao.Converter;
import com.jepp.icaoConverter.dao.ICAOcodeDAO;
import com.jepp.icaoConverter.model.WordInput;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet("/index.jsp")
public class GetQueryStringServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String queryString = req.getParameter("queryString");
        String type = req.getParameter("type");

        if (queryString.isEmpty()) {
            resp.sendRedirect("/index?error");
            return;
        }

        WordInput wordInput = new WordInput();   //zapis słowa do bazy
        wordInput.setConvertedWord(queryString);  //zapis słowa do bazy
        wordInput.setDateTime(LocalDateTime.now());  //zapis słowa do bazy
        ICAOcodeDAO.saveStringToDatabase(wordInput);  //zapis słowa do bazy

        //tutaj kod do konwertowania słowa na kod ICAO
        Converter converter = new Converter();
        converter.convertGivenWord(queryString, type);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("index.jsp");
        requestDispatcher.forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
