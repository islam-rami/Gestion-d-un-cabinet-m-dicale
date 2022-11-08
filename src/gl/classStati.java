package gl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * 
 *
 * 
 * la classe statistique est utiliser comme son nom l'indique pour calculer les statistique 
 * 
 * que c soit  par periode la on outilise une table consultation directement pour calculer le nombre de patient 
 * 
 * soit par peroide avec une maladie : donc on va calculer le nombre de patient qui on une maladie donnee dans une date donnee
 * 
 * et bien sur on prend le cas de maladie diagnostique 
 * 
 * et maladie chronique
 *
 *grace a la creation des tables avec des cles qui respecte les contraintes de chaque table 
 *
 *la supprition d'un patirnt des statistique se fait juste dans
 *
 * la suppression des donnes de patirnt et si le medecin le veut bien sure
 *
 */

public class classStati {


 
private String MALADIE_CHRONIQUE;
private String ID_PATIENT;
private String maladie ;
private String date;


Connection connection = null;
PreparedStatement prp = null;
PreparedStatement ps = null;
ResultSet rs = null;
DefaultTableModel defaultTableModel = new DefaultTableModel();
classStati(){}
public classStati(  String ID_PATIENT, String maladie ,String date) {
	 
	

	this.ID_PATIENT=ID_PATIENT;
	this.setMaladie(maladie);
	this.setDate(date);

	
	
	
}

// cette methode sera utiliser implisitement dans consultation car a chaque fois qu'on fait une consultation a un patient on va enregister sa maladie dans une date donnee 
// donc le role de cette methode elle recolte le id patient avec sa maladie dig et la date pour l'ajouter a la table statistique 
//afin de facilité ls calcule pour trouver le nombre de patient  on utilisant une table qui s'appele statiqtique
public void ajouterSta(String ID_PATIENT, String maladie ,String date) {
	
	connection=sqlConnection.bdConnector();
    if (connection != null) {
    	
        
    	
        
        String Id=ID_PATIENT  ;
       
        String mal = maladie ;
       
        String d = date;
   
      
        
        
         
        String sql = "insert into statistique values (?,?,?)";
        try {
            prp = connection.prepareStatement(sql);
            prp.setString(1, Id);
         
            prp.setString(2, mal);
            
            prp.setString(3, d);
           
       
            prp.execute();
            JOptionPane.showMessageDialog(null, "statistique maladie is Saved");
        } catch (SQLException e) {
        	 JOptionPane.showMessageDialog(null, "statistique maladie faites attention au repetitions");
        	e.printStackTrace();
        	
        }
    }
    
} 

// la meme chose pour la table ajouter patient avec sa date de rdv sera utiliser implicitement 

//(faire appelle dans consultation jfram) et pour les redondance y'aura jamais car on bien 

// choisit nous cles dune façon il y'aurra jamais des redondances

// la table creer c'est la table statistiquepa (veut dire patient par peroide )
public void ajouterStap(String ID_PATIENT,String date) {
	
	connection=sqlConnection.bdConnector();
    if (connection != null) {
    	
        
    	
        
        String Id=ID_PATIENT  ;
        String d=date  ;
 
        String sql = "insert into statistiquepa values (?,?)";
        try {
            prp = connection.prepareStatement(sql);
            prp.setString(1, Id);
            prp.setString(2, d);

            prp.execute();
            JOptionPane.showMessageDialog(null, "statistique patient is Saved");
        } catch (SQLException e) {
         	 JOptionPane.showMessageDialog(null, "statistique patient faites attention au repetitions");
        	e.printStackTrace();
        	
        }
    }
    
}

// tjrs la meme chose mais dans la table statistiquechro (de maladie chronique )
public void ajouterStachro(String ID_PATIENT, String maladiechro ,String date) {
	
	connection=sqlConnection.bdConnector();
    if (connection != null) {
    	
        
    	
        
        String Id=ID_PATIENT  ;
       
        String mal = maladiechro ;
       
        String d = date;
   
      
        
        
         
        String sql = "insert into statistiquechro values (?,?,?)";
        try {
            prp = connection.prepareStatement(sql);
            prp.setString(1, Id);
         
            prp.setString(2, mal);
            
            prp.setString(3, d);
           
       
            prp.execute();
            JOptionPane.showMessageDialog(null, "statistique maladie chronique is  Saved");
        } catch (SQLException e) {
         	 JOptionPane.showMessageDialog(null, "statistique maladie chronique faites attention au repetitions");
        	e.printStackTrace();
        	
        }
    }
    
} 


/* tout les methode de supprission ne seront pas utiliser sauf dnas la suppresion de patient de toute la base de donnes*/
public void supprimerSta(String ID) {
	connection=sqlConnection.bdConnector();
   
     String sql = "Delete from dossier_medicale where id_patient ='"+ ID +"'";
     try {
         ps = connection.prepareStatement(sql);
         ps.execute();
         JOptionPane.showMessageDialog(null, " folder with id : "+ID_PATIENT+ " has been deleted");
              
         connection.close();
     } catch (SQLException e) {
         JOptionPane.showMessageDialog(null, " folder with id :" +ID_PATIENT+ " not found");
}
}


public String getMALADIE_CHRONIQUE() {
	return MALADIE_CHRONIQUE;
}
public void setMALADIE_CHRONIQUE(String mALADIE_CHRONIQUE) {
	MALADIE_CHRONIQUE = mALADIE_CHRONIQUE;
}
public String getMaladie() {
	return maladie;
}
public void setMaladie(String maladie) {
	this.maladie = maladie;
}
public String getDate() {
	return date;
}
public void setDate(String date) {
	this.date = date;
}
	
	
	
	
	
}
