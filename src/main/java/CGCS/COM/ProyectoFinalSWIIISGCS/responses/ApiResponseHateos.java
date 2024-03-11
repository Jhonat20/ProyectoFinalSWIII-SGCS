package CGCS.COM.ProyectoFinalSWIIISGCS.responses;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDateTime;

@JsonPropertyOrder({"status", "message", "data","timestamp"})
public class ApiResponseHateos {
    private String status;
    private Object data;
    private LocalDateTime timestamp;
    private String message;

    public ApiResponseHateos(String status, Object data, LocalDateTime timestamp, String message) {
        this.status = status;
        this.data = data;
        this.timestamp = timestamp;
        this.message = message;
    }
    public static ApiResponseHateos ok(Object data, String message) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return new ApiResponseHateos("OK", data, LocalDateTime.now(), message);
    }

    public static ApiResponseHateos error(String message) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return new ApiResponseHateos("error", null, LocalDateTime.now(), message);
    }

    // Getters y Setters

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
