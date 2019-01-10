package by.vstu.department.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

import java.time.LocalDateTime;
import java.util.Map;

public final class UtilService {

    private static final String DEFAULT_HALF_YEAR;

    static {
        final LocalDateTime date = LocalDateTime.now();
        DEFAULT_HALF_YEAR = date.getYear() + "-" + (date.getMonthValue() < 7 ? "1" : "2");
    }

    private UtilService() {
        throw new IllegalStateException("Utility class");
    }

    public static Object getFieldFromAuthentificationDetails(String field) {
        OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
        return ((Map<String, Object>) details.getDecodedDetails()).get(field);
    }

    public static String getDefaultHalfYear() {
        return DEFAULT_HALF_YEAR;
    }
}
