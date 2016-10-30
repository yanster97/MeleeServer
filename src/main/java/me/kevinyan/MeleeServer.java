package me.kevinyan;

import java.sql.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;


import static spark.Spark.*;
import spark.template.freemarker.FreeMarkerEngine;
import spark.ModelAndView;

import com.heroku.sdk.jdbc.DatabaseUrl;
import static spark.Spark.get;

public class MeleeServer {

  public static void main(String[] args) {

    port(Integer.valueOf(System.getenv("PORT")));
    staticFileLocation("/public");

    get("/hello", (req, res) -> "Hello World");

    get("/", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("message", "Hello World!");

            return new ModelAndView(attributes, "index.ftl");
        }, new FreeMarkerEngine());

    
    post("/submitPlayers", (req, res) -> {
        
        for(String s: req.attributes())
        {
            System.out.println(s);
        }
        System.out.println(req.body());
        System.out.println("req body sent");
        //List<String> playerNames = req.getPlayers();
        //Bracket br = new Bracket(playerNames);
        //database.save(br);
        res.status(200);
        return null;
    });

    get("/admin", (req, res) -> {
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("message", "Hello World!");
        return new ModelAndView(attributes, "admin.ftl");
    }, new FreeMarkerEngine());

    get("/db", (req, res) -> {
      Connection connection = null;
      Map<String, Object> attributes = new HashMap<>();
      try {
        connection = DatabaseUrl.extract().getConnection();

        Statement stmt = connection.createStatement();
        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS ticks (tick timestamp)");
        stmt.executeUpdate("INSERT INTO ticks VALUES (now())");
        ResultSet rs = stmt.executeQuery("SELECT tick FROM ticks");

        ArrayList<String> output = new ArrayList<String>();
        while (rs.next()) {
          output.add( "Read from DB: " + rs.getTimestamp("tick"));
        }

        attributes.put("results", output);
        return new ModelAndView(attributes, "db.ftl");
      } catch (Exception e) {
        attributes.put("message", "There was an error: " + e);
        return new ModelAndView(attributes, "error.ftl");
      } finally {
        if (connection != null) try{connection.close();} catch(SQLException e){}
      }
    }, new FreeMarkerEngine());

  }

}
