/**
 * @file: ErrorResponse.java
 * @author: (c)2024 evalencia 
 * @created: Feb 20, 2024 11:55:00 PM
 */
package CGCS.COM.ProyectoFinalSWIIISGCS.exception;

import lombok.Data;

/**
 * 
 */
@Data
public class ErrorResponse {
	private String message;

    public ErrorResponse(String message) {
        this.message = message;
    }
}
