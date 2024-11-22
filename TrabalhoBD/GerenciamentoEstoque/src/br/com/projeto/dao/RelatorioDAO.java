package br.com.projeto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RelatorioDAO {
    private Connection connection;

    public RelatorioDAO(Connection connection) {
        this.connection = connection;
    }

    // Gerar relatório de produtos com baixo estoque
    public void relatorioBaixoEstoque() throws SQLException {
        String sql = "CALL relatorioBaixoEstoque()";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                System.out.println("Produto ID: " + rs.getInt("id_produto"));
                System.out.println("Nome: " + rs.getString("nome"));
                System.out.println("Quantidade em Estoque: " + rs.getInt("quantidade"));
                System.out.println("----------------------");
            }
        }
    }

    // Gerar relatório de vendas e lucros
    public void relatorioVendasLucros() throws SQLException {
        String sql = "CALL relatorioVendasLucros()";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                System.out.println("Produto ID: " + rs.getInt("id_produto"));
                System.out.println("Nome: " + rs.getString("nome"));
                System.out.println("Lucro: " + rs.getDouble("lucro"));
                System.out.println("----------------------");
            }
        }
    }
}