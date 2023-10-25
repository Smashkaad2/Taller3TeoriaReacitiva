package edu.javeriana.Javier.appwebJavier.Controlador;
import edu.javeriana.Javier.appwebJavier.Excepcion.RegistroNoEncontradoException;
import edu.javeriana.Javier.appwebJavier.Modelo.Nota;
import edu.javeriana.Javier.appwebJavier.Repositorio.RepositorioEstudiante;
import edu.javeriana.Javier.appwebJavier.Repositorio.RepositorioNota;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class ControladorNota {
    @Autowired
    private RepositorioEstudiante repositorioEstudiante;
    @Autowired
    private RepositorioNota repositorioNota;

    @GetMapping("/estudiante/{id}/notas")
    public ResponseEntity<List<Nota>> traeNotasPorEstudiante(@PathVariable Integer id) {
        if (!repositorioEstudiante.existsById(id))
            throw new RegistroNoEncontradoException("No existe un estudiante con id: " + id);
         List<Nota> notas = repositorioNota.findByEstudianteId(id);
         return new ResponseEntity<>(notas, HttpStatus.OK);
    }

    @PostMapping("/estudiante/{id}/nota")
    public ResponseEntity<Nota> creaNota(@PathVariable Integer id, @RequestBody Nota notar) {
        Nota nota = repositorioEstudiante.findById(id)
                .map(estudiante -> {
                    notar.setEstudiante(estudiante);
                    return repositorioNota.save(notar);
                }).orElseThrow(() -> new RegistroNoEncontradoException("No existe estudiante con ese id= " + id));
        return new ResponseEntity<>(nota, HttpStatus.CREATED);
    }
}
