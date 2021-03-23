package model.BO;
import java.io.IOException;
import model.VO.ProdutoVO;
import java.util.List;

public interface ProdutoInterBO {
	
	public void cadastrarProduto(ProdutoVO produto) throws IOException;
	public void editarProduto(ProdutoVO produto) throws IOException;
	public void deletarProduto(ProdutoVO produto) throws IOException;
	public List<ProdutoVO> pesquisarProduto(ProdutoVO produto) throws IOException;
	public List<ProdutoVO> listarProdutos();
}
