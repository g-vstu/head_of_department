package by.vstu.department;

import by.vstu.department.config.security.CustomTokenConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.annotation.PostConstruct;

@SpringBootApplication
@RequiredArgsConstructor
public class DepartmentApplication {

    private final ApplicationContext applicationContext;

    public static void main(String[] args) {
        SpringApplication.run(DepartmentApplication.class, args);
    }

    @PostConstruct
    public void replaceDefaultConverter() {
        JwtAccessTokenConverter jwtAccessTokenConverterBean = applicationContext.getBean(JwtAccessTokenConverter.class);
        jwtAccessTokenConverterBean.setAccessTokenConverter(new CustomTokenConverter());
    }
}
