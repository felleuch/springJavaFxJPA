package com.faiez.test;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages={"com.faiez.test"})
public class ApplicationConfig {
//Spring framework will know what to do with this class
}
