# Biblioteca Spring Boot

Proyecto de ejemplo para la gestión de libros y autores, integrando la API de Gutendex.

## Características

- Registro y consulta de libros por título usando Gutendex.
- Listado de libros y autores registrados.
- Consulta de autores vivos en un año específico.
- Listado de libros por idioma.

## Tecnologías

- Java
- Spring Boot
- Maven

## Estructura del proyecto

- `model/`: Entidades `Libro` y `Autor`.
- `repository/`: Repositorios JPA para acceso a datos.
- `service/`: Lógica de negocio y comunicación con Gutendex.
- `controller/`: Endpoints REST (agregar según implementación).

## Configuración

1. Clona el repositorio.
2. Configura la base de datos en `application.properties`.
3. Ejecuta el proyecto con:

   ## Uso

- Accede a los endpoints REST para gestionar libros y autores.
- Consulta la documentación de la API para detalles de uso.

