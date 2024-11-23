package br.com.projeto.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MovimentacaoDAO {
    private Connection connection;

    public MovimentacaoDAO(Connection connection) {
        this.connection = connection;
    }

    // Relatório de movimentação
    public void registrarMovimentacao(int idProduto, String tipoMovimentacao, int quantidade) throws SQLException {
        // Inserir a movimentação diretamente na tabela Movimentacao_Estoque
        String sql = "INSERT INTO Movimentacao_Estoque (id_produto, tipo_movimentacao, quantidade) VALUES (?, ?, ?)";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idProduto);
            stmt.setString(2, tipoMovimentacao);
            stmt.setInt(3, quantidade);
            stmt.executeUpdate();

            // Atualizar a quantidade do produto após a movimentação
            if (tipoMovimentacao.equals("saida")) {
                // Subtrair a quantidade do estoque
                String updateEstoqueSql = "UPDATE Produtos SET quantidade = quantidade - ? WHERE id_produto = ?";
                try (PreparedStatement updateStmt = connection.prepareStatement(updateEstoqueSql)) {
                    updateStmt.setInt(1, quantidade);
                    updateStmt.setInt(2, idProduto);
                    updateStmt.executeUpdate();
                }
            } else if (tipoMovimentacao.equals("entrada")) {
                // Adicionar a quantidade ao estoque (caso seja entrada)
                String updateEstoqueSql = "UPDATE Produtos SET quantidade = quantidade + ? WHERE id_produto = ?";
                try (PreparedStatement updateStmt = connection.prepareStatement(updateEstoqueSql)) {
                    updateStmt.setInt(1, quantidade);
                    updateStmt.setInt(2, idProduto);
                    updateStmt.executeUpdate();
                }
            }

            // Verificar a quantidade atual do produto após a movimentação
            String checkEstoqueSql = "SELECT quantidade FROM Produtos WHERE id_produto = ?";
            try (PreparedStatement checkStmt = connection.prepareStatement(checkEstoqueSql)) {
                checkStmt.setInt(1, idProduto);
                ResultSet rs = checkStmt.executeQuery();
                
                if (rs.next()) {
                    int quantidadeAtual = rs.getInt("quantidade");
                    if (quantidadeAtual < 10) {
                        // A trigger já gerará o alerta, mas podemos imprimir novamente se necessário
                        System.out.println("ALERTA: Estoque abaixo do mínimo permitido! Estoque atual: " + quantidadeAtual);
                    }
                }
            }
        }
    }

    
   
    

}
