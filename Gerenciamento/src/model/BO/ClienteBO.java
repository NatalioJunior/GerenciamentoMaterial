package model.BO;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.DAO.BaseInterDAO;
import model.DAO.ClienteDAO;
import model.VO.ClienteVO;

public class ClienteBO implements BaseInterBO<ClienteVO> {
	
	private static BaseInterDAO<ClienteVO> dao = new ClienteDAO<>();

	@Override
	public void cadastrar(ClienteVO vo) throws IOException {
		if(vo.getCadastroPessoa() != null && vo.getNome() != null)
		{
			try {
				dao.createDAO(vo);
			} catch (SQLException e) {
				e.printStackTrace();
			}
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
			try {
				dao.updateDAO(vo);
			} catch (SQLException e) {
				e.printStackTrace();
			}
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
			try {
				dao.removeDAO(vo);
			} catch (SQLException e) {
				e.printStackTrace();
			}
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
	public List<ClienteVO> listar() throws IOException {
		ClienteVO cliente = null;
		List<ClienteVO> listClientes = new ArrayList<>();
		
		try {
			ResultSet rs = dao.listDAO();
			while (rs.next()) {
				cliente.setCadastroPessoa(rs.getString("cpf"));
				cliente.setNome(rs.getString("nome"));
				listClientes.add(cliente);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listClientes;
	}
	
}
