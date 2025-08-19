package sanitas.minsalud.API.domain.paciente;

public record DatosListaPaciente(
        String nombre,
        String email,
        String documentoIdentidad
) {
    public DatosListaPaciente(Paciente paciente) {
        this(paciente.getNombre(), paciente.getEmail(), paciente.getDocumentoIdentidad());
    }
}
