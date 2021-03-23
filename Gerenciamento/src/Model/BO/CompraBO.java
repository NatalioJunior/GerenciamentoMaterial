package model.BO;

import java.io.IOException;
import java.util.List;

import model.VO.CompraVO;

public class CompraBO implements CompraInterBO{

	@Override
	public void cadastrarCompra(CompraVO compra) throws IOException {
		
		if(compra.getCliente() == null && compra.getProduto() == null && compra.getDataCompra()
					== null && compra.getQuantidade() < 0 && compra.getValorTotal() < 0)
			{
					throw new IOException("Dados inválidos inseridos em CompraBO!");
			}
			else
			{
					
			}
		
	}

	@Override
	public void editarCompra(CompraVO compra) throws IOException {
		
		if(compra.getCliente() == null && compra.getProduto() == null && compra.getDataCompra()
				== null && compra.getQuantidade() < 0 && compra.getValorTotal() < 0)
		{
			throw new IOException("Dados inválidos inseridos em CompraBO!");
		}
		else
		{
			
		}
		
	}

	@Override
	public void deletarCompra(CompraVO compra) throws IOException {
		
		if(compra.getCliente() == null && compra.getProduto() == null && compra.getDataCompra()
				== null && compra.getQuantidade() < 0 && compra.getValorTotal() < 0)
		{
			throw new IOException("Dados inválidos inseridos em CompraBO!");
		}
		else
		{
			
		}
		
	}

	@Override
	public List<CompraVO> pesquisarCompra(CompraVO compra) throws IOException {
		
		if(compra.getCliente() == null && compra.getProduto() == null && compra.getDataCompra()
				== null && compra.getQuantidade() < 0 && compra.getValorTotal() < 0)
		{
			throw new IOException("Dados inválidos inseridos em CompraBO!");
		}
		else
		{
			
		}
		
		return null;
	}

	@Override
	public List<CompraVO> listarCompras() {
		// TODO Auto-generated method stub
		return null;
	}

}
