/**
 * La clase IllegalOperationException es una excepción personalizada que se lanza cuando se intenta realizar una operación ilegal.
 *
 * @file: IllegalOperationException.java
 * @created: [Fecha de creación]
 */

package CGCS.COM.ProyectoFinalSWIIISGCS.exception;

/**
 * Excepción que se lanza cuando se realiza una operación ilegal.
 */
public class IllegalOperationException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor que recibe un mensaje descriptivo de la operación ilegal.
	 *
	 * @param message Mensaje descriptivo de la operación ilegal.
	 */
	public IllegalOperationException(String message) {
		super(message);
	}
}
