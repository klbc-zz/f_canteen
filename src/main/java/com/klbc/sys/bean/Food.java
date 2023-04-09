package com.klbc.sys.bean;

import java.util.Date;

public class Food {
	private int id;
	private String foodName;
	private int foodTypeId;
	private double price;
	private String remark;
	private String img;
	private int createdBy;
	private Date creationDate;
	private int modifyBy;
	private Date modifyDate;
	private Integer disabled;
	
	private FoodType foodType;
	private Integer buyNum;//购买数量，只做记录数据不保存到数据库
	public Food(){}

	public Food(String foodName, int foodTypeId, double price, String remark, String img, Integer disabled) {
		super();
		this.foodName = foodName;
		this.foodTypeId = foodTypeId;
		this.price = price;
		this.remark = remark;
		this.img = img;
		this.disabled = disabled;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFoodName() {
		return foodName;
	}

	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}

	public int getFoodTypeId() {
		return foodTypeId;
	}

	public void setFoodTypeId(int foodTypeId) {
		this.foodTypeId = foodTypeId;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
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

	public FoodType getFoodType() {
		return foodType;
	}

	public void setFoodType(FoodType foodType) {
		this.foodType = foodType;
	}

	public Integer getBuyNum() {
		return buyNum;
	}

	public void setBuyNum(Integer buyNum) {
		this.buyNum = buyNum;
	}

	@Override
	public String toString() {
		return "Food{" +
				"id=" + id +
				", foodName='" + foodName + '\'' +
				", foodTypeId=" + foodTypeId +
				", price=" + price +
				", remark='" + remark + '\'' +
				", img='" + img + '\'' +
				", createdBy=" + createdBy +
				", creationDate=" + creationDate +
				", modifyBy=" + modifyBy +
				", modifyDate=" + modifyDate +
				", disabled=" + disabled +
				", foodType=" + foodType +
				", buyNum=" + buyNum +
				'}';
	}
}
