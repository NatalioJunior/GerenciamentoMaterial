package model.DAO;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface BaseInterDAO<VO> {
	public void createDAO(VO vo) throws IOException, SQLException;
	public void removeDAO(VO vo) throws IOException, SQLException;
	public void updateDAO(VO vo) throws IOException, SQLException;
	public ResultSet listDAO() throws IOException, SQLException;;
}
