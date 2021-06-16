package com.domain;

import java.time.LocalDateTime;

public class Post {
	private int id;
	private String title;
	private int boardId; //게시판 id
	private int writerId; //작성자 uid
	private String contents;
	private LocalDateTime regdate;
	private int hit;

	public Post() { }

	public Post(int id, int boardId, String title, String contents, LocalDateTime regdate, int hit, int writerId) {
		this.id = id;
		this.title = title;
		this.boardId = boardId;
		this.writerId = writerId;
		this.contents = contents;
		this.regdate = regdate;
		this.hit = hit;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getBoardId() {
		return boardId;
	}

	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}

	public int getWriterId() {
		return writerId;
	}

	public void setWriterId(int writerId) {
		this.writerId = writerId;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public LocalDateTime getRegdate() {
		return regdate;
	}

	public void setRegdate(LocalDateTime regdate) {
		this.regdate = regdate;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}
}
