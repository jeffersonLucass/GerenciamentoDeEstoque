package br.com.jefferson.controller;

import java.util.List;

import br.com.jefferson.models.Categoria;
import br.com.jefferson.models.CategoriaDAO;

public class CategoriaController {
	private CategoriaDAO dao = new CategoriaDAO();

    public void cadastrarCategoria(Categoria categoria) {
        dao.cadastrarCategoria(categoria);
    }

    public List<Categoria> listarCategorias() {
        return dao.listarCategorias();
    }
}
