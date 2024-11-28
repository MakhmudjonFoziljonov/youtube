package com.youtube.util;

import com.youtube.dto.JwtDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil {
    private static final int tokenLiveTime = 1000 * 3600 * 24; // 1-day
    private static final String secretKey = "veryLongSecretmazgillattayevlasharaaxmojonjinnijonsurbetbekkiydirhonuxlatdibekloxovdangasabekochkozjonduxovmashaynikmaydagapchishularnioqiganbolsangizgapyoqaniqsizmazgi";
    private static final long refreshTokenLiveTime = 1000L * 60 * 60 * 24 * 30;

    public static String encode(String username, String role) {
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("username", username);
        extraClaims.put("role", role);

        return Jwts
                .builder()
                .claims(extraClaims)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + tokenLiveTime))
                .signWith(getSignInKey())
                .compact();
    }

    public static JwtDTO decode(String token) {
        Claims claims = Jwts
                .parser()
                .setSigningKey(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        String username = (String) claims.get("username");
        String role = (String) claims.get("role");
        return new JwtDTO(username, role);
    }

    public static String refreshToken(String username, String role) {
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("username", username);
        extraClaims.put("role", role);

        return Jwts
                .builder()
                .claims(extraClaims)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + refreshTokenLiveTime))
                .signWith(getSignInKey())
                .compact();

    }

    private static SecretKey getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
