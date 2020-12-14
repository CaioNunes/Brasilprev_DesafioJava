package com.brasilprev.ClientAPI;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {                                    
    @Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()                                  
          .apis(RequestHandlerSelectors.basePackage("com.brasilprev.ClientAPI.controller"))              
          .paths(PathSelectors.any())                          
          .build()
          .useDefaultResponseMessages(false)
          .globalResponseMessage(RequestMethod.GET, responseMessageForGET())
          .globalResponseMessage(RequestMethod.POST, responseMessageForPOST())
          .globalResponseMessage(RequestMethod.POST, responseMessageForDELETE())                                           
          .globalResponseMessage(RequestMethod.POST, responseMessageForPUT())                                           
          .globalResponseMessage(RequestMethod.POST, responseMessageForPATCH())
          .apiInfo(apiInfo());                                         

    }
    
    private ApiInfo apiInfo() {
    	return new ApiInfoBuilder()
    			.title("Brasil Prev - Java Challenge Clients REST API")
    			.description("An API for BrasilPrev client control.")
    			.build();
    }
    
    private List<ResponseMessage> responseMessageForGET(){
        return new ArrayList<ResponseMessage>() {{
            add(new ResponseMessageBuilder()
                .code(500)
                .message("Internal Server Error")
                .responseModel(new ModelRef("Error"))
                .build());
            add(new ResponseMessageBuilder()
                    .code(204)
                    .message("No Content!")
                    .build());
        }};
    }
    
    private List<ResponseMessage> responseMessageForPOST(){
        return new ArrayList<ResponseMessage>() {{
            add(new ResponseMessageBuilder()
                .code(500)
                .message("Internal Server Error")
                .responseModel(new ModelRef("Error"))
                .build());
            add(new ResponseMessageBuilder()
                    .code(201)
                    .message("Created!")
                    .build());
            add(new ResponseMessageBuilder()
                    .code(409)
                    .message("Data Conflict!")
                    .build());
        }};
    }
    
    private List<ResponseMessage> responseMessageForDELETE(){
        return new ArrayList<ResponseMessage>() {{
            add(new ResponseMessageBuilder()
                .code(500)
                .message("Internal Server Error")
                .responseModel(new ModelRef("Error"))
                .build());
            add(new ResponseMessageBuilder()
                    .code(404)
                    .message("Not Found!")
                    .build());
        }};
    }
    
    private List<ResponseMessage> responseMessageForPUT(){
        return new ArrayList<ResponseMessage>() {{
            add(new ResponseMessageBuilder()
                .code(500)
                .message("Internal Server Error")
                .responseModel(new ModelRef("Error"))
                .build());
            add(new ResponseMessageBuilder()
                    .code(404)
                    .message("Not Found!")
                    .build());
        }};
    }
    
    private List<ResponseMessage> responseMessageForPATCH(){
        return new ArrayList<ResponseMessage>() {{
            add(new ResponseMessageBuilder()
                .code(500)
                .message("Internal Server Error")
                .responseModel(new ModelRef("Error"))
                .build());
            add(new ResponseMessageBuilder()
                    .code(404)
                    .message("Not Found!")
                    .build());
        }};
    }
}

