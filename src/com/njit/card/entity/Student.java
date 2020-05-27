package com.njit.card.entity;

import java.util.Date;
public class Student {
  private  long     studentid;
  private  String   xingming;
  private  String   xingbie;
  private  int      nianling;
  private  String   banji;
  private  String   zhuanye;
  private  String   xuyuan;
  private  String   jiguan;
  private  String   zhuzhi;
  private  Date     ruxueshijian;
  
public Student() {
	super();
}
public Student(long studentid, String xingming, String xingbie, int nianling,
		String banji, String zhuanye, String xuyuan, String jiguan,
		String zhuzhi, Date ruxueshijian) {
	super();
	this.studentid = studentid;
	this.xingming = xingming;
	this.xingbie = xingbie;
	this.nianling = nianling;
	this.banji = banji;
	this.zhuanye = zhuanye;
	this.xuyuan = xuyuan;
	this.jiguan = jiguan;
	this.zhuzhi = zhuzhi;
	this.ruxueshijian = ruxueshijian;
}
public long getStudentid() {
	return studentid;
}
public void setStudentid(long studentid) {
	this.studentid = studentid;
}
public String getXingming() {
	return xingming;
}
public void setXingming(String xingming) {
	this.xingming = xingming;
}
public String getXingbie() {
	return xingbie;
}
public void setXingbie(String xingbie) {
	this.xingbie = xingbie;
}
public int getNianling() {
	return nianling;
}
public void setNianling(int nianling) {
	this.nianling = nianling;
}
public String getBanji() {
	return banji;
}
public void setBanji(String banji) {
	this.banji = banji;
}
public String getZhuanye() {
	return zhuanye;
}
public void setZhuanye(String zhuanye) {
	this.zhuanye = zhuanye;
}
public String getXuyuan() {
	return xuyuan;
}
public void setXuyuan(String xuyuan) {
	this.xuyuan = xuyuan;
}
public String getJiguan() {
	return jiguan;
}
public void setJiguan(String jiguan) {
	this.jiguan = jiguan;
}
public String getZhuzhi() {
	return zhuzhi;
}
public void setZhuzhi(String zhuzhi) {
	this.zhuzhi = zhuzhi;
}
public  Date getRuxueshijian() {
	return ruxueshijian;
}
public void setRuxueshijian(Date ruxueshijian) {
	this.ruxueshijian = ruxueshijian;
}
  
}
