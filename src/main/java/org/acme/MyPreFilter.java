package org.acme;

import java.io.IOException;
import java.security.Principal;

import org.eclipse.microprofile.jwt.Claims;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.logging.Logger;
import jakarta.inject.Inject;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.PreMatching;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;
import jakarta.ws.rs.ext.Provider;


@Provider
@PreMatching
public class MyPreFilter implements ContainerRequestFilter {

    @Inject
    Logger logger;

    
    @Inject
    JsonWebToken jwt;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        String authorizationHeader = requestContext.getHeaderString("Authorization");


        String path = requestContext.getUriInfo().getPath();


        //Login end point
        if ("/auth/login".equals(path)) {
            return;
        }

        //If token is  missing
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            abortWithUnauthorized(requestContext, "Bearer token is missing");
            return;
        }


        //Check Token is expireed or not
        if(!isValidToken()){
            abortWithUnauthorized(requestContext, "Token is Expired");
            return;
        }

        
    }

    //Check token validation
    private boolean isValidToken() {
        if (jwt == null || jwt.getRawToken() == null) {
            return false; 
        }
        Long expiration = jwt.getClaim(Claims.exp);
   
        long currentTime = System.currentTimeMillis() ;
        logger.info("Expiration = " + expiration + " CurrentTime = " + currentTime );
        return expiration > currentTime;  
    }


    //Create repose if toke is not valid
    private void abortWithUnauthorized(ContainerRequestContext requestContext, String message) {
        requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
            .entity(message).build());
    }
    
}
