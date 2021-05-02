package model.BO;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import assistant.InterList;
import assistant.SimplyList;
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
	public InterList<ClienteVO> pesquisar(ClienteVO cliente) throws IOException{
		if(cliente.getCadastroPessoa() != null && cliente.getNome() != null)
		{
			ClienteVO vo = null;
			InterList<ClienteVO> listClientes = new SimplyList<ClienteVO>();
			
			try {
				ResultSet rs = dao.searchDAO(cliente);
				while (rs.next()) {
					vo.setCadastroPessoa(rs.getString("cpf"));
					vo.setNome(rs.getString("nome"));
					listClientes.addLast(vo);
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
	
	public ClienteVO pesquisarCPF(String cadastro) throws IOException{
		ClienteDAO<ClienteVO> daoCPF = new ClienteDAO<ClienteVO>();
		if(cadastro != null)
		{
			ClienteVO vo = new ClienteVO();
			
			try {
				ResultSet rs = daoCPF.searchCPF(cadastro);
				while (rs.next()) {
					vo.setCadastroPessoa(rs.getString("cpf"));
					vo.setNome(rs.getString("nome"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}			
			return vo;
		}
		else
		{
			throw new IOException("Dados inválidos inseridos em cliente BO");
		}
	}

	@Override
	public InterList<ClienteVO> listar() throws IOException {
		ClienteVO cliente = new ClienteVO();
		InterList<ClienteVO> listClientes = new SimplyList<ClienteVO>();
		
		try {
			ResultSet rs = dao.listDAO();
			while (rs.next()) {
				cliente.setCadastroPessoa(rs.getString("cpf"));
				cliente.setNome(rs.getString("nome"));
				listClientes.addLast(cliente);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return listClientes;
	}
	
}
