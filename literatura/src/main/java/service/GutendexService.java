package service;



import model.Autor;
import model.Libro;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GutendexService {

    private final RestTemplate restTemplate = new RestTemplate();

    public Optional<Libro> buscarLibroPorTitulo(String titulo) {
        String url = "https://gutendex.com/books/?search=" + titulo.replace(" ", "%20");
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        if (response.getStatusCode() != HttpStatus.OK) {
            return Optional.empty();
        }

        JSONObject json = new JSONObject(response.getBody());
        JSONArray results = json.getJSONArray("results");

        if (results.isEmpty()) {
            return Optional.empty();
        }

        // Tomaremos el primer resultado como el libro encontrado
        JSONObject libroJson = results.getJSONObject(0);

        Libro libro = new Libro();
        libro.setId(libroJson.getInt("id"));
        libro.setTitulo(libroJson.getString("title"));
        JSONArray languages = libroJson.getJSONArray("languages");
        if (languages.isEmpty()) {
            libro.setIdioma("desconocido");
        } else {
            libro.setIdioma(languages.getString(0));
        }

        // Parseo de Autores
        List<Autor> autores = new ArrayList<>();
        JSONArray autoresJson = libroJson.getJSONArray("authors");

        for (int i = 0; i < autoresJson.length(); i++) {
            JSONObject autorJson = autoresJson.getJSONObject(i);
            Autor autor = new Autor();

            if (autorJson.has("id")) {
                autor.setGutendexId(autorJson.getInt("id"));
            }

            autor.setNombre(autorJson.optString("name", "Desconocido"));


            // Si tiene año de nacimiento
            if (!autorJson.isNull("birth_year")) {
                autor.setAñoNacimiento(autorJson.getInt("birth_year"));
            }

            // Si tiene año de fallecimiento
            if (!autorJson.isNull("death_year")) {
                autor.setAñoFallecimiento(autorJson.getInt("death_year"));
            }

            autores.add(autor);
        }

        libro.setAutores(autores);

        return Optional.of(libro);
    }
}
