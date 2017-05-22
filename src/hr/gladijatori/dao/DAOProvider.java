package hr.gladijatori.dao;

public class DAOProvider {

	private static DAO dao = new DAOImpl();
	
	public static DAO getDAO() {
		return dao;
	}
	
}