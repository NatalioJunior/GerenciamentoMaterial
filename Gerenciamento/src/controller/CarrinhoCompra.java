package controller;

import java.io.IOException;
import java.util.Calendar;

import assistant.InterList;
import assistant.SimplyList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
	
	ProdutoBO boP = new ProdutoBO();
	ClienteBO boC = new ClienteBO();
	BaseInterBO<CompraVO> boCC = new CompraBO();
	InterList<CompraVO> listaCompras = new SimplyList<CompraVO>();
	
	public void addCC(ActionEvent event) throws NumberFormatException, IOException {
		ProdutoVO voP = boP.pesquisarID(Integer.parseInt(idCC.getText()));
		if (voP != null) {
			ClienteVO voC = boC.pesquisarCPF(clienteCC.getText());
			if (voC != null) {
				try {
					CompraVO voCC = new CompraVO();
					voCC.setProduto(voP);
					voCC.setCliente(voC);
					voCC.setDataCompra(Calendar.getInstance());
					voCC.setQuantidade(Integer.parseInt(qntdCC.getText()));
					voCC.setValorTotal(voCC.getQuantidade() * voCC.getProduto().getPreco());
					listaCompras.addLast(voCC);
					clienteCC.setText("");
					qntdCC.setText("");
					idCC.setText("");
				}
				catch (Exception e) {
					throw new IOException("Erro na adição do produto!");
				}
				
			}
			else {
				throw new IOException("Cliente inexistente!");
			}
		}
		else {
			throw new IOException("Produto inexistente!");
		}
	}
	
	public void endCC(ActionEvent event) throws IOException {
		try {
			CompraVO vo = listaCompras.removeFirst();
			
			while (vo != null) {
				boCC.cadastrar(vo);
				vo = listaCompras.removeFirst();
			}
		}
		catch (IOException e) {
			throw new IOException("Erro na finalização da compra!");
		}
		
	}
	
	public void cancelCC(ActionEvent event) throws Exception {
		Telas.telaMenu();
	}
}
