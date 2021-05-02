package model.BO;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import assistant.InterList;
import assistant.SimplyList;
import model.DAO.BaseInterDAO;
import model.DAO.ProdutoDAO;
import model.VO.ProdutoVO;

public class ProdutoBO implements BaseInterBO<ProdutoVO> {
	
	private static BaseInterDAO<ProdutoVO> dao = new ProdutoDAO<>();

	@Override
	public void cadastrar(ProdutoVO produto) throws IOException {
		
		if(produto.getId() < 0 || produto.getNome() == null || produto.getDescricao() == null
				|| produto.getQuantidade() < 0 || produto.getPreco() < 0)
		{
			throw new IOException("Dados inválidos inseridos em produtoBO");			
		}
		else
		{
			try {
				dao.createDAO(produto);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void editar(ProdutoVO produto) throws IOException {
		
		if(produto.getId() < 0 || produto.getNome() == null || produto.getDescricao() == null
				|| produto.getQuantidade() < 0 || produto.getPreco() < 0)
		{
			throw new IOException("Dados inválidos inseridos em produtoBO");
		}
		else
		{
			try {
				dao.updateDAO(produto);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void deletar(ProdutoVO produto) throws IOException {
		
		if(produto.getId() < 0 || produto.getNome() == null || produto.getDescricao() == null
				|| produto.getQuantidade() < 0 || produto.getPreco() < 0)
		{
			throw new IOException("Dados inválidos inseridos em produtoBO");
		}
		else
		{
			try {
				dao.removeDAO(produto);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public InterList<ProdutoVO> pesquisar(ProdutoVO produto) throws IOException {
		
		if(produto.getId() < 0 || produto.getNome() == null || produto.getDescricao() == null
				|| produto.getQuantidade() < 0 || produto.getPreco() < 0)
		{
			throw new IOException("Dados inválidos inseridos em produtoBO");
		}
		else
		{
			ProdutoVO vo = new ProdutoVO();
			InterList<ProdutoVO> listProdutos = new SimplyList<ProdutoVO>();
			
			try {
				ResultSet rs = dao.searchDAO(produto);
				while (rs.next()) {
					vo.setId(rs.getInt("id"));
					vo.setNome(rs.getString("nome"));
					vo.setDescricao(rs.getNString("descricao"));
					vo.setQuantidade(rs.getInt("quantidade"));
					vo.setPreco(rs.getDouble("preco"));
					listProdutos.addLast(vo);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}		
			return listProdutos;
		}
	}
	
	public ProdutoVO pesquisarID(int id) throws IOException {
		ProdutoDAO<ProdutoVO> daoID = new ProdutoDAO<ProdutoVO>();
		
		if(id < 0 )
		{
			throw new IOException("Dados inválidos!");
		}
		else
		{
			ProdutoVO vo = new ProdutoVO();			
			try {
				ResultSet rs = daoID.searchID(id);
				while (rs.next()) {
					vo.setId(rs.getInt("id"));
					vo.setNome(rs.getString("nome"));
					vo.setDescricao(rs.getString("descricao"));
					vo.setQuantidade(rs.getInt("quantidade"));
					vo.setPreco(rs.getDouble("preco"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}		
			return vo;
		}
	}

	@Override
	public InterList<ProdutoVO> listar() throws IOException {
		ProdutoVO produto = new ProdutoVO();
		InterList<ProdutoVO> listProdutos = new SimplyList<ProdutoVO>();
		
		try {
			ResultSet rs = dao.listDAO();
			while (rs.next()) {
				produto.setId(rs.getInt("id"));
				produto.setNome(rs.getString("nome"));
				produto.setDescricao(rs.getString("descricao"));
				produto.setQuantidade(rs.getInt("quantidade"));
				produto.setPreco(rs.getDouble("preco"));
				listProdutos.addLast(produto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listProdutos;
	}

}
