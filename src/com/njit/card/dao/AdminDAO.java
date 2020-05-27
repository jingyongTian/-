package com.njit.card.dao;
import java.util.List;
import java.util.Map;

import com.njit.card.entity.Book;
import com.njit.card.entity.Card;
import com.njit.card.entity.FoodItem;
import com.njit.card.entity.Student;

public interface AdminDAO {
//学生管理
public   List<Student> findStudents()    throws  Exception;
public  void  delStudentById(long id)   throws  Exception;
public  void  updateStudent(Student s)  throws  Exception;
public  void  addStudent(Student s)     throws  Exception;
public  Map  showClass() throws  Exception;
//一卡通管理
public   List<Card>findCrads() throws  Exception; 
public   void updateCard(Card card)throws  Exception;
public   void  delCardById(long cardId)throws Exception;
public   void addCard(Card card) throws Exception;
//菜品管理
public  void addFoodItem(FoodItem f)throws Exception;
public  void delFoodItemById(long id)throws Exception;
public  void updateFoodItem(FoodItem f)throws Exception;
public  FoodItem findFoodItemById(long id) throws Exception;
//图书管理
public  void addBook(Book b)throws Exception;
public  void delBookById(long id)throws Exception;
public  void updateBook(Book b)throws Exception;
public  Book findBookById(long id) throws Exception;
}
