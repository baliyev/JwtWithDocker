package az.abb.jwt.entity;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "abb_students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Integer studentId;

    private String studentName;

    @Column(name = "student_email")
    private String email;

    @Column(name = "student_password")
    private String password;

    @Column(name = "student_role")
    private String role;

}
