package com.domain;

public class Member {
	private int uid;
	private String email;
	private String password;
	private String nickname;
	public Member() {}

	public Member(int uid, String email, String password, String nickname) {
		this.uid = uid;
		this.email = email;
		this.password = password;
		this.nickname = nickname;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
}
