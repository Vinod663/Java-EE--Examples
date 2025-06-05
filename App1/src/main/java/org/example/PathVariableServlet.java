package org.example;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

@WebServlet("/pathVariable/*") // URL එකේ path එකක් ලෙස data යවන්න මෙය භාවිතා කරයි
public class PathVariableServlet extends HttpServlet {
    @Override
    protected void doGet(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response) throws java.io.IOException {
        String pathInfo = request.getPathInfo();//http://localhost:8080/App1_Web_exploded/pathVariable/Niloshana/Kadalana
        String[] parts = pathInfo.split("/");
        System.out.println(parts[1]);//Niloshana
        System.out.println(parts[2]);//Kadalana

    }
}
