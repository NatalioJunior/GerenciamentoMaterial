package model.BO;

import java.io.IOException;
import java.util.List;

import model.VO.ClienteVO;

public class ClienteBO implements BaseInterBO<ClienteVO> {

	@Override
	public void cadastrar(ClienteVO vo) throws IOException {
		if(vo.getCadastroPessoa() != null && vo.getNome() != null)
		{
			
		}
		else
		{
			throw new IOException("Dados inválidos inseridos em cliente BO");
		}
	}

	@Override
	public void editar(ClienteVO vo) throws IOException {
		if(vo.getCadastroPessoa() != null && vo.getNome() != null)
		{
			
		}
		else
		{
			throw new IOException("Dados inválidos inseridos em cliente BO");
		}
		
	}

	@Override
	public void deletar(ClienteVO vo) throws IOException{
		if(vo.getCadastroPessoa() != null && vo.getNome() != null)
		{
			
		}
		else
		{
			throw new IOException("Dados inválidos inseridos em cliente BO");
		}
		
	}

	@Override
	public List<ClienteVO> pesquisar(ClienteVO vo) throws IOException{
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
	public List<ClienteVO> listar() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
