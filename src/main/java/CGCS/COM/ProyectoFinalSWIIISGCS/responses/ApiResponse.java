/**
 * @file: ApiResponse.java
 * @author: (c)2024 evalencia 
 * @created: Feb 20, 2024 9:29:17 PM
 */
package CGCS.COM.ProyectoFinalSWIIISGCS.responses;

import lombok.Data;

/**
 * @param <T>
 * 
 */
@Data
public class ApiResponse<T> {
	private boolean success;
    private String message;
    private T data;

    public ApiResponse(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

}
