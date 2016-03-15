package fr.intech.s5.appusers.services;


import java.sql.*;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;


public class Connexion {
    
	
    //String urlPilote = "com.mysql.jdbc.Driver"; //chemin pour charger le pilote 
    //String urlPilote = "org.hsqldb.jdbcDriver"; //chemin pour charger le pilote 

    
    //String urlBaseDonnees = "jdbc:mysql://localhost/app_users";// chem de connexion a la bd
    
    private JdbcTemplate jdbcTemplate;
	private EmbeddedDatabase database;
    
   // Connection con ;
    
    public Connexion(){
    	
    database = (EmbeddedDatabase) (new EmbeddedDatabaseBuilder())
				.setSeparator(";;")
				.addScript("classpath:bdd.sql")
				.build();
	jdbcTemplate = new JdbcTemplate(database);
    
    /*//chargement de notre poilote 
    
     try{
    //Syntaxe pour charger notre pilote
    
    Class.forName(urlPilote);
    System.out.println("Chargement du pilote reussie");
    
    }
     
     catch(ClassNotFoundException ex){
    
    System.out.println(ex);
   }
     
     //CONNEXION A LA BASE DE DONNESS
     
     try{
         
         con=DriverManager.getConnection(urlBaseDonnees, "root","");
         System.out.println("Connecion a la Bd reussie");
          
     }
     
     catch(SQLException ex){
         
         System.out.println(ex);
     }*/
  
   }
    
    public  Connection getConnexion() throws SQLException{
        
        return jdbcTemplate.getDataSource().getConnection();
    }
}




