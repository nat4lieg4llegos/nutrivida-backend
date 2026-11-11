package cl.duoc.nutrivida_backend.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiracion}")
    private long expiracion;

    private SecretKey getClave() {
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public String generarToken(String usuario) {
        return Jwts.builder()
                .subject(usuario)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expiracion))
                .signWith(getClave())
                .compact();
    }

    // Si el token es falso o está vencido, lanza una excepción
    public String obtenerUsuario(String token) {
        return Jwts.parser()
                .verifyWith(getClave())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }
}