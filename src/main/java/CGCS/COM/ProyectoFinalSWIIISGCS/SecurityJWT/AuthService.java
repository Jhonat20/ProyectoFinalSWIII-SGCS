package CGCS.COM.ProyectoFinalSWIIISGCS.SecurityJWT;

import CGCS.COM.ProyectoFinalSWIIISGCS.User.Role;
import CGCS.COM.ProyectoFinalSWIIISGCS.User.User;
import CGCS.COM.ProyectoFinalSWIIISGCS.User.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @file: AuthService
 * @author: EdwarMoya
 * @created: 11/03/2024
 * @HoraCreated: 10:09 p. m.
 */

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;


    public AuthResponse login(LoginRequest request) {
        return null;
    }

    public AuthResponse register(RegisterRequest request) {
        User user = User.builder()
                .username(request.getUsername())
                .password(request.getPassword())
                .nombre(request.getNombre())
                .apellido(request.apellido)
                .country(request.getCountry())
                .role(Role.USER)
                .build();

        userRepository.save(user);

        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .build();
    }

}
