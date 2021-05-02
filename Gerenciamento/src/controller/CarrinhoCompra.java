package controller;

import java.io.IOException;
import java.util.Calendar;

import assistant.InterList;
import assistant.SimplyList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.BO.BaseInterBO;
import model.BO.ClienteBO;
import model.BO.CompraBO;
import model.BO.ProdutoBO;
import model.VO.ClienteVO;
import model.VO.CompraVO;
import model.VO.ProdutoVO;
import view.Telas;

public class CarrinhoCompra {
	@FXML private TextField clienteCC;
	@FXML private TextField qntdCC;
	@FXML private TextField idCC;
	@FXML private Label error;
	
	ProdutoBO boP = new ProdutoBO();
	ClienteBO boC = new ClienteBO();
	BaseInterBO<CompraVO> boCC = new CompraBO();
	InterList<CompraVO> listaCompras = new SimplyList<CompraVO>();
	
	public void addCC(ActionEvent event) throws NumberFormatException, IOException {
		if (!(idCC.getText().equals("") || qntdCC.getText().equals("") || clienteCC.getText().equals(""))) {
			ProdutoVO voP = boP.pesquisarID(Integer.parseInt(idCC.getText()));
			if (voP.getId() > 0) {
				ClienteVO voC = boC.pesquisarCPF(clienteCC.getText());
				if (voC.getCadastroPessoa() != null) {
					try {
						CompraVO voCC = new CompraVO();
						if (voCC.getCliente() == null || voCC.getCliente().getCadastroPessoa().equals(voC.getCadastroPessoa())) {
							voCC.setCliente(voC);
							voCC.setProduto(voP);						
							voCC.setDataCompra(Calendar.getInstance());
							voCC.setQuantidade(Integer.parseInt(qntdCC.getText()));												
							voCC.setValorTotal(voCC.getQuantidade() * voCC.getProduto().getPreco());
							listaCompras.addLast(voCC);							
							qntdCC.setText("");
							idCC.setText("");
							error.setVisible(false);
						}
						else {
							error.setText("O cliente mudou? Finalize a compra.");
							error.setVisible(true);
						}
						
					}
					catch (Exception e) {
						error.setText("Erro na adição do produto!");
						error.setVisible(true);
						throw new IOException("Erro na adição do produto!");
					}
					
				}
				else {
					error.setText("Cliente inexistente!");
					error.setVisible(true);
					throw new IOException("Cliente inexistente!");
				}
			}
			else {
				error.setText("Produto inexistente!");
				error.setVisible(true);
				throw new IOException("Produto inexistente!");
			}
		}
		else {
			error.setText("Há campos em branco!");
			error.setVisible(true);
			throw new IOException("Há campos em branco!");
		}
	}
	
	public void endCC(ActionEvent event) throws Exception, IOException {
		try {		
			while (!listaCompras.isEmpty()) {
				CompraVO vo = new CompraVO();
				vo = listaCompras.removeFirst();
				
				if (boP.pesquisarID(vo.getProduto().getId()).getQuantidade() >= vo.getQuantidade()) {
					if (boCC.pesquisar(vo).isEmpty()) {
						boCC.cadastrar(vo);
					}
					else {
						CompraVO aux = boCC.pesquisar(vo).peekFirst();
						vo.setDataCompra(Calendar.getInstance());
						vo.setQuantidade(vo.getQuantidade() + aux.getQuantidade());
						vo.setValorTotal(vo.getValorTotal() + aux.getValorTotal());
						boCC.editar(vo);
					}
				}
				else {
					error.setText("Quantidade insuficiente em estoque para algum produto! Cancele a compra.");
					error.setVisible(true);
					throw new IOException("Quantidade insuficiente em estoque!");
				}
			}
			
			Telas.telaMenu();
		}
		catch (IOException e) {
			throw new IOException("Erro na finalização da compra!");
		}
		
	}
	
	public void cancelCC(ActionEvent event) throws Exception {
		Telas.telaMenu();
	}
}
