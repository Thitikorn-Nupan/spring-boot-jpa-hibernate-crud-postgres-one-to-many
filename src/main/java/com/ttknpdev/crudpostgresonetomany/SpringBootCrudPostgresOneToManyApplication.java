package com.ttknpdev.crudpostgresonetomany;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@ComponentScan(basePackages = "com.ttknpdev") // don't forget if you do not you can't inject any beans on jar file
@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass=true) // It enables support for handling components marked with AspectJ’s @Aspect annotation.
public class SpringBootCrudPostgresOneToManyApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootCrudPostgresOneToManyApplication.class, args);
    }

}
