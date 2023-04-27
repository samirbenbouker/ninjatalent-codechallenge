package com.samirbenbouker.ninjatalent;

import com.samirbenbouker.ninjatalent.config.SwaggerInfo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@SpringBootApplication
@RestController
@RequestMapping
public class NinjatalentApplication {

    public static void main(String[] args) {
        SpringApplication.run(NinjatalentApplication.class, args);
    }

    @RequestMapping("/")
    public HashMap helloMessage() {
        SwaggerInfo swaggerInfo = new SwaggerInfo();
        return swaggerInfo.getSwagger();
    }

}
