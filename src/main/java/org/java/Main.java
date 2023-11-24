package org.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Main {
	
	private static final String url = "jdbc:mysql://localhost:3306/db_nations";
	private static final String user = "root";
	private static final String pws = "";


	public static void main(String[] args) {
		test1();

	}
	
	
	
	
	private static void test1() {
		
		
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

}
