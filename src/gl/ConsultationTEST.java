package gl;
// dans cette class on va trouver toutes les methodes qu'on aurra besoin pour la manipulation des consultation par un medecin
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import javax.swing.JOptionPane;
//-------------------------------------------------------- la classe consultation ----------------------------------------------
public class ConsultationTEST {
	 private  String CHRO ;
	private String numcons;
	 private String obs,ID_Patient;
	 private String TRAITEMENT;
	 private String MALADIE;
	 private String date_RDV,HEURE,montant,dose;
	 private static int  incrt =1;
	 
	 static Connection connection = null;
	 PreparedStatement prp = null;
	 PreparedStatement ps = null;
	 ResultSet rs = null;
	public ConsultationTEST() {
		LocalDate d = LocalDate.now();
		
		StringBuilder kk = new StringBuilder();
		
String rs =  kk.append(d.getDayOfMonth()).append(d.getMonthValue()).toString();

		this.numcons =rs;
	}
	
	 public ConsultationTEST( String numcon  ,String ID_Patient, String obs, String MALADIE, String TRAITEMENT,
				String date_RDV, String HEURE, String montant,String CHRO,String dose) {
			super();
			connection=sqlConnection.bdConnector();
			LocalDate d = LocalDate.now();
			System.out.println(d.toString());
			StringBuilder kk = new StringBuilder();
			
	String rs =  kk.append(d.getDayOfMonth()).append(d.getMonthValue()).toString();
	System.out.println("rs "+rs);
			//this.numcons =rs;
	        this.numcons =numcon;
			this.obs = obs;
			this.setID_Patient(ID_Patient);
			this.setHEURE(HEURE);
			this.setTRAITEMENT(TRAITEMENT);
			this.setMALADIE(MALADIE);
			this.setDate_RDV(date_RDV);
			this.setMontant(montant);
			this.setCHRO(CHRO);
			this.setDose(dose);
			
			
		}
	 @SuppressWarnings("unused")
	 // ajouter une consultation pour un patient 
	public void ajouter(String ID_Patient, String OBS, String MALADIE, String TRAITEMENT,
				String date_RDV, String HEURE, String montant,String CHRO, String dose) {
			
			connection=sqlConnection.bdConnector();
		    if (connection != null) {
		        
		    	String Id= ID_Patient;
		        
		        String obs=OBS;
		       
		        String mal = MALADIE;
		        String tr=TRAITEMENT;
		        String d = date_RDV;
		        String h = HEURE;
		        String m = montant;
		        String chro=CHRO;
		        String Dose= dose;
		        
		      
				
				try {

					Statement st = connection.createStatement();
		         String numconsu = numcons;
		        String sql = "insert into CONSULTATION values (?,?,?,?,?,?,?,?,?,?)";
		        
		            prp = connection.prepareStatement(sql);
		            
		            prp.setString(1,genererCode() );
		            prp.setString(2,Id );
		            prp.setString(3, obs);
		            prp.setString(4, mal);
		            prp.setString(5, tr);
		           
		            prp.setString(6, d);
		            prp.setString(7, h);
		            prp.setString(8, m);
		            prp.setString(9, chro) ;
		            prp.setString(10, Dose);

		            
		            ResultSet rs = prp.executeQuery();
		            
		            // message de sauvgarde
		            JOptionPane.showMessageDialog(null, "consult Data Saved");
					classStati s= new classStati();
					// ajouter le patient au statistique patient
					s.ajouterStap(Id, d);
					
					// comme dans une consultation on peut avoir plusieur maladie donc 
					//il faut les deconcatiner et puis les ajouter dan sla table  maladie diagnostique 
					 final String SEPARATEUR = " ";
					    String conte = mal;
					    
					    String mots[] = conte.split(SEPARATEUR);

					    for (int i = 0; i < mots.length; i++) {
					        System.out.println(mots[i]);
					    s.ajouterSta(Id, mots[i], d);  }
					    
					    
						// la meme chose un patient peut avoir plusieur maladie chronique dans une consulation donc 
					    //il faut deconcatiner la chaine des maladies puis l'ajouter a la table maladie chronique 
						    String conte1 = chro;
						    
						    String mots1[] = conte1.split(SEPARATEUR);

						    for (int i = 0; i < mots1.length; i++) {
						        System.out.println(mots1[i]);
						       s.ajouterStachro(Id, mots1[i], d); 
						    }
		            
		        } catch (SQLException e) {
		        	 JOptionPane.showMessageDialog(null, "non enregistrer faites attention au containtes ");
		        	e.printStackTrace();
		        	
		        }
		    }} 
		    public static String genererCode(){
				try {
					// concatination de date de rdv avec le numero sequentiel d'un patient 
					connection=sqlConnection.bdConnector();
					Statement stmt = connection.createStatement();
					String concatDate =new SimpleDateFormat("ddMMyy").format(new Date());
					String sql =" select count(*) from (select*from consultation where (numcons like '"+concatDate+"%'))";
					ResultSet rs = stmt.executeQuery(sql);
					if(rs.next()){
						int sequence = rs.getInt(1)+1;
						return concatDate+String.valueOf(sequence);
					}
					else{
						return concatDate+"00";
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return "";
				}
			}
		    
		


		 // modifier une consultation pour un patient 
		public void modifier(String numcon  ,String ID_Patient, String OBS, String MALADIE, String TRAITEMENT,
				String date_RDV, String HEURE, String montant,String maladie_chro,String dose) {
			connection=sqlConnection.bdConnector();
			
		     String sql = "UPDATE CONSULTATION SET ID_PATIENT='"+ID_Patient+"' ,obs='"+obs+"' ,MALADIE='"+MALADIE+"' , TRAITMNTPR='"+TRAITEMENT+"' ,DATE_RDV='"+date_RDV+"' ,MONTANTP='"+montant+"' , MALADIE_CHRONIQUE='"+maladie_chro+"' ,dose='"+dose+"'WHERE NUMCONS='"+numcon+"'";
		     try {
		         ps = connection.prepareStatement(sql);
		         ps.execute();
		        
		         JOptionPane.showMessageDialog(null, "Data Updated");
		     } catch (HeadlessException | SQLException e) {
		    	 JOptionPane.showMessageDialog(null, "faites attentions a votre saisie de donnees");
		         JOptionPane.showMessageDialog(null, e);
		     }
		 }
		// supprimer une consultation pour un patient 
		public void supprimer(String ID) {
			connection=sqlConnection.bdConnector();
		   
		     String sql = "Delete from CONSULTATION where id_patient ='"+ ID +"' ";
		     try {
		         ps = connection.prepareStatement(sql);
		         ps.execute();
		         JOptionPane.showMessageDialog(null, " Patient with id : "+ID+ " has been deleted");
		              
		         connection.close();
		     } catch (SQLException e) {
		         JOptionPane.showMessageDialog(null, " patient with id :" +ID+ " not found");
		}
		}
// getters and setters 
		public String getCHRO() {
			return CHRO;
		}

		public void setCHRO(String cHRO) {
			CHRO = cHRO;
		}

		public String getID_Patient() {
			return ID_Patient;
		}

		public void setID_Patient(String iD_Patient) {
			ID_Patient = iD_Patient;
		}

		public String getTRAITEMENT() {
			return TRAITEMENT;
		}

		public void setTRAITEMENT(String tRAITEMENT) {
			TRAITEMENT = tRAITEMENT;
		}

		public String getMALADIE() {
			return MALADIE;
		}

		public void setMALADIE(String mALADIE) {
			MALADIE = mALADIE;
		}

		public String getDose() {
			return dose;
		}

		public void setDose(String dose) {
			this.dose = dose;
		}

		public static int getIncrt() {
			return incrt;
		}

		public static void setIncrt(int incrt) {
			ConsultationTEST.incrt = incrt;
		}

		public String getDate_RDV() {
			return date_RDV;
		}

		public void setDate_RDV(String date_RDV) {
			this.date_RDV = date_RDV;
		}

		public String getMontant() {
			return montant;
		}

		public void setMontant(String montant) {
			this.montant = montant;
		}

		public String getHEURE() {
			return HEURE;
		}

		public void setHEURE(String hEURE) {
			HEURE = hEURE;
		}

}
