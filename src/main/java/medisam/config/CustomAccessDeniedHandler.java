/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medisam.config;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;

/**
 *
 * @author jm
 */
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    public static final Logger LOG
            = LoggerFactory.getLogger(CustomAccessDeniedHandler.class);

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException exc) throws IOException, ServletException {
        Authentication auth
                = SecurityContextHolder.getContext().getAuthentication();
//        if (auth != null) {
//            LOG.warn("User: " + auth.getName()
//                    + " attempted to access the protected URL: "
//                    + request.getRequestURI());
//        }
        if (auth != null && auth.isAuthenticated()) {
            System.out.println("is auth");
            response.sendRedirect(request.getContextPath() + "/");
        } else {
            System.out.println("not auth");
            response.sendRedirect(request.getContextPath() + "/access?error");
        }

    }

}
