package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
@WebServlet("/event")
public class Main extends HttpServlet {
/*@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        *//*resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");*//*
        *//*resp.setHeader("Access-Control-Allow-Headers", "Content-Type");*//*
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection
                ("jdbc:mysql://localhost:3306/eventdb",
                        "root", "Ijse@1234");

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
        resp.setContentType("application/json");
        ObjectMapper mapper=new ObjectMapper();
        mapper.writeValue(resp.getWriter(), elist);
    } catch (ClassNotFoundException e) {
        throw new RuntimeException(e);
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
}*/

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {///
        /*resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        resp.setHeader("Access-Control-Allow-Headers", "Content-Type");*/

        // Read JSON data from frontend
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> event = mapper.readValue(req.getReader(), Map.class);

        String eid = event.get("eid");
        String ename = event.get("ename");
        String edescription = event.get("edescription");
        String edate = event.get("edate");
        String eplace = event.get("eplace");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/eventdb", "root", "Ijse@1234");

            String query = "INSERT INTO eventtable (id, name, description, date, place) VALUES (?, ?, ?, ?, ?)";
            var stmt = connection.prepareStatement(query);
            stmt.setString(1, eid);
            stmt.setString(2, ename);
            stmt.setString(3, edescription);
            stmt.setString(4, edate);
            stmt.setString(5, eplace);

            int result = stmt.executeUpdate();

            Map<String, String> response = new HashMap<>();
            if (result > 0) {
                response.put("message", "Event created successfully");
            } else {
                response.put("message", "Event creation failed");
            }

            resp.setContentType("application/json");
            mapper.writeValue(resp.getWriter(), response);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {///
        /*resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        resp.setHeader("Access-Control-Allow-Headers", "Content-Type");*/

        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> event = mapper.readValue(req.getInputStream(), Map.class);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/eventdb", "root", "Ijse@1234");

            PreparedStatement stmt = connection.prepareStatement(
                    "UPDATE eventtable  SET name = ?, description = ?, date = ?, place = ? WHERE id = ?"
            );
            stmt.setString(1, event.get("ename"));
            stmt.setString(2, event.get("edescription"));
            stmt.setString(3, event.get("edate"));
            stmt.setString(4, event.get("eplace"));
            stmt.setString(5, event.get("eid"));

            int rows = stmt.executeUpdate();
            resp.setContentType("application/json");
            mapper.writeValue(resp.getWriter(), rows);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {//////
        /*resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        resp.setHeader("Access-Control-Allow-Headers", "Content-Type");*/


        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> body = mapper.readValue(req.getInputStream(), Map.class);
        String eid = body.get("eid");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/eventdb", "root", "Ijse@1234");

            PreparedStatement stmt = connection.prepareStatement("DELETE FROM eventtable WHERE id = ?");
            stmt.setString(1, eid);

            int rows = stmt.executeUpdate();
            resp.setContentType("application/json");
            mapper.writeValue(resp.getWriter(), rows);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws IOException {///
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        resp.setHeader("Access-Control-Allow-Headers", "Content-Type");
        resp.setStatus(HttpServletResponse.SC_OK);
    }




}