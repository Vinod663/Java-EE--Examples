package org.example;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
@WebFilter("/*")//dbcp
public class CORSFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterchain) throws IOException, ServletException {
        HttpServletResponse response= (HttpServletResponse) servletResponse;
        response.setHeader("Access-Control-Allow-Origin", "*");//http://localhost:63342
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        filterchain.doFilter(servletRequest, servletResponse);

    }
}
