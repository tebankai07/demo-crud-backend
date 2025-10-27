package com.project.demo.rest.auth;

import com.project.demo.logic.entity.auth.AuthenticationService;
import com.project.demo.logic.entity.auth.JwtService;
import com.project.demo.logic.entity.user.LoginResponse;
import com.project.demo.logic.entity.user.RegisterRequest;
import com.project.demo.logic.entity.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthRestController {

    private final AuthenticationService authenticationService;
    private final JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody User request) {
        var authenticatedUser = authenticationService.authenticate(request);
        var jwtToken = jwtService.generateToken(authenticatedUser);
        var resp = new LoginResponse(jwtToken, authenticatedUser, jwtService.getExpirationTime());
        return ResponseEntity.ok(resp);
    }
    @PostMapping("/signup")
    public ResponseEntity<LoginResponse> register(@RequestBody RegisterRequest request) {
        var user = authenticationService.register(request);
        var token = jwtService.generateToken(user);

        var response = new LoginResponse();
        response.setToken(token);
        response.setAuthUser(user);
        response.setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(response);
    }


}

