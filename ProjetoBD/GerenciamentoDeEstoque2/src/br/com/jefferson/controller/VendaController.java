package br.com.jefferson.controller;

import java.util.List;

import br.com.jefferson.models.Venda;
import br.com.jefferson.models.VendaDAO;

public class VendaController {

	private VendaDAO dao = new VendaDAO();

    public void registrarVenda(Venda venda) {
        dao.registrarVenda(venda);
    }

    public List<Venda> gerarRelatorioVendas() {
        return dao.gerarRelatorioVendas();
    }

    public double calcularLucroTotal() {
        return dao.calcularLucroTotal();
    }
	
	
	
}
