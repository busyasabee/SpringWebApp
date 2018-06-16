package com.dmitrromashov;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import java.util.ArrayList;

@SpringBootApplication
public class App 
{

    public static void main( String[] args )
    {

        SpringApplication.run(App.class, args);
    }
}
