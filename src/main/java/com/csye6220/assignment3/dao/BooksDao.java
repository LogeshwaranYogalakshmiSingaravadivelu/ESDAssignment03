/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.csye6220.assignment3.dao;

import com.csye6220.assignment3.model.Books;
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
public class BooksDao {

    private String jdbcURL = "jdbc:mysql://localhost:3306/moviedb?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "password";

    public BooksDao() {
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

    public void insertBooks(List<Books> books) throws SQLException {
        String INSERT_BOOK_SQL = "INSERT INTO books (isbn, title, authors, price) VALUES (?, ?, ?, ?);";

        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_BOOK_SQL)) {

            connection.setAutoCommit(false);

            for (Books book : books) {
                preparedStatement.setString(1, book.getIsbn());
                preparedStatement.setString(2, book.getTitle());
                preparedStatement.setString(3, book.getAuthors());
                preparedStatement.setFloat(4, book.getPrice());

                preparedStatement.addBatch();
            }

            preparedStatement.executeBatch();
            connection.commit();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Failed to insert books batch. Rolling back changes.");
        }
    }

    public List<Books> selectAllBooks() {

        List<Books> books = new ArrayList<>();
        String SELECT_ALL_BOOKS = "SELECT isbn, title, authors, price FROM books";

        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_BOOKS)) {

            System.out.println(preparedStatement);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String isbn = rs.getString("isbn");
                String title = rs.getString("title");
                String authors = rs.getString("authors");
                float price = rs.getFloat("price");

                books.add(new Books(isbn, title, authors, price));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }

        return books; // Return the list of books
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
