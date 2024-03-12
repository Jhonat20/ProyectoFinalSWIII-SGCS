/**
 * @file: JwtService.java
 * @created: [Fecha de creación]
 */

package CGCS.COM.ProyectoFinalSWIIISGCS.SecurityJWT;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Servicio para la generación y validación de tokens JWT.
 */
@Service
public class JwtService {

    // Clave secreta para firmar y verificar el token (debe ser almacenada de manera segura)
    public static final String SECRET_KEY = "586E3272357538782F413F442847284862506553685668597033733676397924";

    /**
     * Genera un token JWT para el usuario.
     *
     * @param user Usuario autenticado.
     * @return Token JWT.
     */
    public String getToken(UserDetails user) {
        return getToken(new HashMap<>(), user);
    }

    /**
     * Genera un token JWT con reclamaciones adicionales para el usuario.
     *
     * @param extraClaims Reclamaciones adicionales.
     * @param user        Usuario autenticado.
     * @return Token JWT.
     */
    private String getToken(Map<String, Object> extraClaims, UserDetails user) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Obtiene la clave secreta para firmar y verificar el token.
     *
     * @return Clave secreta.
     */
    private Key getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * Verifica si un token JWT es válido para el usuario.
     *
     * @param token       Token JWT.
     * @param userDetails Detalles del usuario.
     * @return `true` si el token es válido, de lo contrario, `false`.
     */
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    /**
     * Obtiene el nombre de usuario desde un token JWT.
     *
     * @param token Token JWT.
     * @return Nombre de usuario.
     */
    public String getUsernameFromToken(String token) {
        return getClaim(token, Claims::getSubject);
    }

    /**
     * Obtiene todas las reclamaciones de un token JWT.
     *
     * @param token Token JWT.
     * @return Reclamaciones del token.
     */
    private Claims getAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * Obtiene una reclamación específica de un token JWT.
     *
     * @param token          Token JWT.
     * @param claimsResolver Resolutor de reclamaciones.
     * @param <T>            Tipo de la reclamación.
     * @return Valor de la reclamación.
     */
    public <T> T getClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Obtiene la fecha de expiración de un token JWT.
     *
     * @param token Token JWT.
     * @return Fecha de expiración.
     */
    private Date getExpiration(String token) {
        return getClaim(token, Claims::getExpiration);
    }

    /**
     * Verifica si un token JWT ha expirado.
     *
     * @param token Token JWT.
     * @return `true` si el token ha expirado, de lo contrario, `false`.
     */
    private boolean isTokenExpired(String token) {
        return getExpiration(token).before(new Date());
    }
}
