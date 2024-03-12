/**
 * @file: ApiResponseHateos.java
 * @created: [Fecha de creación]
 */

package CGCS.COM.ProyectoFinalSWIIISGCS.responses;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDateTime;

/**
 * Clase que representa una respuesta HATEOAS de la API.
 */
@JsonPropertyOrder({"status", "message", "data", "timestamp"})
public class ApiResponseHateos {

    private String status;
    private Object data;
    private LocalDateTime timestamp;
    private String message;

    /**
     * Constructor de la clase ApiResponseHateos.
     *
     * @param status Estado de la respuesta.
     * @param data Datos de la respuesta.
     * @param timestamp Marca de tiempo de la respuesta.
     * @param message Mensaje descriptivo de la respuesta.
     */
    public ApiResponseHateos(String status, Object data, LocalDateTime timestamp, String message) {
        this.status = status;
        this.data = data;
        this.timestamp = timestamp;
        this.message = message;
    }

    /**
     * Método para crear una respuesta HATEOAS exitosa.
     *
     * @param data    Datos de la respuesta.
     * @param message Mensaje descriptivo de la respuesta.
     * @return Objeto ApiResponseHateos representando una respuesta exitosa.
     */
    public static ApiResponseHateos ok(Object data, String message) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return new ApiResponseHateos("OK", data, LocalDateTime.now(), message);
    }

    /**
     * Método para crear una respuesta HATEOAS de error.
     *
     * @param message Mensaje descriptivo del error.
     * @return Objeto ApiResponseHateos representando una respuesta de error.
     */
    public static ApiResponseHateos error(String message) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return new ApiResponseHateos("error", null, LocalDateTime.now(), message);
    }

    // Getters y Setters

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
