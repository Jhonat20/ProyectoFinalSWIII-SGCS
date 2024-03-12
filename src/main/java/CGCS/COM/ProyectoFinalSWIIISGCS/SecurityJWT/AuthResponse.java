package CGCS.COM.ProyectoFinalSWIIISGCS.SecurityJWT;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @file: AuthResponse
 * @author: EdwarMoya
 * @created: 11/03/2024
 * @HoraCreated: 09:53 p. m.
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
    String token;
}
