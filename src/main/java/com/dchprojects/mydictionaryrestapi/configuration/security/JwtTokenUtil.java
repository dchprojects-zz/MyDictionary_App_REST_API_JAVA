package com.dchprojects.mydictionaryrestapi.configuration.security;

import com.dchprojects.mydictionaryrestapi.domain.dto.JwtTokenResponse;
import com.dchprojects.mydictionaryrestapi.domain.entity.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.stereotype.Component;

import java.util.Date;

import static java.lang.String.format;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtTokenUtil {

    private final String jwtSecret = "jwtSecretKey.app$8089_jwtExpirationInMs_3600000";
    private final String jwtIssuer = "com.dchprojects.mydictionaryrestapi";

    public JwtTokenResponse generateAccessToken(Long userId, String nickname) {

        DateTime currentDateTime = new DateTime(DateTimeZone.UTC);
        // JWT Expiration - 1 Hour
        DateTime accessTokenExpirationDate = currentDateTime.plusHours(1);

        String accessToken = Jwts.builder()
                .setSubject(format("%s,%s", userId, nickname))
                .setIssuer(jwtIssuer)
                .setIssuedAt(currentDateTime.toDate())
                .setExpiration(accessTokenExpirationDate.toDate())
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();

        return new JwtTokenResponse(accessToken, accessTokenExpirationDate);
    }

    public String getNickname(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject().split(",")[1];
    }

    public Date getExpirationDate(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return claims.getExpiration();
    }

    public Boolean validate(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (SignatureException ex) {
            log.error("Invalid JWT signature - {}", ex.getMessage());
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT token - {}", ex.getMessage());
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token - {}", ex.getMessage());
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token - {}", ex.getMessage());
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty - {}", ex.getMessage());
        }
        return false;
    }

}