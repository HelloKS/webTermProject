package com.persistence;

import com.domain.Board;
import com.domain.Post;

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

public class PostRepository {
    private static PostRepository instance;
    private static DataSource ds;

    private PostRepository() { }

    public static PostRepository getInstance() {
        if (instance == null) {
            try {
                Context context = new InitialContext();
                ds = (DataSource) context.lookup("java:comp/env/jdbc/mariadb");
                return instance = new PostRepository();
            } catch (NamingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return instance;
    }

    public void delete(int id) {
        Connection conn = null;
        PreparedStatement st = null;
        String sql = "DELETE FROM POST WHERE post_id = ?";
        try {
            conn = ds.getConnection();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            st = conn.prepareStatement(sql);
            st.setInt(1, id);

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

    public void save(Post post) {
        Connection conn = null;
        PreparedStatement st = null;
        String sql = "INSERT INTO POST (board_id, post_subject, post_content, post_written, post_hit, user_uid) VALUES (?, ?, ?, now(), 0, ?)";
        try {
            conn = ds.getConnection();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            st = conn.prepareStatement(sql);
            st.setInt(1, post.getBoardId());
            st.setString(2, post.getTitle());
            st.setString(3, post.getContents());
            st.setInt(4, post.getWriterId());

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

    public void update(Post post) {
        Connection conn = null;
        PreparedStatement st = null;
        String sql = "UPDATE POST SET board_id = ?, post_subject = ?, post_content = ? WHERE post_id = ?";
        try {
            conn = ds.getConnection();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            st = conn.prepareStatement(sql);
            st.setInt(1, post.getBoardId());
            st.setString(2, post.getTitle());
            st.setString(3, post.getContents());
            st.setInt(4, post.getId());

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

    public void updateHit(int id) {
        Connection conn = null;
        PreparedStatement st = null;
        String sql = "UPDATE POST SET post_hit = post_hit + 1 WHERE post_id = ?";
        try {
            conn = ds.getConnection();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            st = conn.prepareStatement(sql);
            st.setInt(1, id);
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

    public Post findById(int id) {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM POST P LEFT JOIN USER U ON P.user_uid = U.user_uid WHERE post_id = ?";
        Post article = null;
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
                int aid = rs.getInt("post_id");
                int bid = rs.getInt("board_id");
                String title = rs.getString("post_subject");
                String content = rs.getString("post_content");
                LocalDateTime regdate = rs.getTimestamp("post_written").toLocalDateTime();
                int hit = rs.getInt("post_hit");
                int user = rs.getInt("user_uid");
                String writername = rs.getString("user_name");
                article = new Post(aid, bid, title, content, regdate, hit, user);
                article.setWriterName(writername);
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
        return article;
    }

    public ArrayList<Post> findTenFromOffset(int offset) {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM POST P LEFT JOIN USER U ON P.user_uid = U.user_uid LIMIT ?, 10";
        ArrayList<Post> posts = new ArrayList<>();
        try {
            conn = ds.getConnection();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            st = conn.prepareStatement(sql);
            st.setInt(1, offset);
            rs = st.executeQuery();
            while (rs.next()) {
                int aid = rs.getInt("post_id");
                int bid = rs.getInt("board_id");
                String title = rs.getString("post_subject");
                String content = rs.getString("post_content");
                LocalDateTime regdate = rs.getTimestamp("post_written").toLocalDateTime();
                int hit = rs.getInt("post_hit");
                int user = rs.getInt("user_uid");
                String writername = rs.getString("user_name");
                Post article = new Post(aid, bid, title, content, regdate, hit, user);
                article.setWriterName(writername);
                posts.add(article);
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
        return posts;
    }

    public ArrayList<Post> findTenFromOffsetByBoardId(int offset, int boardId) {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM POST P LEFT JOIN USER U ON P.user_uid = U.user_uid WHERE board_id = ? LIMIT ?, 10";
        ArrayList<Post> posts = new ArrayList<>();
        try {
            conn = ds.getConnection();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            st = conn.prepareStatement(sql);
            st.setInt(1, boardId);
            st.setInt(2, offset);

            rs = st.executeQuery();
            while (rs.next()) {
                int aid = rs.getInt("post_id");
                int bid = rs.getInt("board_id");
                String title = rs.getString("post_subject");
                String content = rs.getString("post_content");
                LocalDateTime regdate = rs.getTimestamp("post_written").toLocalDateTime();
                int hit = rs.getInt("post_hit");
                int user = rs.getInt("user_uid");
                Post article = new Post(aid, bid, title, content, regdate, hit, user);
                article.setWriterName(rs.getString("user_name"));
                posts.add(article);
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
        return posts;
    }
}
