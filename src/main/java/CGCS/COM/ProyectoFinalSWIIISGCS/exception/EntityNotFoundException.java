/**
 * @file: EntityNotFoundException.java
 * @author: (c)2024 evalencia 
 * @created: Feb 15, 2024 1:40:04 PM
 */
package CGCS.COM.ProyectoFinalSWIIISGCS.exception;

/**
 * Excepcion que se lanza cuando el proceso de busqueda no encuentra una entidad
 */
public class EntityNotFoundException extends Exception{

	private static final long serialVersionUID = 1L;

	public EntityNotFoundException(String message) {
		super(message);
	}

}
