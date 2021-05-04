package com.quiz.controller.auth;

import com.quiz.config.security.jwt.JwtProvider;
import com.quiz.entities.User;
import com.quiz.exception.UserNotFoundException;
import com.quiz.exception.ValidationException;
import com.quiz.service.interf.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Log
@RestController
@AllArgsConstructor
public class AuthController {
    private final UserService userService;
    private final JwtProvider jwtProvider;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody  RegistrationRequest registrationRequest) throws ValidationException {
        User u = new User();
        log.info("registration request from /register : " + registrationRequest.toString());

        u.setPassword(registrationRequest.getPassword());
        u.setEmail(registrationRequest.getLogin());
        u.setFirstName(registrationRequest.getFirstName());
        u.setLastName(registrationRequest.getLastName());

        if (registrationRequest.getRoles() != null) {
            u.setRoles(registrationRequest.getRoles());
        }

        userService.addUser(u);
        return ResponseEntity.ok("registration successful");
    }

    @PostMapping("/auth")
    public ResponseEntity<AuthResponse> auth(@Valid @RequestBody AuthRequest request) throws UserNotFoundException {
        String token = null;
        log.info(request.toString());
        User user = userService.findUserByEmailAndPassword(request.getLogin(), request.getPassword());
        if (user != null) {
            token = jwtProvider.generateToken(user.getEmail(),user.getRoles());
        }
        log.info("generated token is : " + token);

        return new ResponseEntity<>(new AuthResponse(token),HttpStatus.OK);
    }
}
