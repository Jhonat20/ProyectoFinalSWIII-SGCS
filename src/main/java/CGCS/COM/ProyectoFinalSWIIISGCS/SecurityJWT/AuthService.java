/**
 * @file: AuthService.java
 * @created: [Fecha de creación]
 */

package CGCS.COM.ProyectoFinalSWIIISGCS.SecurityJWT;

import CGCS.COM.ProyectoFinalSWIIISGCS.User.Role;
import CGCS.COM.ProyectoFinalSWIIISGCS.User.User;
import CGCS.COM.ProyectoFinalSWIIISGCS.User.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Servicio de autenticación y registro.
 */
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    /**
     * Método para realizar la autenticación de un usuario.
     *
     * @param request Objeto que contiene las credenciales del usuario para iniciar sesión.
     * @return Objeto AuthResponse que contiene el token JWT generado.
     */
    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        UserDetails user = userRepository.findByUsername(request.getUsername()).orElseThrow();
        String token = jwtService.getToken(user);
        return AuthResponse.builder()
                .token(token)
                .build();
    }

    /**
     * Método para registrar un nuevo usuario.
     *
     * @param request Objeto que contiene la información del nuevo usuario.
     * @return Objeto AuthResponse que contiene el token JWT generado para el nuevo usuario.
     */
    public AuthResponse register(RegisterRequest request) {
        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .nombre(request.getNombre())
                .apellido(request.getApellido())
                .country(request.getCountry())
                .role(Role.USER)
                .build();

        userRepository.save(user);

        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .build();
    }

}
