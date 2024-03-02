/**
 * @file: ErrorMessage.java
 * @author: (c)2024 evalencia 
 * @created: Feb 15, 2024 1:42:34 PM
 */
package CGCS.COM.ProyectoFinalSWIIISGCS.exception;

public final class ErrorMessage {
	public static final String HISTORIAL_MEDICO_NOT_FOUND = "El Historial Medico con id proporcionado no fue encontrado";
	public static final String HISTORIAL_MEDICO_INVALID = "El Historial no tiene un formato valido";
	
	private ErrorMessage() {
		throw new IllegalStateException ("Utility class");
	}
}
