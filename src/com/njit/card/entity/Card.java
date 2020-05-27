package com.njit.card.entity;

public class Card {
 private  long cardid;  
 private  String mm;
 private  String username;
 private  double balance;
 private  boolean cardstate; 
 private  long studentid;
 
public Card() {
	super();
}
public Card(long cardid, String mm, String username, double balance,
		boolean cardstate, long studentid) {
	super();
	this.cardid = cardid;
	this.mm = mm;
	this.username = username;
	this.balance = balance;
	this.cardstate = cardstate;
	this.studentid = studentid;
}
public long getCardid() {
	return cardid;
}
public void setCardid(long cardid) {
	this.cardid = cardid;
}
public String getMm() {
	return mm;
}
public void setMm(String mm) {
	this.mm = mm;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public double getBalance() {
	return balance;
}
public void setBalance(double balance) {
	this.balance = balance;
}
public boolean getCardstate() {
	return cardstate;
}
public void setCardstate(boolean cardstate) {
	this.cardstate = cardstate;
}
public long getStudentid() {
	return studentid;
}
public void setStudentid(long studentid) {
	this.studentid = studentid;
}
 
 
 
}
