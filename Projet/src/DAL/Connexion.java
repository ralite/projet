package DAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Connexion {
	
	private static Connexion instance=null;
	String url="jdbc:oracle:thin:@162.38.222.149:1521:iut";
	String driver = "oracle.jdbc.driver.OracleDriver";
	String login = "ralitej";
	String mdp="2205000408Z";
	Connection cn=null;
	
	public Connexion(){
		try {
			Class.forName(driver);
			cn=DriverManager.getConnection(url,login,mdp);
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public static Connexion getInstance(){
		if(instance==null){
			return new Connexion();
		}
		else return instance;
	}

	public PreparedStatement prepareStatement(String string,
			int typeScrollSensitive, int concurReadOnly) throws SQLException {

			return cn.prepareStatement(string,typeScrollSensitive,concurReadOnly);
		
	}

	public void deconnexion() {
		// TODO Auto-generated method stub
		try {
		cn.close();
		} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
	}
	
}
