package br.com.jefferson.models;
import br.com.jefferson.util.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/*Classe responsavel por realizar a ponte entre o banco de dados e o modelo*/
public class ProdutoDAO {
	public void cadastrarProduto(Produto produto) {
        String sql = "INSERT INTO produtos (nome, descricao, quantidade, preco_compra, preco_venda, categoria) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getDescricao());
            stmt.setInt(3, produto.getQuantidade());
            stmt.setDouble(4, produto.getPrecoCompra());
            stmt.setDouble(5, produto.getPrecoVenda());
            stmt.setString(6, produto.getCategoria());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	public List<Produto> listarProdutos() {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT * FROM produtos";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                produtos.add(new Produto(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("descricao"),
                    rs.getInt("quantidade"),
                    rs.getDouble("preco_compra"),
                    rs.getDouble("preco_venda"),
                    rs.getString("categoria")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return produtos;
    }
	
	// Método para editar produto
    public void editarProduto(Produto produto) {
        String sql = "UPDATE produtos SET nome = ?, descricao = ?, quantidade = ?, preco_compra = ?, preco_venda = ?, categoria = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getDescricao());
            stmt.setInt(3, produto.getQuantidade());
            stmt.setDouble(4, produto.getPrecoCompra());
            stmt.setDouble(5, produto.getPrecoVenda());
            stmt.setString(6, produto.getCategoria());
            stmt.setInt(7, produto.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para excluir produto
    public void excluirProduto(int id) {
        String sql = "DELETE FROM produtos WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para gerar relatório de produtos com estoque baixo
    public List<Produto> listarProdutosComEstoqueBaixo(int limite) {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT * FROM produtos WHERE quantidade < ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, limite);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                produtos.add(new Produto(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("descricao"),
                        rs.getInt("quantidade"),
                        rs.getDouble("preco_compra"),
                        rs.getDouble("preco_venda"),
                        rs.getString("categoria")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return produtos;
    }
	
	
	
}
























