package gl;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class classOrdonnance {

/**
 * cette classe et propre comme son nom l'indique a l'ordonance
 * 
 *  ici on va pouvoir enregister une ordonnznce ansi que la modifier ou bien la supprimé
 * 
 * la methode supprimé on va pas l'utiliser dans l'inteface ordonnance car on 
 *
 * va par permettre a un medecin de supprimé une ordonnance 
 * 
 * déja prescrit pour des raison de securité
 * 
 * mais bienque lors de la supprisssion d'un patient 
 * 
 * de tout la base de donnes
 * 
 *  on va supprimer meme ses ordonnances 
 */
	 
private String numcons;
private String ID_PATIENT;
private String ordonnance;
private String date;


Connection connection = null;
PreparedStatement prp = null;
PreparedStatement ps = null;
ResultSet rs = null;
DefaultTableModel defaultTableModel = new DefaultTableModel();
classOrdonnance(){}// constructeur
public classOrdonnance(  String ID_PATIENT, String numcons ,String ordonnance) {
	 
	

	this.ID_PATIENT=ID_PATIENT;
	this. numcons= numcons;
	this.ordonnance=ordonnance;

	
	
	
}
 // la methode ajoter une ordonnace a l'aide l'une connecxion aune base de donnes 
public void ajouterOrd(String ID_PATIENT, String numcons ,String ordonnance) {
	
	// connexion sgbd 
	
	connection=sqlConnection.bdConnector();
    if (connection != null) {
    	
        
    	
        
        String Id=ID_PATIENT  ;
       
        String ord = ordonnance ;
       
        String consu =  numcons;
   
      
        
        
         // commande sql 
        String sql = "insert into ORDONNANCE values (?,?,?)";
        try {
            prp = connection.prepareStatement(sql);
            prp.setString(1, Id);
         
            prp.setString(2,consu );
            
            prp.setString(3,ord);
           
       // execution de la commande 
            prp.execute();
         // affichage d'un message 
            JOptionPane.showMessageDialog(null, "l'ordonnance de patient"+Id+" is Saved");
        } catch (SQLException e) {
        	// affichage d'un message d'erreur 
        	 JOptionPane.showMessageDialog(null, "l'ordonnance de patient "+Id+": faites attention au repetitions");
        	e.printStackTrace();
        	
        }
    }
    
} 

//la methode modifier une ordonnace a l'aide l'une connecxion aune base de donnes 

public void modifierOrd( String ID_PATIENT, String  NUMCONS, String ord) {
	connection=sqlConnection.bdConnector();

	
     String sql = "Update ORDONNANCE  set ODR='"+ord+"' where ID_PATIENT='"+ID_PATIENT+"' and numcons ='"+NUMCONS+"' ";
     try {
         ps = connection.prepareStatement(sql);
         ps.execute();
        
         JOptionPane.showMessageDialog(null, "l'ordonnance  Data Updated");
     } catch (HeadlessException | SQLException e) {
    	 JOptionPane.showMessageDialog(null, " l'ordonnance  :faites attentions au contraintes");
        
     }
 }

    

//la methode supprimer une ordonnace a l'aide l'une connecxion aune base de donnes par id et numero de consultation

public void supprimerOrd(String ID,String NUMCONS) {
	connection=sqlConnection.bdConnector();
   
     String sql = "Delete from ORDONNANCE where id_patient ='"+ ID +"' and numcons ='"+NUMCONS+"' ";
     try {
         ps = connection.prepareStatement(sql);
         ps.execute();
      // affichage d'un message 
         JOptionPane.showMessageDialog(null, " l'ordonnance with id : "+ID+ "et"+NUMCONS+" has been deleted");
              
       
     } catch (SQLException e) {
    	// affichage d'un message d'erreur
         JOptionPane.showMessageDialog(null, " l'ordonnance with id :" +ID+ "et"+NUMCONS+ " not found");
}
  
}
//la methode supprimer une ordonnace a l'aide l'une connecxion aune base de donnes par id seulement 
public void supprimerOrd(String ID) {
	connection=sqlConnection.bdConnector();
   
     String sql = "Delete from ORDONNANCE where id_patient ='"+ ID +"'";
     try {
         ps = connection.prepareStatement(sql);
         ps.execute();
         // affichage d'un message 
         JOptionPane.showMessageDialog(null, " l'ordonnance with id : "+ID+" has been deleted");
              
         connection.close();
     } catch (SQLException e) {
    	// affichage d'un message  d'erreur 
         JOptionPane.showMessageDialog(null, " l'ordonnance with id :" +ID+ " not found");
}

}

// et biensur les getters et les setters de touts les variables deja utiliser dans la classe
public String getNumcons() {
	return numcons;
}
public void setNumcons(String numcons) {
	this.numcons = numcons;
}
public String getID_PATIENT() {
	return ID_PATIENT;
}
public void setID_PATIENT(String iD_PATIENT) {
	ID_PATIENT = iD_PATIENT;
}
public String getOrdonnance() {
	return ordonnance;
}
public void setOrdonnance(String ordonnance) {
	this.ordonnance = ordonnance;
}
public String getDate() {
	return date;
}
public void setDate(String date) {
	this.date = date;
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