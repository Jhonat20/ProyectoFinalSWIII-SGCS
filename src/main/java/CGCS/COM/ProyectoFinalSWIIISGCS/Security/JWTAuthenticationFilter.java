package CGCS.COM.ProyectoFinalSWIIISGCS.Security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.autoconfigure.pulsar.PulsarProperties;

import java.io.IOException;
import java.util.Collections;

/**
 * @file: JWTAuthenticationFilter
 * @author: EdwarMoya
 * @created: 08/03/2024
 * @HoraCreated: 12:29 p. m.
 */
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    @Override
    public PulsarProperties.Authentication attemptAuthentication(HttpServletRequest request,
                                                                 HttpServletResponse response) throws AuthenticationException {
        AuthCredentials authCredentials = new AuthCredentials();

        try {
            authCredentials = new ObjectMapper().readValue(request.getReader(), AuthCredentials.class);
        } catch (IOException e) {
        }
        UsernamePasswordAuthenticationToken usernamePAT = new UsernamePasswordAuthenticationToken(

                authCredentials.getEmail(),
                authCredentials.getPassword(),
                Collections.emptyList()
        );
        return getAuthenticationManager().authenticate(usernamePAT);

    }
    @Override
    protected void successfulAuthentication (HttpServletRequest request,
                                             HttpServletResponse response,
                                             FilterChain chain,
                                             Authentication authResult) throws IOException, ServletException {
        UserDetailsImp userDetails = (UserDetailsImp) authResult.getPrincipal();
        String token = TokenUtils.createToken(userDetails.getNombre(), userDetails.getUsername());

        response.addHeader("Authorization", "Bearer " + token);
        response.getWriter().flush();
        super.successfulAuthentication(request, response, chain, authResult);
    }
}
