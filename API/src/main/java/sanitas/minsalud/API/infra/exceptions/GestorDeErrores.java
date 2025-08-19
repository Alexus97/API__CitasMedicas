package sanitas.minsalud.API.infra.exceptions;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice // Gestion de errores.
public class GestorDeErrores  {
    //La anotacion @ExceptionHandler se utiliza para capturar los errores en spring.
    @ExceptionHandler( EntityNotFoundException.class)
    public ResponseEntity gestionarError404() {
        return ResponseEntity.notFound().build();

    }

    @ExceptionHandler( MethodArgumentNotValidException.class)
    public ResponseEntity gestionarError400( MethodArgumentNotValidException ex) {
        var errores = ex.getFieldErrors();
        return ResponseEntity.badRequest().body( errores.stream().map( DatosErrorValidacion::new ).toList() );

    }

    public record DatosErrorValidacion( String campo, String mensaje ) {
        public DatosErrorValidacion(FieldError error) {
            this( error.getField(), error.getDefaultMessage() );
        }

    }


}
