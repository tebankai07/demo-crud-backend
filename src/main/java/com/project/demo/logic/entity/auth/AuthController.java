//package com.project.demo.logic.entity.auth;
//
//import com.project.demo.logic.entity.user.LoginResponse;
//import com.project.demo.logic.entity.user.User;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/auth")
//@RequiredArgsConstructor
//public class AuthController {
//
//    private final AuthenticationService authenticationService;
//    private final JwtService jwtService;
//
//    @PostMapping("/login")
//    public ResponseEntity<LoginResponse> login(@RequestBody User request) {
//        // Autentica usando email+password
//        User authenticatedUser = authenticationService.authenticate(request);
//
//        // Genera el JWT y arma el DTO NUEVO (token, username, roles, expiresIn)
//        String jwtToken = jwtService.generateToken(authenticatedUser);
//        LoginResponse loginResponse =
//                new LoginResponse(jwtToken, authenticatedUser, jwtService.getExpirationTime());
//
//        return ResponseEntity.ok(loginResponse);
//    }
//
//    /*
//     TODO: Agregar /signup cuando tengas RegisterRequest y el método register(...)
//     @PostMapping("/signup")
//     public ResponseEntity<LoginResponse> signup(@RequestBody RegisterRequest request) { ... }
//    */
//}
