package com.privaterestaurant.security;

import org.springframework.security.core.GrantedAuthority;

import com.privaterestaurant.service.CurrentUser;

/**
 * Security utility.
 * Check permission for selected user.
 * 
 * @author Sergey Stotskiy
 */
public class SecurityUtil {
    /**
     * Check permission for selected user.  
     * @param currentUser user
     * @param permission permission
     * @return
     */
    public static boolean hasPermission(CurrentUser currentUser, Permission permission) {
        return currentUser.getAuthorities()
            .contains((GrantedAuthority) permission::toString);
    }

    /**
     * Check permissions for selected user.  
     * @param currentUser user
     * @param permissions permissions
     * @return
     */
    public static boolean hasAnyPermission(CurrentUser currentUser,
        Permission... permissions) {
        for (Permission permission : permissions) {
            if (hasPermission(currentUser, permission)) {
                return true;
            }
        }
        return false;
    }
}
