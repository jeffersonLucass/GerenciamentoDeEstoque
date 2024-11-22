package br.com.projeto.controller;

import java.sql.SQLException;

import br.com.projeto.dao.MovimentacaoDAO;

public class MovimentacaoController {
    private MovimentacaoDAO movimentacaoDAO;

    public MovimentacaoController(MovimentacaoDAO movimentacaoDAO) {
        this.movimentacaoDAO = movimentacaoDAO;
    }

    // Registrar movimentação
    public void registrarMovimentacao(int idProduto, String tipoMovimentacao, int quantidade) throws SQLException {
        movimentacaoDAO.registrarMovimentacao(idProduto, tipoMovimentacao, quantidade);
    }

    // Relatório de movimentação
    public void relatorioMovimentacao() throws SQLException {
        movimentacaoDAO.relatorioMovimentacao();
    }
}