package com.njit.card.entity;

import java.util.Date;

public class CostRecord {
private long     foodid;
private long     cardid;
private Date     costtime;
private double   costleft;

public CostRecord() {
	super();
}
public CostRecord(long foodid, long cardid, Date costtime, double costleft) {
	super();
	this.foodid = foodid;
	this.cardid = cardid;
	this.costtime = costtime;
	this.costleft = costleft;
}
public long getFoodid() {
	return foodid;
}
public void setFoodid(long foodid) {
	this.foodid = foodid;
}
public long getCardid() {
	return cardid;
}
public void setCardid(long cardid) {
	this.cardid = cardid;
}
public Date getCosttime() {
	return costtime;
}
public void setCosttime(Date costtime) {
	this.costtime = costtime;
}
public double getCostleft() {
	return costleft;
}
public void setCostleft(double costleft) {
	this.costleft = costleft;
}

 
 
}
