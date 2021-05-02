package controller;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

import assistant.InterList;
import assistant.SimplyList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
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
	@FXML private Label adicionado;
	
	@FXML private AnchorPane overlay;
	@FXML private Region antiButton;
	
	ProdutoBO boP = new ProdutoBO();
	ClienteBO boC = new ClienteBO();
	BaseInterBO<CompraVO> boCC = new CompraBO();
	InterList<CompraVO> listaCompras = new SimplyList<CompraVO>();
	
	String path = "src/notas/notafiscal.txt";
	
	public void addCC(ActionEvent event) throws NumberFormatException, IOException, InterruptedException {
		//verifica campos em branco;
		if (!(idCC.getText().equals("") || qntdCC.getText().equals("") || clienteCC.getText().equals(""))) {
			
			//verifica se ID e quantidade são apenas numeros;
			if (idCC.getText().matches("^[0-9]*$") && qntdCC.getText().matches("^[0-9]*$")) {				
				ProdutoVO voP = boP.pesquisarID(Integer.parseInt(idCC.getText()));
				
				//verifica se produto é válido e existente;
				if (voP.getId() > 0) {
					ClienteVO voC = boC.pesquisarCPF(clienteCC.getText());
					
					//verifica se o cliente é válido e existente;
					if (voC.getCadastroPessoa() != null) {
							CompraVO voCC = new CompraVO();	
							
							//adiciona o produto ao carrinho;
							voCC.setCliente(voC);
							voCC.setProduto(voP);	
							voCC.setDataCompra(Calendar.getInstance());
							voCC.setQuantidade(Integer.parseInt(qntdCC.getText()));
							
							//verifica se é o primeiro item adicionado no carrinho;
							if (!listaCompras.isEmpty()) {
								//verifica se o cliente mudou;
								if (!voC.getCadastroPessoa().equals(listaCompras.peekLast().getCliente().getCadastroPessoa())) {
									error.setText("O cliente mudou? Finalize a compra.");
									error.setVisible(true);
									throw new IOException("O cliente mudou? Finalize a compra.");
								}
								
								//verifica se o produto já foi adicionado no carrinho;
								for(int i = 1; (i-1) < ((SimplyList<CompraVO>) listaCompras).getSize(); i++) {
									if(listaCompras.search(i).getProduto().getId() == voCC.getProduto().getId()) {
										CompraVO aux = listaCompras.search(i);
										voCC.setQuantidade(voCC.getQuantidade() + aux.getQuantidade());
										if (boP.pesquisarID(voP.getId()).getQuantidade() >= voCC.getQuantidade()) {
											listaCompras.removeId(i);
										}								
									}
								}
							}
							
							//verifica se o estoque é suficiente;
							if (boP.pesquisarID(voP.getId()).getQuantidade() >= voCC.getQuantidade()) {
								voCC.setValorTotal(voCC.getQuantidade() * voCC.getProduto().getPreco());
								listaCompras.addLast(voCC);														
								qntdCC.setText("");
								idCC.setText("");
								error.setVisible(false);
								adicionado.setVisible(true);
							}
							else {
								error.setText("Quantidade insuficiente em estoque para este produto!");
								error.setVisible(true);
								adicionado.setVisible(false);
								throw new IOException("Quantidade insuficiente em estoque!");
							}							
					}
					else {
						error.setText("Cliente inexistente!");
						error.setVisible(true);
						adicionado.setVisible(false);
						throw new IOException("Cliente inexistente!");
					}
				}
				else {
					error.setText("Produto inexistente!");
					error.setVisible(true);
					adicionado.setVisible(false);
					throw new IOException("Produto inexistente!");
				}
			}
			else {
				error.setText("Quantidade e ID são apenas números!");
				error.setVisible(true);
				adicionado.setVisible(false);
				throw new IOException("Quantidade e ID são apenas números!");
			}
		}
		else {
			error.setText("Há campos em branco!");
			error.setVisible(true);
			adicionado.setVisible(false);
			throw new IOException("Há campos em branco!");
		}
	}
	
	public void endCC(ActionEvent event) throws Exception, IOException {
		Double sum = 0.0;
		try {
			//Iniciando a nota fiscal
			BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path));
			buffWrite.append("\t\t\tCupom Fiscal\n ");
			buffWrite.append("Nome da Empresa ");
			buffWrite.append("\tEndereço da Empresa ");
			buffWrite.append("\tCNPJ da Empresa \n");
			buffWrite.append("------------------------------------------------------------------\n");
			buffWrite.append("Data de compra: " + Calendar.getInstance().getTime() + "\n");
			buffWrite.append("CPF/CNPJ do Cliente: " + listaCompras.peekFirst().getCliente().getCadastroPessoa() + "\n");
			buffWrite.append("Cliente: " + listaCompras.peekFirst().getCliente().getNome() + "\n");
			buffWrite.append("------------------------------------------------------------------\n");
			buffWrite.append("\t\t\tProdutos: \n");
			while (!listaCompras.isEmpty()) {
				CompraVO vo = new CompraVO();
				vo = listaCompras.removeFirst();
				
				buffWrite.append("\nCódigo: " + vo.getProduto().getId() + "\n");
				buffWrite.append("Nome: " + vo.getProduto().getNome() + "\n");
				buffWrite.append("Preço unitário: R$" + vo.getProduto().getPreco() + "\n");
				buffWrite.append("Quantidade: " + vo.getQuantidade() + "x" + "\n");
				buffWrite.append("Preço total: R$" + vo.getValorTotal() + "\n");
				sum = sum + vo.getValorTotal();
				
				//verifica se o cliente já comprou este produto antes;
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
			buffWrite.append("\n\t\t\tValor total da compra: R$" + sum);
			buffWrite.close();
			antiButton.setVisible(true);
			overlay.setVisible(true);
		}
		catch (IOException e) {
			error.setText("Erro na finalização da compra!");
			error.setVisible(true);
			adicionado.setVisible(false);
			throw new IOException("Erro na finalização da compra!");
		}
		
	}
	
	public void cancelCC(ActionEvent event) throws Exception {
		Telas.telaMenu();
	}
	
	public void back() throws Exception {
		Telas.telaMenu();
	}
}
