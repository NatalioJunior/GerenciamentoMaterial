package model.BO;

import java.io.IOException;
import java.util.List;

import model.VO.CompraVO;

public interface CompraInterBO {

	public void cadastrarCompra(CompraVO compra) throws IOException;
	public void editarCompra(CompraVO compra) throws IOException;
	public void deletarCompra(CompraVO compra) throws IOException;
	public List<CompraVO> pesquisarCompra(CompraVO compra) throws IOException;
	public List<CompraVO> listarCompras();
}
