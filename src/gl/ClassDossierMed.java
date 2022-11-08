package gl;




/**
 * 
 * ici on va implimenter la classe dossier medicale dans le quel 
 * 
 * on pourra sauvgarder les donnes d'un patient ou les modifier 
 * 
 * evidament il existe la methode supprimer mais juste en cas ou 
 * 
 * on veut supprimer tout les donnes d'un patient
 * 
 * ainsi que les methode de la table des analyses car avec chaque dossier medicale on trouve les analyse demander
 *  
 * 
 *  les cles 
 * 
 */

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ClassDossierMed {

//(le id patient est une cle primere de la table dossier 
	
	//medicale car chaque patient a un seul dossie rmedicale

	// et on fera des modification sur ce dernier dans chaque consultation si on veut bien sure 

private String NUMCONS; // c'est une le etrangere de la table consultation 
private String MALADIE_CHRONIQUE;
private String ID_PATIENT;  // c'est une clé etrangere de la table patient 
private String GROUPE_SANGUIN ;
private String POIDS ;
private String TAILLE;
private String INFORMATION_DE_MALADIE_CHRO;
private String analyse;

private String resultat ;
private String commentaire ;

// initialiser tout les variable responsable de la connexion de a b&ase de donnes + celle de l'execution de la commande sql
Connection connection = null;
PreparedStatement prp = null;
PreparedStatement ps = null;
ResultSet rs = null;
//**********************************************************************************************************
DefaultTableModel defaultTableModel = new DefaultTableModel();

// les constructeurs
public ClassDossierMed(){}
public ClassDossierMed( String NUMCONS , String ID_PATIENT, String GROUPE_SANGUIN, String POIDS, String TAILLE, String INFORMATION_DE_MALADIE_CHRO,String analyse ,String resultat) {
	 
	
	this.NUMCONS = NUMCONS;
	
	this.ID_PATIENT=ID_PATIENT;
	this.GROUPE_SANGUIN=GROUPE_SANGUIN;

	this.POIDS = POIDS;
	this.TAILLE= TAILLE;
	this.INFORMATION_DE_MALADIE_CHRO = INFORMATION_DE_MALADIE_CHRO;
	this.analyse=analyse;
	this.resultat=resultat;
	
	
	
}
/* la methode ajouter un dossier medicale */
public void ajouterFiche( String NUMCONS , String ID_PATIENT, String GROUPE_SANGUIN, String POIDS, String TAILLE, String INFORMATION_DE_MALADIE_CHRO) {
	
	connection=sqlConnection.bdConnector();
    if (connection != null) {
        
    	String NumConsd= NUMCONS ;
        
        String Id_Patient=ID_PATIENT  ;
       
        String Groupe_Sanguin = GROUPE_SANGUIN;
        String Poids=POIDS;
        String Taille = TAILLE;
        String Information_de_Maladie_Chro= INFORMATION_DE_MALADIE_CHRO;

      
        
        
         
        String sql = "insert into dossier_medicale values (?,?,?,?,?,?)";
        try {
            prp = connection.prepareStatement(sql);
            prp.setString(1, NumConsd);
         
            prp.setString(2, Id_Patient);
            prp.setString(3, Groupe_Sanguin );
            prp.setString(4, Poids);
           
            prp.setString(5, Taille);
            prp.setString(6, Information_de_Maladie_Chro);

            
            prp.execute();
            JOptionPane.showMessageDialog(null, " fiche patient :Data Saved");
        } catch (SQLException e) {
        	JOptionPane.showMessageDialog(null, " fiche patient :faites attentions au contraintes");
        	e.printStackTrace();
        	
        }
    }
    
} 


/* la methode modifier un dossier medicale */

public void modifierFiche( String NUMCONS , String ID_PATIENT, String GROUPE_SANGUIN, String POIDS, String TAILLE, String INFORMATION_DE_MALADIE_CHRO) {
	connection=sqlConnection.bdConnector();

	
     String sql = "Update dossier_medicale set GROUPE_SANGUIN='"+GROUPE_SANGUIN+"' ,POIDS='"+POIDS+"' ,TAILLE='"+TAILLE+"' ,INFORMATION_DE_MALADIE_CHRO  = '"+INFORMATION_DE_MALADIE_CHRO +"' where ID_PATIENT='"+ID_PATIENT+"' and numcons = '"+NUMCONS+"' ";
     try {
         ps = connection.prepareStatement(sql);
         ps.execute();
        
         JOptionPane.showMessageDialog(null, "fiche patient Data Updated");
     } catch (HeadlessException | SQLException e) {
    	 JOptionPane.showMessageDialog(null, " fiche patient :faites attentions au contraintes");
        
     }
 }

/* la methode supprimer un dossier medicale mais elle sera pas manipuler dans l'inteface de dossier medicale mais plustot dans la suppression des donnes d'un patient  */

public void supprimerFiche(String numcons,String ID) {
	connection=sqlConnection.bdConnector();
   
     String sql = "Delete from dossier_medicale where NUMCONS ='"+ numcons +"'AND id_patient ='"+ID+"'";
     try {
         ps = connection.prepareStatement(sql);
         ps.execute();
         JOptionPane.showMessageDialog(null, " folder with id : "+ID+ " has been deleted");
              
         connection.close();
     } catch (SQLException e) {
         JOptionPane.showMessageDialog(null, " folder with id :" +ID+ " not found");
}
}

// la meme chose pour cette methode 
public void supprimerFiche(String ID) {
	connection=sqlConnection.bdConnector();
   
     String sql = "Delete from dossier_medicale where id_patient ='"+ ID +"'";
     try {
         ps = connection.prepareStatement(sql);
         ps.execute();
         JOptionPane.showMessageDialog(null, " folder with id : "+ID+ " has been deleted");
              
         connection.close();
     } catch (SQLException e) {
         JOptionPane.showMessageDialog(null, " folder with id :" +ID+ " not found");
}
     
}
/////////////////////////////////////////////////////////////////////////analyse ///////////////////////////////////////////////////////////////////////////

/* la methode ajouter des analyses*/

public void ajouterana( String NUMCONS , String ID_PATIENT, String analyse, String result, String commentaire) {
	
	connection=sqlConnection.bdConnector();
    if (connection != null) {
        
    	String NumConsd= NUMCONS ;
        
        String Id_Patient=ID_PATIENT  ;
       
        String resu = result;
        String ana=analyse;
        String com=commentaire;
 
      
        
        
         
        String sql = "insert into analyse values (?,?,?,?,?)";
        try {
            prp = connection.prepareStatement(sql);
            prp.setString(1, NumConsd);
         
            prp.setString(2, Id_Patient);
            prp.setString(3, ana );
            prp.setString(4, resu);
            prp.setString(5, com);
        

            
            prp.execute();
            JOptionPane.showMessageDialog(null, " analyse patient :Data Saved");
        } catch (SQLException e) {
        	JOptionPane.showMessageDialog(null, " analyse patient :faites attentions au contraintes");
        	e.printStackTrace();
        	
        }
    }
    
} 


/* la methode modifier des analyses*/
public void modifierana( String NUMCONS , String ID_PATIENT,String analyse ,String resultat,String commentaire) {
	connection=sqlConnection.bdConnector();

	
     String sql = "Update analyse set ANALYSE_DEMANDER='"+analyse+"' ,LEUR_RESULTAT='"+resultat+"' ,commentaire='"+commentaire+"' where ID_PATIENT='"+ID_PATIENT+"' and numcons = '"+NUMCONS+"' ";
     try {
         ps = connection.prepareStatement(sql);
         ps.execute();
        
         JOptionPane.showMessageDialog(null, "analyse patient Data Updated");
     } catch (HeadlessException | SQLException e) {
    	 JOptionPane.showMessageDialog(null, " analyse patient :faites attentions au contraintes");
        
     }
 }
/* la methode supprimer des analyses*/
public void supprimerana(String ID) {
	connection=sqlConnection.bdConnector();
   
     String sql = "Delete from analyse where id_patient ='"+ ID +"'";
     try {
         ps = connection.prepareStatement(sql);
         ps.execute();
         JOptionPane.showMessageDialog(null, " analyse with id : "+ID+ " has been deleted");
              
         connection.close();
     } catch (SQLException e) {
         JOptionPane.showMessageDialog(null, " analyse with id :" +ID+ " not found");
}
     
}

// les gettes and les settres 


public String getNUMCONS() {
	return NUMCONS;
}
public void setNUMCONS(String nUMCONS) {
	NUMCONS = nUMCONS;
}
public String getMALADIE_CHRONIQUE() {
	return MALADIE_CHRONIQUE;
}
public void setMALADIE_CHRONIQUE(String mALADIE_CHRONIQUE) {
	MALADIE_CHRONIQUE = mALADIE_CHRONIQUE;
}
public String getID_PATIENT() {
	return ID_PATIENT;
}
public void setID_PATIENT(String iD_PATIENT) {
	ID_PATIENT = iD_PATIENT;
}
public String getGROUPE_SANGUIN() {
	return GROUPE_SANGUIN;
}
public void setGROUPE_SANGUIN(String gROUPE_SANGUIN) {
	GROUPE_SANGUIN = gROUPE_SANGUIN;
}
public String getPOIDS() {
	return POIDS;
}
public void setPOIDS(String pOIDS) {
	POIDS = pOIDS;
}
public String getTAILLE() {
	return TAILLE;
}
public void setTAILLE(String tAILLE) {
	TAILLE = tAILLE;
}
public String getINFORMATION_DE_MALADIE_CHRO() {
	return INFORMATION_DE_MALADIE_CHRO;
}
public void setINFORMATION_DE_MALADIE_CHRO(String iNFORMATION_DE_MALADIE_CHRO) {
	INFORMATION_DE_MALADIE_CHRO = iNFORMATION_DE_MALADIE_CHRO;
}
public String getAnalyse() {
	return analyse;
}
public void setAnalyse(String analyse) {
	this.analyse = analyse;
}
public String getResultat() {
	return resultat;
}
public void setResultat(String resultat) {
	this.resultat = resultat;
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
public String getCommentaire() {
	return commentaire;
}
public void setCommentaire(String commentaire) {
	this.commentaire = commentaire;
}


}

