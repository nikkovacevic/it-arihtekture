package ita.ms.utils;

import io.jsonwebtoken.Jwts;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class JwtUtilService {
    private static String secret = "nikovaskrivnost";

    public static boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(secret.getBytes()).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
