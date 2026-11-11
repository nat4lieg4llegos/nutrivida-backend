package cl.duoc.nutrivida_backend.controller;

import cl.duoc.nutrivida_backend.model.LoginRequest;
import cl.duoc.nutrivida_backend.security.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Tag(name = "Autenticación", description = "Inicio de sesión del administrador con token JWT")
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private JwtUtil jwtUtil;

    @Value("${admin.usuario}")
    private String adminUsuario;

    @Value("${admin.password}")
    private String adminPassword;

    @Operation(summary = "Inicia sesión", description = "Valida usuario y contraseña del administrador y retorna un token JWT")
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginRequest login) {
        if (adminUsuario.equals(login.getUsuario()) && adminPassword.equals(login.getPassword())) {
            String token = jwtUtil.generarToken(login.getUsuario());
            return ResponseEntity.ok(Map.of("token", token)); // 200
        } else
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); // 401
    }
}