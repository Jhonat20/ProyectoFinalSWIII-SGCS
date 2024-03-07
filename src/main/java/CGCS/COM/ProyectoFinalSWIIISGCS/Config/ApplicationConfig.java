package CGCS.COM.ProyectoFinalSWIIISGCS.Config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @file: ApplicationConfig
 * @author: (c)jhons
 * @created: 06/03/2024 23:59
 */

@Configuration
public class ApplicationConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
