package model.BO;

import java.io.IOException;
import assistant.InterList;

public interface BaseInterBO<VO> {
	
	public void cadastrar(VO vo) throws IOException;
	public void editar(VO vo) throws IOException;
	public void deletar(VO vo) throws IOException;
	public InterList<VO> pesquisar(VO vo) throws IOException;
	public InterList<VO> listar() throws IOException;

}
