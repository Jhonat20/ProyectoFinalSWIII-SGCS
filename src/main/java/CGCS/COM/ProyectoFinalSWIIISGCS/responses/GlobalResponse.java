package CGCS.COM.ProyectoFinalSWIIISGCS.responses;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDateTime;

public class GlobalResponse {
    private String status;
    private Object data;
    private LocalDateTime timestamp;
    private String path;

    private GlobalResponse(String status, Object data, HttpServletRequest request) {
        this.status = status;
        this.data = data;
        this.timestamp = LocalDateTime.now();
        this.path = request.getRequestURI();
    }

    // Getters y Setters (se omiten para brevedad)

    public static GlobalResponse ok(Object data) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return new GlobalResponse("OK", data, request);
    }

    public static GlobalResponse error(String message) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return new GlobalResponse("error", message, request);
    }

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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}

