package model.VO;

import java.io.IOException;

abstract class ClienteVO {
	protected String cadastroPessoa;
	protected String nome;
	
	public String getCadastroPessoa() {
		return cadastroPessoa;
	}
	public void setCadastroPessoa(String cadastroPessoa) throws IOException {
		if(cadastroPessoa != null && cadastroPessoa != "")
		{
			this.cadastroPessoa = cadastroPessoa;
		}
		else
		{
			throw new IOException("Cadastro do cliente inválido!");
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
			throw new IOException("Nome do cliente inválido!");
		}
	}
	
	
}
