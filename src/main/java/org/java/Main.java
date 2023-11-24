package org.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Main {
	
	private static final String url = "jdbc:mysql://localhost:3306/db_nations";
	private static final String user = "root";
	private static final String pws = "";


	public static void main(String[] args) {
		//milestone2();
		//milestone3();
		milestone4();
	}
	
	
	
	
	private static void milestone2() {
		
		try (Connection con = DriverManager.getConnection(url, user, pws)) {  
		  
		  final String sql = " SELECT countries.name AS country_name, regions.name AS regions_name , continents.name AS continents_name "
			  			   + " FROM countries "
			  			   + " JOIN regions ON countries.region_id = regions.region_id "
			  			   + " JOIN continents ON regions.continent_id = continents.continent_id "
			  			   + " ORDER BY countries.name"
			  			   + " ; ";		  
		  
		  try(PreparedStatement ps = con.prepareStatement(sql)){
		    try(ResultSet rs = ps.executeQuery()){
		    	
		    	while(rs.next()) {
		    		
		    		String nameCountry = rs.getString(1);
		    		String nameRegion = rs.getString(2);
		    		String nameContinent = rs.getString(3);
		    		
		    		System.out.println(" " + nameCountry + " " + nameRegion + " " + nameContinent);
		    	}
		    }
		  }
		} catch (Exception e) {
			
			System.out.println("Error in db: " + e.getMessage());
		}
		
	}
	
	private static void milestone3() {

		Scanner in = new Scanner(System.in);
		System.out.println("Ricerca un paese");
		
		String userInput = in.nextLine();
		
		try (Connection con = DriverManager.getConnection(url, user, pws)) {  
		  
		  final String sql = " SELECT countries.country_id AS country_id , countries.name AS country_name, regions.name AS regions_name , continents.name AS continents_name "
			  			   + " FROM countries "
			  			   + " JOIN regions ON countries.region_id = regions.region_id "
			  			   + " JOIN continents ON regions.continent_id = continents.continent_id "
			  			   + " WHERE countries.name LIKE ? "
			  			   + " ORDER BY countries.name"
			  			   + " ; ";		  
		  
		  try(PreparedStatement ps = con.prepareStatement(sql)){
			  ps.setString(1, "%" + userInput + "%");
			  
		    try(ResultSet rs = ps.executeQuery()){
		    	
		    	while(rs.next()) {
		    		
		    		int id = rs.getInt(1);
		    		String nameCountry = rs.getString(2);
		    		String nameRegion = rs.getString(3);
		    		String nameContinent = rs.getString(4);
		    		
		    		System.out.println("[" + id + "] " + nameCountry + " - " + nameRegion + " - " + nameContinent + "\n");
		    	}
		    }
		  }
		} catch (Exception e) {
			
			System.out.println("Error in db: " + e.getMessage());
		}
		
		in.close();
	}
	
	private static void milestone4() {
	    Scanner in = new Scanner(System.in);
	    
	    try (Connection con = DriverManager.getConnection(url, user, pws)) {  
	      
	      final String sql = " SELECT countries.country_id AS country_id, countries.name AS country_name, regions.name AS regions_name , continents.name AS continents_name "
	                       + " FROM countries "
	                       + " JOIN regions ON countries.region_id = regions.region_id "
	                       + " JOIN continents ON regions.continent_id = continents.continent_id "
	                       + " ORDER BY countries.name"
	                       + " ; ";          
	    
	      try(PreparedStatement ps = con.prepareStatement(sql)){
	        try(ResultSet rs = ps.executeQuery()){
	            
	            while(rs.next()) {
	                
	                int id = rs.getInt(1);
	                String nameCountry = rs.getString(2);
	                String nameRegion = rs.getString(3);
	                String nameContinent = rs.getString(4);
	                
	                System.out.println("[" + id + "] " + nameCountry + " - " + nameRegion + " - " + nameContinent + "\n");
	            }
	        }
	      }

	      System.out.println("Inserisci l'ID di una nazione:");
	      int countryId = in.nextInt();

	      final String sqlLanguages = " SELECT languages.language"
	      		+ " FROM countries"
	      		+ " JOIN country_languages"
	      		+ " ON countries.country_id = country_languages.country_id"
	      		+ " JOIN languages"
	      		+ " ON country_languages.language_id = languages.language_id"
	      		+ " WHERE countries.country_id = ?"
	      		+ " ; ";;
	      
	      try(PreparedStatement ps = con.prepareStatement(sqlLanguages)){
	          ps.setInt(1, countryId);
	          
	        try(ResultSet rs = ps.executeQuery()){
	            
	            while(rs.next()) {
	                System.out.println("Lingua: " + rs.getString(1));
	            }
	        }
	      }

	      final String sqlStatistics = " SELECT country_stats.year, country_stats.population, country_stats.gdp"
	      		+ " FROM countries"
	      		+ " JOIN country_stats"
	      		+ " ON countries.country_id = country_stats.country_id"
	      		+ " WHERE countries.country_id = ?"
	      		+ " ORDER BY country_stats.year DESC"
	      		+ " LIMIT 1"
	      		+ " ; ";
	      try(PreparedStatement ps = con.prepareStatement(sqlStatistics)){
	          ps.setInt(1, countryId);
	          
			  System.out.print("\n----------------------------------------------------------\n");
			  System.out.println("Statistiche più recenti\n");
	          
	        try(ResultSet rs = ps.executeQuery()){
	            
	            while(rs.next()) {
					  String year = rs.getString(1);
					  String population = rs.getString(2);
					  String gdp = rs.getString(3);
					  
					  System.out.println("Anno statistiche: " + year);
					  System.out.println("Popolazione: " + population);
					  System.out.println("GDP: " + gdp);
	            }
	        }
	      }

	    } catch (Exception e) {
	        
	        System.out.println("Error in db: " + e.getMessage());
	    }
	    
	    in.close();
	}
	
	

}
