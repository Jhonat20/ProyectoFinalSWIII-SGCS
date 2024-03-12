/**
 * La clase ErrorMessage proporciona mensajes de error predefinidos para manejar excepciones en el sistema.
 *
 * @file: ErrorMessage.java
 * @author: (c)2024 evalencia
 * @created: Feb 15, 2024 1:42:34 PM
 */
package CGCS.COM.ProyectoFinalSWIIISGCS.exception;

/**
 * Clase que contiene mensajes de error predefinidos para manejar excepciones en el sistema.
 */
public final class ErrorMessage {

	/** Mensaje de error para indicar que el horario con el ID proporcionado no fue encontrado */
	public static final String HORARIO_NOT_FOUND = "El Horario con el ID proporcionado no fue encontrado";

	/** Mensaje de error para indicar que el horario seleccionado tiene solapamiento con otro horario */
	public static final String HORARIO_SOLAPAMIENTO = "Lo sentimos, el horario seleccionado no está disponible. Por favor, elija un horario diferente.";

	/** Mensaje de error para indicar que el horario que se intenta actualizar ya existe en el sistema */
	public static final String HORARIO_DUPLICADO = "El horario que está intentando actualizar ya existe en el sistema. Por favor, seleccione un horario diferente para la actualización.";

	/** Constructor privado para evitar la creación de instancias de la clase. */
	private ErrorMessage() {
		throw new IllegalStateException("Utility class");
	}
}
