package org.example;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

@WebServlet("/lifeCycles")
public class ServletLifeCycle extends HttpServlet {
    @Override
    public void init() {
        System.out.println("ServletLifeCycle initialized");
    }

    @Override
    public void doGet(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response) throws java.io.IOException {
        response.getWriter().println("ServletLifeCycle is running(doGet method)");
    }

    @Override
    public void destroy() {
        System.out.println("ServletLifeCycle destroyed");
    }
}
