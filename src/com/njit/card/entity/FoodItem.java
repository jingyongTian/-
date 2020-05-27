package com.njit.card.entity;


public class FoodItem {
 private long foodid;
 private String foodname;
 private  double danjia;
 private  int  chuangkou; 

 public FoodItem() {
	super();
}
public FoodItem(long foodid, String foodname, double danjia, int chuangkou) {
	super();
	this.foodid = foodid;
	this.foodname = foodname;
	this.danjia = danjia;
	this.chuangkou = chuangkou;
}
public long getFoodid() {
	return foodid;
}
public void setFoodid(long foodid) {
	this.foodid = foodid;
}
public String getFoodname() {
	return foodname;
}
public void setFoodname(String foodname) {
	this.foodname = foodname;
}
public double getDanjia() {
	return danjia;
}
public void setDanjia(double danjia) {
	this.danjia = danjia;
}
public int getChuangkou() {
	return chuangkou;
}
public void setChuangkou(int chuangkou) {
	this.chuangkou = chuangkou;
} 
 
}
