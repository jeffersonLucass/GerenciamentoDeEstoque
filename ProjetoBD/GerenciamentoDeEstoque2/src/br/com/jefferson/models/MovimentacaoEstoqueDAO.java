package br.com.jefferson.models;
import br.com.jefferson.util.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovimentacaoEstoqueDAO {
	// Método para registrar movimentação de estoque
    public void registrarMovimentacao(MovimentacaoEstoque movimentacao) {
        String sql = "INSERT INTO movimentacao_estoque (produto_id, quantidade, tipo_movimentacao, data) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, movimentacao.getProdutoId());
            stmt.setInt(2, movimentacao.getQuantidade());
            stmt.setString(3, movimentacao.getTipoMovimentacao());
            stmt.setString(4, movimentacao.getData());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Relatório de movimentações de estoque
    public List<MovimentacaoEstoque> gerarRelatorioMovimentacao() {
        List<MovimentacaoEstoque> movimentacoes = new ArrayList<>();
        String sql = "SELECT * FROM movimentacao_estoque";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                movimentacoes.add(new MovimentacaoEstoque(
                        rs.getInt("id"),
                        rs.getInt("produto_id"),
                        rs.getInt("quantidade"),
                        rs.getString("tipo_movimentacao"),
                        rs.getString("data")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movimentacoes;
    }
}
