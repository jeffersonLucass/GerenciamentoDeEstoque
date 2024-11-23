package br.com.projeto.dao;

import java.sql.CallableStatement;
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
    
    public void gerarRelatorioProdutos() throws SQLException {
        // Chama a stored procedure 'relatorioProdutos'
        String sql = "{CALL relatorioProdutos()}";  

        try (CallableStatement stmt = connection.prepareCall(sql)) {
            // Executa a consulta
            ResultSet rs = stmt.executeQuery();

            // Exibe o cabeçalho do relatório
            System.out.println("Relatório de Produtos Cadastrados:");
            System.out.println("--------------------------------------------------------");
            System.out.println("ID Produto | Nome | Quantidade | Preço Compra | Preço Venda | Categoria");

            // Itera sobre os resultados e imprime cada linha
            while (rs.next()) {
                int idProduto = rs.getInt("id_produto");
                String nomeProduto = rs.getString("nome");
                int quantidade = rs.getInt("quantidade");
                double precoCompra = rs.getDouble("preco_compra");
                double precoVenda = rs.getDouble("preco_venda");
                String categoria = rs.getString("categoria");

                // Exibe as informações de cada produto
                System.out.printf("%d | %s | %d | %.2f | %.2f | %s\n", 
                                  idProduto, nomeProduto, quantidade, precoCompra, precoVenda, categoria);
            }
        }
    }
    
    
    
    
    
}