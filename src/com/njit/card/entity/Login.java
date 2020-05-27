package com.njit.card.entity;

public class Login {
 private  long   id;
 private  String  mm;
 private  int    type;
public long getId() {
	return id;
}
public Login(long id, String mm, int type) {
	super();
	this.id = id;
	this.mm = mm;
	this.type = type;
}
public Login() {
	super();
}
public void setId(long id) {
	this.id = id;
}
public String getMm() {
	return mm;
}
public void setMm(String mm) {
	this.mm = mm;
}
public int getType() {
	return type;
}
public void setType(int type) {
	this.type = type;
}
 
}
