package com.vstu.department;

import com.vstu.department.config.security.CustomTokenConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ApplicationContext;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.annotation.PostConstruct;
import java.io.IOException;

@EnableEurekaClient
@SpringBootApplication
@RequiredArgsConstructor
public class HeadOfDepartmentApplication {

    private final ApplicationContext applicationContext;

    public static void main(String[] args) {
        SpringApplication.run(HeadOfDepartmentApplication.class, args);
    }

    @PostConstruct
    public void replaceDefaultConverter() throws IOException {
        JwtAccessTokenConverter jwtAccessTokenConverterBean = applicationContext.getBean(JwtAccessTokenConverter.class);
        jwtAccessTokenConverterBean.setAccessTokenConverter(new CustomTokenConverter());
    }
}
