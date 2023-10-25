package edu.javeriana.Javier.appwebJavier.Repositorio;
import edu.javeriana.Javier.appwebJavier.Modelo.Nota;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepositorioNota extends JpaRepository<Nota, Integer> {
    List<Nota> findByEstudianteId(Integer id);

}