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
	public void cadastrar(ClienteVO cliente) throws IOException {
		if(cliente.getCadastroPessoa() != null && cliente.getNome() != null)
		{
			try {
				dao.createDAO(cliente);
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
	public void editar(ClienteVO cliente) throws IOException {
		if(cliente.getCadastroPessoa() != null && cliente.getNome() != null)
		{
			try {
				dao.updateDAO(cliente);
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
	public void deletar(ClienteVO cliente) throws IOException{
		if(cliente.getCadastroPessoa() != null && cliente.getNome() != null)
		{
			try {
				dao.removeDAO(cliente);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		else
		{
			throw new IOException("Dados inválidos inseridos em cliente BO");
		}
		
	}

	@SuppressWarnings("null")
	@Override
	public List<ClienteVO> pesquisar(ClienteVO cliente) throws IOException{
		if(cliente.getCadastroPessoa() != null && cliente.getNome() != null)
		{
			ClienteVO vo = null;
			List<ClienteVO> listClientes = new ArrayList<>();
			
			try {
				ResultSet rs = dao.searchDAO(cliente);
				while (rs.next()) {
					vo.setCadastroPessoa(rs.getString("cpf"));
					vo.setNome(rs.getString("nome"));
					listClientes.add(vo);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}			
			return listClientes;
		}
		else
		{
			throw new IOException("Dados inválidos inseridos em cliente BO");
		}
	}

	@SuppressWarnings("null")
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
