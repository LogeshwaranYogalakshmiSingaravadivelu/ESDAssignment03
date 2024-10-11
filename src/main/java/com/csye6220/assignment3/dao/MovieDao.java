/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.csye6220.assignment3.dao;

import com.csye6220.assignment3.model.Movie;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author yslog
 */
public class MovieDao {

    private String jdbcURL = "jdbc:mysql://localhost:3306/moviedb?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "password";

    public MovieDao() {
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
            System.out.println("Connected to the database successfully!");
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver not found!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Database connection failed!");
            e.printStackTrace();
        }
        return connection;
    }

    public void insertMovie(Movie movie) throws SQLException {
        String INSERT_MOVIE_SQL = "INSERT INTO movies (title, actor, actress, genre, year) VALUES (?, ?, ?, ?, ?);";

        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_MOVIE_SQL)) {

            preparedStatement.setString(1, movie.getTitle());
            preparedStatement.setString(2, movie.getActor());
            preparedStatement.setString(3, movie.getActress());
            preparedStatement.setString(4, movie.getGenre());
            preparedStatement.setInt(5, movie.getYear());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public Movie selectMovieByTitle(String title) {
        Movie movie = null;
        String SELECT_MOVIE_BY_TITLE = "SELECT title, actor, actress, genre, year FROM movies WHERE title = ?";

        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_MOVIE_BY_TITLE)) {

            preparedStatement.setString(1, title); // Use title as the search parameter
            System.out.println(preparedStatement); // For debugging

            // Execute the query
            ResultSet rs = preparedStatement.executeQuery();

            // Process the ResultSet object
            if (rs.next()) {
                String actor = rs.getString("actor");
                String actress = rs.getString("actress");
                String genre = rs.getString("genre");
                int year = rs.getInt("year");

                // Construct the Movie object using the retrieved values
                movie = new Movie(title, actor, actress, genre, year);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return movie;
    }

    public List<Movie> selectAllMovies() {

        // using try-with-resources to avoid closing resources
        List<Movie> movies = new ArrayList<>();
        String SELECT_ALL_MOVIES = "SELECT title, actor, actress, genre, year FROM movies"; // SQL query to get all movies

        // Step 1: Establishing a Connection
        try (Connection connection = getConnection(); // Step 2: Create a statement using connection object
                 PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_MOVIES)) {

            System.out.println(preparedStatement);

            // Step 3: Execute the query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                String title = rs.getString("title");
                String actor = rs.getString("actor");
                String actress = rs.getString("actress");
                String genre = rs.getString("genre");
                int year = rs.getInt("year");

                // Add each movie to the list
                movies.add(new Movie(title, actor, actress, genre, year));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }

        return movies; // Return the list of movies
    }

    public List<Movie> selectMoviesByActor(String actor) {
        List<Movie> movies = new ArrayList<>();
        String SELECT_MOVIE_BY_ACTOR = "SELECT title, actor, actress, genre, year FROM movies WHERE actor = ?";

        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_MOVIE_BY_ACTOR)) {

            preparedStatement.setString(1, actor); // Use actor as the search parameter
            System.out.println(preparedStatement); // For debugging

            // Execute the query
            ResultSet rs = preparedStatement.executeQuery();

            // Process the ResultSet object
            while (rs.next()) {
                String title = rs.getString("title");
                String actress = rs.getString("actress");
                String genre = rs.getString("genre");
                int year = rs.getInt("year");

                // Add each movie to the list
                movies.add(new Movie(title, actor, actress, genre, year));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return movies; // Return the list of movies for the actor
    }

    public List<Movie> selectMoviesByActress(String actress) {
        List<Movie> movies = new ArrayList<>();
        String SELECT_MOVIE_BY_ACTRESS = "SELECT title, actor, actress, genre, year FROM movies WHERE actress = ?";

        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_MOVIE_BY_ACTRESS)) {

            preparedStatement.setString(1, actress); // Use actress as the search parameter
            System.out.println(preparedStatement); // For debugging

            // Execute the query
            ResultSet rs = preparedStatement.executeQuery();

            // Process the ResultSet object
            while (rs.next()) {
                String title = rs.getString("title");
                String actor = rs.getString("actor");
                String genre = rs.getString("genre");
                int year = rs.getInt("year");

                // Add each movie to the list
                movies.add(new Movie(title, actor, actress, genre, year));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return movies; // Return the list of movies for the actress
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }

}
