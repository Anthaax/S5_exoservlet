package fr.intech.s5.appusers.services;


import java.sql.*;

public class Connexion {
    
    
    String urlPilote = "com.mysql.jdbc.Driver"; //chemin pour charger le pilote 
    
    String urlBaseDonnees = "jdbc:mysql://localhost/app_users";// chem de connexion a la bd
    
    Connection con ;
    
    public Connexion(){
    
    //chargement de notre poilote 
    
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
     }
  
   }
    
    public Connection getConnexion(){
        
        return con;
    }
}




