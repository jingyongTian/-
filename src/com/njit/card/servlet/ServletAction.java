package com.njit.card.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.njit.card.dao.AdminDAO;
import com.njit.card.dao.LoginDAO;
import com.njit.card.dao.StudentDAO;
import com.njit.card.dao.impl.AdminDAOImpl;
import com.njit.card.dao.impl.CountDAOImpl;
import com.njit.card.dao.impl.LoginDAOImpl;
import com.njit.card.dao.impl.StudentDAOImpl;
import com.njit.card.entity.Book;
import com.njit.card.entity.BookRecord;
import com.njit.card.entity.Card;
import com.njit.card.entity.CostRecord;
import com.njit.card.entity.FoodItem;
import com.njit.card.entity.Login;
import com.njit.card.entity.Student;

public class ServletAction extends HttpServlet {
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 设置服务器与页面数据交换的数据编码格式
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		// 获取session对象
		HttpSession session = request.getSession();
		// 获取请求路径并对其分割成为简单地命令请求
		String uri = request.getRequestURI();
		String action = uri.substring(uri.lastIndexOf("/"), uri
				.lastIndexOf("."));
		System.out.println(action);
		if (action.equals("/login")) {
			// 获取页面输入的用户编码
			request.setCharacterEncoding("utf-8");
			// 获取用户在登陆界面上输入的值 包括用户名和密码，角色选择
			long id = Long.parseLong(request.getParameter("id"));
			int type = Integer.parseInt(request.getParameter("type"));
			String pwd = request.getParameter("mm");
			// 连接数据库 查找这个编码对应的记录是否存在
			LoginDAO dao = new LoginDAOImpl();
			Login login = dao.findById(id);

			// 如果数据库查出的角色和用户选择的角色不一样,返回到登陆错误界面
			if (login == null || !(login.getType() == type)
					|| !(login.getMm().equals(pwd))) {
				session.setAttribute("login", login);
				response.sendRedirect("../main/loginError.jsp");
			} else {
				session.setAttribute("login", login);
				if (type == 1) {
					response.sendRedirect("../student/student.jsp");
				} else if (type == 2) {
					response.sendRedirect("../admin/admin.jsp");
				} else {
					response.sendRedirect("../count/count.jsp");
				}
			}
		} else if (action.equals("/listRegist")) {
			Login login = (Login) session.getAttribute("login");
			StudentDAOImpl daoImpl = new StudentDAOImpl();
			Student student = null;
			try {
				student = daoImpl.findById(login.getId());
			} catch (Exception e) {
				e.printStackTrace();
			} 
			System.out.println(student.getRuxueshijian());
			request.setAttribute("student", student);
			request.setAttribute("mm", login.getMm());
			RequestDispatcher rd = request
					.getRequestDispatcher("student/listStudent.jsp");
			rd.forward(request, response);
		} else if (action.equals("/listFoodItem")) {
			StudentDAOImpl daoImpl = new StudentDAOImpl();
			List<FoodItem> foodItems = new ArrayList<FoodItem>();
			try {
				foodItems = daoImpl.findFoodItems();
			} catch (Exception e) {
				e.printStackTrace();
			}
			request.setAttribute("foodItems", foodItems);
			RequestDispatcher rd = request
					.getRequestDispatcher("student/listFoodItems.jsp");
			rd.forward(request, response);

		} else if (action.equals("/foodBuy")) {
			// 获取请求命令传过来的 食品编号
			long foodid = Long.parseLong(request.getParameter("id"));
			// 获取用户的id
			Login login = (Login) session.getAttribute("login");
			/**
			 * 获取数据库中的相关的数值 要购买先查看卡上的余额是否大于或者等于食品单价 这样就需要Card对象和FoodItem对象
			 * 
			 * 比较如果满足条件：再增加到食品记录里面 不满足就给出提示
			 */
			StudentDAOImpl daoImpl = new StudentDAOImpl();
			AdminDAOImpl adminDaoImpl = new AdminDAOImpl();
			Card card = null;
			FoodItem item = null;
			try {
				card = daoImpl.findByCardId(login.getId());
				item = daoImpl.findByFoodItemId(foodid);
			} catch (Exception e) {
				e.printStackTrace();
			}
			double balance = card.getBalance();
			System.out.println(balance);
			System.out.println(card.getBalance());
			double danjia = item.getDanjia();
			if (balance == danjia || balance > danjia) {
				CostRecord record = new CostRecord();
				record.setCardid(login.getId());
				record.setFoodid(foodid);
				card.setBalance(balance - danjia);
				try {
					adminDaoImpl.updateCard(card);
					daoImpl.addCostRecord(record);
				} catch (Exception e) {
					e.printStackTrace();
				}
				response.sendRedirect("student/buyFoodSuccess.jsp");
			} else {
				response.sendRedirect("student/buyFoodError.jsp");
			}
			// 显示个人食堂的消费记录
		} else if (action.equals("/listCostRecord")) {

			
			Login login = (Login) session.getAttribute("login");
			StudentDAOImpl daoImpl = new StudentDAOImpl();
			List<FoodItem> foodItems = null;
			List<CostRecord> records = null;
			try {
				foodItems = daoImpl.findFoodItemsById(login.getId());
				for(FoodItem food : foodItems){
					System.out.println("window"+food.getChuangkou());
					System.out.println("Danjia"+food.getDanjia());
					System.out.println("id"+food.getFoodid());
					System.out.println("foodname"+food.getFoodname());
				}
				records = daoImpl.findCostRecordsById(login.getId());
				
				System.out.println(foodItems);
				System.out.println(records);
			} catch (Exception e) {
				e.printStackTrace();
			}
			request.setAttribute("foodItems",foodItems);
			request.setAttribute("records",records);
			
			RequestDispatcher rt = request.getRequestDispatcher("student/listCostRecords.jsp");
			rt.forward(request,response);
		} else if (action.equals("/listBook")) {
			StudentDAOImpl daoImpl = new StudentDAOImpl();
			List<Book> booklist = null;
			try {
				booklist = daoImpl.findBooks();
			} catch (Exception e) {
				e.printStackTrace();
			}
			request.setAttribute("booklist", booklist);
			RequestDispatcher rd = request
					.getRequestDispatcher("student/booklist.jsp");
			rd.forward(request, response);
		} else if (action.equals("/lend")) {
			// 从请求地址取到参数值----bookid
			long bookid = Long.parseLong(request.getParameter("id"));
			// 根据bookid,找到这本书
			// 将这本书借还状态置为借出，
			StudentDAOImpl daoImpl = new StudentDAOImpl();
			Book book = null;
			try {
				book = daoImpl.findBookById(bookid);
			} catch (Exception e) {
				e.printStackTrace();
			}
			// 判断这本书是否全部借出
			if (book.getBookstate() <= 0) {
				response.sendRedirect("student/lenderror.jsp");
			} else {
				// 获取登录的对象,并获取卡号
				Login login = (Login) session.getAttribute("login");
				System.out.println(login.getId());
				long cardid = login.getId();
				BookRecord bookRecord = new BookRecord();
				bookRecord.setBookid(bookid);
				bookRecord.setCardid(cardid);
				//判断是否借过该书籍
				boolean b=false;
				try {
					b = daoImpl.findBookById(cardid, bookid);
				} catch (Exception e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
				if(b) {
					//借过将借过标识符传给前端页面
					request.setAttribute("choice", 1);
				}
				else {
					try {
						//未借过将该图书加入图书借阅记录
					daoImpl.addBookRecord(bookRecord);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
				RequestDispatcher rd = request
						.getRequestDispatcher("student/lendsuccess.jsp");
				rd.forward(request, response);
			}

		} else if (action.equals("/listBookRecord")) {
			Login login = (Login) session.getAttribute("login");
			StudentDAOImpl daoImpl = new StudentDAOImpl();
			List<Book> bookrecords = null;
			try {
				if(login.getType()==3) {
					long id = Long.parseLong(request.getParameter("id"));
					bookrecords = daoImpl.findBookRecordsById(id);
				}else {
					bookrecords = daoImpl.findBookRecordsById(login.getId());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			request.setAttribute("bookrecords", bookrecords);
			request.setAttribute("type", login.getType());
			RequestDispatcher rd = request
					.getRequestDispatcher("student/listbookrecord.jsp");
			rd.forward(request, response);
		} else if (action.equals("/giveBook")) {
			long bookid = Long.parseLong(request.getParameter("id"));
			Login login = (Login) session.getAttribute("login");
			StudentDAOImpl daoImpl = new StudentDAOImpl();
			try {
				daoImpl.delBookRecordById(bookid);
			} catch (Exception e) {
				e.printStackTrace();
			}
			request.setAttribute("type", login.getType());
			RequestDispatcher rd = request
					.getRequestDispatcher("student/givesuccess.jsp");
			rd.forward(request, response);
		} else if (action.equals("/manageStudent")) {
			AdminDAOImpl daoImpl = new AdminDAOImpl();
			List<Student> students = null;
			try {
				students = daoImpl.findStudents();
			} catch (Exception e) {
				e.printStackTrace();
			}
			request.setAttribute("students", students);
			Login login=(Login)session.getAttribute("login");
			RequestDispatcher rd=null;
			if(login.getType() == 3) {
				rd = request.getRequestDispatcher("count/listStudents.jsp");
			}else {
				rd = request.getRequestDispatcher("admin/listStudents.jsp");
				}
			rd.forward(request, response);
		} else if (action.equals("/Delstudnet")) {
			long id = Long.parseLong(request.getParameter("id"));
			AdminDAOImpl daoImpl = new AdminDAOImpl();
			LoginDAOImpl loginImpl = new LoginDAOImpl();
			try {
				daoImpl.delStudentById(id);
				loginImpl.delById(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
			response.sendRedirect("manageStudent.do");
		} else if (action.equals("/loadStudnet")) {
			request.setCharacterEncoding("utf-8");
			long studentid = Long.parseLong(request.getParameter("id"));
			StudentDAOImpl daoImpl = new StudentDAOImpl();
			LoginDAOImpl login = new LoginDAOImpl();
			Student student = daoImpl.findById(studentid);
			Login mm = login.findById(studentid);
			request.setAttribute("student", student);
			request.setAttribute("mm", mm.getMm());
			RequestDispatcher rd = request
					.getRequestDispatcher("admin/loadStudent.jsp");
			rd.forward(request, response);
		} else if (action.equals("/updateStudent")) {
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			long studentid = Long.parseLong(request.getParameter("studentid"));
			String xingming = request.getParameter("xingming");
			String xingbie = request.getParameter("xingbie");
			int nianling = Integer.parseInt(request.getParameter("nianling"));
			String banji = request.getParameter("banji");
			String zhuanye = request.getParameter("zhuanye");
			String xueyuan = request.getParameter("xueyuan");
			String jiguan = request.getParameter("jiguan");
			String zhuzhi = request.getParameter("zhuzhi");
			String ruxueshijian = request.getParameter("ruxueshijian");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Student s = new Student();
			s.setBanji(banji);
			s.setJiguan(jiguan);
			s.setNianling(nianling);
			try {
				s.setRuxueshijian(sdf.parse(ruxueshijian));
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			s.setStudentid(studentid);
			s.setXingbie(xingbie);
			s.setXingming(xingming);
			s.setXuyuan(xueyuan);
			s.setZhuanye(zhuanye);
			s.setZhuzhi(zhuzhi);
			AdminDAOImpl adminDAOImpl = new AdminDAOImpl();
			try {
				adminDAOImpl.updateStudent(s);
				response.sendRedirect("manageStudent.do");
			} catch (Exception e) {
				e.printStackTrace();
			}
			LoginDAO dao = new LoginDAOImpl();
			String newmm = request.getParameter("mm");
			try {
				dao.updataById(studentid, newmm);
			} catch (Exception e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		} else if (action.equals("/addStudent")) {
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			long studentid = Long.parseLong(request.getParameter("studentid"));
			System.out.println(studentid);
			StudentDAOImpl daoImpl = new StudentDAOImpl();
			Student s = daoImpl.findById(studentid);
			System.out.println(s);
			if (s != null) {
				response.sendRedirect("admin/addStudentError.jsp");
			} else {
				// 获取页面传过来数据
				String xingming = request.getParameter("xingming");
				String mm = request.getParameter("mm");
				String xingbie = request.getParameter("xingbie");
				int nianling = Integer.parseInt(request
						.getParameter("nianling"));
				String banji = request.getParameter("banji");
				String zhuanye = request.getParameter("zhuanye");
				String xueyuan = request.getParameter("xueyuan");
				String jiguan = request.getParameter("jiguan");
				String zhuzhi = request.getParameter("zhuzhi");
				String ruxueshijian = request.getParameter("ruxueshijian");
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				// 创建新的学生
				s = new Student();
				Login login = new Login(studentid,mm,1);
				// 给学生的各个属性赋值
				s.setStudentid(studentid);
				s.setBanji(banji);
				s.setJiguan(jiguan);
				s.setNianling(nianling);
				try {
					s.setRuxueshijian(sdf.parse(ruxueshijian));
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				s.setXingbie(xingbie);
				s.setXingming(xingming);
				s.setXuyuan(xueyuan);
				s.setZhuanye(zhuanye);
				s.setZhuzhi(zhuzhi);
				// 将学生信息插入数据库
				AdminDAOImpl impl = new AdminDAOImpl();
				LoginDAOImpl loginImp = new LoginDAOImpl();
				try {
					impl.addStudent(s);
					loginImp.addLogin(login);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				response.sendRedirect("manageStudent.do");
			}
			
		} else if (action.equals("/foodItemManage")) {
			// 查找到所有的菜品
			StudentDAO dao = new StudentDAOImpl();
			List<FoodItem> foodItems = null;
			try {
				foodItems = dao.findFoodItems();
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (foodItems.size() == 0) {
				response.sendRedirect("admin/manageFoodItemError.jsp");
			} else {
				// 转发到食品管理页面
				request.setAttribute("foodItems", foodItems);
				RequestDispatcher rd = request
						.getRequestDispatcher("admin/manageFoodItem.jsp");
				rd.forward(request, response);
			}

		} else if (action.equals("/addFoodItem")) {
			// 获取表单的数据
			long foodid = Long.parseLong(request.getParameter("foodid"));
			String foodname = request.getParameter("foodname");
			double danjia = Double.parseDouble(request.getParameter("danjia"));
			int chuangkou = Integer.parseInt(request.getParameter("chuangkou"));
			// 判断是否存在
			AdminDAO dao = new AdminDAOImpl();
			FoodItem f = null;
			try {
				f = dao.findFoodItemById(foodid);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			if (f != null) {
				response.sendRedirect("admin/addFoodItemError.jsp");
			} else {
				// 封装成为FoodItem对象
				f = new FoodItem();
				f.setChuangkou(chuangkou);
				f.setDanjia(danjia);
				f.setFoodid(foodid);
				f.setFoodname(foodname);
				// 保存数据
				try {
					dao.addFoodItem(f);
				} catch (Exception e) {
					e.printStackTrace();
				}
				response.sendRedirect("foodItemManage.do");
			}

		} else if (action.equals("/delFoodItem")) {
			long foodid = Long.parseLong(request.getParameter("id"));
			// 依据食品编号删除这个对象
			AdminDAO dao = new AdminDAOImpl();
			try {
				dao.delFoodItemById(foodid);
			} catch (Exception e) {
				e.printStackTrace();
			}			 
			response.sendRedirect("foodItemManage.do");
		} else if (action.equals("/loadFoodItem")) {
			long foodid = Long.parseLong(request.getParameter("id"));
			// 根据食品编号找到这条记录 并转发到页面
			AdminDAO dao = new AdminDAOImpl();
			FoodItem f = null;
			try {
				f = dao.findFoodItemById(foodid);
			} catch (Exception e) {
				e.printStackTrace();
			}
			request.setAttribute("f", f);
			RequestDispatcher rd = request
					.getRequestDispatcher("admin/loadFoodItem.jsp");
			rd.forward(request, response);			 
		} else if (action.equals("/updateFoodItem")) {
			// 获取表单数据
			long foodid = Long.parseLong(request.getParameter("foodid"));
			String foodname = request.getParameter("foodname");
			double danjia = Double.parseDouble(request.getParameter("danjia"));
			int chuangkou = Integer.parseInt(request.getParameter("chuangkou"));
			// 依据食品编号查找这个对象
			AdminDAO dao = new AdminDAOImpl();
			FoodItem f = null;
			try {
				f = dao.findFoodItemById(foodid);
			} catch (Exception e) {
				e.printStackTrace();
			}
			// 为FoodItem设置属性
			f.setChuangkou(chuangkou);
			f.setDanjia(danjia);
			f.setFoodid(foodid);
			f.setFoodname(foodname);
			// 调用业务逻辑方法修改数据库中数据
			try {
				dao.updateFoodItem(f);
			} catch (Exception e) {
				e.printStackTrace();
			}
			response.sendRedirect("foodItemManage.do");
			
			
		} else if (action.equals("/bookManage")) {
			// 查找到所有的图书
			StudentDAO dao = new StudentDAOImpl();
			List<Book> books = null;
			try {
				books = dao.findBooks();
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (books.size() == 0) {
				response.sendRedirect("admin/manageBookError.jsp");
			} else {
				// 转发到图书管理页面上
				request.setAttribute("books", books);
				RequestDispatcher rd = request
						.getRequestDispatcher("admin/manageBook.jsp");
				rd.forward(request, response);
			}

		} else if (action.equals("/addBook")) {
			// 获取表单信息
			long bookid = Long.parseLong(request.getParameter("bookid"));
			String bookname = request.getParameter("bookname");
			String chubanshe = request.getParameter("chubanshe");
			String zuozhe = request.getParameter("zuozhe");
			String qixian = request.getParameter("qixian");
			Integer bookstate = Integer.parseInt(request.getParameter("bookstate"));
			// 查询数据库看是否重复
			Book b = null;
			AdminDAO dao = new AdminDAOImpl();
			try {
				b = dao.findBookById(bookid);
				System.out.println(b);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (b == null) {
				// 封装成为Book对象
				b = new Book();
				b.setBookid(bookid);
				b.setBookname(bookname);
				b.setBookstate(bookstate);
				b.setChubanshe(chubanshe);
				b.setQixian(qixian);
				b.setZuozhe(zuozhe);
				// 掉用业务逻辑保存数据
				try {
					dao.addBook(b);
				} catch (Exception e) {
					e.printStackTrace();
				}
				response.sendRedirect("bookManage.do");

			} else {
				response.sendRedirect("admin/addBookError.jsp");
			}

		} else if (action.equals("/delBook")) {
			long bookid = Long.parseLong(request.getParameter("bookid"));
			AdminDAOImpl daoImpl = new AdminDAOImpl();
			try {
				daoImpl.delBookById(bookid);
			} catch (Exception e) {
				e.printStackTrace();
			}
			response.sendRedirect("bookManage.do");	
			
		} else if (action.equals("/loadBook")) {
			long bookid = Long.parseLong(request.getParameter("id"));
			// 根据图书编号找到这条记录
			AdminDAO dao = new AdminDAOImpl();
			Book b = null;
			try {
				b = dao.findBookById(bookid);
			} catch (Exception e) {
				e.printStackTrace();
			}
			// 转发到修改界面
			request.setAttribute("b", b);
			RequestDispatcher rd = request
					.getRequestDispatcher("admin/loadBook.jsp");
			rd.forward(request, response);
		} else if (action.equals("/updateBook")) {
			// 获取表单信息
			long bookid = Long.parseLong(request.getParameter("bookid"));
			String bookname = request.getParameter("bookname");
			String chubanshe = request.getParameter("chubanshe");
			String zuozhe = request.getParameter("zuozhe");
			String qixian = request.getParameter("qixian");
			Integer bookstate = Integer.parseInt(request.getParameter("bookstate"));
			// 依据图书编号查询数据
			Book b = null;
			AdminDAO dao = new AdminDAOImpl();
			try {
				b = dao.findBookById(bookid);
			} catch (Exception e) {
				e.printStackTrace();
			}
			// 设置Book对象的属性值
			b.setBookname(bookname);
			b.setBookstate(bookstate);
			b.setChubanshe(chubanshe);
			b.setQixian(qixian);
			b.setZuozhe(zuozhe);
			// 掉用业务逻辑保存数据
			try {
				dao.updateBook(b);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			response.sendRedirect("bookManage.do");

		} else if (action.equals("/listCards")) {
			// 连接数据库 查找所有的一卡通记录
			AdminDAOImpl daoImpl = new AdminDAOImpl();
			List<Card> cards = null;
			try {
				cards = daoImpl.findCrads();
			} catch (Exception e) {
				e.printStackTrace();
			}
			// 将查到的记录转发到显示页面
			if (cards.size() == 0) {
				response.sendRedirect("admin/manageCardError.jsp");
			} else {
				request.setAttribute("cards", cards);
				Login login=(Login)session.getAttribute("login");
				RequestDispatcher rd=null;
				if(login.getType() == 2){
					rd = request.getRequestDispatcher("admin/listCards.jsp");
				}else {
					rd = request.getRequestDispatcher("count/listCards.jsp");
				}
				rd.forward(request, response);
			}

		} else if (action.equals("/addCard")) {
			// 设置字符编码
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("text/html;charset=utf-8");
			// 获取页面输入的卡号
			long cardid = Long.parseLong(request.getParameter("cardid"));
			StudentDAOImpl daoImpl = new StudentDAOImpl();
			/**
			 * 判断这个卡号是否存在 存在，，不保存 不存在，再创建并封装成为card对象 并保存到数据库
			 * 
			 */
			Card card = null;
			try {
				card = daoImpl.findByCardId(cardid);
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println(card);
			if (card == null) {
				// 获取页面传入的数值
				String mm = request.getParameter("mm");
				String username = request.getParameter("username");
				double balance = Double.parseDouble(request
						.getParameter("balance"));
				boolean cardstate = Boolean.parseBoolean(request
						.getParameter("cardstate"));
				long studentid = Long.parseLong(request
						.getParameter("studentid"));
				// 封装为card对象
				card = new Card();
				card.setCardid(cardid);
				card.setBalance(balance);
				card.setCardstate(cardstate);
				card.setMm(mm);
				card.setStudentid(studentid);
				card.setUsername(username);
				// 保存到数据库
				AdminDAOImpl impl = new AdminDAOImpl();
				try {
					impl.addCard(card);
					response.sendRedirect("listCards.do");
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				response.sendRedirect("admin/addCardError.jsp");
			}

		} else if (action.equals("/findCard")) {
			long cardid = Long.parseLong(request.getParameter("cardid"));
			StudentDAOImpl daoImpl = new StudentDAOImpl();
			Card card = null;
			try {
				card = daoImpl.findByCardId(cardid);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (card == null) {
				response.sendRedirect("admin/manageCardStateError.jsp");
			} else {
				request.setAttribute("card", card);
				RequestDispatcher rd = request
						.getRequestDispatcher("admin/manageCardState.jsp");
				rd.forward(request, response);
			}
		} else if (action.equals("/stopCard")) {
			long cardid = Long.parseLong(request.getParameter("cardid"));
			StudentDAOImpl daoImpl = new StudentDAOImpl();
			Card card = null;
			try {
				card = daoImpl.findByCardId(cardid);
			} catch (Exception e) {
				e.printStackTrace();
			}
			card.setCardstate(false);
			AdminDAOImpl impl = new AdminDAOImpl();
			try {
				impl.updateCard(card);
			} catch (Exception e) {
				e.printStackTrace();
			}
			response.sendRedirect("listCards.do");
		} else if (action.equals("/useCard")) {
			long cardid = Long.parseLong(request.getParameter("cardid"));
			StudentDAOImpl daoImpl = new StudentDAOImpl();
			Card card = null;
			try {
				card = daoImpl.findByCardId(cardid);
			} catch (Exception e) {
				e.printStackTrace();
			}
			card.setCardstate(true);
			AdminDAOImpl impl = new AdminDAOImpl();
			try {
				impl.updateCard(card);
			} catch (Exception e) {
				e.printStackTrace();
			}
			response.sendRedirect("listCards.do");
		} else if (action.equals("/findFoodRecords")) {
			long cardid = Long.parseLong(request.getParameter("cardid"));
			// 检验输入的卡号是否正确
			StudentDAOImpl daoImpl = new StudentDAOImpl();
			Card card = null;
			try {
				card = daoImpl.findByCardId(cardid);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (card == null) {
				response.sendRedirect("admin/cardIdError.jsp");
			} else {
				session.setAttribute("cardid", cardid);
				List<FoodItem> foodItems = null;
				List<CostRecord> records = null;
				try {
					foodItems = daoImpl.findFoodItemsById(cardid);
					records = daoImpl.findCostRecordsById(cardid);
				} catch (Exception e) {
					e.printStackTrace();
				}
				request.setAttribute("foodItems", foodItems);
				request.setAttribute("records", records);
				RequestDispatcher rd = request
						.getRequestDispatcher("count/manageFoodRecords.jsp");
				rd.forward(request, response);
			}
		} else if (action.equals("/delFoodRecord")) {
			long cardid = Long.parseLong(request.getParameter("cardid"));
			long foodid = Long.parseLong(request.getParameter("foodid"));
			CountDAOImpl daoImpl = new CountDAOImpl();
			try {
				daoImpl.delFoodRecordsById(cardid, foodid);
			} catch (Exception e) {
				e.printStackTrace();
			}
			response.sendRedirect("findFoodRecords.do?cardid="
					+ session.getAttribute("cardid"));
		} else if (action.equals("/addValue")) {
			// 获取输入的卡号和金额
			long cardid = Long.parseLong(request.getParameter("cardid"));
			double value = Double.parseDouble(request.getParameter("value"));
			/**
			 * 调用数据处理逻辑 查看卡的状态是否为可用 可用充值 不可用转发到另一个页面提示
			 */
			StudentDAOImpl daoImpl = new StudentDAOImpl();
			Card card = null;
			try {
				card = daoImpl.findByCardId(cardid);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (card.getCardstate()) {
				AdminDAOImpl impl = new AdminDAOImpl();
				card.setBalance(value + card.getBalance());
				try {
					impl.updateCard(card);
				} catch (Exception e) {
					e.printStackTrace();
				}
				// 将这张卡的信息和充值额转发到页面
				request.setAttribute("card", card);
				request.setAttribute("value", value);
				RequestDispatcher rd = request
						.getRequestDispatcher("count/listBalance.jsp");
				rd.forward(request, response);
			} else {
				response.sendRedirect("count/cardStateError.jsp");
			}
		}
		else if(action.equals("/repassword")) {
			LoginDAO dao = new LoginDAOImpl();
			long id = Long.parseLong(request.getParameter("id"));
			String newmm = request.getParameter("mm");
			try {
				dao.updataById(id, newmm);
			} catch (Exception e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			response.sendRedirect("main/login.jsp");
		}else if (action.equals("/listStudent")) {
			AdminDAOImpl daoImpl = new AdminDAOImpl();
			List<Student> students = null;
			try {
				students = daoImpl.findStudents();
			} catch (Exception e) {
				e.printStackTrace();
			}
			request.setAttribute("students", students);
			RequestDispatcher rd = request
					.getRequestDispatcher("count/findbook.jsp");
			rd.forward(request, response);
		}else if(action.equals("/showclass")){
			AdminDAOImpl daoImpl = new AdminDAOImpl();
			Map map= new HashMap();
			try {
				map = daoImpl.showClass();
			} catch (Exception e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			request.setAttribute("className", map.get("className"));
			request.setAttribute("num", map.get("num"));
			RequestDispatcher rd = request
					.getRequestDispatcher("admin/showClass.jsp");
			rd.forward(request, response);
		}
	}
}
