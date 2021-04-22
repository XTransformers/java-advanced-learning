package com.transformers.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.Base64Codec;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestDemo {

    @Test
    public void createJwt() {
        JwtBuilder jwtBuilder = Jwts.builder()
                .setId("id")
                .setSubject("subject")
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, "salt");
        String token = jwtBuilder.compact();
        System.out.println(token);

        String[] split = token.split("\\.");
        System.out.println(Base64Codec.BASE64.decodeToString(split[0]));
        System.out.println(Base64Codec.BASE64.decodeToString(split[1]));
        System.out.println(Base64Codec.BASE64.decodeToString(split[2]));
        /**
         * eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJpZCIsInN1YiI6InN1YmplY3QiLCJpYXQiOjE2MTkwMTQ1ODZ9.FSEEQviOqeRi-krKwz6QJC1JUZLrNnhwS7qrQc5uUXY
         * {"alg":"HS256"}
         * {"jti":"id","sub":"subject","iat":1619014586}
         * !B����b���Ϥ	  RTd�͞��s��
         */
    }

    @Test
    public void parseJwt() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJpZCIsInN1YiI6InN1YmplY3QiLCJpYXQiOjE2MTkwMTQ1ODZ9.FSEEQviOqeRi-krKwz6QJC1JUZLrNnhwS7qrQc5uUXY";
        Claims claims = Jwts.parser()
                .setSigningKey("salt")
                .parseClaimsJws(token)
                .getBody();
        System.out.println("id:" + claims.getId());
        System.out.println("subject:" + claims.getSubject());
        System.out.println("issuedAt:" + claims.getIssuedAt());
        /**
         * id:id
         * subject:subject
         * issuedAt:Wed Apr 21 22:16:26 CST 2021
         */
    }

    @Test
    public void createTokenHasExpiration() {
        long now = System.currentTimeMillis();
        long exp = now + 60 * 1000;
        JwtBuilder builder = Jwts.builder()
                .setId("id")
                .setSubject("subject")
                .setIssuedAt(new Date())
                .setExpiration(new Date(exp))
                .signWith(SignatureAlgorithm.HS256, "salt");
        String token = builder.compact();
        System.out.println(token);
    }

    @Test
    public void parseTokenHasExpiration() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJpZCIsInN1YiI6InN1YmplY3QiLCJpYXQiOjE2MTkwMTY3NDgsImV4cCI6MTYxOTAxNjgwOH0.KA6KZ09_xHBzuECZ_oXYDTLAmUHkYxGFItusSWt_vDI";
        Claims claims = Jwts.parser()
                .setSigningKey("salt")
                .parseClaimsJws(token)
                .getBody();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("current time : " + sdf.format(new Date()));
        System.out.println("issued  time : " + sdf.format(claims.getIssuedAt()));
        System.out.println("expire  time : " + sdf.format(claims.getExpiration()));

        /**
         * current time : 2021-04-21 22:52:57
         * issued  time : 2021-04-21 22:52:28
         * expire  time : 2021-04-21 22:53:28
         *
         * io.jsonwebtoken.ExpiredJwtException: JWT expired at 2021-04-21T22:53:28Z. Current time: 2021-04-21T22:53:32Z, a difference of 4181 milliseconds.  Allowed clock skew: 0 milliseconds.
         */
    }

    // 自定义声明
    @Test
    public void createTokenWithClaims() {
        JwtBuilder builder = Jwts.builder()
                .setId("id")
                .setSubject("subject")
                .setIssuedAt(new Date())
                .claim("roles", "admin,root")
                .claim("userId", 1)
                .signWith(SignatureAlgorithm.HS256, "salt");
        String token = builder.compact();
        System.out.println(token);

        String[] split = token.split("\\.");
        System.out.println(Base64Codec.BASE64.decodeToString(split[0]));
        System.out.println(Base64Codec.BASE64.decodeToString(split[1]));

        /**
         * eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJpZCIsInN1YiI6InN1YmplY3QiLCJpYXQiOjE2MTkwMTczNjgsInJvbGVzIjoiYWRtaW4scm9vdCIsInVzZXJJZCI6MX0.tMALNePHUKqe4JfrKWDcJsg3qYTu8oodZb8GNRxmh8Y
         * {"alg":"HS256"}
         * {"jti":"id","sub":"subject","iat":1619017368,"roles":"admin,root","userId":
         */
    }

    @Test
    public void parseTokenWithClaims() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJpZCIsInN1YiI6InN1YmplY3QiLCJpYXQiOjE2MTkwMTczNjgsInJvbGVzIjoiYWRtaW4scm9vdCIsInVzZXJJZCI6MX0.tMALNePHUKqe4JfrKWDcJsg3qYTu8oodZb8GNRxmh8Y";
        Claims claims = Jwts.parser()
                .setSigningKey("salt")
                .parseClaimsJws(token)
                .getBody();
        System.out.println("roles : " + claims.get("roles"));
        System.out.println("userId : " + claims.get("userId"));
    }
}
