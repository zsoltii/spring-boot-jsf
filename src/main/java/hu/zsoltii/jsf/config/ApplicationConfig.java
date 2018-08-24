package hu.zsoltii.jsf.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"hu.zsoltii.jsf.controller", "hu.zsoltii.jsf.data", "hu.zsoltii.jsf.service"})
public class ApplicationConfig {
}
