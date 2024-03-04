package CGCS.COM.ProyectoFinalSWIIISGCS.responses;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CustomVerificacionResponse {
    private String message;

    public CustomVerificacionResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

//    public String toJSONString() {
//        ObjectMapper objectMapper = new ObjectMapper();
//        try {
//            return objectMapper.writeValueAsString(this.message);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//            return "{\"message\":\"Error al convertir a JSON\"}";
//        }
//    }
}
