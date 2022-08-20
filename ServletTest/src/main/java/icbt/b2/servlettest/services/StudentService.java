package icbt.b2.servlettest.services;

import java.util.List;

import javax.sql.DataSource;

import icbt.b2.servlettest.entites.Student;
import icbt.b2.servlettest.models.StudentDao;

public class StudentService {

	
	public static List<Student> getStudents(DataSource dataSource) {
		List<Student> students = StudentDao.getStudents(dataSource);		
		return students;		
	}
	
	public static void addStudent(Student student, DataSource dataSource) {
		StudentDao.addStudent(student, dataSource);
	}
	
	public static void updateStudent(Student student, DataSource dataSource) {
		StudentDao.updateStudent(student, dataSource);
	}
	
	public static void deleteStudent(int studentId, DataSource dataSource) {
		StudentDao.deleteStudent(studentId, dataSource);
	}
	
	public static Student getStudent(int studentId, DataSource dataSource) {
		return StudentDao.getStudent(studentId, dataSource);
	}
}
