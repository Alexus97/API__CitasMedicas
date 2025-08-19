package sanitas.minsalud.API.infra.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import sanitas.minsalud.API.domain.usuario.UsuarioRepository;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //validacion del token.
//        System.out.println("Se llamo el filter!");
        System.out.println("FILTRO LLAMADO!");
        var tokenJWT = recuperarToken( request );
        if ( tokenJWT != null ) {
            var subject = tokenService.getSubject( tokenJWT );
            var usuario = repository.findByLogin(subject);

            var authentication = new UsernamePasswordAuthenticationToken( usuario, null, usuario.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication( authentication );
            System.out.println("Usuario logueado!");
        }

//        System.out.println( subject );
//        System.out.println( tokenJWT );

        filterChain.doFilter( request, response ); // Seguir con la cadena de filtros, si no se coloca no se continua con la cadena de filtros.
    }

    private String recuperarToken( HttpServletRequest request ) {
        var authorizationHeader = request.getHeader( "Authorization");
//        if ( authorizationHeader == null) {
//            throw new RuntimeException("Token JWT no enviado en el encabezado de Authorization");
//
//        }
//        return authorizationHeader.replace( "Bearer ", "");

        if ( authorizationHeader != null ) {
//            return authorizationHeader.replace( "Bearer ", "").trim();
            return authorizationHeader.replace("Bearer ", "").trim();
        }
        return null;
    }
}
