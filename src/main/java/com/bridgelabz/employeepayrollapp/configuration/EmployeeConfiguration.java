package com.bridgelabz.employeepayrollapp.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * Purpose: To configure model mapper
 *
 * @author: Sonali G
 * @since: 13-12-2021
 */
@Configuration
public class  EmployeeConfiguration {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
