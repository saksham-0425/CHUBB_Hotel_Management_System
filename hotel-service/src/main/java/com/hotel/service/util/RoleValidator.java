package com.hotel.service.util;

import com.hotel.service.exception.UnauthorizedException;

import java.util.Set;

public class RoleValidator {

    public static void checkRole(String role, Set<String> allowedRoles) {

        if (role == null || !allowedRoles.contains(role)) {
            throw new UnauthorizedException("You are not authorized to perform this action");
        }
    }
}
