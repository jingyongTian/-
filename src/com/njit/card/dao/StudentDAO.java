package com.njit.card.dao;

import java.util.List;

import com.njit.card.entity.Book;
import com.njit.card.entity.BookRecord;
import com.njit.card.entity.CostRecord;
import com.njit.card.entity.FoodItem;
import com.njit.card.entity.Student;

public interface StudentDAO {
//通过ID查学生 
	public Student findById(long id) throws Exception;

//查食品列表
	public List<FoodItem> findFoodItems() throws Exception;

//通过ID查食品，增加
	public FoodItem findByFoodItemId(long id) throws Exception;

	public void addCostRecord(CostRecord record) throws Exception;

//通过卡查食品列表
	public List<FoodItem> findFoodItemsById(long cardid) throws Exception;

	public List<CostRecord> findCostRecordsById(long cardid) throws Exception;

//查书
	public List<Book> findBooks() throws Exception;

	public Book findBookById(long bookid) throws Exception;
	
	public boolean findBookById(long studentId, long bookId) throws Exception;

//书增删改查
	public void addBookRecord(BookRecord bookRecord) throws Exception;

	public void delBookRecordById(long bookRecordId) throws Exception;

	public void updateBookState(long bookid, int bookstate) throws Exception;

	public List<Book> findBookRecordsById(long id) throws Exception;

}
