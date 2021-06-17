package com.persistence;

import com.domain.Agree;
import com.domain.Board;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AgreeRepository {
    private static AgreeRepository instance;
    private static DataSource ds;
    private AgreeRepository(){}

    public static AgreeRepository getInstance() {
        if (instance == null) {
            try {
                Context context = new InitialContext();
                ds = (DataSource) context.lookup("java:comp/env/jdbc/mariadb");
                return instance = new AgreeRepository();
            } catch (NamingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return instance;
    }
    public ArrayList<Agree> findAll(){
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM AGREE";
        Agree sqlResult = null;
        ArrayList<Agree> result = new ArrayList<>();
        try {
            conn = ds.getConnection();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            st = conn.prepareStatement(sql);
            System.out.println(st);
            rs = st.executeQuery();
            while (rs.next()) {
                int agreeId = rs.getInt("agree_uid");
                int userId = rs.getInt("user_uid");
                int postId = rs.getInt("post_id");
                sqlResult = new Agree(agreeId,userId,postId);
                result.add(sqlResult);
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
        return result;
    }
    public Agree findById(int userid, int postid){
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM AGREE WHERE user_uid = ? AND post_id = ?";
        Agree result = null;
        try {
            conn = ds.getConnection();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            st = conn.prepareStatement(sql);
            st.setInt(1, userid);
            st.setInt(2, postid);
            System.out.println(st);
            rs = st.executeQuery();
            while (rs.next()) {
                int agreeId = rs.getInt("agree_uid");
                int userId = rs.getInt("user_uid");
                int postId = rs.getInt("post_id");
                result = new Agree(agreeId,userId,postId);
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
        return result;
    }
    public ArrayList<Agree> findByUser(int id){
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM AGREE WHERE user_uid = ?";
        Agree sqlResult = null;
        ArrayList<Agree> result = new ArrayList<>();
        try {
            conn = ds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            st = conn.prepareStatement(sql);
            st.setInt(1, id);
            System.out.println(st);
            rs = st.executeQuery();
            while (rs.next()) {
                int agreeId = rs.getInt("agree_uid");
                int userId = rs.getInt("user_uid");
                int postId = rs.getInt("post_id");
                sqlResult = new Agree(agreeId,userId,postId);
                result.add(sqlResult);
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
        return result;
    }
    public ArrayList<Agree> findByPostId(int id){
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM AGREE WHERE post_id = ?";
        Agree sqlResult = null;
        ArrayList<Agree> result = new ArrayList<>();
        try {
            conn = ds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            st = conn.prepareStatement(sql);
            st.setInt(1, id);
            System.out.println(st);
            rs = st.executeQuery();
            while (rs.next()) {
                int agreeId = rs.getInt("agree_uid");
                int userId = rs.getInt("user_uid");
                int postId = rs.getInt("post_id");
                sqlResult = new Agree(agreeId,userId,postId);
                result.add(sqlResult);
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
        return result;
    }
    public void save(Agree agree){
        Connection conn = null;
        PreparedStatement st = null;
        String sql = "INSERT INTO AGREE(user_uid, post_id) VALUES(?,?)";
        try {
            conn = ds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            st = conn.prepareStatement(sql);
            st.setInt(1, agree.getUserId());
            st.setInt(2, agree.getPostId());

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
    public void delete(Agree agree){
        Connection conn = null;
        PreparedStatement st = null;
        String sql = "DELETE FROM AGREE WHERE user_uid = ? AND post_id = ?";
        try {
            conn = ds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            st = conn.prepareStatement(sql);
            st.setInt(1, agree.getUserId());
            st.setInt(2, agree.getPostId());

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
