package model.VO;

import java.io.IOException;
import java.util.Calendar;

public class CompraVO {

	private ClienteVO cliente;
	private ProdutoVO produto;
	private Calendar dataCompra;
	private int quantidade;
	private double valorTotal;
	
	//getters e setters
	
	public ClienteVO getCliente() {
		return cliente;
	}
	public void setCliente(ClienteVO cliente) throws IOException {
		if(cliente != null)
		{
			this.cliente = cliente;
		}
		else
		{
			throw new IOException("Cliente da compra inválido!");
		}
	}
	public ProdutoVO getProduto() {
		return produto;
	}
	public void setProduto(ProdutoVO produto) throws IOException {
		if(produto != null)
		{
			this.produto = produto;
		}
		else
		{
			throw new IOException("Produto da compra inválido!");
		}
	}
	public Calendar getDataCompra() {
		return dataCompra;
	}
	public void setDataCompra(Calendar dataCompra) throws IOException {
		if(dataCompra != null)
		{
			this.dataCompra = dataCompra;
		}
		else
		{
			throw new IOException("data da compra inválido!");
		}
	}
	
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) throws IOException {
		if(quantidade < 0)
		{
			throw new IOException("Quantidade de itens da compra inválida!");
		}
		else
		{
			this.quantidade = quantidade;
		}
	}
	
	public double getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(double valorTotal) throws IOException {
		if(valorTotal < 0)
		{
			throw new IOException("Cliente da compra inválido!");
		}
		else
		{
			this.valorTotal = valorTotal;
		}
	}
	
	
}
