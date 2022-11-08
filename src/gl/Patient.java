package gl;

/**
 * pour la classe Patient
 * 
 *  c'est pour faire les inscription  d'un ouveau patient 
 *  
 *  dans cette classe on va s'intersser ou donnees de chaque patient ainsi l'ajout  la  modification et la suppression
 * 
 * et tout grace a des tables creer dans le sgbd avec sql 
 * 
 * et biensur respercter tout les containte preciser pour chaque table
 * 
 * 
 * */
 
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Patient {

private String nom;
 
private String sexe;
private String prenom;
private String Adresse ;
private String Telephone ;
private String dateNai;
private String eMail;
private String dateDinscription;

Connection connection = null;
PreparedStatement prp = null;
PreparedStatement ps = null;
ResultSet rs = null;
DefaultTableModel defaultTableModel = new DefaultTableModel();
public Patient(){} // les constructeurs
public Patient( String nom ,String prenom, String sexe, String dateNai, String adresse, String telephone, String eMail,
		String dateDinscription) {
	 
	
	this.nom = nom;
	this.prenom=prenom;
	this.dateNai=dateNai;
	this.dateDinscription=dateDinscription;

	this.sexe = sexe;
	Adresse = adresse;
	Telephone = telephone;
	this.eMail = eMail;
	
	
	
}
// inscrir un patient
public void ajouterPatient(String id ,String nom ,String prenom,String sexe,String Datenai,String Adresse,String Telephone,String eMail, String dateDinscription) {
	
	connection=sqlConnection.bdConnector(); // connexion  avec la classe connection sql (connexion vers sgbd ORACLE)
    if (connection != null) {
        
    	String Id= id;
        String Nom = nom;
        String Prenom=prenom;
       
        String Sexe = sexe;
        String DateNai=Datenai;
        String Addresse = Adresse;
        String phone = Telephone;
        String email = eMail;
        String DateDinscription=dateDinscription;
        
        
         
        String sql = "insert into patient values (?,?,?,?,?,?,?,?,?)";
        try {
            prp = connection.prepareStatement(sql);
            prp.setString(1, Id);
            prp.setString(2, Nom);
            prp.setString(3, Prenom);
            prp.setString(4, Sexe);
            prp.setString(5, DateNai);
           
            prp.setString(6, Addresse);
            prp.setString(7, phone);
            prp.setString(8, email);
            prp.setString(9, DateDinscription);

            
            prp.execute();
            JOptionPane.showMessageDialog(null, "Data Saved");
        } catch (SQLException e) {
        	JOptionPane.showMessageDialog(null, "faites atention ce id existe deja pour un autre patient ou vous avez mal remplit les dates verfier l'existance avec une recherche de id");
        	e.printStackTrace();
        	
        }
    }
    
} 

// suppression d'un patient
public void supprimerPatient(String ID) {
	connection=sqlConnection.bdConnector();
   
	  String sql = "Delete from patient where id_patient ='"+ ID +"'";
     try {
         ps = connection.prepareStatement(sql);
         ps.execute();
         JOptionPane.showMessageDialog(null, " Patient with id : "+ID+ " has been deleted");
              
         connection.close();
     } catch (SQLException e) {
         JOptionPane.showMessageDialog(null, " patient with id :" +ID+ " not found, ou si ce patient a fait des consultations il faut d'abord supprimer tout ces coordonnes et ces fichiers");
}
}
// modifier les inscription d'in patient comme l'adresse le num de telephone ............
public void modifierPatient( String id ,String nom ,String prenom,String sexe,String Datenai,String Adresse,String Telephone,String eMail, String dateDinscription) {
	connection=sqlConnection.bdConnector();
	
     String sql = "Update patient set id_patient='"+id+"' ,NOM='"+nom+"' ,PRENOM='"+prenom+"' ,SEXE='"+sexe+"' ,DATE_DE_NAISSANCE='"+Datenai+"' ,ADRESSE='"+Adresse+"' ,TELEPHONE = '"+Telephone+"', email = '"+eMail+"', DATEDINSCRIPTION= '"+dateDinscription+"' where id_patient='"+id+"' ";
     try {
         ps = connection.prepareStatement(sql);
         ps.execute();
        
         JOptionPane.showMessageDialog(null, "Data Updated");
     } catch (HeadlessException | SQLException e) {
         JOptionPane.showMessageDialog(null, e);
     }
 }
// getters and setters 
public String getNom() {
	return nom;
}
public void setNom(String nom) {
	this.nom = nom;
}
public String getSexe() {
	return sexe;
}
public void setSexe(String sexe) {
	this.sexe = sexe;
}
public String getPrenom() {
	return prenom;
}
public void setPrenom(String prenom) {
	this.prenom = prenom;
}
public String getAdresse() {
	return Adresse;
}
public void setAdresse(String adresse) {
	Adresse = adresse;
}
public String getTelephone() {
	return Telephone;
}
public void setTelephone(String telephone) {
	Telephone = telephone;
}
public String getDateNai() {
	return dateNai;
}
public void setDateNai(String dateNai) {
	this.dateNai = dateNai;
}
public String geteMail() {
	return eMail;
}
public void seteMail(String eMail) {
	this.eMail = eMail;
}
public String getDateDinscription() {
	return dateDinscription;
}
public void setDateDinscription(String dateDinscription) {
	this.dateDinscription = dateDinscription;
}
public Connection getConnection() {
	return connection;
}
public void setConnection(Connection connection) {
	this.connection = connection;
}
public PreparedStatement getPrp() {
	return prp;
}
public void setPrp(PreparedStatement prp) {
	this.prp = prp;
}
public PreparedStatement getPs() {
	return ps;
}
public void setPs(PreparedStatement ps) {
	this.ps = ps;
}
public ResultSet getRs() {
	return rs;
}
public void setRs(ResultSet rs) {
	this.rs = rs;
}
public DefaultTableModel getDefaultTableModel() {
	return defaultTableModel;
}
public void setDefaultTableModel(DefaultTableModel defaultTableModel) {
	this.defaultTableModel = defaultTableModel;
}


}
