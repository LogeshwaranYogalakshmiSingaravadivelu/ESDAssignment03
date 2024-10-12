package com.csye6220.assignment3.controller;

import com.csye6220.assignment3.dao.BooksDao;
import com.csye6220.assignment3.model.Books;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * bookController.java
 * This servlet acts as a page controller for the book application, handling all
 * requests from the user.
 */
@WebServlet(name = "bookController", urlPatterns = {"/part7_book", "/part7_showForm", "/part7_insert", "/part7_list"})
public class bookController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private BooksDao booksDao;

    public void init() {
        booksDao = new BooksDao();
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
            case "/part7_showForm":  // Show the form for adding a new book
                showBookForm(request, response);
                break;
            case "/part7_insert":  // Insert a new book into the database
                insertBooks(request, response);
                break;
            default:  // Default action: List all books
                listBooks(request, response);
                break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listBooks(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Books> listBooks = booksDao.selectAllBooks();
        request.setAttribute("listBooks", listBooks);
        RequestDispatcher dispatcher = request.getRequestDispatcher("book-list.jsp");
        dispatcher.forward(request, response);
    }
    
    private void showBookForm(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    int count = Integer.parseInt(request.getParameter("count"));  // Get the number of books
    request.setAttribute("count", count);  // Pass the count to the JSP page
    RequestDispatcher dispatcher = request.getRequestDispatcher("book-form.jsp");
    dispatcher.forward(request, response);  // Forward to the form with multiple input fields
}


    private void insertBooks(HttpServletRequest request, HttpServletResponse response) 
            throws SQLException, IOException {
        String[] isbns = request.getParameterValues("isbn");
        String[] titles = request.getParameterValues("title");
        String[] authors = request.getParameterValues("authors");
        String[] prices = request.getParameterValues("price");

        List<Books> booksList = new ArrayList<>();
        
        for (int i = 0; i < isbns.length; i++) {
            String isbn = isbns[i];
            String title = titles[i];
            String author = authors[i];
            float price = Float.parseFloat(prices[i]);

            Books book = new Books(isbn, title, author, price);
            booksList.add(book);
        }

        booksDao.insertBooks(booksList);
        response.sendRedirect("part7_list");
    }
}