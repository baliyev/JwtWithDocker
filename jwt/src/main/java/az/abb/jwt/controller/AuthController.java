package az.abb.jwt.controller;

import az.abb.jwt.dto.LoginUserDto;
import az.abb.jwt.entity.Student;
import az.abb.jwt.security.JwtTokenProvider;
import az.abb.jwt.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final PasswordEncoder passwordEncoder;
    private final StudentService studentService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/login")
    public String login(@RequestBody LoginUserDto user){
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword());
        Authentication auth = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(auth);
        String jwtToken = jwtTokenProvider.generateJwtToken(auth);
        return "Bearer " + jwtToken;
    }


    @PostMapping("/register")
    public ResponseEntity createStudent(@RequestBody Student student){
        student.setPassword(passwordEncoder.encode(student.getPassword()));
        if (studentService.createStudent(student)){
            return ResponseEntity.ok("The user has been successfully created.");
        }


        return ResponseEntity.status(HttpStatus.CONFLICT.value())
                .body("The user could not be created." +
                        "\nThis email address has already been registered.");
    }




}
