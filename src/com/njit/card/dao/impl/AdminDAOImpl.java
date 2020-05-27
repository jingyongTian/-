package com.njit.card.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.njit.card.dao.AdminDAO;
import com.njit.card.entity.Book;
import com.njit.card.entity.Card;
import com.njit.card.entity.FoodItem;
import com.njit.card.entity.Student;
import com.njit.card.utils.DBUtil;
public class AdminDAOImpl implements AdminDAO{
	//查询所有的学生信息
	public List<Student> findStudents() throws Exception {
		Connection conn=null;
		List<Student>students=new ArrayList<Student>();
		Student s=null;
		conn=DBUtil.getConnection();
		String sql="select  *  from student";
		PreparedStatement prep=conn.prepareStatement(sql);
		ResultSet rst=prep.executeQuery();
		while(rst.next()){
			s=new Student();
			s.setStudentid(rst.getLong("studentid"));
			s.setBanji(rst.getString("banji"));
			s.setJiguan(rst.getString("jiguan"));
			s.setNianling(rst.getInt("nianling"));
			s.setRuxueshijian(rst.getDate("ruxueshijian"));
			s.setXingbie(rst.getString("xingbie"));
			s.setXingming(rst.getString("xingming"));
			s.setXuyuan(rst.getString("xueyuan"));
			s.setZhuanye(rst.getString("zhuanye"));
			s.setZhuzhi(rst.getString("zhuzhi")); 
			students.add(s);
		}
		return students;
	}
	//删除学生信息
	public void delStudentById(long id) throws Exception {
		 Connection conn=null;
		 conn=DBUtil.getConnection();
		 String  sql="delete  from  student  where studentid=?";
		 PreparedStatement prep=conn.prepareStatement(sql);
		 prep.setLong(1,id);
		 prep.execute();		
	}
	 //修改学生信息
	public void updateStudent(Student s) throws Exception {
		//连接数据库 
		Connection conn=null;
		 conn=DBUtil.getConnection();
		 String sql=" update  student set xingming=?,xingbie=?," +
		 		    "nianling=?,banji=?,zhuanye=?,xueyuan=?,jiguan=?," +
		 		    "zhuzhi=?,ruxueshijian=? where studentid=?";
         PreparedStatement prep=conn.prepareStatement(sql);		 
		 //设置预编译执行体的内容
         prep.setString(1,s.getXingming());
		 prep.setString(2,s.getXingbie());
		 prep.setInt(3, s.getNianling());
		 prep.setString(4,s.getBanji());
		 prep.setString(5,s.getZhuanye());
		 prep.setString(6,s.getXuyuan());
		 prep.setString(7, s.getJiguan());
		 prep.setString(8, s.getZhuzhi());   
		 java.sql.Date ruxueshijian=new java.sql.Date(s.getRuxueshijian().getTime());
         prep.setDate(9,ruxueshijian);
		 prep.setLong(10, s.getStudentid());		 
        //执行sql语句 修改数据库里面的内容
		 prep.execute();		
	}

	public void addStudent(Student s) throws Exception {
	   //连接数据库
		Connection conn=null;
	   conn=DBUtil.getConnection();
	   String sql="insert  into student(studentid,xingming,xingbie,nianling,banji,zhuanye,xueyuan,jiguan,zhuzhi,ruxueshijian)" +
	   		       "values(?,?,?,?,?,?,?,?,?,?)";
	   PreparedStatement prep=conn.prepareStatement(sql);
	   //设置预编译执行体的内容
	   prep.setLong(1,s.getStudentid());
	   prep.setString(2,s.getXingming());
	   prep.setString(3,s.getXingbie());
	   prep.setInt(4,s.getNianling());
	   prep.setString(5,s.getBanji());
	   prep.setString(6,s.getZhuanye());
	   prep.setString(7,s.getXuyuan());
	   prep.setString(8,s.getJiguan());
	   prep.setString(9,s.getZhuzhi());
	   prep.setDate(10,new Date(s.getRuxueshijian().getTime()));
	   //执行插入操作
	   prep.execute();
	}
	
	public void addCard(Card c) throws Exception {
		//连接数据库
		Connection conn=null;
		conn=DBUtil.getConnection();
		String sql="insert  into  card(cardid,mm,username,balance,cardstate,studentid)values(?,?,?,?,?,?)";
		PreparedStatement prep=conn.prepareStatement(sql);
		//设置预编译执行体的内容
		prep.setLong(1,c.getCardid());
		prep.setString(2,c.getMm());
		prep.setString(3,c.getUsername());
		prep.setDouble(4,c.getBalance());
		prep.setBoolean(5,c.getCardstate());
		prep.setLong(6,c.getStudentid());
		//执行sql语句  进行插入操作
		prep.execute();
		
	}

	public void delCardById(long cardid) throws Exception {
		Connection conn=null;
		conn=DBUtil.getConnection();
		String  sql="delete from card where cardid=?";
		PreparedStatement prep=conn.prepareStatement(sql);
		prep.setLong(1,cardid);
		prep.execute();
		DBUtil.close(conn);
	}
	public List<Card> findCrads() throws Exception {
		Connection conn=null;
		List<Card>cards=new ArrayList<Card>();		
		conn=DBUtil.getConnection();
		String sql="select  * from card";
		PreparedStatement prep=conn.prepareStatement(sql);
		ResultSet rst=prep.executeQuery();
		Card card=null;
		while(rst.next()){
			card=new Card();
			card.setCardid(rst.getLong("cardid"));
			card.setBalance(rst.getDouble("balance"));
			card.setCardstate(rst.getBoolean("cardstate"));
		    card.setMm(rst.getString("mm"));
		    card.setStudentid(rst.getLong("studentid"));
		    card.setUsername(rst.getString("username"));
		    cards.add(card);
		}
		return cards;
	}
	public void updateCard(Card card) throws Exception {
		 Connection conn=null;
		 conn=DBUtil.getConnection();
		 System.out.println(card.getBalance()+":"+card.getMm()+"："+card.getCardid());
		 System.out.println(card.getBalance()+":"+card.getCardstate());
		 String sql="update card set mm=?,balance=?,cardstate=? where cardid=?";
		 PreparedStatement prep=conn.prepareStatement(sql);
		 prep.setString(1,card.getMm());
		 prep.setDouble(2,card.getBalance());
		 prep.setBoolean(3,card.getCardstate());
		 prep.setLong(4,card.getCardid());
		 prep.execute();		
	}
	public void addBook(Book b) throws Exception {
		 Connection conn=null;
		 try{
		 conn=DBUtil.getConnection();
		 String sql="insert into book(bookid,bookname,chubanshe,zuozhe,qixian,bookstate)" +
		 		"values(?,?,?,?,?,?)";
		 PreparedStatement prep=conn.prepareStatement(sql);
		 prep.setLong(1,b.getBookid());
		 prep.setString(2,b.getBookname());
		 prep.setString(3,b.getChubanshe());
		 prep.setString(4,b.getZuozhe());
		 prep.setString(5,b.getQixian());
		 System.out.println(b.getBookstate());
		 prep.setInt(6,b.getBookstate());
		 prep.execute();
		 }catch(Exception e){
			 e.printStackTrace();
		 }finally{
			 DBUtil.close(conn);
		 }
		
	}
	public void addFoodItem(FoodItem f) throws Exception {
		 Connection conn=null;
		 try{
		 conn=DBUtil.getConnection();
		 String sql="insert into fooditem(foodid,foodname,danjia,chuangkou)" +
		 		    "values(?,?,?,?)";
		 PreparedStatement prep=conn.prepareStatement(sql);
		 prep.setLong(1,f.getFoodid());
		 prep.setString(2,f.getFoodname());
		 prep.setDouble(3, f.getDanjia());
		 prep.setInt(4,f.getChuangkou());
		 prep.execute();
		 }catch(Exception e){
			 e.printStackTrace();
		 }finally{
			 DBUtil.close(conn);
		 }
	}
	public void delBookById(long id) throws Exception {
		 Connection conn=null;
		 try{
         conn=DBUtil.getConnection();
         String sql="delete from book where bookid=?";
		 PreparedStatement prep=conn.prepareStatement(sql);
		 prep.setLong(1,id);
		 prep.execute();
		 }catch(Exception e){
			 e.printStackTrace();
		 }finally{
			 DBUtil.close(conn);
		 }
		
	}
	public void delFoodItemById(long id) throws Exception {
		 Connection conn=null;
		 try{
         conn=DBUtil.getConnection();
         String sql="delete from fooditem where foodid=?";
		 PreparedStatement prep=conn.prepareStatement(sql);
		 prep.setLong(1,id);
		 prep.execute();
		 }catch(Exception e){
			 e.printStackTrace();
		 }finally{
			 DBUtil.close(conn);
		 }
		 
		
	}
	public Book findBookById(long id) throws Exception {
		 Connection conn=null;
		 Book b=null;
		 try{
         conn=DBUtil.getConnection();
         String sql="select * from book where bookid=?";
		 PreparedStatement prep=conn.prepareStatement(sql);
		 prep.setLong(1,id);
		 ResultSet rst=prep.executeQuery();
		 while(rst.next()){
			b=new Book();
			b.setBookid(rst.getLong("bookid"));
			b.setBookname(rst.getString("bookname"));
			b.setBookstate(rst.getInt("bookstate"));
			b.setChubanshe(rst.getString("chubanshe"));
			b.setQixian(rst.getString("qixian"));
			b.setZuozhe(rst.getString("zuozhe"));			
		 }
		 }catch(Exception e){
			 e.printStackTrace();
		 }finally{
			 DBUtil.close(conn);
		 }
		 return b;
		
	}
	public FoodItem findFoodItemById(long id) throws Exception {
		Connection conn=null;
		FoodItem f=null;
		 try{
        conn=DBUtil.getConnection();
        String sql="select * from fooditem where foodid=?";
		 PreparedStatement prep=conn.prepareStatement(sql);
		 prep.setLong(1,id);
		 ResultSet rst=prep.executeQuery();
		 while(rst.next()){
			f=new FoodItem();
			f.setChuangkou(rst.getInt("chuangkou"));
			f.setDanjia(rst.getDouble("danjia"));
			f.setFoodid(rst.getLong("foodid"));
			f.setFoodname(rst.getString("foodname"));
		 }
		 }catch(Exception e){
			 e.printStackTrace();
		 }finally{
			 DBUtil.close(conn);
		 }
		 return f;		
	} 		
	 
	public void updateBook(Book b) throws Exception {
		Connection conn=null;
		 try{
		 conn=DBUtil.getConnection();
		 String sql="update book set bookname=?,chubanshe=?,zuozhe=?,qixian=?,bookstate=? " +
		 		    "where bookid=?";		 		 
		 PreparedStatement prep=conn.prepareStatement(sql);		
		 prep.setString(1,b.getBookname());
		 prep.setString(2,b.getChubanshe());
		 prep.setString(3,b.getZuozhe());
		 prep.setString(4,b.getQixian());
		 prep.setInt(5,b.getBookstate());
		 prep.setLong(6,b.getBookid());
		 prep.execute();
		 }catch(Exception e){
			 e.printStackTrace();
		 }finally{
			 DBUtil.close(conn);
		 }	 
		
	}
	public void updateFoodItem(FoodItem f) throws Exception {
		 Connection conn=null;
		 try{
		 conn=DBUtil.getConnection();
		 String sql="update fooditem  set foodname=?,danjia=?,chuangkou=?" +
		 		    " where foodid=?";		 		     
		 PreparedStatement prep=conn.prepareStatement(sql);		
		 prep.setString(1,f.getFoodname());
		 prep.setDouble(2, f.getDanjia());
		 prep.setInt(3,f.getChuangkou());
		 prep.setLong(4,f.getFoodid());
		 prep.execute();
		 }catch(Exception e){
			 e.printStackTrace();
		 }finally{
			 DBUtil.close(conn);
		 } 
		
	}
	@Override
	public Map showClass() throws Exception {
		// TODO 自动生成的方法存根
		 Connection conn=null;
		 List<String> className = new LinkedList<>();
		 List<Integer> num = new LinkedList<>();
		 Map show = new HashMap();
		 try{
		 conn=DBUtil.getConnection();
		 String sql="select banji, count(banji) as num from student group  by banji order by num;";		 		     
		 PreparedStatement prep=conn.prepareStatement(sql);		
		 prep.execute();
		 ResultSet rst=prep.executeQuery();
		 while(rst.next()){
			className.add(rst.getString("banji"));
		    num.add(rst.getInt("num"));
		 }
		 }catch(Exception e){
			 e.printStackTrace();
		 }finally{
			 System.out.println(className.toString());
			 System.out.println(num.toString());
			 show.put("className", className);
			 show.put("num", num);
			 DBUtil.close(conn);
		 } 
		return show;
	}

	 

	 
}
