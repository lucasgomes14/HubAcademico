package com.academichub.AcademicHub.util;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;

public class AuthUtil {
    public static Long getAuthenticatedUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof Jwt jwt) {
            Object userIdClaim = jwt.getClaim("userId");

            if (userIdClaim instanceof Number) {
                return ((Number) userIdClaim).longValue();
            } else if (userIdClaim instanceof String) {
                return Long.valueOf((String) userIdClaim);
            }
        }
        return null;
    }
}
