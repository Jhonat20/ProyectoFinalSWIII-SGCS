/**
 * La clase EntityNotFoundException extiende Exception y representa una excepción personalizada que se lanza cuando el proceso de búsqueda no encuentra una entidad.
 * Se utiliza para manejar situaciones en las que se espera encontrar una entidad, pero no se encuentra en el sistema.
 *
 * @file: EntityNotFoundException.java
 * @author: (c)2024 evalencia
 * @created: Feb 15, 2024 1:40:04 PM
 */
package CGCS.COM.ProyectoFinalSWIIISGCS.exception;

/**
 * Excepción que se lanza cuando el proceso de búsqueda no encuentra una entidad.
 */
public class EntityNotFoundException extends Exception {

	/** Número de versión de la clase para serialización */
	private static final long serialVersionUID = 1L;

	/**
	 * Construye una nueva instancia de EntityNotFoundException con el mensaje de error proporcionado.
	 *
	 * @param message El mensaje de error asociado a la excepción.
	 */
	public EntityNotFoundException(String message) {
		super(message);
	}
}
