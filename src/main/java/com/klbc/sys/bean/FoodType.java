package com.klbc.sys.bean;

import java.util.Date;

public class FoodType {
	private int id;
	private String typeName;
	private int createdBy;
	private Date creationDate;
	private int modifyBy;
	private Date modifyDate;
	private Integer disabled;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public int getModifyBy() {
		return modifyBy;
	}

	public void setModifyBy(int modifyBy) {
		this.modifyBy = modifyBy;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public Integer getDisabled() {
		return disabled;
	}

	public void setDisabled(Integer disabled) {
		this.disabled = disabled;
	}

	@Override
	public String toString() {
		return "FoodType{" +
				"id=" + id +
				", typeName='" + typeName + '\'' +
				", createdBy=" + createdBy +
				", creationDate=" + creationDate +
				", modifyBy=" + modifyBy +
				", modifyDate=" + modifyDate +
				", disabled=" + disabled +
				'}';
	}
}
