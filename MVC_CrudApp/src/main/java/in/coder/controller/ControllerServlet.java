package in.coder.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.coder.dto.Student;
import in.coder.service.IStudentService;
import in.coder.servicefactory.StudentServiceFactory;


@WebServlet(urlPatterns="/controller/*")
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		IStudentService studentService = StudentServiceFactory.getStudentService();
		
		System.out.println("Request URI:: "+request.getRequestURI());
		System.out.println("Path Info:: "+request.getPathInfo());
		
		if(request.getRequestURI().endsWith("addform")) {
			String sname = request.getParameter("sname");
			String sage = request.getParameter("sage");
			String saddress = request.getParameter("saddr");
			
			Student student = new Student();
			
			student.setSname(sname);
			student.setSage(Integer.parseInt(sage));;
			student.setAddress(saddress);
			
			String status = studentService.addStudent(student);
			RequestDispatcher requestDispatcher = null;
			if(status.equals("success")) {
				requestDispatcher = request.getRequestDispatcher("../insertResult.jsp");
				request.setAttribute("status", "success");
				requestDispatcher.forward(request, response);
			}else {
				requestDispatcher = request.getRequestDispatcher("../insertResult.jsp");
				request.setAttribute("status", "success");
				requestDispatcher.forward(request, response);
			}
			
		}
		
		if(request.getRequestURI().endsWith("searchform")) {
			String sid = request.getParameter("sid");
			
			Student student = studentService.serachStudent(Integer.parseInt(sid));
			
			request.setAttribute("student", student);
			
			RequestDispatcher requestDispatcher = null;
			requestDispatcher = request.getRequestDispatcher("../searchDisplay.jsp");
			requestDispatcher.forward(request, response);
		}
		
		if(request.getRequestURI().endsWith("editform")) {
			String sid = request.getParameter("sid");
			Student student = studentService.serachStudent(Integer.parseInt(sid));
            request.setAttribute("student", student);
			
			RequestDispatcher requestDispatcher = null;
			requestDispatcher = request.getRequestDispatcher("../updateForm.jsp");
			requestDispatcher.forward(request, response);
			
		}
		
		if(request.getRequestURI().endsWith("updateRecord")) {
			String sid = request.getParameter("sid");
			String sname = request.getParameter("sname");
			String sage = request.getParameter("sage");
			String saddress = request.getParameter("saddr");
			
			Student student = new Student();
			student.setSid(Integer.parseInt(sid));
			student.setSname(sname);
			student.setSage(Integer.parseInt(sage));
			student.setAddress(saddress);
			
			String status = studentService.updateStudent(student);
			RequestDispatcher requestDisp = null;
			if(status.equals("success")) {
				request.setAttribute("status", "success");
				requestDisp = request.getRequestDispatcher("../../updateResult.jsp");
				requestDisp.forward(request, response);
				
			}else {
				request.setAttribute("status", "failure");
				requestDisp = request.getRequestDispatcher("../../updateResult.jsp");
				requestDisp.forward(request, response);
			}
			
			
			
		}
		
		if(request.getRequestURI().endsWith("deleteform")) {
			String sid = request.getParameter("sid");
			
			String status = studentService.deleteStudent(Integer.parseInt(sid));
			RequestDispatcher requestDispatcher = null;
			if(status.equals("success")) {
				request.setAttribute("status", "success");
				 requestDispatcher = request.getRequestDispatcher("../deleteResult.jsp");
				 requestDispatcher.forward(request, response);
			}else if(status.equals("failure")) {
				request.setAttribute("status", "failure");
				requestDispatcher = request.getRequestDispatcher("../deleteResult.jsp");
				requestDispatcher.forward(request, response);
			}else {
				request.setAttribute("status", "not found");
				requestDispatcher = request.getRequestDispatcher("../deleteResult.jsp");
				requestDispatcher.forward(request, response);
			}
		}
		
	}

}
