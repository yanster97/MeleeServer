package me.kevinyan;

import com.google.gson.Gson;
import java.sql.*;
import java.util.ArrayList;
import java.util.Map;


import static spark.Spark.*;
import spark.template.freemarker.FreeMarkerEngine;
import spark.ModelAndView;

import com.heroku.sdk.jdbc.DatabaseUrl;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import me.kevinyan.melee.Bracket;
import me.kevinyan.melee.Match;
import me.kevinyan.melee.Player;
import me.kevinyan.melee.Round;
import static spark.Spark.get;

public class MeleeServer {

  public static void main(String[] args) {

    port(50000);
    staticFileLocation("/public");

    get("/hello", (req, res) -> "Hello World");

    get("/", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("message", "Hello World!");

            return new ModelAndView(attributes, "index.ftl");
        }, new FreeMarkerEngine());

    
    post("/submitPlayers", (req, res) -> {
        String allPlayersJSON = req.body();
        //List<String> playerNames = req.getPlayers();
        //Bracket br = new Bracket(playerNames);
        //database.save(br);
        //res.status(200);
        Gson g = new Gson();
        Map pmap = g.fromJson(allPlayersJSON, HashMap.class);
        List<String> players = (List<String>)pmap.get("players");
        Bracket b = new Bracket(players.stream().map(pn -> new Player(pn)).collect(Collectors.toCollection(ArrayList::new)));
        for(int i = 0; i < b.rounds.size(); i++) {
            Round r = b.rounds.get(i);
            for(int j = 0; j < r.matches.size(); j++) {
                Match m = r.matches.get(j);
                System.out.println("Round " + i + ", match " + j + ", players:" + (m.topPlayer == null ? null : m.topPlayer.name) + ", " + (m.bottomPlayer == null ? null : m.bottomPlayer.name));
            }
        }
        //make json representation of matches, send to front end, and display
        return "testret";
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
