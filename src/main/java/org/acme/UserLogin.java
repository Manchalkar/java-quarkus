package org.acme;

import org.jboss.logging.Logger;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import org.acme.UserCredentials;
import org.acme.TokenGeneratorService;


@Path("/auth")
public class UserLogin {

    @Inject
    Logger logger;

    @Inject
    TokenGeneratorService tokenGeneratingService;

    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    public Token login(UserCredentials userDetails) {
        
        logger.info("Logging User Details => "+ userDetails.getUsername());

        Token jwt = tokenGeneratingService.generateJwt();

        return jwt;
    }

    @GET
    @Path("/getToken")
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    public Token getNewToken() {
        Token jwt = tokenGeneratingService.generateJwt();
        return jwt;
    }
}
