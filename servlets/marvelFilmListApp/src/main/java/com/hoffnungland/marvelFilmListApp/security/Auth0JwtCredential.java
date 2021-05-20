package com.hoffnungland.marvelFilmListApp.security;

import javax.security.enterprise.credential.Credential;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;

class Auth0JwtCredential implements Credential {

    private Auth0JwtPrincipal auth0JwtPrincipal;

    /**
     * Constructor
     *
     * @param token the JWT token representing this Credential.
     */
    Auth0JwtCredential(String token) {
        DecodedJWT decodedJWT = JWT.decode(token);
        this.auth0JwtPrincipal = new Auth0JwtPrincipal(decodedJWT);
    }

    /**
     * @return the {@link Auth0JwtPrincipal} for this Credential.
     */
    Auth0JwtPrincipal getAuth0JwtPrincipal() {
        return auth0JwtPrincipal;
    }
}
