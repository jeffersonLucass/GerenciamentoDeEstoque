package br.com.jefferson.models;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import br.com.jefferson.util.DatabaseConnection;

public class CategoriaDAO {
	public void cadastrarCategoria(Categoria categoria) {
        String sql = "INSERT INTO categorias (nome, descricao) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, categoria.getNome());
            stmt.setString(2, categoria.getDescricao());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
	public List<Categoria> listarCategorias() {
        List<Categoria> categorias = new ArrayList<>();
        String sql = "SELECT * FROM categorias";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                categorias.add(new Categoria(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("descricao")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categorias;
    }
	
	
}





