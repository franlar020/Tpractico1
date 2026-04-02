package unlar.edu.ar.objets;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Biblioteca {
    // 2.2 Catálogo de libros: ArrayList (permite orden y duplicados si es necesario)
    private ArrayList<Libro> catalogoLibros;
    
    // 2.2 Registro de estudiantes: HashMap (clave: legajo)
    private HashMap<String, Estudiante> registroEstudiantes;
    
    // 2.2 Préstamos activos: HashSet (evita duplicados)
    private HashSet<Prestamo> prestamosActivos;

    public Biblioteca() {
        this.catalogoLibros = new ArrayList<>();
        this.registroEstudiantes = new HashMap<>();
        this.prestamosActivos = new HashSet<>();
    }

    // Métodos básicos para agregar datos
    public void agregarLibro(Libro libro) {
        catalogoLibros.add(libro);
    }

    public void registrarEstudiante(Estudiante estudiante) {
        registroEstudiantes.put(estudiante.getLegajo(), estudiante);
    }

    // Getters para que App.java pueda ver las listas
    public ArrayList<Libro> getCatalogoLibros() { return catalogoLibros; }
    public HashMap<String, Estudiante> getRegistroEstudiantes() { return registroEstudiantes; }
    public HashSet<Prestamo> getPrestamosActivos() { return prestamosActivos; }
}