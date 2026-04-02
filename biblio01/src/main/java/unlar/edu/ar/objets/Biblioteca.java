package unlar.edu.ar.objets;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import unlar.edu.ar.exception.*; // Importamos las excepciones personalizadas

public class Biblioteca {
    // 2.2 Estructuras de Datos 
    private ArrayList<Libro> catalogoLibros; // 
    private HashMap<String, Estudiante> registroEstudiantes; // Clave: legajo 
    private HashSet<Prestamo> prestamosActivos; // Evita duplicados 

    public Biblioteca() {
        this.catalogoLibros = new ArrayList<>();
        this.registroEstudiantes = new HashMap<>();
        this.prestamosActivos = new HashSet<>();
    }

    // Métodos de carga inicial
    public void agregarLibro(Libro libro) {
        catalogoLibros.add(libro);
    }

    public void registrarEstudiante(Estudiante estudiante) {
        registroEstudiantes.put(estudiante.getLegajo(), estudiante);
    }

    // 2.4 Registrar préstamo con todas las validaciones 
    public void registrarPrestamo(String isbn, String legajo) 
            throws LibroNoDisponibleException, EstudianteNoEncontradoException, LimitePrestamosExcedidoException {
        
        // 1. Validar si el estudiante existe 
        if (!registroEstudiantes.containsKey(legajo)) {
            throw new EstudianteNoEncontradoException("El legajo " + legajo + " no existe.");
        }
        Estudiante est = registroEstudiantes.get(legajo);

        // 2. Validar disponibilidad del libro
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

        // Si pasa las validaciones, se registra el préstamo
        Prestamo nuevo = new Prestamo(libEncontrado, est, java.time.LocalDate.now());
        prestamosActivos.add(nuevo);
        libEncontrado.setDisponible(false); // [cite: 34]
        
        System.out.println("Préstamo registrado con éxito: " + libEncontrado.getTitulo());
    }

    // 2.4 Buscar libros: búsqueda parcial (case-insensitive) 
    public void buscarLibro(String parteDelTitulo) {
        System.out.println("Resultados para: " + parteDelTitulo);
        for (Libro l : catalogoLibros) {
            if (l.getTitulo().toLowerCase().contains(parteDelTitulo.toLowerCase())) {
                System.out.println(l);
            }
        }
    }

    // 2.4 Listar préstamos por estudiante 
    public void listarPrestamosEstudiante(String legajo) {
        System.out.println("Préstamos del legajo " + legajo + ":");
        for (Prestamo p : prestamosActivos) {
            if (p.getEstudiante().getLegajo().equals(legajo)) {
                System.out.println(p);
            }
        }
    }

    // 2.4 Registrar devolución 
    public void registrarDevolucion(String isbn) {
        Prestamo aEliminar = null;
        for (Prestamo p : prestamosActivos) {
            if (p.getLibro().getIsbn().equals(isbn)) {
                aEliminar = p;
                break;
            }
        }
        if (aEliminar != null) {
            prestamosActivos.remove(aEliminar);
            aEliminar.getLibro().setDisponible(true); // Liberar libro 
            System.out.println("Devolución procesada.");
        }
    }

    // 2.5 Método recursivo para calcular multa 
    public double calcularMulta(int diasRetraso, double valorLibro) {
        // Caso base y límite de 30 días 
        if (diasRetraso <= 0 || diasRetraso > 30) {
            return 0;
        }
        // 1% por día de retraso calculado recursivamente 
        return (valorLibro * 0.01) + calcularMulta(diasRetraso - 1, valorLibro);
    }

    // Getters necesarios para la App.java 
    public ArrayList<Libro> getCatalogoLibros() { return catalogoLibros; }
    public HashMap<String, Estudiante> getRegistroEstudiantes() { return registroEstudiantes; }
}