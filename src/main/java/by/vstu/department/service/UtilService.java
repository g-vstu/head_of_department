package by.vstu.department.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

import java.util.Map;

public class UtilService {

    public static Object getFieldFromAuthentificationDetails(String field) {
        OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
        return ((Map<String, Object>) details.getDecodedDetails()).get(field);
    }
}
