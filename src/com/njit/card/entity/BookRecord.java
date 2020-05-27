package com.njit.card.entity;
import java.util.Date;

public class BookRecord {
	private long cardid;
	private long bookid;
	private Date jieshushijian;
	private Date huanshushijian;
	private boolean outtime;	
	public BookRecord() {
		super();
	}
	public BookRecord(long cardid, long bookid, Date jieshushijian,
			Date huanshushijian, boolean outtime) {
		    super();
		this.cardid = cardid;
		this.bookid = bookid;
		this.jieshushijian = jieshushijian;
		this.huanshushijian = huanshushijian;
		this.outtime = outtime;
	}
	public long getCardid() {
		return cardid;
	}
	public void setCardid(long cardid) {
		this.cardid = cardid;
	}
	public long getBookid() {
		return bookid;
	}
	public void setBookid(long bookid) {
		this.bookid = bookid;
	}
	public Date getJieshushijian() {
		return jieshushijian;
	}
	public void setJieshushijian(Date jieshushijian) {
		this.jieshushijian = jieshushijian;
	}
	public Date getHuanshushijian() {
		return huanshushijian;
	}
	public void setHuanshushijian(Date huanshushijian) {
		this.huanshushijian = huanshushijian;
	}
	public boolean isOuttime() {
		return outtime;
	}
	public void setOuttime(boolean outtime) {
		this.outtime = outtime;
	}
	 

}
