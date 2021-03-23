package model.BO;
import java.io.IOException;
import java.util.List;

import model.VO.ProdutoVO;

public interface ProdutoInterBO {
	
	public void cadastrarProduto(ProdutoVO produto) throws IOException;
	public void editarProduto(ProdutoVO produto) throws IOException;
	public void deletarProduto(ProdutoVO produto) throws IOException;
	public List<ProdutoVO> pesquisarProduto(ProdutoVO produto) throws IOException;
	public List<ProdutoVO> listarProdutos();
}
