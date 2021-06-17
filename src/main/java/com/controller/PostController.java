package com.controller;

import com.domain.*;
import com.service.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PostController implements Controller {
    private final PostService postService = new PostService();
    private final BoardService boardService = new BoardService();
    private final MemberService memberService = new MemberService();
    private final CommentService commentService = new CommentService();
    private final AgreeService agreeService = new AgreeService();
    private LocalDateTime current = null;

    @Override
    public ModelAndView process(HttpServletRequest request, HttpServletResponse response, String url)
            throws ServletException, IOException {
        ModelAndView modelAndView = new ModelAndView();
        //GET = 글쓰기창, POST = 글쓰기 요청 req
        if (url.equals("/post/write")) {
            if (request.getMethod().equals("GET")) {
                // 글쓰기 기능 최초 요청 시, 글쓰기 화면 요청
                List<Board> boards = boardService.findBoards();
                modelAndView.setViewName("post/post-write");
                modelAndView.getModel().put("boards", boards);
            } else if (request.getMethod().equals("POST")) {
                // 글쓰기 화면에서 글쓰기 버튼을 클릭하여 글쓰기 기능을 요청한 경우
                Member user = (Member) request.getSession().getAttribute("LOGIN");
                Post post = new Post();
                post.setBoardId(Integer.parseInt(request.getParameter("board")));
                post.setContents(request.getParameter("content"));
                post.setTitle(request.getParameter("title"));
                post.setWriterId(user.getUid());
                postService.write(post);
                modelAndView.setViewName("post/post-writeok");
            }
        } else if (url.equals("/post/list")) {
            // GET = 해당 게시판의 모든 게시글을 조회
            String bdid = request.getParameter("bdid");
            String pageParam = request.getParameter("page");
            int page = 1;
            if (pageParam != null && !pageParam.isEmpty()) {
                page = Integer.parseInt(pageParam);
            }
            ArrayList<Post> posts = new ArrayList<>();
            Board board = null;
            if (bdid != null && !bdid.isEmpty()) {
                posts = postService.findBoardPosts((page - 1) * 10, Integer.parseInt(bdid));
                board = boardService.findBoardById(Integer.parseInt(bdid));
            } else {
                posts = postService.findTenPostsFrom((page - 1) * 10);
            }
            modelAndView.setViewName("post/post-list");
            modelAndView.getModel().put("posts", posts);
            modelAndView.getModel().put("allboard", boardService.findBoards());
            modelAndView.getModel().put("curpage", page);
            if (board != null) {
                modelAndView.getModel().put("board", board);
            }
        } else if (url.equals("/post/detail")) {
            int postid = Integer.parseInt(request.getParameter("id"));
            Member user = (Member) request.getSession().getAttribute("LOGIN");

            int agreeCount = agreeService.countAgreeByPostId(postid);
            if(user != null) {
                int userid = user.getUid();
                Agree agree = new Agree(userid, postid);
                if (agreeService.findAgree(userid, postid) == null) {
                    agreeService.save(agree);
                } else {
                    agreeService.delete(agree);
                }
            }

            //댓글 작성
            if(request.getMethod().equals("POST")) {
                int user_uid = user.getUid();
                String content = request.getParameter("comment_content");
                Comment comment = new Comment();
                comment.setCmt_content(content);
                comment.setPost_id(postid);
                comment.setUser_uid(user_uid);
                commentService.write(comment);
            }
            // 해당 게시글의 상세 내용 조회
            postService.addHit(postid);
            ArrayList<Comment> comments = commentService.showComment(postid);
            Post article = postService.findArticleById(postid);
            Board board = boardService.findBoardById(article.getBoardId());
            modelAndView.setViewName("post/post-detail");
            modelAndView.getModel().put("agreeCount", agreeCount);
            modelAndView.getModel().put("post", article);
            modelAndView.getModel().put("board", board);
            modelAndView.getModel().put("comments", comments);


        } else if (url.equals("/post/modify")) {
            if (request.getMethod().equals("GET")) {
                // GET = 해당 게시글의 수정화면 출력
                Post article = postService.findArticleById(Integer.parseInt(request.getParameter("id")));
                List<Board> boards = boardService.findBoards();
                modelAndView.setViewName("post/post-modify");
                modelAndView.getModel().put("boards", boards);
                modelAndView.getModel().put("post", article);
            } else if (request.getMethod().equals("POST")) {
                // POST = 해당 게시글을 새로운 내용으로 수정
                request.setCharacterEncoding("UTF-8");
                Post post = new Post();
                post.setId(Integer.parseInt(request.getParameter("id")));
                post.setTitle(request.getParameter("title"));
                post.setWriterId(Integer.parseInt(request.getParameter("writer")));
                post.setBoardId(Integer.parseInt(request.getParameter("board")));
                post.setContents(request.getParameter("content"));

                postService.update(post);
                modelAndView.setViewName("post/post-writeok");
            }
        } else if (url.equals("/post/delete")) {
            // 해당 게시글 삭제
            int postId = Integer.parseInt(request.getParameter("id"));
            postService.delete(postId);
            modelAndView.setViewName("post/post-writeok");
        } else if (url.equals("/post/commentDelete")){
            //댓글 삭제
            int postid = Integer.parseInt(request.getParameter("id"));
            int cmtId = Integer.parseInt(request.getParameter("cmt_id"));
            commentService.delete(cmtId);
            //페이지 다시불러오기
            postService.addHit(postid);
            ArrayList<Comment> comments = commentService.showComment(postid);
            Post article = postService.findArticleById(postid);
            Board board = boardService.findBoardById(article.getBoardId());
            modelAndView.setViewName("post/post-detail");
            modelAndView.getModel().put("post", article);
            modelAndView.getModel().put("board", board);
            modelAndView.getModel().put("comments", comments);
        } else {
            // 이외의 요청은 404 NOT FOUND
            modelAndView.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
        return modelAndView;
    }
}
