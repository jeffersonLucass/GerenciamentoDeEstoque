package br.com.projeto.controller;

import br.com.projeto.dao.MovimentacaoDAO;

import java.sql.SQLException;

public class MovimentacaoController {
    private MovimentacaoDAO movimentacaoDAO;

    public MovimentacaoController(MovimentacaoDAO movimentacaoDAO) {
        this.movimentacaoDAO = movimentacaoDAO;
    }

    // Chamar o método que gera o relatório
    public void registrarMovimentacao(int idProduto, String tipoMovimentacao, int quantidade) throws SQLException {
        movimentacaoDAO.registrarMovimentacao(idProduto, tipoMovimentacao, quantidade);
    }
    
    

}
