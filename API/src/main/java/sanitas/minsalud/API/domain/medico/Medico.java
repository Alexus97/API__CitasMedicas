package sanitas.minsalud.API.domain.medico;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sanitas.minsalud.API.domain.direccion.Direccion;

@Table( name = "medicos" )
@Entity( name = "Medico" )
@Getter //Genera de forma automatica todos los getter
@NoArgsConstructor // Crea un constructor que no tiene ningun argumenteo.
@AllArgsConstructor //Crea un constructor que tiene todos los atributos de medico.
@EqualsAndHashCode( of = "id" ) //Sirve para identificar que dos objetos sean iguales si el id es igual, si tienen el mismo id entonces son iguales.
public class Medico {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;
    private Boolean activo;
    private String nombre;
    private String email;
    private String telefono;
    private String documento;

    @Enumerated( EnumType.STRING )
    private Especialidad    especialidad;

    @Embedded
    private  Direccion direccion;

    public Medico( DatosRegistroMedico datos ) {
        this.id = null;
        this.activo = true;
        this.nombre = datos.nombre();
        this.email = datos.email();
        this.telefono = datos.telefono();
        this.documento = datos.documento();
        this.especialidad = datos.especialidad();
        this.direccion = new Direccion(datos.direccion());
    }

    public void actualizarInformaciones(@Valid DatosActualizacionMedico datos) {
        if( datos.nombre() != null ) {
            this.nombre = datos.nombre();
        }
        if( datos.telefono() != null ) {
            this.telefono = datos.telefono();
        }
        if( datos.direccion() != null ) {
            this.direccion.actualizarDireccion( datos.direccion() );
        }

    }

    public void eliminar() {
        this.activo = false;
    }
}
