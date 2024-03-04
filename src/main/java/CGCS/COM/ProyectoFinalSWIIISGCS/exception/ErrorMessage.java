/**
 * @file: ErrorMessage.java
 * @author: (c)2024 evalencia 
 * @created: Feb 15, 2024 1:42:34 PM
 */
package CGCS.COM.ProyectoFinalSWIIISGCS.exception;

public final class ErrorMessage {

	public static final String HORARIO_NOT_FOUND = "El Horario con id proporcionado no fue encontrado";
	public static final String HORARIO_SOLAPAMIENTO ="Lo sentimos, el horario seleccionado no está disponible. Por favor, elija un horario diferente.";
	public static final String HORARIO_DUPLICADO = "El horario que está intentando actualizar ya existe en el sistema. Por favor, seleccione un horario diferente para la actualización.";
	private ErrorMessage() {
		throw new IllegalStateException ("Utility class");
	}
}
