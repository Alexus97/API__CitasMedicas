package sanitas.minsalud.API.domain.paciente;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import sanitas.minsalud.API.domain.direccion.DatosDireccion;

public record DatosRegistroPaciente(
        @NotBlank String nombre,
        @NotBlank @Email String email,
        @NotBlank String telefono,
        @NotBlank @Pattern(regexp = "\\d{7,10}") String documento_identidad,
        @NotNull @Valid DatosDireccion direccion

) {
}
