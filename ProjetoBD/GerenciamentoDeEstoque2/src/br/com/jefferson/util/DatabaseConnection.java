package br.com.jefferson.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

	private static final String URL = "jdbc:mysql://localhost:3306/gerenciamentodeestoque";
    private static final String USER = "root"; // Substitua pelo seu usuário
    private static final String PASSWORD = "admin"; // Substitua pela sua senha

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
            return null;
        }
    }
	
	
}
