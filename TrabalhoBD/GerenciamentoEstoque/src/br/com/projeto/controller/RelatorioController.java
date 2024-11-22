package br.com.projeto.controller;

import java.sql.SQLException;

import br.com.projeto.dao.RelatorioDAO;

public class RelatorioController {
    private RelatorioDAO relatorioDAO;

    public RelatorioController(RelatorioDAO relatorioDAO) {
        this.relatorioDAO = relatorioDAO;
    }

    // Relatório de baixo estoque
    public void relatorioBaixoEstoque() throws SQLException {
        relatorioDAO.relatorioBaixoEstoque();
    }

    // Relatório de vendas e lucros
    public void relatorioVendasLucros() throws SQLException {
        relatorioDAO.relatorioVendasLucros();
    }
}
