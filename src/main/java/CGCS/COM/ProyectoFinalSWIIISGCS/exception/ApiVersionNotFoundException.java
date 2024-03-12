package CGCS.COM.ProyectoFinalSWIIISGCS.exception;

/**
 * La clase ApiVersionNotFoundException extiende RuntimeException y representa una excepción personalizada para indicar que la versión de la API no ha sido encontrada.
 * Se utiliza para manejar casos en los que se busca una versión específica de la API y no se encuentra.
 */
public class ApiVersionNotFoundException extends RuntimeException {

    /**
     * Construye una nueva instancia de ApiVersionNotFoundException con el mensaje de error proporcionado.
     *
     * @param message El mensaje de error asociado a la excepción.
     */
    public ApiVersionNotFoundException(String message) {
        super(message);
    }
}
