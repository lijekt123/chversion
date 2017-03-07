package com.yun.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yun.dto.Car;

public class CarDaoImpl implements CarDao{
	final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
	final String USER = "yun";
	final String PASSWORD = "1111";
	
	public CarDaoImpl() {}

	@Override
	public int add(Car list) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");	
		} catch (ClassNotFoundException e) {			
			throw new RuntimeException();						
		}
		
		Connection conn = null;										
		PreparedStatement pstmt= null;											
		
		String query = "INSERT INTO CAR VALUES(?, ?, ?, ?, ? ,?, ?, ?)";
		
		int x = -1;
		
		try {
		    conn = DriverManager.getConnection(URL, USER, PASSWORD);
		    pstmt = conn.prepareStatement(query);									
		    
		    pstmt.setString(1, list.getCarNumber());											
		    pstmt.setString(2, list.getBrand());
		    pstmt.setString(3, list.getOil());
		    pstmt.setInt(4, list.getHourPrice());
		    pstmt.setString(5, list.getCarName());
		    pstmt.setString(6, list.getCarOption());
		    pstmt.setString(7, list.getCarCategory());
		    pstmt.setString(8, list.getCarSpot());
		    
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
	public int delete(String carNumber) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");	
		} catch (ClassNotFoundException e) {					
			throw new RuntimeException();						
		}
		
		Connection conn = null;										
		PreparedStatement pstmt= null;											
		
		String query = "DELETE FROM CAR WHERE CARNUMBER=?";		
		
		int x = -1;
		
		try {
		    conn = DriverManager.getConnection(URL, USER, PASSWORD);
		    pstmt = conn.prepareStatement(query);									
		    
		    pstmt.setString(1, carNumber);											
	
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
	public int deleteAll() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");	
		} catch (ClassNotFoundException e) {					
			throw new RuntimeException();						
		}
		
		Connection conn = null;										
		PreparedStatement pstmt= null;											
		
		String query = "DELETE FROM CAR";		
		
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
	public Car get(String carNumber) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");	
		} catch (ClassNotFoundException e) {					
			throw new RuntimeException();						
		}
		
		Connection conn = null;										
		PreparedStatement pstmt= null;											
		ResultSet rs = null;
		
		String query = "SELECT * FROM CAR WHERE CARNUMBER=?";		
		
		Car car = null;
		
		try {
		    conn = DriverManager.getConnection(URL, USER, PASSWORD);
		    pstmt = conn.prepareStatement(query);									
		    
		    pstmt.setString(1, carNumber);											
		   
		    rs = pstmt.executeQuery();
		    
		    if(rs.next()){
		    	car = new Car(
		    			rs.getString("carNumber"), 
		    			rs.getString("brand"), 
		    			rs.getString("oil"), 
		    			rs.getInt("hourPrice"), 
		    			rs.getString("carName"), 
		    			rs.getString("carOption"), 
		    			rs.getString("carCategory"), 
		    			rs.getString("carSpot")
		    	);
		    }
		    
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
		    if (pstmt != null) try {pstmt.close(); } catch(SQLException ex) {}	
		    if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		
		return car;
	}

	@Override
	public List<Car> getAll() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");	
		} catch (ClassNotFoundException e) {					
			throw new RuntimeException();						
		}
		
		Connection conn = null;
		PreparedStatement pstmt= null;									
		ResultSet rs = null;
		
		String query = "SELECT * FROM CAR ORDER BY CARNUMBER ASC";		
		
		List<Car> list = null;
		
		try {
		    conn = DriverManager.getConnection(URL, USER, PASSWORD);
		    pstmt = conn.prepareStatement(query);									
		   		   
		    rs = pstmt.executeQuery();
		    
		    list = new ArrayList<Car>();
		    
		    while(rs.next()){
		    	list.add(
		    		new Car(
		    			rs.getString("carNumber"), 
		    			rs.getString("brand"), 
		    			rs.getString("oil"), 
		    			rs.getInt("hourPrice"), 
		    			rs.getString("carName"), 
		    			rs.getString("carOption"), 
		    			rs.getString("carCategory"),
		    			rs.getString("carSpot")
		    	));
		    }
		    
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
		    if (pstmt != null) try {pstmt.close(); } catch(SQLException ex) {}	
		    if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		    if (rs != null) try { rs.close(); } catch(SQLException ex) {}
		}
		
		return list;
	}
	
	@Override
	public int update(Car list) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");	
		} catch (ClassNotFoundException e) {					
			throw new RuntimeException();						
		}
		
		Connection conn = null;										
		PreparedStatement pstmt= null;											
		
		String query = "UPDATE CAR SET BRAND=?, OIL=?, "
				+ "HOURPRICE=?, CARNAME=?, CAROPTION=?, CARCATEGORY=?, CARSPOT=? WHERE CARNUMBER=?";
		
		int x = -1;
		
		try {
		    conn = DriverManager.getConnection(URL, USER, PASSWORD);
		    pstmt = conn.prepareStatement(query);									
		    											
		    pstmt.setString(1, list.getBrand());
		    pstmt.setString(2, list.getOil());
		    pstmt.setInt(3, list.getHourPrice());
		    pstmt.setString(4, list.getCarName());
		    pstmt.setString(5, list.getCarOption());
		    pstmt.setString(6, list.getCarCategory());
		    pstmt.setString(7, list.getCarSpot());
		    pstmt.setString(8, list.getCarNumber());
		   
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
	public int getCount() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");	
		} catch (ClassNotFoundException e) {					
			throw new RuntimeException();						
		}
		
		Connection conn = null;										
		PreparedStatement pstmt= null;											
		ResultSet rs = null;
		
		String query = "SELECT COUNT(*) FROM CAR";
		
		int x = -1;
		
		try {
		    conn = DriverManager.getConnection(URL, USER, PASSWORD);
		    pstmt = conn.prepareStatement(query);									
		   					
		    rs = pstmt.executeQuery();
		    
		    if(rs.next()){
		    	x = rs.getInt(1);
		    }
		    
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
		    if (pstmt != null) try {pstmt.close(); } catch(SQLException ex) {}	
		    if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		    if (rs != null) try {rs.close(); } catch(SQLException ex) {}
		}
		
		return x;
	}
	
	@Override
	public int getCountBySpot(String spot) {													// Ư�������� ��� �ڵ��� ����
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");	
		} catch (ClassNotFoundException e) {					
			throw new RuntimeException();						
		}
		
		Connection conn = null;							
		PreparedStatement pstmt= null;								
		ResultSet rs = null;
		
		String query = "SELECT COUNT(*) FROM CAR WHERE CARSPOT=?";
		
		int x = -1;
		
		try {
		    conn = DriverManager.getConnection(URL, USER, PASSWORD);
		    pstmt = conn.prepareStatement(query);									
		    pstmt.setString(1, spot);
		    
		    rs = pstmt.executeQuery();
		    
		    if(rs.next()){
		    	x = rs.getInt(1);
		    }
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
		    if (pstmt != null) try {pstmt.close(); } catch(SQLException ex) {}	
		    if (conn != null) try {conn.close(); } catch(SQLException ex) {}
		    if (rs != null) try {rs.close(); } catch(SQLException ex) {}
		}

		return x;
	}

	@Override
	public List<Car> getAllBySpot(String spot) {														// Ư�� ������  ��� �ڵ��� ��������
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");	
		} catch (ClassNotFoundException e) {					
			throw new RuntimeException();
		}
		
		Connection conn = null;							
		PreparedStatement pstmt= null;								
		ResultSet rs = null;
		
		String query = "SELECT * FROM CAR WHERE CARSPOT=? ORDER BY CARNUMBER ASC";
		
		List<Car> list = new ArrayList<Car>();
				
		try {
		    conn = DriverManager.getConnection(URL, USER, PASSWORD);
		    pstmt = conn.prepareStatement(query);									
		    pstmt.setString(1, spot);
		    
		    rs = pstmt.executeQuery();
		    
		    while(rs.next()){
		    	list.add(new Car(rs.getString("carNumber"), 
		    			rs.getString("brand"), 
		    			rs.getString("oil"), 
		    			rs.getInt("hourPrice"), 
		    			rs.getString("carName"), 
		    			rs.getString("carOption"), 
		    			rs.getString("carCategory"), 
		    			rs.getString("carSpot")
		    	));
		    }
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
		    if (pstmt != null) try {pstmt.close(); } catch(SQLException ex) {}	
		    if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		    if (rs != null) try { rs.close(); } catch(SQLException ex) {}
		}
		
		return list;
	}

	@Override
	public int checkCarNumber(String carNumber) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");	
		} catch (ClassNotFoundException e) {					
			throw new RuntimeException();						
		}
		
		Connection conn = null;										
		PreparedStatement pstmt= null;											
		ResultSet rs = null;
		
		String query = "SELECT COUNT(*) FROM CAR WHERE CARNUMBER=?";
		
		int x = -1;
		
		try {
		    conn = DriverManager.getConnection(URL, USER, PASSWORD);
		    pstmt = conn.prepareStatement(query);									
		    
		    pstmt.setString(1, carNumber);
		    
		    rs = pstmt.executeQuery();
		    
		    if(rs.next()){
		    	x = rs.getInt(1);
		    }
		    
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
		    if (pstmt != null) try {pstmt.close(); } catch(SQLException ex) {}	
		    if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		    if (rs != null) try {rs.close(); } catch(SQLException ex) {}
		}
		
		return x;
	}
}