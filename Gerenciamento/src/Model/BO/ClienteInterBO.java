package model.BO;

import java.io.IOException;
import java.util.List;

public interface ClienteInterBO<VO> {

	public void cadastrarCliente(VO vo) throws IOException;
	public void editarCliente(VO vo) throws IOException;
	public void deletarCliente(VO vo) throws IOException;
	public List<VO> pesquisarCliente(VO vo) throws IOException;
	public List<VO> listarClientes();
}
