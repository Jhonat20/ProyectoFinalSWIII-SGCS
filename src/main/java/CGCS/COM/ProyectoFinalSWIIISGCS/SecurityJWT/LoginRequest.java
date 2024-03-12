package CGCS.COM.ProyectoFinalSWIIISGCS.SecurityJWT;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @file: LoginRequest
 * @author: EdwarMoya
 * @created: 11/03/2024
 * @HoraCreated: 09:47 p. m.
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {

    String username;
    String password;
}
