package com.online.shop.ecommerceshop.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.online.shop.ecommerceshop.domain.UserPrincipal;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.online.shop.ecommerceshop.constant.SecurityConstant.*;
import static java.time.Instant.now;
import static java.util.Arrays.stream;
import static java.util.Date.from;
import static java.util.stream.Collectors.toList;

@Component
public class JwtTokenProvider {

    @Value("${jwt-secret-key}")
    private String secret;

    //wygenerowanie tokena od razu po zalogowaniu (after user has been authenticated)
    public String generateJwtToken(UserPrincipal userPrincipal) {
        //trzeba sparsowac z user principal claimsy i podac do jwt tokena
        //aby stworzyc jwt token trzeba miec claimsy
        String[] claims = getClaimsFromUser(userPrincipal);

        return JWT.create()
                .withIssuer(TOKEN_ISSUER)
                .withAudience(TOKEN_AUDIENCE)
                .withIssuedAt(from(now()))
                .withSubject(userPrincipal.getUsername())
                .withArrayClaim(AUTHORITIES, claims)
                .withExpiresAt(from(now().plusMillis(EXPIRATION_TIME)))
                .sign(Algorithm.HMAC512(secret.getBytes()));
    }

    public List<GrantedAuthority> getAuthorities(String token){
       String[] claims =  getClaimsFromToken(token);
       return stream(claims).map(SimpleGrantedAuthority::new).collect(toList());
    }

    public String getSubject(String token) {
        JWTVerifier jwtVerifier = getJWTVerifier();
        return jwtVerifier.verify(token).getSubject();
    }

    public boolean isTokenValid(String username, String token) {
        JWTVerifier jwtVerifier = getJWTVerifier();
        return StringUtils.isNotBlank(username) && !isTokenExpired(jwtVerifier, token);
    }

    public Authentication getAuthentication(String username, List<GrantedAuthority> authorities, HttpServletRequest request) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(username, null, authorities);

        usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        return usernamePasswordAuthenticationToken;
    }

    private boolean isTokenExpired(JWTVerifier jwtVerifier, String token) {
        Date expirationDate = jwtVerifier.verify(token).getExpiresAt();
        return expirationDate.before(from(now()));
    }


    private String[] getClaimsFromToken(String token) {
       JWTVerifier jwtVerifier = getJWTVerifier();
        return jwtVerifier.verify(token).getClaim(AUTHORITIES).asArray(String.class);
    }

    private String[] getClaimsFromUser(UserPrincipal userPrincipal) {
       return userPrincipal.getAuthorities()
               .stream()
               .map(GrantedAuthority::getAuthority)
               .toArray(String[]::new);
    }

    private JWTVerifier getJWTVerifier(){
        JWTVerifier jwtVerifier;

        try {
            Algorithm algorithm = Algorithm.HMAC512(secret);
            jwtVerifier = JWT.require(algorithm).withIssuer(TOKEN_ISSUER).build();
        }
        catch (JWTVerificationException exception) {
            throw new JWTVerificationException(TOKEN_CANNOT_BE_VERIFIED);
        }

        return jwtVerifier;
    }


}
