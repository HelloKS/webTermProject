package com.persistence;

import com.domain.Board;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;

public class BoardRepository {
    private static BoardRepository instance;
    private static DataSource ds;

    private BoardRepository() {

    }

    public static BoardRepository getInstance() {
        if (instance == null) {
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

    public Board findById(int id) {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM BOARD WHERE board_id = ?";
        Board bd = null;
        try {
            conn = ds.getConnection();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            st = conn.prepareStatement(sql);
            st.setInt(1, id);
            System.out.println(st);
            rs = st.executeQuery();
            while (rs.next()) {
                int uid = rs.getInt("board_id");
                String name = rs.getString("board_name");
                String desc = rs.getString("board_desc");
                bd = new Board(uid, name, desc);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                st.close();
                conn.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return bd;
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
                int id = rs.getInt("board_id");
                String name = rs.getString("board_name");
                String desc = rs.getString("board_desc");
                Board posts = new Board(id, name, desc);
                boards.add(posts);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
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
