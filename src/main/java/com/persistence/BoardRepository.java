package com.persistence;

import com.domain.Board;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.sql.Connection;

public class BoardRepository {
	private static BoardRepository instance;
	private static DataSource ds;
	private BoardRepository() {
		
	}
	public static BoardRepository getInstance() {
		if(instance==null) {
			try {
				Context context = new InitialContext();
				ds = (DataSource) context.lookup("java:comp/env/jdbc/mariadb");
				return instance = new BoardRepository();
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return instance;		
	}

	public void delete(long id) {
		Connection conn = null;
		PreparedStatement st = null;
		String sql = "DELETE FROM BOARD WHERE id = ?";
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			st = conn.prepareStatement(sql);
			st.setLong(1, id);

			st.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				st.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void save(Board board){
		Connection conn = null;
		PreparedStatement st = null;
		String sql = "INSERT INTO BOARD (title, writer, contents, regdate, hit) VALUES (?,?,?,now(),?)";
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			st = conn.prepareStatement(sql);
			st.setString(1, board.getTitle());
			st.setString(2, board.getWriter());
			st.setString(3, board.getContents());
			st.setString(4, "0");

			st.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				st.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void update(Board board){
		Connection conn = null;
		PreparedStatement st = null;
		String sql = "UPDATE BOARD SET title = ?, writer = ?, contents = ? WHERE id = ?";
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			st = conn.prepareStatement(sql);
			st.setString(1, board.getTitle());
			st.setString(2, board.getWriter());
			st.setString(3, board.getContents());
			st.setLong(4, board.getId());

			st.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				st.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public Board findById(Long id){
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM BOARD WHERE id = ?";
		Board article = null;
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			st = conn.prepareStatement(sql);
			st.setLong(1, id);
			System.out.println(st);
			rs = st.executeQuery();
			while (rs.next()) {
				Long aid = rs.getLong("id");
				String title = rs.getString("title");
				String writer = rs.getString("writer");
				String contents = rs.getString("contents");
				LocalDateTime regdate = rs.getTimestamp("regdate").toLocalDateTime();
				String hit = rs.getString("hit");
				article = new Board(aid,title,writer,contents,regdate,hit);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				st.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return article;
	}
	
	public ArrayList<Board> findAll() {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM BOARD";
		ArrayList<Board> boards = new ArrayList<Board>();
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Long id = rs.getLong("id");
				String title = rs.getString("title");
				String writer = rs.getString("writer");
				String contents = rs.getString("contents");
				LocalDateTime regdate = rs.getTimestamp("regdate").toLocalDateTime();
				String hit = rs.getString("hit");
				Board posts = new Board(id,title,writer,contents,regdate,hit);
				boards.add(posts);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				st.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}		
		return boards;
	}
}
