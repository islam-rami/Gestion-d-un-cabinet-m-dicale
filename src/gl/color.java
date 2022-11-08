package gl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;



public class color {
	
static Connection connection = null;
PreparedStatement prp = null;
static PreparedStatement ps = null;
ResultSet rs = null;

/**
 * cette classe est utiliser pour la suppression d'un patient qui est pas revenu depuis 5ans 
 * elle sera instantier dans le jframe de consultation telle que si le medecin selectionne un patient il verra 
 * automatiquement si il est revenu au non et si il n'est pas revenu depuis 5nas le medecin aurra le choix de 
 * le supprimer de tout sa base de donnees mais il le va pas supprimer dans ses statistiques*/
	public color() {
		// TODO Auto-generated constructor stub
		
	}
	// voir si la periode entre la derniere 
	//consultation d'un patient et le jour la
	//est >=5ans afin de le supprimer
	public static  boolean checkdate(String d) 
	{
		
		LocalDate today= LocalDate.now();
		LocalDate dataDay= LocalDate.of(Stringtoannee (d), Stringtomois (d), Stringtojour (d));
		
		int year=Period.between(dataDay,today).getYears();

		if (year>= 5) {return true ;}
		else  return false ;
	}
	//chercher la derniere consultation d'un patient 
	public String searchMaxDate(String id) {
		connection=sqlConnection.bdConnector();
		String i = null;
		try {
		String max ="SELECT MAX(date_rdv) FROM consultation WHERE id_patient ='"+id+"' " ;
			ps = connection.prepareStatement(max);
			
			ResultSet rs=ps.executeQuery();
			
			
		     while(rs.next()) {
		    	  i=rs.getString(1);}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	     

		return i;}
	
	// recupere l'annes d'une date sql qui est ecrit en dd/mm/yy vers yyyy/mm/dd
	@SuppressWarnings("deprecation")
	public static int Stringtoannee (String d)
	{
		
		Date date1 = null;
		try {
			date1 = new SimpleDateFormat("yyyy-MM-dd").parse(d);
		} catch (ParseException e) {
			
			e.printStackTrace();
		}  
	    
	return (date1.getYear()+1900);}
	
	// recupere le mois d'une date sql qui est ecrit en dd/mm/yy vers yyyy/mm/dd
	@SuppressWarnings("deprecation")
	public static int Stringtomois (String d)
	{
		
		Date date1 = null;
		try {
			date1 = new SimpleDateFormat("yyyy-MM-dd").parse(d);
		} catch (ParseException e) {
			
			e.printStackTrace();
		}  
	      
	return (date1.getMonth()+1);}
	
	
	@SuppressWarnings("deprecation")
	// recupere le jour d'une date sql qui est ecrit en dd/mm/yy vers yyyy/mm/dd
	public static int Stringtojour (String d)
	{
		
		Date date1 = null;
		try {
			date1 = new SimpleDateFormat("yyyy-MM-dd").parse(d);
		} catch (ParseException e) {
			
			e.printStackTrace();
		}  
	   return date1.getDate();  
	}
	
	
		
		


}
