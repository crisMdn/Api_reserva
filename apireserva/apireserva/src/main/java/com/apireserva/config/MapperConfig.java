package com.apireserva.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // Indica que esta clase define configuraciones para Spring
public class MapperConfig {

    @Bean // Registra el bean para que pueda ser inyectado en cualquier servicio
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
