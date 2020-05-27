package com.njit.card.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.njit.card.dao.impl.StudentDAOImpl;
import com.njit.card.entity.Book;
import com.njit.card.entity.BookRecord;
import com.njit.card.entity.Card;
import com.njit.card.entity.CostRecord;
import com.njit.card.entity.FoodItem;

public class TestStudentDAO {
	@Test
	public void testFindById() {
		StudentDAOImpl s = new StudentDAOImpl();
		long id = 40905060111L;
		try {
			System.out.println(s.findById(id).getXingming());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	 
	 
@Test
public    void testFindBooks(){
	StudentDAOImpl daoImpl=new StudentDAOImpl();
	List<Book>books=new ArrayList<Book>();
	try {
		books=daoImpl.findBooks();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	System.out.println(books.size());
}
@Test
public   void   testFindBookById(){
	StudentDAOImpl daoImpl=new StudentDAOImpl();
	try {
		Book book=daoImpl.findBookById(111);
		System.out.println(book.getBookname());
	} catch (Exception e) {		 
		e.printStackTrace();
	}
	
}
@Test
public   void testAddBookRecord(){
	StudentDAOImpl daoImpl=new StudentDAOImpl();
	BookRecord record=new BookRecord();
	record.setBookid(114);
	record.setCardid(40905060111L);
	try {
		daoImpl.addBookRecord(record);
	} catch (Exception e) {		 
		e.printStackTrace();
	}
}
@Test
public   void testUpdate(){
	StudentDAOImpl daoImpl=new StudentDAOImpl();
	try {
		daoImpl.updateBookState(112, 10);
	} catch (Exception e) {		 
		e.printStackTrace();
	}
}
@Test
public  void testFindBookRecords(){
	StudentDAOImpl daoImpl=new StudentDAOImpl();
	List<Book>books=new ArrayList<Book>();	
	try {
		books=daoImpl.findBookRecordsById(40905060111L);
	 System.out.println(books.size());
	} catch (Exception e) {		 
		e.printStackTrace();
	}
}
@Test
public   void  testDelBookRecord(){
	StudentDAOImpl daoImpl=new StudentDAOImpl();
	try {
		daoImpl.delBookRecordById(112);
	} catch (Exception e) {		 
		e.printStackTrace();
	}
}
@Test
public   void   testFindAllFood(){
	StudentDAOImpl daoImpl=new StudentDAOImpl();
	List<FoodItem>items=new ArrayList<FoodItem>();
	try {
		items=daoImpl.findFoodItems();
		System.out.println(items.size());
	} catch (Exception e) {		 
		e.printStackTrace();
	}
}
@Test
public   void   testFindFoodById(){
	StudentDAOImpl daoImpl=new StudentDAOImpl();
	try {
		FoodItem item=daoImpl.findByFoodItemId(11);
		System.out.println(item.getFoodname());
	} catch (Exception e) {		 
		e.printStackTrace();
	}
}
@Test
public   void   testFindCardById(){
	StudentDAOImpl daoImpl=new StudentDAOImpl();
	try {
		 Card card=daoImpl.findByCardId(40905060111L);
		System.out.println(card.getBalance());
	} catch (Exception e) {		 
		e.printStackTrace();
	}
}
@Test
public   void   testAddCostRecord(){
	CostRecord record=new CostRecord();
	record.setCardid(40905060111L);
	record.setFoodid(11);
	StudentDAOImpl daoImpl=new StudentDAOImpl();
	try {
		daoImpl.addCostRecord(record);
	} catch (Exception e) {		 
		e.printStackTrace();
	}
}
@Test
public   void testFindCostRecordById(){
	StudentDAOImpl daoImpl=new StudentDAOImpl();
	try {
		List<FoodItem>foodItems=
		daoImpl.findFoodItemsById(40905060111l);
		System.out.println(foodItems.size());
	} catch (Exception e) {		 
		e.printStackTrace();
	}
}
}
