package org.example;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

@WebServlet("/queryStringParam")//URL Eke data yawanna me kramaya use karai
public class QueryStringParamServlet extends HttpServlet {
    protected void doGet(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response) throws java.io.IOException {
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        System.out.println("Name: "+name);
        System.out.println("Address: "+address);
    }
}
