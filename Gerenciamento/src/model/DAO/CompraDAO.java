package model.DAO;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.VO.CompraVO;

public class CompraDAO<VO extends CompraVO> extends BaseDAO implements BaseInterDAO<VO> {

	@Override
	public void createDAO(VO vo) throws IOException, SQLException {
		String sql = "insert into compras values (?,?,?,?,?)";
		PreparedStatement ptst;
		try {
			ptst = getConnection().prepareStatement(sql);
			ptst.setString(1, vo.getCliente().getCadastroPessoa());
			ptst.setInt(2, vo.getProduto().getId());
			java.sql.Date date = new java.sql.Date(vo.getDataCompra().getTime().getTime());
			ptst.setDate(3, date);
			ptst.setInt(4, vo.getQuantidade());
			ptst.setDouble(5, vo.getValorTotal());
			ptst.execute();
		} catch (SQLException e){
			e.printStackTrace();
		}
		
	}

	@Override
	public void removeDAO(VO vo) throws IOException, SQLException {
		String sql = "delete from compras where id_cliente = ? and id_produto = ?";
		PreparedStatement ptst;
		
		try {
			ptst = getConnection().prepareStatement(sql);
			ptst.setString(1, vo.getCliente().getCadastroPessoa());
			ptst.setInt(2, vo.getProduto().getId());
			ptst.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void updateDAO(VO vo) throws IOException, SQLException {
		String sql = "update compras set data_compra = ?, quantidade = ?, valor_total = ? where id_cliente = ? and id_produto = ?";
		PreparedStatement ptst;
		
		try {
			ptst = getConnection().prepareStatement(sql);
			java.sql.Date date = new java.sql.Date(vo.getDataCompra().getTime().getTime());
			ptst.setDate(1, date);
			ptst.setInt(2, vo.getQuantidade());
			ptst.setDouble(3, vo.getValorTotal());
			ptst.setString(4, vo.getCliente().getCadastroPessoa());
			ptst.setInt(5, vo.getProduto().getId());
			ptst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public ResultSet listDAO() throws IOException, SQLException {
		String sql = "select * from compras";
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
		String sql = "select * from compras where id_cliente = ? and id_produto = ?";
		ResultSet rs = null;
		PreparedStatement ptst;
		
		try {
			ptst = getConnection().prepareStatement(sql);
			ptst.setString(1, vo.getCliente().getCadastroPessoa());
			ptst.setInt(2, vo.getProduto().getId());
			rs = ptst.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

}
