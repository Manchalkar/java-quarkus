package org.acme;
import java.util.HashSet;
import io.smallrye.jwt.build.Jwt;
import jakarta.inject.Singleton;
import org.acme.UserCredentials;
import java.util.Set;
import java.util.*;


@Singleton
public class TokenGeneratorService {
    
    public String generateJwt(UserCredentials userDetails){
        
        Set<String> roles =new HashSet<>(Arrays.asList("user"));

        Map<String, Object> claims = new HashMap<>();
        claims.put("username", userDetails.getUsername());
        return Jwt.claim("username" , userDetails.getUsername() ).issuer("someone").subject("some").groups(roles).expiresAt(System.currentTimeMillis()+36000).sign();
    }
}
