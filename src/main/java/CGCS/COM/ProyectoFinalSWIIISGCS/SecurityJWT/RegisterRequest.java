package CGCS.COM.ProyectoFinalSWIIISGCS.SecurityJWT;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @file: RegisterRequest
 * @author: EdwarMoya
 * @created: 11/03/2024
 * @HoraCreated: 09:49 p. m.
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    String username;
    String password;
    String nombre;
    String apellido;
    String country;
}
