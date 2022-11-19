//package com.example.restservice.configuration;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.service.Contact;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger.web.*;
//
//import java.util.Collections;
//
//
//@Controller
//@EnableWebMvc
//@Configuration
//public class SpringFoxConfig {
//        private ApiInfo apiInfo() {
//            return new ApiInfo(
//                    "My REST API",
//                    "API.",
//                    "1.0.0",
//                    "Terms of service",
//                    new Contact("Nguyen Viet Anh",
//                            "", "myeaddress@company.com"),
//                    "License of API",
//                    "API license URL",
//                    Collections.emptyList());
//        }
//

//        @Bean
//        public Docket api() {
//            return new Docket(DocumentationType.SWAGGER_2)
//                    .apiInfo(apiInfo())
//                    .select()
//                    .apis(RequestHandlerSelectors.basePackage("com.example.restservice.los_person.controller"))
//                    .paths(PathSelectors.any())
//                    .build();
//        }
//}
