package br.com.projeto.dao;

import br.com.projeto.model.Produto;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {
    private Connection connection;

    public ProdutoDAO(Connection connection) {
        this.connection = connection;
    }

    public void cadastrarProduto(String nome, String descricao, int quantidade, BigDecimal precoCompra, BigDecimal precoVenda, int idCategoria) throws SQLException {
        String sql = "CALL cadastrarProduto(?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nome);
            stmt.setString(2, descricao);
            stmt.setInt(3, quantidade);
            stmt.setBigDecimal(4, precoCompra);
            stmt.setBigDecimal(5, precoVenda);
            stmt.setInt(6, idCategoria);
            stmt.executeUpdate();
        }
    }

    public List<Produto> listarProdutos() throws SQLException {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT * FROM produtos";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Produto produto = new Produto();
                produto.setId(rs.getInt("id_produto"));
                produto.setNome(rs.getString("nome"));
                produto.setDescricao(rs.getString("descricao"));
                produto.setQuantidade(rs.getInt("quantidade"));
                produto.setPrecoCompra(rs.getDouble("preco_compra"));
                produto.setPrecoVenda(rs.getDouble("preco_venda"));
                produto.setIdCategoria(rs.getInt("id_categoria"));
                produtos.add(produto);
            }
        }
        return produtos;
    }

    public void editarProduto(int id, String novoNome, String novaDescricao, int novaQuantidade, double novoPrecoCompra, double novoPrecoVenda, int novoIdCategoria) throws SQLException {
        String sql = "UPDATE produtos SET nome = ?, descricao = ?, quantidade = ?, preco_compra = ?, preco_venda = ?, id_categoria = ? WHERE id_produto = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, novoNome);
            stmt.setString(2, novaDescricao);
            stmt.setInt(3, novaQuantidade);
            stmt.setDouble(4, novoPrecoCompra);
            stmt.setDouble(5, novoPrecoVenda);
            stmt.setInt(6, novoIdCategoria);
            stmt.setInt(7, id);
            stmt.executeUpdate();
        }
    }

    public void excluirProduto(int id) throws SQLException {
        String sql = "DELETE FROM produtos WHERE id_produto = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
