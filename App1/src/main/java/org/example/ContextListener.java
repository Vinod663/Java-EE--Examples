package org.example;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.SQLException;

@WebListener
public class ContextListener implements ServletContextListener {//1
    @Override
    public void contextInitialized(jakarta.servlet.ServletContextEvent sce) {
        System.out.println("Context initialized");
        // You can perform initialization tasks here
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/eventdb");
        ds.setUsername("root");
        ds.setPassword("Ijse@1234");
        ds.setInitialSize(50);
        ds.setMaxTotal(100);

        ServletContext context = sce.getServletContext();
        context.setAttribute("dataSource", ds); // Store the DataSource in the ServletContext

    }

    @Override
    public void contextDestroyed(jakarta.servlet.ServletContextEvent sce) {
        System.out.println("Context destroyed");
        // You can perform cleanup tasks here
        try {
            ServletContext context = sce.getServletContext();
            BasicDataSource ds = (BasicDataSource) context.getAttribute("dataSource");
            ds.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
