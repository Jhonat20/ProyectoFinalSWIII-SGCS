package CGCS.COM.ProyectoFinalSWIIISGCS.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * La clase ApiVersionExceptionHandler proporciona manejo de excepciones para la excepción ApiVersionNotFoundException.
 * Se encarga de devolver una respuesta adecuada en caso de que se lance dicha excepción.
 */
@ControllerAdvice
public class ApiVersionExceptionHandler {

    /**
     * Maneja la excepción ApiVersionNotFoundException y devuelve una respuesta con el mensaje de error y el código de estado HTTP adecuado.
     *
     * @param ex La excepción ApiVersionNotFoundException lanzada.
     * @return ResponseEntity con el mensaje de error y el código de estado HTTP NOT_FOUND.
     */
    @ExceptionHandler(ApiVersionNotFoundException.class)
    public ResponseEntity<String> handleApiVersionNotFoundException(ApiVersionNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
