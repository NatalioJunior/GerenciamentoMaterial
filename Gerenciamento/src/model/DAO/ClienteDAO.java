package model.DAO;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.VO.ClienteVO;

public class ClienteDAO<VO extends ClienteVO> extends BaseDAO implements BaseInterDAO<VO>{

	public void createDAO(VO vo) throws IOException, SQLException {
		String sql = "insert into clientes values (?,?)";
		PreparedStatement ptst;
		try {
			ptst = getConnection().prepareStatement(sql);
			ptst.setString(1, vo.getCadastroPessoa());
			ptst.setString(2,vo.getNome());
			ptst.execute();
		} catch (SQLException e){
			e.printStackTrace();
		}
		
	}

	public void removeDAO(VO vo) throws IOException, SQLException {
		String sql = "delete from clientes where cpf = ?";
		PreparedStatement ptst;
		
		try {
			ptst = getConnection().prepareStatement(sql);
			ptst.setString(1, vo.getCadastroPessoa());
			ptst.execute();
		} catch (SQLException e) {
				throw new SQLException("Erro ao excluir, verifique se há compras cadastradas com este cliente!");
		}
		
	}

	public void updateDAO(VO vo) throws IOException, SQLException {
		String sql = "update clientes set nome = ? where cpf = ?";
		PreparedStatement ptst;
		
		try {
			ptst = getConnection().prepareStatement(sql);
			ptst.setString(1, vo.getNome());
			ptst.setString(2, vo.getCadastroPessoa());
			ptst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public ResultSet listDAO() throws IOException, SQLException {
		String sql = "select * from clientes";
		Statement st;
		ResultSet rs = null;
		
		try {
			st = getConnection().createStatement();
			rs = st.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	public ResultSet searchDAO(VO vo) throws IOException, SQLException {
		String sql = "select * from clientes where cpf = ? or nome = ?";
		ResultSet rs = null;
		PreparedStatement ptst;
		
		try {
			ptst = getConnection().prepareStatement(sql);
			ptst.setString(1, vo.getCadastroPessoa());
			ptst.setString(2, vo.getNome());
			rs = ptst.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
		
	}
	
	public ResultSet searchCPF(String cadastro) throws IOException, SQLException {
		String sql = "select * from clientes where cpf = ?";
		ResultSet rs = null;
		PreparedStatement ptst;
		
		try {
			ptst = getConnection().prepareStatement(sql);
			ptst.setString(1, cadastro);
			rs = ptst.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
		
	}

}
