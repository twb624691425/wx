package com.tang.wx.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.ApiSelectorBuilder;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Value("${custom.swaggerTitle}")
    private String title;
    @Bean
    public Docket createRestApi() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2);

        // 用于在界面上添加各种信息
        ApiInfoBuilder apiInfoBuilder = new ApiInfoBuilder();
        apiInfoBuilder.title(this.title);
        ApiInfo apiInfo = apiInfoBuilder.build();
        docket.apiInfo(apiInfo);

        // ApiSelectorBuilder 用来设置哪些方法会生成到Rest API中
        ApiSelectorBuilder apiSelectorBuilder = docket.select();
        apiSelectorBuilder.paths(PathSelectors.any());
        apiSelectorBuilder.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class));
        docket = apiSelectorBuilder.build();

        // 开启对JWT的支持
        List<ApiKey> apiKeys = new ArrayList<>();
        // 规定用户需要输入什么参数
        apiKeys.add(new ApiKey("token", "token", "header"));
        docket.securitySchemes(apiKeys);

        AuthorizationScope scope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] scopeArray = {scope};

        SecurityReference reference = new SecurityReference("token", scopeArray);
        List<SecurityReference> refList = new ArrayList<>();
        refList.add(reference);
        SecurityContext context  =SecurityContext.builder().securityReferences(refList).build();
        List<SecurityContext> ctxList = new ArrayList<>();
        ctxList.add(context);
        docket.securityContexts(ctxList);

        return docket;
    }
}
