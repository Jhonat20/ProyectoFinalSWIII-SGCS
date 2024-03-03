package CGCS.COM.ProyectoFinalSWIIISGCS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"CGCS.COM.ProyectoFinalSWIIISGCS"})
public class ProyectoFinalSwiiiSgcsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProyectoFinalSwiiiSgcsApplication.class, args);
	}

}
