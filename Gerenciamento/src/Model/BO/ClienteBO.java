package model.BO;

import java.io.IOException;
import java.util.List;
import model.VO.ClienteVO;

public class ClienteBO implements ClienteInterBO<ClienteVO> {

	@Override
	public void cadastrarCliente(ClienteVO vo) throws IOException {
		if(vo.getCadastroPessoa() != null && vo.getNome() != null)
		{
			
		}
		else
		{
			throw new IOException("Dados inválidos inseridos em cliente BO");
		}
	}

	@Override
	public void editarCliente(ClienteVO vo) throws IOException {
		if(vo.getCadastroPessoa() != null && vo.getNome() != null)
		{
			
		}
		else
		{
			throw new IOException("Dados inválidos inseridos em cliente BO");
		}
		
	}

	@Override
	public void deletarCliente(ClienteVO vo) throws IOException{
		if(vo.getCadastroPessoa() != null && vo.getNome() != null)
		{
			
		}
		else
		{
			throw new IOException("Dados inválidos inseridos em cliente BO");
		}
		
	}

	@Override
	public List<ClienteVO> pesquisarCliente(ClienteVO vo) throws IOException{
		if(vo.getCadastroPessoa() != null && vo.getNome() != null)
		{
			
		}
		else
		{
			throw new IOException("Dados inválidos inseridos em cliente BO");
		}
		return null;
	}

	@Override
	public List<ClienteVO> listarClientes() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
