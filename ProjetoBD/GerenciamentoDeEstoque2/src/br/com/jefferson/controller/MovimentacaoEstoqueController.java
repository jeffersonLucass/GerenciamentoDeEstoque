package br.com.jefferson.controller;
import br.com.jefferson.models.MovimentacaoEstoque;
import br.com.jefferson.models.MovimentacaoEstoqueDAO;

import java.util.List;

public class MovimentacaoEstoqueController {
	private MovimentacaoEstoqueDAO dao = new MovimentacaoEstoqueDAO();

    public void registrarMovimentacao(MovimentacaoEstoque movimentacao) {
        dao.registrarMovimentacao(movimentacao);
    }

    public List<MovimentacaoEstoque> gerarRelatorioMovimentacao() {
        return dao.gerarRelatorioMovimentacao();
    }
}	
