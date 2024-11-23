package br.com.projeto.controller;

import br.com.projeto.dao.ProdutoDAO;
import br.com.projeto.model.Produto;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public class ProdutoController {
    private ProdutoDAO produtoDAO;

    public ProdutoController(ProdutoDAO produtoDAO) {
        this.produtoDAO = produtoDAO;
    }

    public void cadastrarProduto(String nome, String descricao, int quantidade, BigDecimal precoCompra, BigDecimal precoVenda, int idCategoria) throws SQLException {
        produtoDAO.cadastrarProduto(nome, descricao, quantidade, precoCompra, precoVenda, idCategoria);
    }

    public List<Produto> listarProdutos() throws SQLException {
        return produtoDAO.listarProdutos();
    }

    public void editarProduto(int id, String novoNome, String novaDescricao, int novaQuantidade, double novoPrecoCompra, double novoPrecoVenda, int novoIdCategoria) throws SQLException {
        produtoDAO.editarProduto(id, novoNome, novaDescricao, novaQuantidade, novoPrecoCompra, novoPrecoVenda, novoIdCategoria);
    }

    public void excluirProduto(int id) throws SQLException {
        produtoDAO.excluirProduto(id);
    }
}
