package baeshins.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class BoardDao {

	DataSource ds = null;
	Connection con = null;
	PreparedStatement ps = null;
	
	public BoardDao() {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/mysql");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private void connect() {
		try {
			con = ds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void disConnect() {
		try {
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<BoardDto> list() {
		connect();
		
		ArrayList<BoardDto> dtos = new ArrayList<BoardDto>();
		ResultSet rs = null;
		
		try {
			String query = "select * from mvc_board order by ggroup desc, step asc";
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String pw = rs.getString("pw");
				String title = rs.getString("title");
				String content = rs.getString("content");
				Timestamp date = rs.getTimestamp("date");
				int hit = rs.getInt("hit");
				int group = rs.getInt("ggroup");
				int step = rs.getInt("step");
				int indent = rs.getInt("indent");
				
				BoardDto dto = new BoardDto(id, name, pw, title, content, date, hit, group, step, indent);
				dtos.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return dtos;
	}
	
	public void write(String name, String pw, String title, String content) {
		
		connect();
		ResultSet rs=null;
		try {
			int currval=0;
			String query0 = "select ifnull(max(id),0)+1 from mvc_board";
			ps = con.prepareStatement(query0);
			rs = ps.executeQuery();
			if(rs.next()) currval = rs.getInt(1);
			System.out.println(currval);
			
			String query = "insert into mvc_board (name, pw, title, content, ggroup, step, indent) values (?, MD5(?), ?, ?, ?, 0, 0)";
			ps = con.prepareStatement(query);
			ps.setString(1, name);
			ps.setString(2, pw);
			ps.setString(3, title);
			ps.setString(4, content);
			ps.setInt(5, currval);
			int rn = ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			disConnect();
		}
		
	}
	
	public BoardDto contentView(String strID) {
		// TODO Auto-generated method stub
		
		upHit(strID);
		
		BoardDto dto = null;
		ResultSet rs = null;
		
		connect();
		try {
			String query = "select * from mvc_board where id = ?";
			ps = con.prepareStatement(query);
			ps.setInt(1, Integer.parseInt(strID));
			rs = ps.executeQuery();
			
			if(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String pw = rs.getString("pw");
				String title = rs.getString("title");
				String content = rs.getString("content");
				Timestamp date = rs.getTimestamp("date");
				int hit = rs.getInt("hit");
				int group = rs.getInt("ggroup");
				int step = rs.getInt("step");
				int indent = rs.getInt("indent");
				
				dto = new BoardDto(id, name, pw, title, content, date, hit, group, step, indent);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			disConnect();
		}
		return dto;
	}
	
	public void modify(String bId, String bName, String bTitle, String bContent) {
		connect();
		
		try {
			String query = "update mvc_board set name = ?, title = ?, content = ? where id = ?";
			ps = con.prepareStatement(query);
			ps.setString(1, bName);
			ps.setString(2, bTitle);
			ps.setString(3, bContent);
			ps.setInt(4, Integer.parseInt(bId));
			int rn = ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
	}
	
	public void delete(String bId) {
		connect();
		
		try {
			String query = "delete from mvc_board where id = ?";
			ps = con.prepareStatement(query);
			ps.setInt(1, Integer.parseInt(bId));
			int rn = ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
	}
	public BoardDto reply_view(String str) {
		BoardDto dto = null;
		ResultSet rs = null;
		
		connect();
		try {
			String query = "select * from mvc_board where id = ?";
			ps = con.prepareStatement(query);
			ps.setInt(1, Integer.parseInt(str));
			rs = ps.executeQuery();
			
			if(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String pw = rs.getString("pw");
				String title = rs.getString("title");
				String content = rs.getString("content");
				Timestamp date = rs.getTimestamp("date");
				int hit = rs.getInt("hit");
				int group = rs.getInt("ggroup");
				int step = rs.getInt("step");
				int indent = rs.getInt("indent");
				
				dto = new BoardDto(id, name, pw, title, content, date, hit, group, step, indent);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		
		return dto;
	}
	
	public void reply(String id, String name, String title, String content, String group, String step, String indent) {
		// TODO Auto-generated method stub
		
		replyShape(group, step);
		connect();
		try {
			String query = "insert into mvc_board (name, title, content, ggroup, step, indent) values ( ?, ?, ?, ?, ?, ?)";
			ps = con.prepareStatement(query);
			
			ps.setString(1, name);
			ps.setString(2, title);
			ps.setString(3, content);
			ps.setInt(4, Integer.parseInt(group));
			ps.setInt(5, Integer.parseInt(step) + 1);
			ps.setInt(6, Integer.parseInt(indent) + 1);
			
			int rn = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		
	}
	
	private void replyShape( String strGroup, String strStep) {
		
		connect();
		
		try {
			String query = "update mvc_board set step = step + 1 where ggroup = ? and step > ?";
			ps = con.prepareStatement(query);
			ps.setInt(1, Integer.parseInt(strGroup));
			ps.setInt(2, Integer.parseInt(strStep));
			
			System.out.println(query);
			int rn = ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			disConnect();
		}
	}
	private void upHit( String id) {
		connect();
		
		try {
			String query = "update mvc_board set hit = hit + 1 where id = ?";
			ps = con.prepareStatement(query);
			ps.setString(1, id);
			
			int rn = ps.executeUpdate();
					
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			disConnect();
		}
	}
	
}
