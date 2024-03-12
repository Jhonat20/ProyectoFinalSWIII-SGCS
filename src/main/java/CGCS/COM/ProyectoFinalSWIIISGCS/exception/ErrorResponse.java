/**
 * La clase ErrorResponse representa una respuesta de error en el sistema.
 *
 * @file: ErrorResponse.java
 * @author: (c)2024 evalencia
 * @created: Feb 20, 2024 11:55:00 PM
 */
package CGCS.COM.ProyectoFinalSWIIISGCS.exception;

import lombok.Data;

/**
 * Clase que representa una respuesta de error en el sistema.
 */
@Data
public class ErrorResponse {

    /** Mensaje de error en la respuesta */
    private String message;

    /**
     * Construye una nueva instancia de ErrorResponse con el mensaje de error proporcionado.
     *
     * @param message El mensaje de error asociado a la respuesta.
     */
    public ErrorResponse(String message) {
        this.message = message;
    }
}
