package model.BO;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import assistant.InterList;
import assistant.SimplyList;
import model.DAO.BaseInterDAO;
import model.DAO.CompraDAO;
import model.VO.CompraVO;

public class CompraBO implements BaseInterBO<CompraVO>{
	
	private static BaseInterDAO<CompraVO> dao = new CompraDAO<>();

	@Override
	public void cadastrar(CompraVO compra) throws IOException {
		
		if(compra.getCliente() == null || compra.getProduto() == null || compra.getDataCompra()
					== null || compra.getQuantidade() < 0 || compra.getValorTotal() < 0)
			{
					throw new IOException("Dados inválidos inseridos em CompraBO!");
			}
			else
			{
				try {
					dao.createDAO(compra);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}	
	}

	@Override
	public void editar(CompraVO compra) throws IOException {
		
		if(compra.getCliente() == null || compra.getProduto() == null || compra.getDataCompra()
				== null || compra.getQuantidade() < 0 || compra.getValorTotal() < 0)
		{
			throw new IOException("Dados inválidos inseridos em CompraBO!");
		}
		else
		{
			try {
				dao.updateDAO(compra);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void deletar(CompraVO compra) throws IOException {
		
		if(compra.getCliente() == null || compra.getProduto() == null || compra.getDataCompra()
				== null || compra.getQuantidade() < 0 || compra.getValorTotal() < 0)
		{
			throw new IOException("Dados inválidos inseridos em CompraBO!");
		}
		else
		{
			try {
				dao.removeDAO(compra);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public InterList<CompraVO> pesquisar(CompraVO compra) throws IOException {
		
		if(compra.getCliente() == null || compra.getProduto() == null || compra.getDataCompra()
				== null || compra.getQuantidade() < 0 || compra.getValorTotal() < 0)
		{
			throw new IOException("Dados inválidos inseridos em CompraBO!");
		}
		else
		{
			InterList<CompraVO> listCompras = new SimplyList<CompraVO>();
			Calendar cal = Calendar.getInstance();
			
			try {
				ResultSet rs = dao.searchDAO(compra);
				while (rs.next()) {
					CompraVO vo = new CompraVO();
					vo.setCliente(compra.getCliente());
					vo.setProduto(compra.getProduto());
					cal.setTime(rs.getDate("data_compra"));
					vo.setDataCompra(cal);
					vo.setQuantidade(rs.getInt("quantidade"));
					vo.setValorTotal(rs.getDouble("valor_total"));
					listCompras.addLast(vo);
				}
			} catch (SQLException e) {
				return null;
			}
			return listCompras;
		}
	}

	@Override
	public InterList<CompraVO> listar() throws IOException {
		InterList<CompraVO> listCompras = new SimplyList<CompraVO>();
		Calendar cal = Calendar.getInstance();
		
		try {
			ResultSet rs = dao.listDAO();
			while (rs.next()) {
				CompraVO compra = new CompraVO();
				compra.setCliente(compra.getCliente());
				compra.setProduto(compra.getProduto());
				cal.setTime(rs.getDate("data_compra"));
				compra.setDataCompra(cal);
				compra.setQuantidade(rs.getInt("quantidade"));
				compra.setValorTotal(rs.getDouble("valor_total"));
				listCompras.addLast(compra);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return listCompras;
	}

}
