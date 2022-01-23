package com.auth0.example.web;

import com.auth0.AuthenticationController;
import com.auth0.example.security.Auth0AuthenticationConfig;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * Responsible for redirecting to Auth0 to initiate the authentication flow when a login request is made.
 */
@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 8059362242271896954L;


	private static final Logger logger = Logger.getLogger("com.auth0.example.web.LoginServlet");


    private final Auth0AuthenticationConfig config;
    private final AuthenticationController authenticationController;

    @Inject
    LoginServlet(Auth0AuthenticationConfig config, AuthenticationController authenticationController) {
        this.config = config;
        this.authenticationController = authenticationController;
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("in LoginServlet");
        response.sendRedirect(buildAuthUrl(request, response));
    }

    private String buildAuthUrl(HttpServletRequest request, HttpServletResponse response) {
    	
    	String redirectUrl = null;
    	if("".equals(request.getContextPath())) {
    		redirectUrl = String.format(
                    "%s://%s:%s/callback",
                    request.getScheme(),
                    request.getServerName(),
                    request.getServerPort());
    	} else {
    		/*redirectUrl = String.format(
                    "%s://%s:%s%s/callback",
                    request.getScheme(),
                    request.getServerName(),
                    request.getServerPort(),
                    request.getContextPath());*/
    		
    		redirectUrl = String.format(
                    "%s://%s%s/callback",
                    request.getScheme(),
                    request.getServerName(),
                    request.getContextPath());
    	}
    	
    	
    	
        logger.info("redirectUrl " + redirectUrl);
        return authenticationController.buildAuthorizeUrl(request, response, redirectUrl)
                .withScope(config.getScope())
                .build();
    }
}
