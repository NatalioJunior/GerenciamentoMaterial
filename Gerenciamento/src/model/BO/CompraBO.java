package model.BO;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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
	public List<CompraVO> pesquisar(CompraVO compra) throws IOException {
		
		if(compra.getCliente() == null || compra.getProduto() == null || compra.getDataCompra()
				== null || compra.getQuantidade() < 0 || compra.getValorTotal() < 0)
		{
			throw new IOException("Dados inválidos inseridos em CompraBO!");
		}
		else
		{
			CompraVO vo = new CompraVO();
			List<CompraVO> listCompras = new ArrayList<>();
			Calendar cal = Calendar.getInstance();
			
			try {
				ResultSet rs = dao.searchDAO(compra);
				while (rs.next()) {
					vo.setCliente(compra.getCliente());
					vo.setProduto(compra.getProduto());
					cal.setTime(rs.getDate("data_compra"));
					vo.setDataCompra(cal);
					vo.setQuantidade(rs.getInt("quantidade"));
					vo.setValorTotal(rs.getDouble("valor_total"));
					listCompras.add(vo);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}			
			return listCompras;
		}
	}

	@Override
	public List<CompraVO> listar() throws IOException {
		CompraVO compra = new CompraVO();
		List<CompraVO> listCompras = new ArrayList<>();
		Calendar cal = Calendar.getInstance();
		
		try {
			ResultSet rs = dao.listDAO();
			while (rs.next()) {
				compra.setCliente(compra.getCliente());
				compra.setProduto(compra.getProduto());
				cal.setTime(rs.getDate("data_compra"));
				compra.setDataCompra(cal);
				compra.setQuantidade(rs.getInt("quantidade"));
				compra.setValorTotal(rs.getDouble("valor_total"));
				listCompras.add(compra);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return listCompras;
	}

}
