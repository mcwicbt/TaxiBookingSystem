package icbt.b2.servlettest.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import icbt.b2.servlettest.entites.Student;

public class StudentDao {
		
	public static List<Student> getStudents(DataSource dataSource){
		Connection con = null;
		Statement stmt = null;
		ResultSet rset = null; 
		
		List<Student> students = new ArrayList<>();
		
		try {
			con = dataSource.getConnection();
			
			String sql = "SELECT * FROM student";
			stmt = con.createStatement();
			
			rset = stmt.executeQuery(sql);
			
			while(rset.next()) {
				int id = rset.getInt("id");
				String firstName = rset.getString("first_name");	
				String lastName = rset.getString("last_name");	
				String email = rset.getString("email");	
				
				Student student = new Student(id, firstName, lastName, email);
				students.add(student);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(con, stmt, rset);
		}
		
		return students;
	}
	
	private static void close(Connection con, Statement stmt, ResultSet rset) {
		try {
			if (rset != null) {
				rset.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if(con != null) {
				con.close();
			}				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void addStudent(Student student, DataSource dataSource) {
		Connection con = null;
		PreparedStatement stmt = null;
		
		try {
			con = dataSource.getConnection();
			String sql = "INSERT INTO student (first_name, last_name, email) VALUES (?, ?, ?)";
			stmt = con.prepareStatement(sql);
			
			stmt.setString(1, student.getFirstName());
			stmt.setString(2, student.getLastName());
			stmt.setString(3, student.getEmail());
		
			stmt.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(con, stmt, null);
		}
	}

	public static Student getStudent(int studentId, DataSource dataSource) {
		Student student = null;
		
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rset = null; 
		
		try {
			con = dataSource.getConnection();
			
			String sql = "SELECT * FROM student WHERE id=?";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, studentId);
			
			rset = stmt.executeQuery();
			if (rset.next()) {
				int id = rset.getInt("id");
				String firstName = rset.getString("first_name");	
				String lastName = rset.getString("last_name");	
				String email = rset.getString("email");	
				
				student = new Student(id, firstName, lastName, email);
				
			}else {
				
			}		
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(con, stmt, rset);
		}
				
		return student;
	}

	public static void updateStudent(Student student, DataSource dataSource) {
		Connection con = null;
		PreparedStatement stmt = null;
		
		try {
			con = dataSource.getConnection();
			String sql = "UPDATE student SET first_name=?, last_name=?, email=? WHERE id=?";
			stmt = con.prepareStatement(sql);
			
			stmt.setString(1, student.getFirstName());
			stmt.setString(2, student.getLastName());
			stmt.setString(3, student.getEmail());
			stmt.setInt(4, student.getId());
		
			stmt.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(con, stmt, null);
		}
		
	}

	public static void deleteStudent(int studentId, DataSource dataSource) {
		Connection con = null;
		PreparedStatement stmt = null;
		
		try {
			con = dataSource.getConnection();
			String sql = "DELETE FROM student where id=?";
			stmt = con.prepareStatement(sql);
		
			stmt.setInt(1, studentId);
		
			stmt.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(con, stmt, null);
		}
		
	}
}
