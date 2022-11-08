package gl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 * 
 * dans cette classe on trouve toute les methodes qu'on ayrra besoin pour la manipulation des rdvs 
 *  modifier 
 *  ajouter
 *  supprimer 
 *  consulter 
 *  chercher 
 *
 */

public class ClassRv {
/**
 * ****************************** ici on va implimenter tout les methodes issentiel ********
 * 
 */
	
	
private String heure;
private String Date;
private String nom,prenom,email,tel;
@SuppressWarnings("unused")
private static int attribution = 0;
@SuppressWarnings("unused")
private int numéro = 0;
Connection connection = null;
PreparedStatement prp = null;
PreparedStatement ps = null;
ResultSet rs = null;
DefaultTableModel defaultTableModel = new DefaultTableModel();
public ClassRv() {
	
	connection=sqlConnection.bdConnector();
}

public ClassRv(String nom ,String prenom,String date,String heure,  String email, String tel) {
	connection=sqlConnection.bdConnector();
	this.heure = heure;
	Date = date;
    this.nom=nom ;
    this.prenom=prenom ;
	
	this.email = email;
	this.tel = tel;
}


@SuppressWarnings("unused")
// *********************************************** la methide ajouter un rendez vous ***********************************************
public void ajouterrdv()  {
	
	try {
		connection=sqlConnection.bdConnector();
				
			Statement stt =connection.createStatement();
			

	
		String sql="INSERT INTO rdv VALUES(?,?,?,?,?,?)";
		PreparedStatement	rv = connection.prepareStatement(sql);
		
//******************************* affecter le resultat des colones vers les variables de parametres ********************************************
		rv.setString(3, nom);
		rv.setString(4, prenom);
		rv.setString(1,Date);
		rv.setString(2, heure);
		rv.setString(5, email);
		rv.setString(6, tel);
		ResultSet rss= rv.executeQuery();
		JOptionPane.showMessageDialog(null, "BIEN INSERREE DANS LA TABLE DES RDV");
		}
		catch (Exception e) {
			
			JOptionPane.showMessageDialog(null, "DATE DEJA INSERREE");
			System.out.println(e);
			
		}
		
		
	
}
//*********************************************** la methide supprimer un rendez vous ***********************************************
public void supprimer(String dat,String h) {
	
	
	connection=sqlConnection.bdConnector();
	// on utilise les deucx paramettres date et heure car elle represente la cle composer de la table creer en sql 
	String sql = "DELETE FROM RDV WHERE DATE_RDV='"+dat+"'AND HEURE ='"+h+"'";
	try {
		ps = connection.prepareStatement(sql);
		ps.execute();
        JOptionPane.showMessageDialog(null, " Patient avec rdv  : "+dat+"a"+h+ " a ete supprimé ");
        
        connection.close();
	} catch (SQLException e) {
		JOptionPane.showMessageDialog(null, "non supprimé faites atentions au containtes");
		e.printStackTrace();
		System.out.println(e);
	}
	
	
}
/**********************************************************************************************************/
public void supprimerd(String dat) {
	
	
	connection=sqlConnection.bdConnector();
	// on utilise les deucx paramettres date et heure car elle represente la cle composer de la table creer en sql 
	String sql = "DELETE FROM RDV WHERE DATE_RDV='"+dat+"'";
	try {
		ps = connection.prepareStatement(sql);
		ps.execute();
        JOptionPane.showMessageDialog(null, " Patient avec rdv  : "+dat+" a ete supprimé ");
        
        connection.close();
	} catch (SQLException e) {
		JOptionPane.showMessageDialog(null , "rdv : non supprimé faites atentions au containtes");
		e.printStackTrace();
		System.out.println(e);
	}
	
	
}


//public void supprimer(String dat,String h) {
//	connection=sqlConnection.bdConnector();
//	String sql = "DELETE FROM RDV WHERE id_patient='"+id+"'";
//	try {
//		ps = connection.prepareStatement(sql);
//		ps.execute();
//        JOptionPane.showMessageDialog(null, " Patient avec le id : "+id+ " a ete supprimé de la table rdv ");
//        
//        connection.close();
//	} catch (SQLException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//		JOptionPane.showMessageDialog(null, "non supprimé faites atentions au containtes");
//		System.out.println(e);
//	}
//}	
	
//*********************************************** la methide modifier un rendez vous ***********************************************

public void modifier1(String dat,String h) {
			connection=sqlConnection.bdConnector();
		String sql = "UPDATE RDV SET NOM='"+nom+"' ,PRENOM='"+prenom+"' ,DATE_RDV='"+dat+"' ,HEURE='"+h+"' , EMAIL='"+email+"' ,TEL='"+tel+ "'WHERE DATE_RDV='"+dat+"'AND HEURE='"+h+"' ";

	try {
	   ps=connection.prepareStatement(sql);
	    ps.execute();
	     
        JOptionPane.showMessageDialog(null, "Data Updated");
    } catch (SQLException e) {
    	JOptionPane.showMessageDialog(null, "non modifier faites atentions au containtes");
        JOptionPane.showMessageDialog(null, e);
    }
}




}
