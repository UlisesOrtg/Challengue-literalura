package console;

import model.Autor;
import model.Libro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import service.BibliotecaService;

import java.util.List;
import java.util.Scanner;

@Component
public class MenuConsola implements CommandLineRunner {

    @Autowired
    private BibliotecaService bibliotecaService;

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void run(String... args) {
        int opcion = -1;

        while (opcion != 0) {
            mostrarMenu();
            try {
                opcion = Integer.parseInt(scanner.nextLine());
                ejecutarOpcion(opcion);
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un número válido.");
            }
        }

        System.out.println("Aplicación finalizada.");
    }

    private void mostrarMenu() {
        System.out.println("\n=== MENÚ BIBLIOTECA ===");
        System.out.println("1. Buscar libro por título");
        System.out.println("2. Listar libros registrados");
        System.out.println("3. Listar autores registrados");
        System.out.println("4. Listar autores vivos en un determinado año");
        System.out.println("5. Listar libros por idioma");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opción: ");
    }

    private void ejecutarOpcion(int opcion) {
        switch (opcion) {
            case 1 -> buscarLibroPorTitulo();
            case 2 -> listarLibros();
            case 3 -> listarAutores();
            case 4 -> listarAutoresVivos();
            case 5 -> listarLibrosPorIdioma();
            case 0 -> System.out.println("Saliendo...");
            default -> System.out.println("Opción inválida, intente de nuevo.");
        }
    }

    private void buscarLibroPorTitulo() {
        System.out.print("Ingrese el título del libro: ");
        String titulo = scanner.nextLine();
        String resultado = bibliotecaService.buscarYRegistrarLibro(titulo);
        System.out.println(resultado);
    }

    private void listarLibros() {
        List<Libro> libros = bibliotecaService.listarLibros();
        if (libros.isEmpty()) {
            System.out.println("No hay libros registrados.");
            return;
        }

        System.out.println("\n--- Libros Registrados ---");
        for (Libro libro : libros) {
            System.out.println("ID: " + libro.getId() + " | Título: " + libro.getTitulo() + " | Idioma: " + libro.getIdioma());
        }
    }

    private void listarAutores() {
        List<Autor> autores = bibliotecaService.listarAutores();
        if (autores.isEmpty()) {
            System.out.println("No hay autores registrados.");
            return;
        }

        System.out.println("\n--- Autores Registrados ---");
        for (Autor autor : autores) {
            System.out.println("Nombre: " + autor.getNombre() +
                    " | Nacimiento: " + (autor.getAñoNacimiento() != null ? autor.getAñoNacimiento() : "N/D") +
                    " | Fallecimiento: " + (autor.getAñoFallecimiento() != null ? autor.getAñoFallecimiento() : "N/D"));
        }
    }

    private void listarAutoresRegistrados() {
        List<Libro> libros = bibliotecaService.listarAutoresRegistrados();

        if (libros.isEmpty()) {
            System.out.println("No hay libros registrados.");
            return;
        }

        System.out.println("Lista de Autores Registrados:");

        for (Libro libro : libros) {
            System.out.println("Libro: " + libro.getTitulo());
            System.out.println("Autores:");
            for (Autor autor : libro.getAutores()) {
                System.out.println(" - " + autor.getNombre());
            }
            System.out.println("-------------------------------");
        }
    }

    private void listarAutoresVivos() {
        System.out.print("Ingrese el año: ");
        int año = Integer.parseInt(scanner.nextLine());

        List<Autor> autores = bibliotecaService.listarAutoresVivosEnAño(año);
        if (autores.isEmpty()) {
            System.out.println("No se encontraron autores vivos en el año " + año);
            return;
        }

        System.out.println("\n--- Autores vivos en el año " + año + " ---");
        for (Autor autor : autores) {
            System.out.println("Nombre: " + autor.getNombre() + " | Falleció en: " + autor.getAñoFallecimiento());
        }
    }

    private void listarLibrosPorIdioma() {
        System.out.print("Ingrese el código del idioma (ej: 'en' para inglés, 'es' para español): ");
        String idioma = scanner.nextLine();

        List<Libro> libros = bibliotecaService.listarLibrosPorIdioma(idioma);
        if (libros.isEmpty()) {
            System.out.println("No se encontraron libros en el idioma: " + idioma);
            return;
        }

        System.out.println("\n--- Libros en idioma '" + idioma + "' ---");
        for (Libro libro : libros) {
            System.out.println("ID: " + libro.getId() + " | Título: " + libro.getTitulo());
        }
    }
}
