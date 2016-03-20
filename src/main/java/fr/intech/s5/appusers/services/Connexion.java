package fr.intech.s5.appusers.services;


import java.sql.*;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;


public class Connexion {
   
    
    private JdbcTemplate jdbcTemplate;
	private EmbeddedDatabase database;
    
    /**
     * Constructeur permet d'initialiser la bdd et ouvrir une connexion
     */
    public Connexion(){
    	
    	//Initialisation de la base de données
	    database = (EmbeddedDatabase) (new EmbeddedDatabaseBuilder())
					.setSeparator(";;")
					.addScript("classpath:bdd.sql")
					.build();
		jdbcTemplate = new JdbcTemplate(database);
  
   }
    
    /**
     * Permet de recuperer la connexion...
     * @return
     * @throws SQLException
     */
    
    public  Connection getConnexion() throws SQLException{
        
        return jdbcTemplate.getDataSource().getConnection();
    }
}




