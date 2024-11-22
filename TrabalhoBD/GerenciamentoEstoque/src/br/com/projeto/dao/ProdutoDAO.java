package br.com.projeto.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import br.com.projeto.model.Produto;
import br.com.projeto.util.DatabaseConnection;


public class ProdutoDAO {
    private Connection connection;

    public ProdutoDAO() throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/GerenciamentoDeEstoque", "root", "admin");
    }

    public void cadastrarProduto(Produto produto) throws SQLException {
        String sql = "CALL cadastrarProduto(?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getDescricao());
            stmt.setInt(3, produto.getQuantidade());
            stmt.setDouble(4, produto.getPrecoCompra());
            stmt.setDouble(5, produto.getPrecoVenda());
            stmt.setInt(6, produto.getIdCategoria());
            stmt.execute();
        }
    }

    public List<Produto> consultarProdutos(String filtroNome) throws SQLException {
        String sql = "CALL consultarProdutos(?, NULL)";
        List<Produto> produtos = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, filtroNome);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Produto produto = new Produto();
                    produto.setId(rs.getInt("id_produto"));
                    produto.setNome(rs.getString("nome"));
                    produto.setDescricao(rs.getString("descricao"));
                    produto.setQuantidade(rs.getInt("quantidade"));
                    produto.setPrecoCompra(rs.getDouble("preco_compra"));
                    produto.setPrecoVenda(rs.getDouble("preco_venda"));
                    produtos.add(produto);
                }
            }
        }
        return produtos;
    }
    
    
    public void editarProduto(Produto produto) throws SQLException {
        String sql = "CALL editarProduto(?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, produto.getId());
            stmt.setString(2, produto.getNome());
            stmt.setString(3, produto.getDescricao());
            stmt.setInt(4, produto.getQuantidade());
            stmt.setDouble(5, produto.getPrecoCompra());
            stmt.setDouble(6, produto.getPrecoVenda());
            stmt.setInt(7, produto.getIdCategoria());
            stmt.execute();
        }
    }
    
 // Método para excluir produto
    public void excluirProduto(int idProduto) throws SQLException {
        String sql = "CALL excluirProduto(?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idProduto);
            stmt.execute();
        }
    }
    
    
}