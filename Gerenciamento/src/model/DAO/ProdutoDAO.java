package model.DAO;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.VO.ProdutoVO;

public class ProdutoDAO<VO extends ProdutoVO> extends BaseDAO implements BaseInterDAO<VO> {

	public void createDAO(VO vo) throws IOException, SQLException {
		String sql = "insert into produtos values (?,?,?,?,?)";
		PreparedStatement ptst;
		try {
			ptst = getConnection().prepareStatement(sql);
			ptst.setInt(1, vo.getId());
			ptst.setString(2,vo.getNome());
			ptst.setString(3, vo.getDescricao());
			ptst.setInt(4, vo.getQuantidade());
			ptst.setDouble(5, vo.getPreco());
			ptst.execute();
		} catch (SQLException e){
			e.printStackTrace();
		}
		
	}

	@Override
	public void removeDAO(VO vo) throws IOException, SQLException {
		String sql = "delete from produtos where id = ?";
		PreparedStatement ptst;
		
		try {
			ptst = getConnection().prepareStatement(sql);
			ptst.setInt(1, vo.getId());
			ptst.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void updateDAO(VO vo) throws IOException, SQLException {
		String sql = "update produtos set nome = ?, descricao = ?, quantidade = ?, preco = ? where id = ?";
		PreparedStatement ptst;
		
		try {
			ptst = getConnection().prepareStatement(sql);
			ptst.setString(1, vo.getNome());
			ptst.setString(2, vo.getDescricao());
			ptst.setInt(3, vo.getQuantidade());
			ptst.setDouble(4, vo.getPreco());
			ptst.setInt(5, vo.getId());
			ptst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public ResultSet listDAO() throws IOException, SQLException {
		String sql = "select * from produtos";
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

	@Override
	public ResultSet searchDAO(VO vo) throws IOException, SQLException {
		String sql = "select * from produtos where id = ?, nome = ?";
		ResultSet rs = null;
		PreparedStatement ptst;
		
		try {
			ptst = getConnection().prepareStatement(sql);
			ptst.setInt(1, vo.getId());
			ptst.setString(2, vo.getNome());
			rs = ptst.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	public ResultSet searchID(int id) throws IOException, SQLException {
		String sql = "select * from produtos where id = ?";
		ResultSet rs = null;
		PreparedStatement ptst;
		
		try {
			ptst = getConnection().prepareStatement(sql);
			ptst.setInt(1, id);
			rs = ptst.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

}
