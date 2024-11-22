package br.com.projeto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MovimentacaoDAO {
    private Connection connection;

    public MovimentacaoDAO(Connection connection) {
        this.connection = connection;
    }

    // Registrar movimentação de entrada ou saída
    public void registrarMovimentacao(int idProduto, String tipoMovimentacao, int quantidade) throws SQLException {
        String sql = "CALL registrarMovimentacao(?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idProduto);
            stmt.setString(2, tipoMovimentacao);
            stmt.setInt(3, quantidade);
            stmt.executeUpdate();
        }
    }

    // Relatório de movimentação
    public void relatorioMovimentacao() throws SQLException {
        String sql = "CALL relatorioMovimentacao()";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                System.out.println("ID Movimentação: " + rs.getInt("id_movimentacao"));
                System.out.println("Produto: " + rs.getString("produto"));
                System.out.println("Tipo: " + rs.getString("tipo_movimentacao"));
                System.out.println("Quantidade: " + rs.getInt("quantidade"));
                System.out.println("Data: " + rs.getString("data_movimentacao"));
                System.out.println("----------------------");
            }
        }
    }
}