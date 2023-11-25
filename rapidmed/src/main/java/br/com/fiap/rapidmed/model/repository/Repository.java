package br.com.fiap.rapidmed.model.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class Repository {

    protected static Connection connection;

    static {
        initializeConnection();
    }

    private static void initializeConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = ConnectionFactory.getInstance().getConexao();
            }
        } catch (Exception e) {
            System.out.println("Error initializing connection: " + e.getMessage());
            e.printStackTrace(); // Print the stack trace for more information
        }
    }

    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                initializeConnection();
            }
            return connection;
        } catch (Exception e) {
            System.out.println("Error getting connection: " + e.getMessage());
            e.printStackTrace(); // Print the stack trace for more information
        }
        return null;
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
 // Método para fechar PreparedStatement
    public static void closePreparedStatement(PreparedStatement preparedStatement) {
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
}
