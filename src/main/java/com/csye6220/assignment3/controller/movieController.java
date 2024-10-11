package com.csye6220.assignment3.controller;

import com.csye6220.assignment3.model.Movie;
import com.csye6220.assignment3.dao.MovieDao;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;


/**
 * MovieServlet.java
 * This servlet acts as a page controller for the movie application, handling all
 * requests from the user.
 */

@WebServlet("/")
public class movieController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private MovieDao movieDao;
    
    public void init() {
        movieDao = new MovieDao();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            switch (action) {
            case "/new":  // Show the form for adding a new movie
                showNewForm(request, response);
                break;
            case "/insert":  // Insert a new movie into the database
                insertMovie(request, response);
                break;
            case "/searchByTitle":  // Search movie by title
                searchMovieByTitle(request, response);
                break;
            case "/searchByActor":  // Search movies by actor
                searchMoviesByActor(request, response);
                break;
            case "/searchByActress":  // Search movies by actress
                searchMoviesByActress(request, response);
                break;
            default:  // Default action: List all movies
                listMovies(request, response);
                break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listMovies(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Movie> listMovies = movieDao.selectAllMovies();
        request.setAttribute("listMovies", listMovies);
        RequestDispatcher dispatcher = request.getRequestDispatcher("movie-list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("movie-form.jsp");
        dispatcher.forward(request, response);
    }

    private void insertMovie(HttpServletRequest request, HttpServletResponse response) 
            throws SQLException, IOException {
        String title = request.getParameter("title");
        String actor = request.getParameter("actor");
        String actress = request.getParameter("actress");
        String genre = request.getParameter("genre");
        int year = Integer.parseInt(request.getParameter("year"));
        
        Movie newMovie = new Movie(title, actor, actress, genre, year);
        movieDao.insertMovie(newMovie);
        response.sendRedirect("list");
    }


    private void searchMovieByTitle(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        String title = request.getParameter("title");
        Movie movie = movieDao.selectMovieByTitle(title);
        request.setAttribute("movie", movie);
        RequestDispatcher dispatcher = request.getRequestDispatcher("movie-details.jsp");
        dispatcher.forward(request, response);
    }

    private void searchMoviesByActor(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        String actor = request.getParameter("actor");
        List<Movie> listMovies = movieDao.selectMoviesByActor(actor);
        request.setAttribute("listMovies", listMovies);
        RequestDispatcher dispatcher = request.getRequestDispatcher("movie-list.jsp");
        dispatcher.forward(request, response);
    }

    private void searchMoviesByActress(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        String actress = request.getParameter("actress");
        List<Movie> listMovies = movieDao.selectMoviesByActress(actress);
        request.setAttribute("listMovies", listMovies);
        RequestDispatcher dispatcher = request.getRequestDispatcher("movie-list.jsp");
        dispatcher.forward(request, response);
    }
}
