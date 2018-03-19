package org.pdm.ib.pmt.router.swaggers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.*;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    private static final Contact DEFAULT_CONTACT = new Contact("Bogdan Dina",
            "www.github.com/dinabogdan",
            "bogdan.dina03@gmail.com");

    private static final ApiInfo DEFAULT_API_INFO = new ApiInfo("PDMRouter REST API Swagger Documenttation",
            "Here it's the documentation for all HTTP Requests",
            "1.0",
            "urn:tos",
            DEFAULT_CONTACT,
            "Apache 2.0",
            "http://www.apache.org/licenses/LICENSE-2.0",
            new ArrayList<>());

    private static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES = new HashSet<>(Collections.singletonList("application/json"));

    @Bean
    public Docket docketAPI() {
        return new Docket(DocumentationType.SWAGGER_2).
                apiInfo(DEFAULT_API_INFO).
                produces(DEFAULT_PRODUCES_AND_CONSUMES).
                consumes(DEFAULT_PRODUCES_AND_CONSUMES);
    }
}
