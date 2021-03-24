package model.BO;

import java.io.IOException;
import java.util.List;

public interface BaseInterBO<VO> {
	
	public void cadastrar(VO vo) throws IOException;
	public void editar(VO vo) throws IOException;
	public void deletar(VO vo) throws IOException;
	public List<VO> pesquisar(VO vo) throws IOException;
	public List<VO> listar();

}
