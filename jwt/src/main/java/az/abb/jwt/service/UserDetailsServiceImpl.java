package az.abb.jwt.service;

import az.abb.jwt.entity.Student;
import az.abb.jwt.security.JwtUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final IStudentService studentService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Student user = studentService.getStudent(username);
        return JwtUserDetails.create(user);
    }

    public UserDetails loadUserById(Integer userId) {
        Student user = studentService.getStudent(userId);
        return JwtUserDetails.create(user);
    }

}
