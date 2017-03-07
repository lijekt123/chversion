package com.yun.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RentSeqDaoImpl implements RentSeqDao{
	final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
	final String USER = "yun";
	final String PASSWORD = "1111";
	
	public void getClassLoad(){
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");	
		} catch (ClassNotFoundException e) {			
			throw new RuntimeException();						
		}
	}
	
	@Override
	public int createSeq() {
		getClassLoad();
		
		Connection conn = null;										
		PreparedStatement pstmt= null;											
		
		String query = "CREATE SEQUENCE RENT_SEQ "
						+ " INCREMENT BY 1 "
						+ " START WITH 1000 "
						+ " MINVALUE 1000 "
						+ " NOCYCLE ";
		
		int x = -1;
		
		try {
		    conn = DriverManager.getConnection(URL, USER, PASSWORD);
		    pstmt = conn.prepareStatement(query);
		    
		    x = pstmt.executeUpdate();								
		    
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
		    if (pstmt != null) try {pstmt.close(); } catch(SQLException ex) {}	
		    if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		
		return x;
	}

	@Override
	public int dropSeq() {
		getClassLoad();
				
		Connection conn = null;										
		PreparedStatement pstmt= null;											

		String query = " DROP SEQUENCE RENT_SEQ ";
		
		int x = -1;
		
		try {
		    conn = DriverManager.getConnection(URL, USER, PASSWORD);
		    pstmt = conn.prepareStatement(query);
		    		    
		    x = pstmt.executeUpdate();				
		    
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
		    if (pstmt != null) try {pstmt.close(); } catch(SQLException ex) {}	
		    if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		
		return x;
	}
		
}
