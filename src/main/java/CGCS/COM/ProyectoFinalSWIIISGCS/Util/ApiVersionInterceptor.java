package CGCS.COM.ProyectoFinalSWIIISGCS.Util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;

public class ApiVersionInterceptor implements HandlerInterceptor {

    @Value("${api.version}")
    private String apiVersion = "v0.1.0";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setHeader("API-Version", apiVersion);
        return true;
    }
}
