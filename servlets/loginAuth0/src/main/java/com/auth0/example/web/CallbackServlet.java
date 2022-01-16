package com.auth0.example.web;



import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * Handles requests to the callback URI. Redirects to the home page or to the referring page if set.
 */
@WebServlet(urlPatterns = {"/callback"})
public class CallbackServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger("com.auth0.example.web.CallbackServlet");

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        /*
         * Since we perform the token exchange in Auth0AuthenticationMechanism (which happens prior to this Servlet's
         * execution in the filter chain), we simply redirect the home page or to the referring URI if one was set
         * in the session.
         */
        Object referer = request.getSession().getAttribute("Referer");
        String redirectTo = referer != null ? (String) referer : request.getContextPath();

        logger.info("After callback, redirecting to: " + referer);
        response.sendRedirect(redirectTo);
    }
}
