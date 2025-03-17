package com.poc.login_with_google_poc;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;


@Component
public class JwtUtil {

    private final String SECRET_KEY = "my-secret-key"; // 🔐 Troque por uma chave segura!

    public String generateToken(String user, String person) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(this.SECRET_KEY);
            return JWT
                    .create()
                    .withIssuer("GENERATED_ISSUER")
                    .withSubject("login")
                    .withClaim("kind", "USER")
                    .withClaim("accepted", true)
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro ao gerar token jwt", exception);
        }
    }

    public Claims extractClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }
}
