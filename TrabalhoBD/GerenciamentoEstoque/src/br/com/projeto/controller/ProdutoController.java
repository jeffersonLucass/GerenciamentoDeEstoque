package br.com.projeto.controller;

import java.sql.*;
import java.util.List;

import br.com.projeto.dao.ProdutoDAO;
import br.com.projeto.model.Produto;

public class ProdutoController {
    private ProdutoDAO produtoDAO;

    public ProdutoController() throws SQLException {
        produtoDAO = new ProdutoDAO();
    }

    public void cadastrarProduto(String nome, String descricao, int quantidade, double precoCompra, double precoVenda, int idCategoria) throws SQLException {
        Produto produto = new Produto();
        produto.setNome(nome);
        produto.setDescricao(descricao);
        produto.setQuantidade(quantidade);
        produto.setPrecoCompra(precoCompra);
        produto.setPrecoVenda(precoVenda);
        produto.setIdCategoria(idCategoria);
        produtoDAO.cadastrarProduto(produto);
    }

    public List<Produto> consultarProdutos(String filtroNome) throws SQLException {
        return produtoDAO.consultarProdutos(filtroNome);
    }

    public void editarProduto(int id, String nome, String descricao, int quantidade, double precoCompra, double precoVenda, int idCategoria) throws SQLException {
        Produto produto = new Produto();
        produto.setId(id);
        produto.setNome(nome);
        produto.setDescricao(descricao);
        produto.setQuantidade(quantidade);
        produto.setPrecoCompra(precoCompra);
        produto.setPrecoVenda(precoVenda);
        produto.setIdCategoria(idCategoria);
        produtoDAO.editarProduto(produto);
    }

    public void excluirProduto(int id) throws SQLException {
        produtoDAO.excluirProduto(id);
    }
}

