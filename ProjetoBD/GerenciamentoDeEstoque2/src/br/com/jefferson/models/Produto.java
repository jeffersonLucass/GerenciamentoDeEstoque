package br.com.jefferson.models;

public class Produto {

	  private int id;
	  private String nome;
	  private String descricao;
	  private int quantidade;
	  private double precoCompra;
	  private double precoVenda;
	  private String categoria;
	
	  
	  public Produto(int id, String nome, String descricao, int quantidade, double precoCompra, double precoVenda,
			String categoria) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.quantidade = quantidade;
		this.precoCompra = precoCompra;
		this.precoVenda = precoVenda;
		this.categoria = categoria;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public int getQuantidade() {
		return quantidade;
	}


	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}


	public double getPrecoCompra() {
		return precoCompra;
	}


	public void setPrecoCompra(double precoCompra) {
		this.precoCompra = precoCompra;
	}


	public double getPrecoVenda() {
		return precoVenda;
	}


	public void setPrecoVenda(double precoVenda) {
		this.precoVenda = precoVenda;
	}


	public String getCategoria() {
		return categoria;
	}


	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	  
	  
	  
	  
	  

	  
	
	
}
