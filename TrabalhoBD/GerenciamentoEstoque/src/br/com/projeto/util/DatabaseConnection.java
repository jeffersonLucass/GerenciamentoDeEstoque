package br.com.projeto.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/GerenciamentoDeEstoque";
    private static final String USER = "root"; // Substitua pelo usuário do MySQL
    private static final String PASSWORD = "admin"; // Substitua pela senha do MySQL

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
  }
    
}
