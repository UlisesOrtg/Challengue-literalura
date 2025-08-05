// LibroRepository.java
package repository;



import model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LibroRepository extends JpaRepository<Libro, Integer> {
    Optional<Libro> findByTitulo(String titulo);
    boolean existsById(Integer id);  // Asegúrate que el ID sea Integer (no String)

    List<Libro> findByIdioma(String idioma);



    @Query("SELECT DISTINCT l FROM Libro l JOIN FETCH l.autores")
    List<Libro> findAllWithAutoresDistinct();


}
