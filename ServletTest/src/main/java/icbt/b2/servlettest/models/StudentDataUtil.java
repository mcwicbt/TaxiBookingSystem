package icbt.b2.servlettest.models;

import java.util.ArrayList;
import java.util.List;

import icbt.b2.servlettest.entites.Student;

public class StudentDataUtil {

	public static List<Student> getStudents(){
		List<Student> list = new ArrayList<Student>();
		list.add(new Student("Kamal", "Silva", "kamal@test.com"));
		list.add(new Student("Namal", "Perera", "namal@test.com"));
		list.add(new Student("Sunil", "Mendis", "sunil@test.com"));
		
		return list;
	}
	
}
