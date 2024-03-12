/**
 * @file: ApiResponse.java
 * @created: Feb 20, 2024 9:29:17 PM
 */

package CGCS.COM.ProyectoFinalSWIIISGCS.responses;

import lombok.Data;

/**
 * Clase que representa una respuesta estándar de la API.
 *
 * @param <T> Tipo de datos de la carga útil.
 */
@Data
public class ApiResponse<T> {

    private boolean success;
    private String message;
    private T data;

    /**
     * Constructor de la clase ApiResponse.
     *
     * @param success Indica si la operación fue exitosa.
     * @param message Mensaje descriptivo de la respuesta.
     * @param data Carga útil de datos.
     */
    public ApiResponse(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

}
