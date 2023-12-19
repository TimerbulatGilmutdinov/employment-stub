package ru.itis.employmentstub.util.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;
import ru.itis.employmentstub.model.User;


import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@Component
@DependsOn("jwtPublicKeyPuller")
@Slf4j
public class JwtUtil {
    private final JwtKeyHolder keyHolder;
    private final JWTVerifier jwtVerifier;

    public JwtUtil(JwtKeyHolder keyHolder) {
        this.keyHolder = keyHolder;
        this.jwtVerifier = buildJWTVerifier();
    }

    private JWTVerifier buildJWTVerifier() {
        Algorithm algorithm;
        try {
            algorithm = Algorithm.RSA256(getRSAPublicKey(keyHolder.getKey()), null);
        } catch (GeneralSecurityException e) {
            throw new RuntimeException(e);
        }
        return JWT.require(algorithm).build();
    }

    private RSAPublicKey getRSAPublicKey(String stringPublicKey) throws GeneralSecurityException {
        byte[] encoded = Base64.getDecoder().decode(stringPublicKey);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return (RSAPublicKey) kf.generatePublic(new X509EncodedKeySpec(encoded));
    }

    public User parseToken(String token) throws JWTVerificationException{
        DecodedJWT decodedJWT = jwtVerifier.verify(token);
        return User.builder()
                .sub(decodedJWT.getSubject())
                .iat(decodedJWT.getIssuedAt())
                .exp(decodedJWT.getExpiresAt())
                .scope(decodedJWT.getClaim("scope").asString())
                .sessionId(decodedJWT.getClaim("sessionId").asString())
                .sessionHash(decodedJWT.getClaim("sessionHash").asString())
                .allId(decodedJWT.getClaim("allId").asLong())
                .roles(decodedJWT.getClaim("roles").asList(User.Role.class))
                .build();
    }
}
