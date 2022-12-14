package az.abb.jwt.service;

import az.abb.jwt.entity.Student;
import az.abb.jwt.repository.StudentRepsitory;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class StudentService implements IStudentService {
    private final StudentRepsitory studentRepsitory;
    @Override
    public List<Student> getStudents(){
        return studentRepsitory.findAll();
    }

    @Override
    public Student getStudent(String email){
        return studentRepsitory.findStudentByEmail(email);
    }
    @Override
    public Student getStudent(Integer id){
        Student student = studentRepsitory.findStudentByStudentId(id);
        return student;
    }
    @Override
    public boolean createStudent(Student student){
        boolean result = false;
        if (getStudent(student.getEmail())==null){
            studentRepsitory.save(student);
            result = true;
        }
        return result;
    }
}
