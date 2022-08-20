package icbt.b2.servlettest.controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import icbt.b2.servlettest.entites.Student;
import icbt.b2.servlettest.models.StudentDataUtil;
import icbt.b2.servlettest.services.StudentService;
import icbt.b2.servlettest.utils.EntityValidator;
import icbt.b2.servlettest.models.StudentDao;

/**
 * Servlet implementation class MvcDemoServlet
 */

//Controller
@WebServlet("/MvcDemoServlet")
public class MvcDemoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DataSource dataSource;
	
	@Override
	public void init() throws ServletException {
		super.init();
		
		try {
			InitialContext ic = new InitialContext();
			Context xmlContext = (Context) ic.lookup("java:comp/env");	
			dataSource = (DataSource) xmlContext.lookup("jdbc/student_application");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
    /**
     * 
     * Default constructor. 
     */
    public MvcDemoServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String command = request.getParameter("command");
		if (command == null) {
			showStudentList(request, response);
		}
		
		if(command.equals("SHOW_UPDATE")) {
			showUpdateStudent(request, response);
		}else if(command.equals("DELETE")) {
			deleteStudent(request, response);
		}
		else {
			showStudentList(request, response);
		}
		
	}
	
	private void deleteStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		String studentIdStr = request.getParameter("studentId");
		int studentId = Integer.parseInt(studentIdStr);
		
		StudentService.deleteStudent(studentId, dataSource);
		showStudentList(request, response);
	}

	private void showStudentList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Student> students = StudentService.getStudents(dataSource);		
		request.setAttribute("student_list", students);	
		request.getRequestDispatcher("/student-list.jsp").forward(request, response);
	}
	
	private void showUpdateStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String studentIdStr = request.getParameter("studentId");
		int studentId = Integer.parseInt(studentIdStr);
		
		Student student = StudentService.getStudent(studentId, dataSource);
		request.setAttribute("student", student);
		
		request.getRequestDispatcher("/update-student.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String command = request.getParameter("command");
		if (command == null) {
			showStudentList(request, response);
		}
		
		if(command.equals("ADD")) {
			addStudent(request, response);
		}else if (command.equals("UPDATE")) {
			updateStudent(request, response);
		}		
	}
	
	private void addStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		
		Student student = new Student(firstName, lastName, email);
		
		EntityValidator<Student> validator = new EntityValidator<Student>();
		String errors = validator.validate(student);
		
		if(!errors.isEmpty()) {
			request.setAttribute("student", student);
			request.setAttribute("errors", errors);
			request.getRequestDispatcher("/add-student.jsp").forward(request, response);
		}else {
			StudentService.addStudent(student, dataSource);
			showStudentList(request, response);
		}
	}
	
	private void updateStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String studentIdStr = request.getParameter("studentId");
		int studentId = Integer.parseInt(studentIdStr);
		
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		
		Student student = new Student(studentId, firstName, lastName, email);
		
		EntityValidator<Student> validator = new EntityValidator<Student>();
		String errors = validator.validate(student);
		
		if(!errors.isEmpty()) {
			request.setAttribute("student", student);
			request.setAttribute("errors", errors);
			request.getRequestDispatcher("/update-student.jsp").forward(request, response);
		}else {
			StudentService.updateStudent(student, dataSource);
			showStudentList(request, response);
		}
	}

}
