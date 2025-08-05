package service;



import model.Autor;
import model.Libro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.AutorRepository;
import repository.LibroRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BibliotecaService {

    @Autowired
    private LibroRepository libroRepo;

    @Autowired
    private AutorRepository autorRepo;

    @Autowired
    private GutendexService gutendexService;

    /**
     * Buscar un libro por título y registrarlo si no existe.
     */
    public String buscarYRegistrarLibro(String titulo) {
        Optional<Libro> optionalLibro = gutendexService.buscarLibroPorTitulo(titulo);

        if (optionalLibro.isEmpty()) {
            return "Libro no encontrado en la API de Gutendex.";
        }

        Libro libro = optionalLibro.get();

        // Validar si el libro YA existe en la base de datos por su ID único
        if (libroRepo.existsById(libro.getId())) {
            return "El libro ya está registrado en la base de datos.";
        }

        // Guardar libro y autores
        libroRepo.save(libro);
        return "Libro registrado correctamente.";
    }

    /**
     * Listar todos los libros registrados.
     */
    public List<Libro> listarLibros() {
        return libroRepo.findAll();
    }

    /**
     * Listar todos los autores registrados.
     */
    public List<Autor> listarAutores() {
        return autorRepo.findAll();
    }

    public List<Libro> listarAutoresRegistrados() {
        return libroRepo.findAllWithAutoresDistinct();
    }

    /**
     * Listar autores vivos en un determinado año.
     */
    public List<Autor> listarAutoresVivosEnAño(int año) {
        return autorRepo.findByAñoFallecimientoGreaterThan(año);
    }

    /**
     * Listar libros por idioma.
     */
    public List<Libro> listarLibrosPorIdioma(String idioma) {
        return libroRepo.findByIdioma(idioma);
    }
}
