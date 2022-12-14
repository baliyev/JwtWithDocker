package az.abb.jwt.repository;

import az.abb.jwt.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepsitory extends JpaRepository<Student, Integer> {
    Student findStudentByEmail(String email);
    Student findStudentByEmailAndPassword(String email,String password);

    Student findStudentByStudentId(Integer studentId);
}
