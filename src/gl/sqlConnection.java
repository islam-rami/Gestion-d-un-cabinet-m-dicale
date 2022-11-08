package gl;

import java.sql.*;
import javax.swing.*;
public class sqlConnection {
	Connection conn=null; // pour eviter les types d'exeptions il sera mieux 
	public static Connection bdConnector() 
	{
		try {
			// utiliser la classe driver resonsable de la connexion entre le java et le sgbd oracle
			Class.forName("oracle.jdbc.driver.OracleDriver");
		  // majouter 'URl de connexion a la base oracle 
			// plus le user name et le password
			// dans l'oracle la base de donnne est interne dans cette connexion "@localhost:1521:orcl" 
			
		Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","SYSTEM","Bes14juin2015");
		 
		return conn;
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		return null;}
	}
	

}
/**/
