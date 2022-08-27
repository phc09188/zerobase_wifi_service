package com.example.zerobase_part1_project;

import DB.HistoryTable;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "HistoryDeleteServlet", value = "/HistoryDeleteServlet")
public class HistoryDeleteServlet extends HttpServlet {
    /*@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }*/

    /*@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        doGet(request,response);
    }*/

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HistoryTable wifiHistory = new HistoryTable();
        String input = req.getParameter("id");
        int affected = wifiHistory.withdraw(input);
        String message = "삭제되지 않았습니다.";
        if (affected > 0) {
            message = "삭제되었습니다.";
        }
        PrintWriter out = resp.getWriter();
        out.print("<html><body><script>");
        out.print("alert(" + message + ");");
        out.print("location.href="+"history.jsp");
        out.print("</html></body></script>");

        resp.sendRedirect("/history.jsp");
    }
}
