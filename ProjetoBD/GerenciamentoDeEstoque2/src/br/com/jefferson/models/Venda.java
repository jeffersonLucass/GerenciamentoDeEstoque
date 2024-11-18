package br.com.jefferson.models;

public class Venda {
	private int id;
    private int produtoId;
    private int quantidade;
    private double precoVenda;
    private double lucro;
    private String data;

    public Venda(int id, int produtoId, int quantidade, double precoVenda, double lucro, String data) {
        this.id = id;
        this.produtoId = produtoId;
        this.quantidade = quantidade;
        this.precoVenda = precoVenda;
        this.lucro = lucro;
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

	public double getPrecoVenda() {
		return precoVenda;
	}

	public void setPrecoVenda(double precoVenda) {
		this.precoVenda = precoVenda;
	}

	public double getLucro() {
		return lucro;
	}

	public void setLucro(double lucro) {
		this.lucro = lucro;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
    
    
    
}
