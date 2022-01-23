package com.auth0.example.web;

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
 * Logs the user out from the Auth0 authorization server and clears the session.
 */
@WebServlet(urlPatterns = "/logout")
public class LogoutServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 2267209792514959802L;

	private static final Logger logger = Logger.getLogger("com.auth0.example.web.LogoutServlet");

    private final Auth0AuthenticationConfig config;

    @Inject
    LogoutServlet(Auth0AuthenticationConfig config) {
        this.config = config;
    }

    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        clearSession(request);
        response.sendRedirect(getLogoutUrl(request));
    }

    private void clearSession(HttpServletRequest request) {
        if (request.getSession() != null) {
            request.getSession().invalidate();
        }
    }

    private String getLogoutUrl(HttpServletRequest request) {
    	String returnUrl = null;
    	if("".equals(request.getContextPath())) {
    		returnUrl = String.format("%s://%s", request.getScheme(), request.getServerName());
    	} else {
    		returnUrl = String.format("%s://%s%s", request.getScheme(), request.getServerName(), request.getContextPath());
    	}
        int port = request.getServerPort();
        String scheme = request.getScheme();

        if (("http".equals(scheme) && port != 80) ||
                ("https".equals(scheme) && port != 443)) {
            returnUrl += ":" + port;
        }

        returnUrl += "/";

        String logoutUrl = String.format(
                "https://%s/v2/logout?client_id=%s&returnTo=%s",
                config.getDomain(),
                config.getClientId(),
                returnUrl
        );

        logger.info("Logout URL: " + logoutUrl);
        return logoutUrl;
    }
}
