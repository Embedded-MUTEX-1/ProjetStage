package fr.greta92.projetstage.service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

    @Value("${application.security.jwt.secret-key}")
    private String secretKey;
    @Value("${application.security.jwt.expiration}")
    private long jwtExpiration;
    private Algorithm algorithm;
    private JWTVerifier verifier;

    @PostConstruct
    public void init()
    {
        algorithm = Algorithm.HMAC256(secretKey);
        verifier = JWT.require(algorithm).withIssuer("Application").build();
    }

    public String generateToken(UserDetails userDetails) {
        return JWT.create()
                .withIssuer("Application")
                .withSubject("User Details")
                .withClaim("mail", userDetails.getUsername())
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + jwtExpiration * 1000L))
                .withJWTId(UUID.randomUUID().toString())
                .sign(algorithm);
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        DecodedJWT decodedJWT = null;
        try {
            decodedJWT = verifier.verify(token);
        } catch (JWTVerificationException e) {
            return false;
        }

        final String username = extractUsername(decodedJWT);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(decodedJWT);
    }

    private String extractUsername(DecodedJWT decodedJWT) {
        Claim claim = decodedJWT.getClaim("mail");
        return claim.asString();
    }
    public String extractUsername(String token) {
        DecodedJWT decodedJWT = null;

        try {
            decodedJWT = verifier.verify(token);
        } catch (JWTVerificationException e) {
            return null;
        }

        Claim claim = decodedJWT.getClaim("mail");
        return claim.asString();
    }

    private boolean isTokenExpired(DecodedJWT decodedJWT) {
        Date date = decodedJWT.getExpiresAt();
        return date.before(new Date());
    }
}
