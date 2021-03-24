package model.BO;

import java.io.IOException;
import java.util.List;

import model.VO.ProdutoVO;

public class ProdutoBO implements BaseInterBO<ProdutoVO> {

	@Override
	public void cadastrar(ProdutoVO produto) throws IOException {
		
		if(produto.getId() < 0 && produto.getNome() == null && produto.getDescricao() == null
				&& produto.getQuantidade() < 0 && produto.getPreco() < 0)
		{
			throw new IOException("Dados inválidos inseridos em produtoBO");
		}
		else
		{
			
		}
		
	}

	@Override
	public void editar(ProdutoVO produto) throws IOException {
		
		if(produto.getId() < 0 && produto.getNome() == null && produto.getDescricao() == null
				&& produto.getQuantidade() < 0 && produto.getPreco() < 0)
		{
			throw new IOException("Dados inválidos inseridos em produtoBO");
		}
		else
		{
			
		}
		
	}

	@Override
	public void deletar(ProdutoVO produto) throws IOException {
		
		if(produto.getId() < 0 && produto.getNome() == null && produto.getDescricao() == null
				&& produto.getQuantidade() < 0 && produto.getPreco() < 0)
		{
			throw new IOException("Dados inválidos inseridos em produtoBO");
		}
		else
		{
			
		}
		
	}

	@Override
	public List<ProdutoVO> pesquisar(ProdutoVO produto) throws IOException {
		
		if(produto.getId() < 0 && produto.getNome() == null && produto.getDescricao() == null
				&& produto.getQuantidade() < 0 && produto.getPreco() < 0)
		{
			throw new IOException("Dados inválidos inseridos em produtoBO");
		}
		else
		{
			
		}
		return null;
	}

	@Override
	public List<ProdutoVO> listar() {
		// TODO Auto-generated method stub
		return null;
	}

}
