# Solución al Problema de Búsqueda de Libros

## Problema
Al buscar cualquier libro en la aplicación, aparecía el mensaje "Ingrese una opción válida" en lugar de mostrar los resultados de la búsqueda o un mensaje de error apropiado.

## Causa Raíz
Después de analizar el código, se identificó que el problema estaba en la clase `MenuConsola`:

1. **Manejo de excepciones insuficiente**: La clase `MenuConsola` solo capturaba excepciones de tipo `NumberFormatException` en el bucle principal, pero no manejaba otras excepciones que podían ocurrir durante la ejecución de las operaciones del menú.

2. **Propagación de excepciones**: Cuando ocurría una excepción en cualquiera de los métodos de operación del menú (como `buscarYRegistrarLibro`), esta excepción se propagaba hasta el bucle principal y terminaba la aplicación, mostrando el mensaje genérico "Ingrese una opción válida" en la siguiente iteración.

## Solución Implementada

Se realizaron las siguientes mejoras en la clase `MenuConsola`:

1. **Manejo de excepciones general en el bucle principal**:
   - Se agregó un bloque `catch (Exception e)` para capturar cualquier excepción no manejada y mostrar un mensaje de error más descriptivo.

2. **Manejo de excepciones en cada método de operación**:
   - Se agregaron bloques try-catch en todos los métodos de operación del menú:
     - `buscarYRegistrarLibro()`
     - `listarLibros()`
     - `listarAutores()`
     - `listarAutoresVivosEnAnio()`
     - `listarLibrosPorIdioma()`

3. **Mensajes de error específicos**:
   - Se agregaron mensajes de error específicos para cada operación, lo que facilita la identificación del problema.
   - En el caso de `listarAutoresVivosEnAnio()`, se agregó un manejo específico para `NumberFormatException` para mostrar un mensaje más claro cuando el usuario ingresa un valor no numérico.

## Beneficios de la Solución

1. **Mejor experiencia de usuario**: Los usuarios ahora reciben mensajes de error claros y específicos cuando ocurre un problema, en lugar del mensaje genérico "Ingrese una opción válida".

2. **Mayor robustez**: La aplicación ahora puede manejar errores sin terminar abruptamente, permitiendo al usuario continuar usando otras funciones incluso si una operación específica falla.

3. **Facilidad de depuración**: Los mensajes de error más específicos facilitan la identificación y solución de problemas futuros.

## Recomendaciones Adicionales

1. **Implementar logging adecuado**: Reemplazar los mensajes de error por consola con un sistema de logging apropiado como SLF4J/Logback.

2. **Validación de entrada**: Agregar validación de entrada más robusta antes de procesar las solicitudes del usuario.

3. **Pruebas unitarias**: Crear pruebas unitarias para verificar el correcto funcionamiento de la aplicación en diferentes escenarios, incluyendo casos de error.