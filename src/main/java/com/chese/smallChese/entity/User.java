package com.chese.smallChese.entity;

public class User {
	
	int id;
	String nickName;
	int sex;
	int age;
	int shareCount;
	String openid;
	String sessionKey;
	String md5SessionKey;
	int playCount;
	int mostPlayCount;
	int freeCoin;
	int questionCoin;
	String profession;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getShareCount() {
		return shareCount;
	}
	public void setShareCount(int shareCount) {
		this.shareCount = shareCount;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getSessionKey() {
		return sessionKey;
	}
	public void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
	}
	public String getMd5SessionKey() {
		return md5SessionKey;
	}
	public void setMd5SessionKey(String md5SessionKey) {
		this.md5SessionKey = md5SessionKey;
	}
	public int getPlayCount() {
		return playCount;
	}
	public void setPlayCount(int playCount) {
		this.playCount = playCount;
	}
	public int getMostPlayCount() {
		return mostPlayCount;
	}
	public void setMostPlayCount(int mostPlayCount) {
		this.mostPlayCount = mostPlayCount;
	}
	public int getFreeCoin() {
		return freeCoin;
	}
	public void setFreeCoin(int freeCoin) {
		this.freeCoin = freeCoin;
	}
	public int getQuestionCoin() {
		return questionCoin;
	}
	public void setQuestionCoin(int questionCoin) {
		this.questionCoin = questionCoin;
	}
	public String getProfession() {
		return profession;
	}
	public void setProfession(String profession) {
		this.profession = profession;
	}

	
}
