package com.persistence;

import com.domain.Comment;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class CommentRepository {
    private static CommentRepository instance;
    private static DataSource ds;

    private CommentRepository() { }

    public static CommentRepository getInstance(){
        if (instance == null) {
            try {
                Context context = new InitialContext();
                ds = (DataSource) context.lookup("java:comp/env/jdbc/mariadb");
                return instance = new CommentRepository();
            } catch (NamingException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    public ArrayList<Comment> showByPostId(int id)
    {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM COMMENT WHERE post_id = ?";
        ArrayList<Comment> comments = new ArrayList<>();
        try {
            conn = ds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try{
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()){
                int cmt_id = rs.getInt("cmt_id");
                int post_id = rs.getInt("post_id");
                String cmt_content = rs.getString("cmt_content");
                LocalDateTime cmt_date = rs.getTimestamp("cmt_date").toLocalDateTime();
                int user_id = rs.getInt("user_id");
                Comment comment = new Comment(cmt_id,post_id,cmt_content,cmt_date,user_id);
                comments.add(comment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                st.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return comments;
    }

    public void write(Comment comment)
    {
        Connection conn = null;
        PreparedStatement st = null;

        String sql = "INSERT INTO COMMENT(post_id, cmt_content, cmt_date, user_uid) values(?,?,now(),?)";
        try {
            conn = ds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            st = conn.prepareStatement(sql);
            st.setInt(1, comment.getPost_id());
            st.setString(2, comment.getCmt_content());
            st.setInt(3, comment.getUser_uid());

            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                st.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void update(Comment comment)
    {
        Connection conn = null;
        PreparedStatement st = null;

        String sql = "UPDATE COMMENT SET cmt_content = ?, cmt_date = now() WHERE cmt_id = ?";
        try {
            conn = ds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            st = conn.prepareStatement(sql);
            st.setString(1, comment.getCmt_content());
            st.setInt(2, comment.getCmt_id());

            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                st.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void delete(int id)
    {
        Connection conn = null;
        PreparedStatement st = null;
        String sql = "DELETE FROM COMMENT WHERE cmt_id = ?";
        try {
            conn = ds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            st = conn.prepareStatement(sql);
            st.setInt(1, id);

            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                st.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
