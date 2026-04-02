package unlar.edu.ar.objets;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import unlar.edu.ar.exception.*; // Los imports de excepciones

public class Biblioteca {
    // 2.2 Estructuras de Datos 
    private ArrayList<Libro> catalogoLibros; // Catálogo 
    private HashMap<String, Estudiante> registroEstudiantes; // Registro (legajo es clave) 
    private HashSet<Prestamo> prestamosActivos; // Préstamos activos sin duplicados 

    public Biblioteca() {
        this.catalogoLibros = new ArrayList<>();
        this.registroEstudiantes = new HashMap<>();
        this.prestamosActivos = new HashSet<>();
    }

    // Métodos de carga básicos
    public void agregarLibro(Libro libro) {
        catalogoLibros.add(libro);
    }

    public void registrarEstudiante(Estudiante estudiante) {
        registroEstudiantes.put(estudiante.getLegajo(), estudiante);
    }

    // 2.4 Registrar préstamo con validaciones 
    public void registrarPrestamo(String isbn, String legajo) 
            throws LibroNoDisponibleException, EstudianteNoEncontradoException, LimitePrestamosExcedidoException {
        
        // 1. Validar si el estudiante existe 
        if (!registroEstudiantes.containsKey(legajo)) {
            throw new EstudianteNoEncontradoException("El legajo " + legajo + " no existe.");
        }
        Estudiante est = registroEstudiantes.get(legajo);

        // 2. Validar si el libro existe y está disponible
        Libro libEncontrado = null;
        for (Libro l : catalogoLibros) {
            if (l.getIsbn().equals(isbn)) {
                libEncontrado = l;
                break;
            }
        }

        if (libEncontrado == null || !libEncontrado.isDisponible()) {
            throw new LibroNoDisponibleException("El libro no está disponible para préstamo.");
        }

        // 3. Validar límite de 3 préstamos por estudiante 
        long contador = prestamosActivos.stream()
                        .filter(p -> p.getEstudiante().getLegajo().equals(legajo))
                        .count();
        
        if (contador >= 3) {
            throw new LimitePrestamosExcedidoException("El estudiante ya superó el límite de 3 libros.");
        }

        // Si todo está bien, registramos el préstamo
        Prestamo nuevo = new Prestamo(libEncontrado, est, java.time.LocalDate.now());
        prestamosActivos.add(nuevo);
        libEncontrado.setDisponible(false); // Cambiamos el estado del libro [cite: 18]
        
        System.out.println("Prestamo registrado: " + libEncontrado.getTitulo());
    }

    // Getters para la main
    public ArrayList<Libro> getCatalogoLibros() { return catalogoLibros; }
    public HashMap<String, Estudiante> getRegistroEstudiantes() { return registroEstudiantes; }
}