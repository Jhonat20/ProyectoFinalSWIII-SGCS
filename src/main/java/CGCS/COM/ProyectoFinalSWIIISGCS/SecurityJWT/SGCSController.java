package CGCS.COM.ProyectoFinalSWIIISGCS.SecurityJWT;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @file: SGCSController
 * @author: EdwarMoya
 * @created: 11/03/2024
 * @HoraCreated: 04:44 p. m.
 */
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class SGCSController {
    @PostMapping(value = "sgcs")
    public String Welcome(){
        return "Welcome form secure endpoint";
    }
}
