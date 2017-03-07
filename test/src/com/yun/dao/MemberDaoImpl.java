package com.yun.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yun.dto.Member;

public class MemberDaoImpl implements MemberDao{
	final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
	final String USER = "yun";
	final String PASSWORD = "1111";
	
	public MemberDaoImpl() {}
	
	@Override
	public int add(Member list){																						// ȸ������
	
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");	// jdbc ����Ŭ ����̹��� ��´�.
		} catch (ClassNotFoundException e) {					// ����̹��� ���� ���� ��� classNotFoundException�� �߻��Ѵ�. (ojdbc6.jar �� ���ų� buildPath���� ������ classNotFoundException�� �߻�..)
			throw new RuntimeException();						// ���⿡ ���� ������ �� ��� ����.. 
		}
		
		Connection conn = null;										// ������ ���̽��� �����ϴ� Ŭ�����̴�.(���� ����ִ�.)
		PreparedStatement pstmt= null;							// ������ ���̽����� ���� ���ǹ� �� ?, ?, ? ������ ���� �����ϱ� ���� Ŭ�����̴�.							
		
		String query = "INSERT INTO MEMBER VALUES(?, ?, ?, ?, ?)";		// ������ ���̽����� ���� ���ǹ��̴�.
		
		int x = -1;																				// ������ ���̽����� ���ǹ��� ���� �� 1�Ǵ� -1�� ���� ���� �����̴�.
		
		try {
		    conn = DriverManager.getConnection(URL, USER, PASSWORD);	// 27��° �� -> ����ִ� connection�� ��ü�� �����Ѵ�. (DriverManagerŬ������ getConnection�޼ҵ带 ȣ���ϸ� Connection�� �ν��Ͻ�(�ּҰ�...(������ ���ϸ� �ּ� ������... (�� ������ ���ϸ� �𸣰ھ��..)))�� ������ ���̴�.)
		    pstmt = conn.prepareStatement(query);									// connection��ü�� ���ǹ��� ���� �ְ� PreparedStatement�� �־�д�.
		    
		    pstmt.setString(1, list.getId());												// ? �� ���� (list �� add�޼ҵ��� ���� ���̴�.(�Ķ����))
		    pstmt.setString(2, list.getPassword());
		    pstmt.setString(3, list.getName());
		    pstmt.setString(4, list.getPhoneNumber());
		    pstmt.setString(5, list.getBirth());
		    
		    x = pstmt.executeUpdate();													// ������ ���̽��� ���ǹ��� ������.
		    
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
		    if (pstmt != null) try {pstmt.close(); } catch(SQLException ex) {}	// �ݵ�� PreparedStatement�� Connection �׸��� ResultSet�� close() ���ش�.(ResultSet�� get �Ǵ� getAll �޼ҵ忡 �ִ�. �̳��� select�� ���� ���ڵ���� �޴� ���̴�.)
		    if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		
		return x;
	}
	
	@Override
	public int delete(String element){																				// ȸ��Ż��
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException();
		}
		
		Connection conn = null;
		PreparedStatement pstmt= null;
		String query = "DELETE FROM MEMBER WHERE ID=?";
		
		int x = -1;
		
		try {
		    conn = DriverManager.getConnection(URL, USER, PASSWORD);
		    pstmt = conn.prepareStatement(query);
		    
		    pstmt.setString(1, element);
		    
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
	public int update(Member element){																			// ȸ�� ���� ����
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException();
		}
		
		Connection conn = null;
		PreparedStatement pstmt= null;
		
		String query = "UPDATE MEMBER SET PASSWORD=?, NAME=?, "
				+ "PHONENUMBER=?, BIRTH=? WHERE ID=?";
		
		int x = -1;
		
		try {
		    conn = DriverManager.getConnection(URL, USER, PASSWORD);
		    pstmt = conn.prepareStatement(query);
		    
		    pstmt.setString(1, element.getPassword());
		    pstmt.setString(2, element.getName());
		    pstmt.setString(3, element.getPhoneNumber());
		    pstmt.setString(4, element.getBirth());
		    pstmt.setString(5, element.getId());
		    
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
	public Member get(String element){																			// Ư�� ȸ�� ��ȸ
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException();
		}
		
		Connection conn = null;
		PreparedStatement pstmt= null;
		ResultSet rs = null;
		
		String query = "SELECT * FROM MEMBER WHERE ID=?";
		
		Member member = null;
		
		try {
		    conn = DriverManager.getConnection(URL, USER, PASSWORD);
		    pstmt = conn.prepareStatement(query);
		    
		    pstmt.setString(1, element);
		    
		    rs = pstmt.executeQuery();
		    
		    if(rs.next()){
			    member = new Member(
			    		rs.getString("id"),
			    		rs.getString("password"), 
			    		rs.getString("name"), 
			    		rs.getString("phoneNumber"), 
			    		rs.getString("birth")
			    );
		    }
		    
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
		    if (pstmt != null) try {pstmt.close(); } catch(SQLException ex) {}
		    if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		    if (rs != null) try { conn.close(); } catch(SQLException ex) {}		
		}
		
		return member;
	}

	@Override
	public List<Member> getAll(){																				// ��� ȸ�� ��ȸ
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException();
		}
		
		Connection conn = null;
		PreparedStatement pstmt= null;
		ResultSet rs = null;
		
		String query = "SELECT * FROM MEMBER ORDER BY ID ASC";
		
		List<Member> members = new ArrayList<Member>();
		
		try {
		    conn = DriverManager.getConnection(URL, USER, PASSWORD);
		    pstmt = conn.prepareStatement(query);
		    
		    rs = pstmt.executeQuery();
		    
		    while(rs.next()){
		    	 	Member member = new Member(	
		    			rs.getString("id"),
						rs.getString("password"),
						rs.getString("name"),
						rs.getString("phoneNumber"), 
						rs.getString("birth")
		    	);
		    	members.add(member);
		    }
		    
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
		    if (pstmt != null) try {pstmt.close(); } catch(SQLException ex) {}
		    if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		    if (rs != null) try { conn.close(); } catch(SQLException ex) {}		
		}
		
		return members;
	}

	@Override
	public int getCount(){																							// ȸ�� �� ��ȸ
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException();
		}
		
		Connection conn = null;
		PreparedStatement pstmt= null;
		ResultSet rs = null;
		
		String query = "SELECT COUNT(*) FROM MEMBER";
		int x = 0;

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
		    if (conn != null) try {conn.close(); } catch(SQLException ex) {}
		    if (rs != null) try {rs.close(); } catch(SQLException ex) {}		
		}
		
		return x;
	}

	@Override
	public int checkId(String id){																			// �ߺ� ���̵� üũ
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException();
		}
		
		Connection conn = null;
		PreparedStatement pstmt= null;
		ResultSet rs = null;
		
		String query = "SELECT COUNT(*) FROM MEMBER WHERE ID=?";
		
		int x = -1;
		
		try {
		    conn = DriverManager.getConnection(URL, USER, PASSWORD);
		    pstmt = conn.prepareStatement(query);
		    pstmt.setString(1, id);
		    
		    rs = pstmt.executeQuery();
		    
		    if(rs.next()){
		    	x = rs.getInt(1);
		    }
		    
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
		    if (pstmt != null) try {pstmt.close(); } catch(SQLException ex) {}
		    if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		
		return x;
	}

	@Override
	public int deleteAll() {																							// ��� ȸ�� ����(**�����ؼ� ���..)
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException();
		}
		
		Connection conn = null;
		PreparedStatement pstmt= null;
		
		String query = "DELETE FROM MEMBER";
		int x = 0;

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
