package br.com.jefferson.models;

import br.com.jefferson.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VendaDAO {
	// Método para registrar venda
    public void registrarVenda(Venda venda) {
        String sql = "INSERT INTO vendas (produto_id, quantidade, preco_venda, lucro, data) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, venda.getProdutoId());
            stmt.setInt(2, venda.getQuantidade());
            stmt.setDouble(3, venda.getPrecoVenda());
            stmt.setDouble(4, venda.getLucro());
            stmt.setString(5, venda.getData());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Relatório de vendas e lucros
    public List<Venda> gerarRelatorioVendas() {
        List<Venda> vendas = new ArrayList<>();
        String sql = "SELECT * FROM vendas";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                vendas.add(new Venda(
                        rs.getInt("id"),
                        rs.getInt("produto_id"),
                        rs.getInt("quantidade"),
                        rs.getDouble("preco_venda"),
                        rs.getDouble("lucro"),
                        rs.getString("data")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vendas;
    }

    // Método para calcular o lucro total
    public double calcularLucroTotal() {
        double lucroTotal = 0.0;
        String sql = "SELECT SUM(lucro) AS lucro_total FROM vendas";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                lucroTotal = rs.getDouble("lucro_total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lucroTotal;
    }
}
