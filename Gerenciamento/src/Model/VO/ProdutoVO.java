package model.VO;

import java.io.IOException;

class ProdutoVO {

	private int id;
	private String nome;
	private String descricao;
	private int quantidade;
	private double preco;
	
	//getters e setters
	
	public int getId() {
		return id;
	}
	public void setId(int id) throws IOException {
		if(id < 0)
		{
			throw new IOException("Id do produto inválido");
		}
		else
		{
			this.id = id;
		}
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) throws IOException {
		if(nome != null && nome != "")
		{
			this.nome = nome;
		}
		else
		{
			throw new IOException("nome do produto inválido");
		}
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) throws IOException {
		if(descricao != null & descricao != "")
		{
			this.descricao = descricao;
		}
		else
		{
			throw new IOException("Id do produto inválido");
		}
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) throws IOException {
		if(quantidade < 0)
		{
			throw new IOException("Id do produto inválido");
		}
		else
		{
			this.quantidade = quantidade;
		}
	}
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) throws IOException {
		if(preco < 0)
		{
			throw new IOException("Id do produto inválido");
		}
		else
		{
			this.preco = preco;
		}
	}
	
	
}
