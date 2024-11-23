package br.com.projeto.controller;

import br.com.projeto.dao.CategoriaDAO;
import br.com.projeto.model.Categoria;

import java.sql.SQLException;
import java.util.List;

public class CategoriaController {
    private CategoriaDAO categoriaDAO;

    public CategoriaController(CategoriaDAO categoriaDAO) {
        this.categoriaDAO = categoriaDAO;
    }

    public int cadastrarCategoria(String nome, String descricao) throws SQLException {
        Categoria categoria = new Categoria();
        categoria.setNome(nome);
        categoria.setDescricao(descricao);
        return categoriaDAO.cadastrarCategoria(categoria);
    }

    public void editarCategoria(int id, String novoNome, String novaDescricao) throws SQLException {
        categoriaDAO.editarCategoria(id, novoNome, novaDescricao);
    }

    public void excluirCategoria(int id) throws SQLException {
        categoriaDAO.excluirCategoria(id);
    }

    public List<Categoria> listarCategorias() throws SQLException {
        return categoriaDAO.listarCategorias();
    }
}
