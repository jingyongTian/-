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
		// ���÷�������ҳ�����ݽ��������ݱ����ʽ
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		// ��ȡsession����
		HttpSession session = request.getSession();
		// ��ȡ����·��������ָ��Ϊ�򵥵���������
		String uri = request.getRequestURI();
		String action = uri.substring(uri.lastIndexOf("/"), uri
				.lastIndexOf("."));
		System.out.println(action);
		if (action.equals("/login")) {
			// ��ȡҳ��������û�����
			request.setCharacterEncoding("utf-8");
			// ��ȡ�û��ڵ�½�����������ֵ �����û��������룬��ɫѡ��
			long id = Long.parseLong(request.getParameter("id"));
			int type = Integer.parseInt(request.getParameter("type"));
			String pwd = request.getParameter("mm");
			// �������ݿ� ������������Ӧ�ļ�¼�Ƿ����
			LoginDAO dao = new LoginDAOImpl();
			Login login = dao.findById(id);

			// ������ݿ����Ľ�ɫ���û�ѡ��Ľ�ɫ��һ��,���ص���½�������
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
			// ��ȡ������������� ʳƷ���
			long foodid = Long.parseLong(request.getParameter("id"));
			// ��ȡ�û���id
			Login login = (Login) session.getAttribute("login");
			/**
			 * ��ȡ���ݿ��е���ص���ֵ Ҫ�����Ȳ鿴���ϵ�����Ƿ���ڻ��ߵ���ʳƷ���� ��������ҪCard�����FoodItem����
			 * 
			 * �Ƚ�������������������ӵ�ʳƷ��¼���� ������͸�����ʾ
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
			// ��ʾ����ʳ�õ����Ѽ�¼
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
			// �������ַȡ������ֵ----bookid
			long bookid = Long.parseLong(request.getParameter("id"));
			// ����bookid,�ҵ��Ȿ��
			// ���Ȿ��軹״̬��Ϊ�����
			StudentDAOImpl daoImpl = new StudentDAOImpl();
			Book book = null;
			try {
				book = daoImpl.findBookById(bookid);
			} catch (Exception e) {
				e.printStackTrace();
			}
			// �ж��Ȿ���Ƿ�ȫ�����
			if (book.getBookstate() <= 0) {
				response.sendRedirect("student/lenderror.jsp");
			} else {
				// ��ȡ��¼�Ķ���,����ȡ����
				Login login = (Login) session.getAttribute("login");
				System.out.println(login.getId());
				long cardid = login.getId();
				BookRecord bookRecord = new BookRecord();
				bookRecord.setBookid(bookid);
				bookRecord.setCardid(cardid);
				//�ж��Ƿ������鼮
				boolean b=false;
				try {
					b = daoImpl.findBookById(cardid, bookid);
				} catch (Exception e) {
					// TODO �Զ����ɵ� catch ��
					e.printStackTrace();
				}
				if(b) {
					//����������ʶ������ǰ��ҳ��
					request.setAttribute("choice", 1);
				}
				else {
					try {
						//δ�������ͼ�����ͼ����ļ�¼
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
				// TODO �Զ����ɵ� catch ��
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
				// ��ȡҳ�洫��������
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
				// �����µ�ѧ��
				s = new Student();
				Login login = new Login(studentid,mm,1);
				// ��ѧ���ĸ������Ը�ֵ
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
				// ��ѧ����Ϣ�������ݿ�
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
			// ���ҵ����еĲ�Ʒ
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
				// ת����ʳƷ����ҳ��
				request.setAttribute("foodItems", foodItems);
				RequestDispatcher rd = request
						.getRequestDispatcher("admin/manageFoodItem.jsp");
				rd.forward(request, response);
			}

		} else if (action.equals("/addFoodItem")) {
			// ��ȡ��������
			long foodid = Long.parseLong(request.getParameter("foodid"));
			String foodname = request.getParameter("foodname");
			double danjia = Double.parseDouble(request.getParameter("danjia"));
			int chuangkou = Integer.parseInt(request.getParameter("chuangkou"));
			// �ж��Ƿ����
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
				// ��װ��ΪFoodItem����
				f = new FoodItem();
				f.setChuangkou(chuangkou);
				f.setDanjia(danjia);
				f.setFoodid(foodid);
				f.setFoodname(foodname);
				// ��������
				try {
					dao.addFoodItem(f);
				} catch (Exception e) {
					e.printStackTrace();
				}
				response.sendRedirect("foodItemManage.do");
			}

		} else if (action.equals("/delFoodItem")) {
			long foodid = Long.parseLong(request.getParameter("id"));
			// ����ʳƷ���ɾ���������
			AdminDAO dao = new AdminDAOImpl();
			try {
				dao.delFoodItemById(foodid);
			} catch (Exception e) {
				e.printStackTrace();
			}			 
			response.sendRedirect("foodItemManage.do");
		} else if (action.equals("/loadFoodItem")) {
			long foodid = Long.parseLong(request.getParameter("id"));
			// ����ʳƷ����ҵ�������¼ ��ת����ҳ��
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
			// ��ȡ������
			long foodid = Long.parseLong(request.getParameter("foodid"));
			String foodname = request.getParameter("foodname");
			double danjia = Double.parseDouble(request.getParameter("danjia"));
			int chuangkou = Integer.parseInt(request.getParameter("chuangkou"));
			// ����ʳƷ��Ų����������
			AdminDAO dao = new AdminDAOImpl();
			FoodItem f = null;
			try {
				f = dao.findFoodItemById(foodid);
			} catch (Exception e) {
				e.printStackTrace();
			}
			// ΪFoodItem��������
			f.setChuangkou(chuangkou);
			f.setDanjia(danjia);
			f.setFoodid(foodid);
			f.setFoodname(foodname);
			// ����ҵ���߼������޸����ݿ�������
			try {
				dao.updateFoodItem(f);
			} catch (Exception e) {
				e.printStackTrace();
			}
			response.sendRedirect("foodItemManage.do");
			
			
		} else if (action.equals("/bookManage")) {
			// ���ҵ����е�ͼ��
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
				// ת����ͼ�����ҳ����
				request.setAttribute("books", books);
				RequestDispatcher rd = request
						.getRequestDispatcher("admin/manageBook.jsp");
				rd.forward(request, response);
			}

		} else if (action.equals("/addBook")) {
			// ��ȡ����Ϣ
			long bookid = Long.parseLong(request.getParameter("bookid"));
			String bookname = request.getParameter("bookname");
			String chubanshe = request.getParameter("chubanshe");
			String zuozhe = request.getParameter("zuozhe");
			String qixian = request.getParameter("qixian");
			Integer bookstate = Integer.parseInt(request.getParameter("bookstate"));
			// ��ѯ���ݿ⿴�Ƿ��ظ�
			Book b = null;
			AdminDAO dao = new AdminDAOImpl();
			try {
				b = dao.findBookById(bookid);
				System.out.println(b);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (b == null) {
				// ��װ��ΪBook����
				b = new Book();
				b.setBookid(bookid);
				b.setBookname(bookname);
				b.setBookstate(bookstate);
				b.setChubanshe(chubanshe);
				b.setQixian(qixian);
				b.setZuozhe(zuozhe);
				// ����ҵ���߼���������
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
			// ����ͼ�����ҵ�������¼
			AdminDAO dao = new AdminDAOImpl();
			Book b = null;
			try {
				b = dao.findBookById(bookid);
			} catch (Exception e) {
				e.printStackTrace();
			}
			// ת�����޸Ľ���
			request.setAttribute("b", b);
			RequestDispatcher rd = request
					.getRequestDispatcher("admin/loadBook.jsp");
			rd.forward(request, response);
		} else if (action.equals("/updateBook")) {
			// ��ȡ����Ϣ
			long bookid = Long.parseLong(request.getParameter("bookid"));
			String bookname = request.getParameter("bookname");
			String chubanshe = request.getParameter("chubanshe");
			String zuozhe = request.getParameter("zuozhe");
			String qixian = request.getParameter("qixian");
			Integer bookstate = Integer.parseInt(request.getParameter("bookstate"));
			// ����ͼ���Ų�ѯ����
			Book b = null;
			AdminDAO dao = new AdminDAOImpl();
			try {
				b = dao.findBookById(bookid);
			} catch (Exception e) {
				e.printStackTrace();
			}
			// ����Book���������ֵ
			b.setBookname(bookname);
			b.setBookstate(bookstate);
			b.setChubanshe(chubanshe);
			b.setQixian(qixian);
			b.setZuozhe(zuozhe);
			// ����ҵ���߼���������
			try {
				dao.updateBook(b);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			response.sendRedirect("bookManage.do");

		} else if (action.equals("/listCards")) {
			// �������ݿ� �������е�һ��ͨ��¼
			AdminDAOImpl daoImpl = new AdminDAOImpl();
			List<Card> cards = null;
			try {
				cards = daoImpl.findCrads();
			} catch (Exception e) {
				e.printStackTrace();
			}
			// ���鵽�ļ�¼ת������ʾҳ��
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
			// �����ַ�����
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("text/html;charset=utf-8");
			// ��ȡҳ������Ŀ���
			long cardid = Long.parseLong(request.getParameter("cardid"));
			StudentDAOImpl daoImpl = new StudentDAOImpl();
			/**
			 * �ж���������Ƿ���� ���ڣ��������� �����ڣ��ٴ�������װ��Ϊcard���� �����浽���ݿ�
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
				// ��ȡҳ�洫�����ֵ
				String mm = request.getParameter("mm");
				String username = request.getParameter("username");
				double balance = Double.parseDouble(request
						.getParameter("balance"));
				boolean cardstate = Boolean.parseBoolean(request
						.getParameter("cardstate"));
				long studentid = Long.parseLong(request
						.getParameter("studentid"));
				// ��װΪcard����
				card = new Card();
				card.setCardid(cardid);
				card.setBalance(balance);
				card.setCardstate(cardstate);
				card.setMm(mm);
				card.setStudentid(studentid);
				card.setUsername(username);
				// ���浽���ݿ�
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
			// ��������Ŀ����Ƿ���ȷ
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
			// ��ȡ����Ŀ��źͽ��
			long cardid = Long.parseLong(request.getParameter("cardid"));
			double value = Double.parseDouble(request.getParameter("value"));
			/**
			 * �������ݴ����߼� �鿴����״̬�Ƿ�Ϊ���� ���ó�ֵ ������ת������һ��ҳ����ʾ
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
				// �����ſ�����Ϣ�ͳ�ֵ��ת����ҳ��
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
				// TODO �Զ����ɵ� catch ��
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
				// TODO �Զ����ɵ� catch ��
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
