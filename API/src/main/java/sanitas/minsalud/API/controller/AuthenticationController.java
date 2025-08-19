package sanitas.minsalud.API.controller;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sanitas.minsalud.API.domain.usuario.DatosAutenticacion;
import sanitas.minsalud.API.domain.usuario.Usuario;
import sanitas.minsalud.API.infra.security.DatosTokenJWT;
import sanitas.minsalud.API.infra.security.TokenService;


@RestController
@RequestMapping("/login")
public class AuthenticationController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager manager;

    @PostMapping
    public ResponseEntity iniciarSesion( @RequestBody @Valid DatosAutenticacion datos ) {

//        var token = new UsernamePasswordAuthenticationToken( datos.login(), datos.contrasena());
//        var authentication = manager.authenticate( token );
//
//        var tokenJWT = tokenService.generarToken((Usuario) authentication.getPrincipal());
//
//        return ResponseEntity.ok(tokenService.generarToken((Usuario) authentication.getPrincipal()));

//        try{
//            var authenticationToken = new UsernamePasswordAuthenticationToken(datos.login(), datos.contrasena());
//            var authentication = manager.authenticate(authenticationToken);
//
//            var tokenJWT = tokenService.generarToken((Usuario) authentication.getPrincipal());
//
//            return ResponseEntity.ok(new DatosTokenJWT( tokenJWT ));
//
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }

//        var authenticationToken = new UsernamePasswordAuthenticationToken(datos.login(), datos.contrasena());
//        var autenticacion = manager.authenticate(authenticationToken);
//
//        var tokenJWT = tokenService.generarToken((Usuario) autenticacion.getPrincipal());
//
//        return ResponseEntity.ok(new DatosTokenJWT(tokenJWT));

        var authenticationToken = new UsernamePasswordAuthenticationToken(datos.login(), datos.contrasena());
        var authentication = manager.authenticate(authenticationToken);
        var tokenJWT = tokenService.generarToken((Usuario) authentication.getPrincipal());
        return ResponseEntity.ok(new DatosTokenJWT(tokenJWT));


    }
}
