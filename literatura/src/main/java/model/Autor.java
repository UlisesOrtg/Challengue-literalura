package model;


import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getGutendexId() {
        return gutendexId;
    }

    public void setGutendexId(Integer gutendexId) {
        this.gutendexId = gutendexId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getAñoNacimiento() {
        return añoNacimiento;
    }

    public void setAñoNacimiento(Integer añoNacimiento) {
        this.añoNacimiento = añoNacimiento;
    }

    public Integer getAñoFallecimiento() {
        return añoFallecimiento;
    }

    public void setAñoFallecimiento(Integer añoFallecimiento) {
        this.añoFallecimiento = añoFallecimiento;
    }

    private Integer gutendexId;
    private String nombre;
    private Integer añoNacimiento;
    private Integer añoFallecimiento;

    @ManyToMany(mappedBy = "autores")
    private Set<Libro> libros = new HashSet<>();

    // Getters y Setters
}
