package br.com.projeto.controller;

import java.sql.SQLException;
import java.util.List;

import br.com.projeto.dao.CategoriaDAO;
import br.com.projeto.model.Categoria;

public class CategoriaController {
    private CategoriaDAO categoriaDAO;

    public CategoriaController(CategoriaDAO categoriaDAO) {
        this.categoriaDAO = categoriaDAO;
    }

    // Cadastrar categoria
    public void cadastrarCategoria(String nome, String descricao) throws SQLException {
        categoriaDAO.cadastrarCategoria(nome, descricao);
    }

    // Listar categorias
    public List<Categoria> listarCategorias() throws SQLException {
        return categoriaDAO.listarCategorias();
    }

    // Editar categoria
    public void editarCategoria(int idCategoria, String nome, String descricao) throws SQLException {
        categoriaDAO.editarCategoria(idCategoria, nome, descricao);
    }

    // Excluir categoria
    public void excluirCategoria(int idCategoria) throws SQLException {
        categoriaDAO.excluirCategoria(idCategoria);
    }
}
