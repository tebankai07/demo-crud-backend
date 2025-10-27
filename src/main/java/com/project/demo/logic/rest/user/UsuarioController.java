package com.project.demo.logic.rest.user;

import com.project.demo.logic.entity.user.UserRepository;
import com.project.demo.logic.entity.user.UserSummary;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UserRepository userRepository;

    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @GetMapping
    public ResponseEntity<?> list() {
        var list = userRepository.findAll()
                .stream()
                .map(u -> new UserSummary(
                        u.getId(),
                        u.getName(),
                        u.getLastname(),
                        u.getEmail(),
                        u.getRole() != null ? u.getRole().getName().name() : null
                ))
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }
}
