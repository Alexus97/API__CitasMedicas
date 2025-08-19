package sanitas.minsalud.API.domain.medico;

import jakarta.validation.constraints.NotNull;
import sanitas.minsalud.API.domain.direccion.DatosDireccion;

public record DatosActualizacionMedico(
        @NotNull Long id,
        String nombre,
        String telefono,
        DatosDireccion direccion
) {
}
