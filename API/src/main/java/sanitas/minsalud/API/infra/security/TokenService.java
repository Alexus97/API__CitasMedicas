package sanitas.minsalud.API.infra.security;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import sanitas.minsalud.API.domain.usuario.Usuario;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String generarToken(Usuario usuario) {
        try {
            var algorithm = Algorithm.HMAC256( secret );
//                String token = JWT.create()
                return JWT.create()
                    .withIssuer("sanitas.minsalud.API")
                    .withSubject( usuario.getLogin() )
                    .withExpiresAt( fechaExpiracion() )
                    .sign( algorithm );
        } catch (JWTCreationException exception){
            // Invalid Signing configuration / Couldn't convert Claims.
            throw new RuntimeException("Error generate tokens JWT");
        }
    }

    private Instant fechaExpiracion() {

        return LocalDateTime.now().plusHours( 2 ).toInstant(ZoneOffset.of("-03:00") );
    }

    public String getSubject( String tokenJWT ) {
        try {
            var algorithm = Algorithm.HMAC256( secret );
            return JWT.require( algorithm )
                    // specify any specific claim validations
                    .withIssuer("sanitas.minsalud.API")
                    // reusable verifier instance
                    .build()
                    .verify( tokenJWT )
                    .getSubject();

        } catch ( JWTVerificationException exception ){
            // Invalid signature/claims
            throw new RuntimeException("Token JWT invalido o expirado!" + tokenJWT);
        }


    }
}
