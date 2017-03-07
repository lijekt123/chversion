package com.yun.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yun.dto.Car;
import com.yun.dto.Member;
import com.yun.dto.Rent;

public class RentDaoImpl implements RentDao{
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
	public int add(Rent rent, String id, String carNumber) {
		getClassLoad();		
		
		Connection conn = null;										
		PreparedStatement pstmt= null;											
		
		String query = "INSERT INTO RENT VALUES(RENT_SEQ.NEXTVAL,?,?,TO_DATE(?, 'YYYY-MM-DD HH24:MI'), "
				+ " TO_DATE(?, 'YYYY-MM-DD HH24:MI'), TO_DATE(SYSDATE, 'YYYY-MM-DD HH:MI'),?,?,?,?)";
		
		int x = -1;
		
		try {
		    conn = DriverManager.getConnection(URL, USER, PASSWORD);
		    pstmt = conn.prepareStatement(query);
		    
		    pstmt.setString(1, id);
		    pstmt.setString(2, carNumber);
		    pstmt.setString(3, rent.getStartDate());											
		    pstmt.setString(4, rent.getEndDate());
		    pstmt.setInt(5, rent.getInsurance());
		    pstmt.setString(6, rent.getRentSpot());
		    pstmt.setString(7, rent.getReturnSpot());
		    pstmt.setInt(8, rent.getRentState());
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
	public int delete(int rentNumber) {
		getClassLoad();
		
		Connection conn = null;										
		PreparedStatement pstmt= null;											
		
		String query = "DELETE FROM RENT WHERE RENTNUMBER=?";
		
		int x = -1;
		
		try {
		    conn = DriverManager.getConnection(URL, USER, PASSWORD);
		    pstmt = conn.prepareStatement(query);									
		    
		    pstmt.setInt(1, rentNumber);											
		    
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
		getClassLoad();
		
		Connection conn = null;										
		PreparedStatement pstmt= null;											
		
		String query = "DELETE FROM RENT";
		
		int x = -1;
		
		try {
		    conn = DriverManager.getConnection(URL, USER, PASSWORD);
		    pstmt = conn.prepareStatement(query);						
		    
		    x = pstmt.executeUpdate();												
		    
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
		    if (pstmt != null) try {pstmt.close();} catch(SQLException ex) {}	
		    if (conn != null) try {conn.close();} catch(SQLException ex) {}
		}
		
		return x;
	}

	@Override
	public Rent get(String id, int rentNumber) {
		getClassLoad();
		
		Connection conn = null;										
		PreparedStatement pstmt= null;											
		ResultSet rs = null;
		
		String query = " SELECT RENTNUMBER, RENT.ID, RENT.CARNUMBER, "
				+ " MEMBER.NAME, MEMBER.PHONENUMBER, MEMBER.BIRTH, "
				+ " CAR.BRAND, CAR.CARCATEGORY, CAR.CARNAME, CAR.CARNUMBER, CAR.CAROPTION, CAR.CARSPOT, CAR.HOURPRICE, CAR.OIL, "
				+ " TO_CHAR(STARTDATE, 'YYYY-MM-DD HH24:MI') AS STARTDATE, "
				+ " TO_CHAR(ENDDATE, 'YYYY-MM-DD HH24:MI') AS ENDDATE, TO_CHAR(REGDATE, 'YYYY-MM-DD HH24:MI') AS REGDATE, "
				+ " INSURANCE, RENTSPOT, RETURNSPOT, RENTSTATE "
				+ " FROM RENT, MEMBER, CAR "
				+ " WHERE RENT.ID = MEMBER.ID AND RENT.CARNUMBER = CAR.CARNUMBER AND RENTNUMBER=?"
				+ " ORDER BY REGDATE ASC ";
		
		Rent rent = null;
		
		try {
		    conn = DriverManager.getConnection(URL, USER, PASSWORD);
		    pstmt = conn.prepareStatement(query);	
		    
		    pstmt.setInt(1, rentNumber);
		    
		    rs = pstmt.executeQuery();
		    
		    if(rs.next()){
		    	rent = new Rent(
		    			rs.getInt("rentNumber"), 
		    			rs.getString("startDate"),
		    			rs.getString("endDate"), 
		    			rs.getString("regDate"),
		    			rs.getInt("insurance"), 
		    			rs.getString("rentSpot"), 
		    			rs.getString("returnSpot"),
		    			new Member(rs.getString("id"), null, rs.getString("name"), rs.getString("phoneNumber"), rs.getString("birth")),
		    			new Car(rs.getString("carNumber"), rs.getString("brand"), rs.getString("oil"), rs.getInt("hourPrice"), rs.getString("carName"), rs.getString("carOption"),
		    					rs.getString("carCategory"), rs.getString("carSpot"))
		    		);
		    }
		    
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
		    if (pstmt != null) try {pstmt.close();} catch(SQLException ex) {}	
		    if (conn != null) try {conn.close();} catch(SQLException ex) {}
		    if (rs != null) try {rs.close();} catch(SQLException ex) {}
		}
		
		return rent;
	}

	@Override
	public List<Rent> getAll() {
		getClassLoad();
		
		Connection conn = null;						
		PreparedStatement pstmt= null;											
		ResultSet rs = null;
		
		String query = " SELECT RENTNUMBER, RENT.ID, RENT.CARNUMBER,"
				+ " MEMBER.NAME, MEMBER.PHONENUMBER, MEMBER.BIRTH,"
				+ " CAR.BRAND, CAR.CARCATEGORY, CAR.CARNAME, CAR.CARNUMBER, CAR.CAROPTION, CAR.CARSPOT, CAR.HOURPRICE, CAR.OIL,"
				+ " TO_CHAR(STARTDATE, 'YYYY-MM-DD HH24:MI')AS STARTDATE, TO_CHAR(ENDDATE, 'YYYY-MM-DD HH24:MI')AS ENDDATE, "
				+ "TO_CHAR(REGDATE, 'YYYY-MM-DD')AS REGDATE," 
				+ " INSURANCE, RENTSPOT, RETURNSPOT, RENTSTATE"
				+ " FROM RENT, MEMBER, CAR"
				+ " WHERE TO_DATE(RENT.REGDATE, 'YYYY-MM-DD') = TO_DATE(SYSDATE, 'YYYY-MM-DD') "
				+ "AND MEMBER.ID = RENT.ID AND CAR.CARNUMBER = RENT.CARNUMBER"
				+ " ORDER BY RENTNUMBER ASC";
		
		List<Rent> list = new ArrayList<Rent>();
		
		try {
		    conn = DriverManager.getConnection(URL, USER, PASSWORD);
		    pstmt = conn.prepareStatement(query);	
		    
		    rs = pstmt.executeQuery();
		    
		    while(rs.next()){
		    	list.add(new Rent(
				    			rs.getInt("rentNumber"), 
				    			rs.getString("startDate"),
				    			rs.getString("endDate"), 
				    			rs.getString("regDate"),
				    			rs.getInt("insurance"), 
				    			rs.getString("rentSpot"), 
				    			rs.getString("returnSpot"),
				    			new Member(rs.getString("id"), null, rs.getString("name"), rs.getString("phoneNumber"), rs.getString("birth")),
				    			new Car(rs.getString("carNumber"), rs.getString("brand"), rs.getString("oil"), rs.getInt("hourPrice"), rs.getString("carName"), rs.getString("carOption"),
				    					rs.getString("carCategory"), rs.getString("carSpot"))
		    			));
		    }
		    
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
		    if (pstmt != null) try {pstmt.close();} catch(SQLException ex) {}	
		    if (conn != null) try {conn.close();} catch(SQLException ex) {}
		    if (rs != null) try {rs.close();} catch(SQLException ex) {}
		}
		
		return list;
	}

	@Override
	public List<Rent> getToday() {
		getClassLoad();
		
		Connection conn = null;
		PreparedStatement pstmt= null;											
		ResultSet rs = null;
		
		String query = " SELECT RENTNUMBER, RENT.ID, RENT.CARNUMBER, "
				+ " MEMBER.NAME, MEMBER.PHONENUMBER, MEMBER.BIRTH,"
				+ " CAR.BRAND, CAR.CARCATEGORY, CAR.CARNAME, CAR.CARNUMBER, CAR.CAROPTION, CAR.CARSPOT, CAR.HOURPRICE, CAR.OIL,"
				+ " TO_CHAR(STARTDATE, 'YYYY-MM-DD HH24:MI')AS STARTDATE, TO_CHAR(ENDDATE, 'YYYY-MM-DD HH24:MI')AS ENDDATE, TO_CHAR(REGDATE, 'YYYY-MM-DD')AS REGDATE,"
				+ " INSURANCE, RENTSPOT, RETURNSPOT, RENTSTATE"
				+ " FROM RENT, MEMBER, CAR"
				+ " WHERE TO_DATE(RENT.REGDATE, 'YYYY-MM-DD HH:MI') = TO_DATE(SYSDATE, 'YYYY-MM-DD HH:MI') AND MEMBER.ID = RENT.ID AND CAR.CARNUMBER = RENT.CARNUMBER"
				+ " ORDER BY REGDATE ASC";
		
		List<Rent> list = new ArrayList<Rent>();
		
		try {
		    conn = DriverManager.getConnection(URL, USER, PASSWORD);
		    pstmt = conn.prepareStatement(query);	
		    
		    rs = pstmt.executeQuery();
		    
		    while(rs.next()){
		    	list.add(new Rent(
				    			rs.getInt("rentNumber"), 
				    			rs.getString("startDate"),
				    			rs.getString("endDate"), 
				    			rs.getString("regDate"),
				    			rs.getInt("insurance"), 
				    			rs.getString("rentSpot"), 
				    			rs.getString("returnSpot"),
				    			new Member(rs.getString("id"), null, rs.getString("name"), rs.getString("phoneNumber"), rs.getString("birth")),
				    			new Car(rs.getString("carNumber"), rs.getString("brand"), rs.getString("oil"), rs.getInt("hourPrice"), rs.getString("carName"), rs.getString("carOption"),
				    					rs.getString("carCategory"), rs.getString("carSpot"))
		    			));
		    }
		    
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
		    if (pstmt != null) try {pstmt.close();} catch(SQLException ex) {}	
		    if (conn != null) try {conn.close();} catch(SQLException ex) {}
		    if (rs != null) try {rs.close();} catch(SQLException ex) {}
		}
		
		return list;
	}
	
	@Override
	public int getTodayCount() {
		getClassLoad();
		
		Connection conn = null;						
		PreparedStatement pstmt= null;											
		ResultSet rs = null;
		
		String query = "SELECT COUNT(*) FROM RENT"
				+ " WHERE TO_DATE(RENT.REGDATE, 'YYYY-MM-DD HH:MI') = TO_DATE(SYSDATE, 'YYYY-MM-DD HH:MI') "
				+ " AND MEMBER.ID = RENT.ID AND CAR.CARNUMBER = RENT.CARNUMBER";
		
		List<Rent> list = new ArrayList<Rent>();
		
		try {
		    conn = DriverManager.getConnection(URL, USER, PASSWORD);
		    pstmt = conn.prepareStatement(query);	
		    
		    rs = pstmt.executeQuery();
		    
		    while(rs.next()){
		    	list.add(new Rent(
				    			rs.getInt("rentNumber"), 
				    			rs.getString("startDate"),
				    			rs.getString("endDate"), 
				    			rs.getString("regDate"),
				    			rs.getInt("insurance"), 
				    			rs.getString("rentSpot"), 
				    			rs.getString("returnSpot"),
				    			new Member(rs.getString("id"), null, rs.getString("name"), rs.getString("phoneNumber"), rs.getString("birth")),
				    			new Car(rs.getString("carNumber"), rs.getString("brand"), rs.getString("oil"), rs.getInt("hourPrice"), rs.getString("carName"), rs.getString("carOption"),
				    					rs.getString("carCategory"), rs.getString("carSpot"))
		    			));
		    }
		    
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
		    if (pstmt != null) try {pstmt.close();} catch(SQLException ex) {}	
		    if (conn != null) try {conn.close();} catch(SQLException ex) {}
		    if (rs != null) try {rs.close();} catch(SQLException ex) {}
		}
		
		return 0;
	}
	
	@Override
	public List<Car> checkCar(String startDate, String endDate, String rentSpot, String returnSpot) {
		
		return null;
	}

	@Override
	public int update(Rent rent, String carNumber) {
		getClassLoad();
		
		Connection conn = null;										
		PreparedStatement pstmt= null;											
		
		String query = "UPDATE RENT SET CARNUMBER=?, STARTDATE=TO_DATE(?, 'YYYY-MM-DD HH:MI'), ENDDATE=TO_DATE(?, 'YYYY-MM-DD HH:MI'), "
								+ "INSURANCE=?, RENTSPOT=?, RETURNSPOT=?, RENTSTATE=?"
								+" WHERE RENTNUMBER=?";
		
		int x = -1;
		
		try {
		    conn = DriverManager.getConnection(URL, USER, PASSWORD);
		    pstmt = conn.prepareStatement(query);
		    
		    pstmt.setString(1, carNumber);
		    pstmt.setString(2, rent.getStartDate());											
		    pstmt.setString(3, rent.getEndDate());
		    pstmt.setInt(4, rent.getInsurance());
		    pstmt.setString(5, rent.getRentSpot());
		    pstmt.setString(6, rent.getReturnSpot());
		    pstmt.setInt(7, rent.getRentState());
		    pstmt.setInt(8, rent.getRentNumber());
		    
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
