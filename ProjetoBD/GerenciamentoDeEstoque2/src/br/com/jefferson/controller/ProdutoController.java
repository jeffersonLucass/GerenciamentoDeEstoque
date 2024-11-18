package br.com.jefferson.controller;
import java.util.List;

import br.com.jefferson.models.Produto;
import br.com.jefferson.models.ProdutoDAO;
public class ProdutoController {

	private ProdutoDAO produtoDAO = new ProdutoDAO();

    public void cadastrarProduto(Produto produto) {
        produtoDAO.cadastrarProduto(produto);
    }

    public List<Produto> listarProdutos() {
        return produtoDAO.listarProdutos();
    }
	
    public List<Produto> gerarRelatorioEstoqueBaixo() {
        return produtoDAO.listarProdutosComEstoqueBaixo(10); // Exemplo: 10 como limite de estoque baixo
    }
	
	
}
