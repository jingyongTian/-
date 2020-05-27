package com.njit.card.entity;

public class Book {
  private  long     bookid;
  private  String   bookname;
  private String    chubanshe;
  private  String   zuozhe;  
  private  String     qixian;
  private  Integer bookstate;  
 public Book() {
	super();
}

public Book(long bookid, String bookname, String chubanshe, String zuozhe,
		String qixian, Integer bookstate) {
	super();
	this.bookid = bookid;
	this.bookname = bookname;
	this.chubanshe = chubanshe;
	this.zuozhe = zuozhe;
	this.qixian = qixian;
	this.bookstate = bookstate;
}

public long getBookid() {
	return bookid;
}

public void setBookid(long bookid) {
	this.bookid = bookid;
}

public String getBookname() {
	return bookname;
}

public void setBookname(String bookname) {
	this.bookname = bookname;
}

public String getChubanshe() {
	return chubanshe;
}

public void setChubanshe(String chubanshe) {
	this.chubanshe = chubanshe;
}

public String getZuozhe() {
	return zuozhe;
}

public void setZuozhe(String zuozhe) {
	this.zuozhe = zuozhe;
}

public String getQixian() {
	return qixian;
}

public void setQixian(String qixian) {
	this.qixian = qixian;
}

public Integer getBookstate() {
	return bookstate;
}

public void setBookstate(Integer bookstate) {
	this.bookstate = bookstate;
}

@Override
public String toString() {
	return "Book [bookid=" + bookid + ", bookname=" + bookname + ", chubanshe=" + chubanshe + ", zuozhe=" + zuozhe
			+ ", qixian=" + qixian + ", bookstate=" + bookstate + "]";
}
 
}
