package org.example;




import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/inbuild")
public class InBuildDBCP extends HttpServlet {//2
    @Resource(name = "jdbc/pool")
    private DataSource ds;


    @Override
    protected void doGet(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response) throws java.io.IOException {
        try {
            Connection connection = ds.getConnection();// Get a connection from the pool

            ResultSet resultSet = connection.prepareStatement("SELECT * FROM eventtable").executeQuery();
            List<Map<String, String>> elist = new ArrayList<>();
            while (resultSet.next()) {
                Map<String, String> event=new HashMap<String,String>();
                event.put("eid", resultSet.getString("id"));
                event.put("ename", resultSet.getString("name"));
                event.put("edescription", resultSet.getString("description"));
                event.put("edate", resultSet.getString("date"));
                event.put("eplace", resultSet.getString("place"));
                elist.add(event);
            }
            response.setContentType("application/json");
            ObjectMapper mapper=new ObjectMapper();
            mapper.writeValue(response.getWriter(), elist);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        /*response.setContentType("text/plain");
        response.getWriter().println("DBCP Connection Pool Initialized Successfully");*/

    }
}
