package org.acme;
import java.util.HashSet;
import io.smallrye.jwt.build.Jwt;
import jakarta.inject.Singleton;
import java.util.Set;
import java.util.*;


@Singleton
public class TokenGeneratorService {
    
    public Token generateJwt(){
        
        Set<String> roles =new HashSet<>(Arrays.asList("user"));


        String accessToken = Jwt.issuer("someone").subject("accessToken").groups(roles).expiresAt(System.currentTimeMillis()+3600000).sign();
        String refreshToken = Jwt.issuer("someone").subject("refreshToken").groups(roles).expiresAt(System.currentTimeMillis()+4600000).sign();
        Token token = new Token(accessToken, refreshToken);
        return token;
    }
}
