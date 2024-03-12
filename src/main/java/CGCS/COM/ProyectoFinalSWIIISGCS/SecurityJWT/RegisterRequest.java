/**
 * @file: RegisterRequest.java
 * @created: 11/03/2024
 * @HoraCreated: 09:49 p. m.
 * @author: Edwar Moya
 */

package CGCS.COM.ProyectoFinalSWIIISGCS.SecurityJWT;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase que representa la solicitud de registro.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    private String username;
    private String password;
    private String nombre;
    private String apellido;
    private String country;
}
