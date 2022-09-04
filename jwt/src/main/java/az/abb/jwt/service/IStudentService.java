package az.abb.jwt.service;

import az.abb.jwt.entity.Student;
import java.util.List;

public interface IStudentService {

    public List<Student> getStudents();
    public Student getStudent(String email);
    public Student getStudent(Integer id);
    public boolean createStudent(Student student);



}
