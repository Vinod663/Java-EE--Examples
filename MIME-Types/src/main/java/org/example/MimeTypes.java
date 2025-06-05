package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.stream.Collectors;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
@WebServlet("/mime")
@MultipartConfig  // file keihipayak upload karanawanm meee annotation eka use karanna ona. nathnm 500 error eka enawa
public class MimeTypes extends HttpServlet {
    /*@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
       //read text//plain data from http request body
        String body = new BufferedReader(new InputStreamReader(
                req.getInputStream())).
                lines().collect(Collectors.joining("\n"));

        resp.setContentType("text/plain");
        resp.getWriter().println(body);


    }*/

        //read x=www-form-urlencoded data from http request
    /*@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
       String name=req.getParameter("name");
       String address=req.getParameter("address");

       resp.setContentType("text/plain");
         resp.getWriter().write(name+"-"+address);
    }*/

    //read multipart/form-data from http request
   /* @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String name = req.getParameter("name");

        Part part = req.getPart("file");
        String submittedFileName = part.getSubmittedFileName();

        resp.setContentType("text/plain");
        resp.getWriter().println(name+"-"+submittedFileName);
    }*/

    //read JSON data from http request
    /*@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(req.getReader());

        //data reading
        String name = jsonNode.get("name").asText();
        String address = jsonNode.get("address").asText();


        resp.setContentType("text/plain");
        resp.getWriter().write(name +" "+address);

    }*/


    //how to read JSON Array from http request
    /*@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        List<JsonNode> users = mapper.readValue(req.getReader(),
                new TypeReference<List<JsonNode>>() {});

        for (JsonNode user : users) {
            String name = user.get("name").asText();
            String address = user.get("address").asText();
            System.out.println(name + "-" + address);
        }

        resp.setContentType("text/plain");
        resp.getWriter().println("OK");

    }*/
   
}