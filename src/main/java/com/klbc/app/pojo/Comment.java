package com.klbc.app.pojo;

import java.util.Date;

public class Comment {
	private Integer id;
	private Integer userId;
	private Integer foodId;
	private String content;
	private Integer disabled;
	private Date createTime;
	
	private User user;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getNameId() {
		return userId;
	}
	public void setNameId(Integer userId) {
		this.userId = userId;
	}
	public Integer getFoodId() {
		return foodId;
	}
	public void setFoodId(Integer foodId) {
		this.foodId = foodId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getDisabled() {
		return disabled;
	}
	public void setDisabled(Integer disabled) {
		this.disabled = disabled;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Override
	public String toString() {
		return "Comment [id=" + id + ", userId=" + userId + ", foodId=" + foodId + ", content=" + content
				+ ", disabled=" + disabled + ", createTime=" + createTime + ", user=" + user + "]";
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}
