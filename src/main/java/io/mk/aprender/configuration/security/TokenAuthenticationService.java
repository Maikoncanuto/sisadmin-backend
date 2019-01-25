package io.mk.aprender.configuration.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.mk.aprender.model.entity.Usuario;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;

public class TokenAuthenticationService {

    static final long EXPIRATION_TIME = 860_000_000;
    static final String SECRET = "MySecret";
    static final String TOKEN_PREFIX = "Bearer";
    static final String HEADER_STRING = "Authorization";

    private TokenAuthenticationService() {
    }

    public static void addAuthentication(HttpServletResponse response, Authentication authentication) {
        final Usuario usuario = (Usuario) authentication.getPrincipal();

        HashMap<String, Object> mapa = new HashMap<>();
        mapa.put("id", usuario.getId());
        mapa.put("username", usuario.getUsername());
        mapa.put("roles", usuario.getAuthorities());

        final String JWT = Jwts.builder()
                .setClaims(mapa)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();

        response.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + JWT);
    }

    public static Authentication getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);

        if (Objects.nonNull(token)) {
            final String user = (String) Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody()
                    .get("username");

            if (Objects.nonNull(user))
                return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
            else
                throw new AccessDeniedException("Acesso negado");
        }

        return null;
    }
}
