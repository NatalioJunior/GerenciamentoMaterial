package model.BO;

import java.io.IOException;
import java.util.List;

import model.VO.CompraVO;

public class CompraBO implements BaseInterBO<CompraVO>{

	@Override
	public void cadastrar(CompraVO compra) throws IOException {
		
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
	public void editar(CompraVO compra) throws IOException {
		
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
	public void deletar(CompraVO compra) throws IOException {
		
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
	public List<CompraVO> pesquisar(CompraVO compra) throws IOException {
		
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
	public List<CompraVO> listar() {
		// TODO Auto-generated method stub
		return null;
	}

}
