package CGCS.COM.ProyectoFinalSWIIISGCS.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @file: ApiVersionExceptionHandler
 * @author: EdwarMoya
 * @created: 09/03/2024
 * @HoraCreated: 10:04 p. m.
 */

@ControllerAdvice
public class ApiVersionExceptionHandler {
    @ExceptionHandler(ApiVersionNotFoundException.class)
    public ResponseEntity<String> handleApiVersionNotFoundException(ApiVersionNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
