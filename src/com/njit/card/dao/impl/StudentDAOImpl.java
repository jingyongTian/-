package com.njit.card.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.njit.card.dao.StudentDAO;
import com.njit.card.entity.Book;
import com.njit.card.entity.BookRecord;
import com.njit.card.entity.Card;
import com.njit.card.entity.FoodItem;
import com.njit.card.entity.CostRecord;
import com.njit.card.entity.Student;
import com.njit.card.utils.DBUtil;

public class StudentDAOImpl implements StudentDAO{	
	
	public Student findById(long id){
		Connection conn=null;
		Student s=null;
		try{
		conn=DBUtil.getConnection();
		String sql="select *  from student where studentid=?";
		PreparedStatement prep=conn.prepareStatement(sql);
		prep.setLong(1, id);
		ResultSet rst=prep.executeQuery();		
		while(rst.next()){
			s=new Student();
			s.setStudentid(id);
			s.setBanji(rst.getString("banji"));
			s.setJiguan(rst.getString("jiguan"));
			s.setNianling(rst.getInt("nianling"));
			s.setRuxueshijian(rst.getDate("ruxueshijian"));
			s.setXingbie(rst.getString("xingbie"));
			s.setXingming(rst.getString("xingming"));
			s.setXuyuan(rst.getString("xueyuan"));
			s.setZhuanye(rst.getString("zhuanye"));
			s.setZhuzhi(rst.getString("zhuzhi"));
		}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBUtil.close(conn);
		}
		return  s;
	}
	
	
	public List<FoodItem> findFoodItems() throws Exception {
	    Connection conn=null;
	    List<FoodItem>foodItems= new ArrayList<FoodItem>();
	    FoodItem f=null;
	    try{
	    conn=DBUtil.getConnection();	     
	    String sql="select *  from   fooditem";
	    PreparedStatement prep=conn.prepareStatement(sql);	     
	    ResultSet rst=prep.executeQuery();	     
	    while(rst.next()){
	      f=new FoodItem();
		  f.setFoodid(rst.getLong("foodid"));
		  f.setFoodname(rst.getString("foodname"));
		  f.setDanjia(rst.getDouble("danjia"));
		  f.setChuangkou(rst.getInt("chuangkou"));
		  foodItems.add(f);		  
		 }
	    }catch(Exception e){
	    	e.printStackTrace();
	    }finally{
	    	DBUtil.close(conn);
	    }
		return foodItems;
	}
	
	public FoodItem findByFoodItemId(long foodid) throws Exception {
		 Connection conn=null;
		 FoodItem f=null;
		 try{
		 conn=DBUtil.getConnection();		 
		 String sql="select  *   from  fooditem where foodid=?";
		 PreparedStatement prep=conn.prepareStatement(sql);
		 prep.setLong(1,foodid);
		 ResultSet rst=prep.executeQuery();		 
		 while(rst.next()){
			 f=new FoodItem();
			 f.setFoodid(rst.getLong("foodid"));
			 f.setFoodname(rst.getString("foodname"));
			 f.setDanjia(rst.getDouble("danjia"));
			 f.setChuangkou(rst.getInt("chuangkou"));
		 }
		 }catch(Exception e){
			 e.printStackTrace();
		 }finally{
			 DBUtil.close(conn);
		 }
		return f;
	}
	
	public void addCostRecord(CostRecord record) throws Exception {
		Connection conn=null;
		conn=DBUtil.getConnection();
		String sql="insert into costrecord(cardid,foodid,costtime,costleft)values(?,?,?,?)";
		PreparedStatement prep=conn.prepareStatement(sql);
		prep.setLong(1,record.getCardid());
		prep.setLong(2,record.getFoodid());
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String costtime=sdf.format(date);
		prep.setString(3,costtime);
		Card card=findByCardId(record.getCardid());	
		System.out.println();
		prep.setDouble(4, card.getBalance());
		prep.execute();		
	}
	public List<FoodItem> findFoodItemsById(long cardid) throws Exception {
		 Connection conn=null;
		 List<FoodItem> foodItems=new ArrayList<FoodItem>();
		 FoodItem item=null;		 
		 conn=DBUtil.getConnection();
		 String sql="select *  from costrecord where  cardid=?";
		 PreparedStatement prep=conn.prepareStatement(sql);
		 prep.setLong(1,cardid);		
		 ResultSet rst= prep.executeQuery();
		 while(rst.next()){				 		 
			item=findByFoodItemId(rst.getLong("foodid"));
			foodItems.add(item);
		 }
		return  foodItems;
	} 
	
	public List<CostRecord> findCostRecordsById(long cardid) throws Exception {
		 Connection conn=null;
		 List<CostRecord>records=new ArrayList<CostRecord>();
		 CostRecord record=null;
		 conn=DBUtil.getConnection();		
		 String  sql="select  *   from  costrecord where cardid=?";
		 PreparedStatement prep=conn.prepareStatement(sql);
		 prep.setLong(1,cardid);
		 ResultSet rst=prep.executeQuery();
		 while(rst.next()){
			record=new CostRecord();
			record.setCardid(cardid);
			record.setCostleft(rst.getDouble("costleft"));
			record.setCosttime(rst.getDate("costtime"));
			record.setFoodid(rst.getLong("foodid"));
			records.add(record);
		 }
		return records;
	}	
 
	
	public List<Book> findBooks() throws Exception {
		 Connection conn=null;
		 List<Book>booklist=new ArrayList<Book>();
		 Book book=null;
		 try{
		 conn=DBUtil.getConnection();
		 String sql="select  *  from  book";
		 PreparedStatement prep=conn.prepareStatement(sql);
		ResultSet rst=prep.executeQuery();
		while(rst.next()){
			book=new Book();
			book.setBookid(rst.getLong("bookid"));
			book.setBookname(rst.getString("bookname"));
			book.setBookstate(rst.getInt("bookstate"));
			book.setChubanshe(rst.getString("chubanshe"));
			book.setQixian(rst.getString("qixian"));
			book.setZuozhe(rst.getString("zuozhe"));
			booklist.add(book);
		}
		 }catch(Exception e){
			 e.printStackTrace();
		 }finally{
			 DBUtil.close(conn);
		 }
		 return booklist;
	}
     
	
	public Book findBookById(long bookid) throws Exception {
		 Connection conn=null;
		 Book book=null;
		 try{
		 conn=DBUtil.getConnection();
		 String sql="select  * from book where bookid=?";
		 PreparedStatement prep=conn.prepareStatement(sql);
		 prep.setLong(1,bookid);
		 ResultSet rst=prep.executeQuery();
		 while(rst.next()){
			book=new Book();
			book.setBookid(bookid);
			book.setBookname(rst.getString("bookname"));
			book.setBookstate(rst.getInt("bookstate"));
			book.setChubanshe(rst.getString("chubanshe"));
			book.setQixian(rst.getString("qixian"));
			book.setZuozhe(rst.getString("zuozhe"));
		 }
		 }catch(Exception e){
			 DBUtil.close(conn);
		 }
		return book;
	}
	
	public void addBookRecord(BookRecord bookRecord) throws Exception {
		 Connection conn=null;		  
		 try{
		 conn=DBUtil.getConnection();		  
		 String sql="insert into bookrecord(cardid,bookid,jieshushijian,huanshushijian)values(?,?,?,?)";		
		 PreparedStatement prep=conn.prepareStatement(sql);
		 prep.setLong(1, bookRecord.getCardid());
		 prep.setLong(2, bookRecord.getBookid());
		 Date date=new Date();
		 SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		 sdf.format(date);
		 prep.setString(3,sdf.format(date) );
		 Calendar cal = Calendar.getInstance();
		 cal.setTime(date);
		 cal.add(Calendar.DATE, 60);
		 prep.setString(4,sdf.format(cal.getTime()));
		 prep.execute();		 
		 Book book=findBookById(bookRecord.getBookid());
		 updateBookState(book.getBookid(), book.getBookstate()-1);		 
		 }catch(Exception e){
			e.printStackTrace();
		 }finally{
			 DBUtil.close(conn);
		 }
	}
	
	public void updateBookState(long bookid,int bookstate) throws Exception {
		Connection conn=null;
		try{
		conn=DBUtil.getConnection();
		String sql="update book set bookstate=? where bookid=?";
		PreparedStatement prep=conn.prepareStatement(sql);
		prep.setInt(1,bookstate);
		prep.setLong(2,bookid);
		prep.execute();
		}catch(Exception e){
			e.printStackTrace(); 
		}finally{
			DBUtil.close(conn);
		}
		
	}
	public List<Book> findBookRecordsById(long cardid) throws Exception {
		 Connection conn=null;
		 List<Book>records=new ArrayList<Book>();
		 BookRecord record=null;
		 conn=DBUtil.getConnection();
		 String sql="select *  from bookrecord where cardid=?";
		 PreparedStatement prep=conn.prepareStatement(sql);
		 prep.setLong(1,cardid);
		 ResultSet rst=prep.executeQuery();
		 while(rst.next()){
			record=new BookRecord();
			record.setCardid(cardid);
			record.setBookid(rst.getLong("bookid"));			
		    Book book=findBookById(rst.getLong("bookid"));
		    records.add(book);
		 }
		return records;
	}
	
	public void delBookRecordById(long bookRecordId) throws Exception {
		Connection conn=null;
		try{
		conn=DBUtil.getConnection();
		String sql="delete from bookrecord where bookid=?";
		PreparedStatement prep=conn.prepareStatement(sql);
		prep.setLong(1,bookRecordId);
		prep.execute();
		int num= findBookById(bookRecordId).getBookstate();
		updateBookState(bookRecordId, num+1);
		}finally{
			DBUtil.close(conn);
		}
	}
	public Card findByCardId(long cardid) throws Exception {
		 Connection conn=null;
		 Card card=null;
		 try{
		 conn=DBUtil.getConnection();
		 String sql="select  * from  card where cardid=?";
		 PreparedStatement prep=conn.prepareStatement(sql);
		 prep.setLong(1,cardid);
		 ResultSet rst=prep.executeQuery();
		 while(rst.next()){
			card=new Card();
			card.setCardid(cardid);
			card.setBalance(rst.getDouble("balance"));
			card.setCardstate(rst.getBoolean("cardstate"));
			card.setMm(rst.getString("mm"));			 
			card.setUsername(rst.getString("username"));
			card.setStudentid(rst.getLong("studentid"));
		 }
		 }finally{
			 DBUtil.close(conn);
		 }
		return card;
	}


	@Override
	public boolean findBookById(long studentId, long bookId) throws Exception {
		// TODO 自动生成的方法存根
		Connection conn=null;
		 conn=DBUtil.getConnection();
		 String sql="select  * from  bookrecord where cardid=? and bookid=?";
		 PreparedStatement prep=conn.prepareStatement(sql);
		 prep.setLong(1,studentId);
		 prep.setLong(2,bookId);
		 ResultSet rst=prep.executeQuery();
		 boolean b = rst.next();
		DBUtil.close(conn);
		System.out.println(b);
		return b;
}

}
