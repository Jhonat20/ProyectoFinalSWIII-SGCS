/**
 * La clase GlobalExceptionHandler es un manejador de excepciones global para el manejo centralizado de distintos tipos de excepciones en el sistema.
 *
 * @file: GlobalExceptionHandler.java
 * @created: [Fecha de creación]
 */

package CGCS.COM.ProyectoFinalSWIIISGCS.exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Clase que actúa como manejador de excepciones global para el sistema.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Maneja excepciones generales.
     *
     * @param ex      La excepción capturada.
     * @param request La solicitud HTTP asociada a la excepción.
     * @return ResponseEntity con información detallada sobre el error.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleException(Exception ex, HttpServletRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now().toString());
        body.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        body.put("error", "Internal Server Error");
        body.put("message", "Error interno del servidor. Por favor, contacte al administrador.");
        body.put("uri", request.getRequestURI());

        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Maneja excepciones de violación de restricciones de validación.
     *
     * @param ex      La excepción capturada.
     * @param request La solicitud HTTP asociada a la excepción.
     * @return ResponseEntity con información detallada sobre el error.
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String, Object>> handleConstraintViolationException(ConstraintViolationException ex, HttpServletRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now().toString());
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("error", "Bad Request");
        body.put("message", "Violación de restricciones de validación. Por favor, verifique sus datos e inténtelo de nuevo.");
        body.put("uri", request.getRequestURI());

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    /**
     * Maneja excepciones cuando no se encuentra un manejador para la solicitud.
     *
     * @param ex      La excepción capturada.
     * @param request La solicitud HTTP asociada a la excepción.
     * @return ResponseEntity con información detallada sobre el error.
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<Map<String, Object>> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpServletRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now().toString());
        body.put("status", HttpStatus.NOT_FOUND.value());
        body.put("error", "Not Found");
        body.put("message", "Recurso no encontrado. El recurso solicitado no existe.");
        body.put("uri", request.getRequestURI());

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    /**
     * Maneja excepciones cuando no se encuentra ningún resultado en la base de datos.
     *
     * @param ex      La excepción capturada.
     * @param request La solicitud HTTP asociada a la excepción.
     * @return ResponseEntity con información detallada sobre el error.
     */
    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<Map<String, Object>> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex, HttpServletRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now().toString());
        body.put("status", HttpStatus.NOT_FOUND.value());
        body.put("error", "Not Found");
        body.put("message", "No se encontraron resultados en la base de datos. Por favor, verifique su consulta.");
        body.put("uri", request.getRequestURI());

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    /**
     * Maneja excepciones de operaciones ilegales.
     *
     * @param ex      La excepción capturada.
     * @param request La solicitud HTTP asociada a la excepción.
     * @return ResponseEntity con información detallada sobre el error.
     */
    @ExceptionHandler(IllegalOperationException.class)
    public ResponseEntity<Map<String, Object>> handleIllegalOperationException(IllegalOperationException ex, HttpServletRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now().toString());
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("error", "Bad Request");
        body.put("message", ex.getMessage());
        body.put("uri", request.getRequestURI());

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
}
