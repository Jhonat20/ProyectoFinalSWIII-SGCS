package CGCS.COM.ProyectoFinalSWIIISGCS.exception;

/**
 * @file: ApiVersionNotFoundException
 * @author: EdwarMoya
 * @created: 09/03/2024
 * @HoraCreated: 09:58 p. m.
 */
public class ApiVersionNotFoundException extends RuntimeException{
    public ApiVersionNotFoundException(String message) {
        super(message);
    }
}
