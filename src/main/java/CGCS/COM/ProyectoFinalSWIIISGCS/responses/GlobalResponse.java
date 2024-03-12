/**
 * @file: GlobalResponse.java
 * @created: [Fecha de creación]
 */

package CGCS.COM.ProyectoFinalSWIIISGCS.responses;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDateTime;

/**
 * Clase que representa una respuesta global de la API.
 */
public class GlobalResponse {

    private String status;
    private Object data;
    private LocalDateTime timestamp;
    private String path;

    /**
     * Constructor privado de la clase GlobalResponse.
     *
     * @param status  Estado de la respuesta.
     * @param data    Datos de la respuesta.
     * @param request Objeto HttpServletRequest que proporciona información sobre la solicitud.
     */
    private GlobalResponse(String status, Object data, HttpServletRequest request) {
        this.status = status;
        this.data = data;
        this.timestamp = LocalDateTime.now();
        this.path = request.getRequestURI();
    }

    /**
     * Método estático para crear una respuesta global exitosa.
     *
     * @param data Datos de la respuesta.
     * @return Objeto GlobalResponse representando una respuesta exitosa.
     */
    public static GlobalResponse ok(Object data) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return new GlobalResponse("OK", data, request);
    }

    /**
     * Método estático para crear una respuesta global de error.
     *
     * @param message Mensaje descriptivo del error.
     * @return Objeto GlobalResponse representando una respuesta de error.
     */
    public static GlobalResponse error(String message) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return new GlobalResponse("error", message, request);
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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
