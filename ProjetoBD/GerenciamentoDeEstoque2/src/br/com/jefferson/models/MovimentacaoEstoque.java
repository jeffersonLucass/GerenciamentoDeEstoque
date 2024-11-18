package br.com.jefferson.models;

public class MovimentacaoEstoque {
	private int id;
    private int produtoId;
    private int quantidade;
    private String tipoMovimentacao; // "entrada" ou "saida"
    private String data;
    
    
	public MovimentacaoEstoque(int id, int produtoId, int quantidade, String tipoMovimentacao, String data) {
		super();
		this.id = id;
		this.produtoId = produtoId;
		this.quantidade = quantidade;
		this.tipoMovimentacao = tipoMovimentacao;
		this.data = data;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getProdutoId() {
		return produtoId;
	}


	public void setProdutoId(int produtoId) {
		this.produtoId = produtoId;
	}


	public int getQuantidade() {
		return quantidade;
	}


	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}


	public String getTipoMovimentacao() {
		return tipoMovimentacao;
	}


	public void setTipoMovimentacao(String tipoMovimentacao) {
		this.tipoMovimentacao = tipoMovimentacao;
	}


	public String getData() {
		return data;
	}


	public void setData(String data) {
		this.data = data;
	}
    
    
    
}
