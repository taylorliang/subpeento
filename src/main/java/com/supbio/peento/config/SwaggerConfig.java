package com.supbio.peento.config;

import com.google.common.base.Predicates;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Value("${swagger.show}")
    private boolean swaggerShow;

    static List<Parameter> webParameter = new ArrayList<Parameter>();

    static List<Parameter> apparameter = new ArrayList<Parameter>();

    static {
        //web
        ParameterBuilder webToken = new ParameterBuilder();
        webToken.name("token").description("令牌").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        Parameter webPar = webToken.build();
        webParameter.add(webPar);

        //app
        ParameterBuilder appToken = new ParameterBuilder();
        appToken.name("accessToken").description("令牌").modelRef(new ModelRef("string")).parameterType("query").required(false).build();
        Parameter appPar = appToken.build();
        webParameter.add(appPar);
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("supbio peento manage API")
                .description("supbio peento manage API")
                .license("Apache 2.0")
                .licenseUrl("http:/www.apache.org/licenses/LICENSE-2.0.html")
                .termsOfServiceUrl("")
                .version("1.0.0")
                .contact(new Contact("peento","",""))
                .build();
    }

    @Bean
    public Docket manageApi(){
        return new Docket(DocumentationType.SWAGGER_2).enable(swaggerShow).groupName("manageApi")
                .genericModelSubstitutes(DeferredResult.class)
                .useDefaultResponseMessages(false)
                .forCodeGeneration(true)
                .pathMapping("/")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.supbio.peento.controller.manage"))
                .paths(Predicates.or(PathSelectors.regex("/api/peento/manage/.*")))//过滤的接口
                .build()
                .globalOperationParameters(webParameter).apiInfo(apiInfo());
    }

}
