package sanitas.minsalud.API.controller;


import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import sanitas.minsalud.API.domain.medico.*;

@RestController
@RequestMapping("/medicos")
public class    MedicoController {
    @Autowired
    private MedicoRepository repository;


    @Transactional
    @PostMapping
    public ResponseEntity registrar(@RequestBody @Valid DatosRegistroMedico datos, UriComponentsBuilder uriComponentsBuilder ) {
        var medico = new Medico( datos );
        repository.save( medico );

        var uri = uriComponentsBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();


//        System.out.println( datos );
        return ResponseEntity.created( uri ).body( new DatosDetalleMedico( medico ) );

    }

    @GetMapping
    public ResponseEntity< Page<DatosListaMedico> > listar(@PageableDefault( size = 10, sort = {"nombre"} ) Pageable paginacion ) {
//        return repository.findAllByActivoTrue( paginacion ).map(DatosListaMedico::new);
        var page = repository.findAllByActivoTrue( paginacion ).map(DatosListaMedico::new);

        return ResponseEntity.ok( page );
    }

    @GetMapping("/{id}")
    public ResponseEntity detallar ( @PathVariable Long id) {
        var medico = repository.getReferenceById( id );

        return ResponseEntity.ok( new DatosDetalleMedico( medico ) );



    }

    @Transactional
    @PutMapping
    public ResponseEntity actualizar(@RequestBody @Valid DatosActualizacionMedico datos) {
        var medico = repository.getReferenceById( datos.id() );
        medico.actualizarInformaciones( datos );

        return ResponseEntity.ok( new DatosDetalleMedico( medico ) );

    }

    @Transactional
    @DeleteMapping("/{id}")
//    public void eliminar(@PathVariable Long id) {
//        repository.deleteById( id ); realiza la eliminacion fisica y debe quedar es inactivo.
    public ResponseEntity eliminar (@PathVariable Long id) { // se utiliza la clase responseentity que nos puede devolver codigos http diferentes.
        var medico = repository.getReferenceById( id );
        medico.eliminar();

        return ResponseEntity.noContent().build(); // se utiliza el build para delvolver un responseEntity.


    }


}
