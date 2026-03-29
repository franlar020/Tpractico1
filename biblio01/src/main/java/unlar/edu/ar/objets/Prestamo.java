package unlar.edu.ar.objets;

import java.time.LocalDate; 

public class Prestamo {
    // Atributos privados (Encapsulamiento) 
    private Libro libro;
    private Estudiante estudiante;
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucion;

    // Constructor por defecto 
    public Prestamo() {}

    // Constructor parametrizado [cite: 47]
    public Prestamo(Libro libro, Estudiante estudiante, LocalDate fechaPrestamo) {
        this.libro = libro;
        this.estudiante = estudiante;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = null; // Se setea cuando devuelven el libro
    }

    // Getters y Setters 
    public Libro getLibro() { return libro; }
    public void setLibro(Libro libro) { this.libro = libro; }

    public Estudiante getEstudiante() { return estudiante; }
    public void setEstudiante(Estudiante estudiante) { this.estudiante = estudiante; }

    public LocalDate getFechaPrestamo() { return fechaPrestamo; }
    public void setFechaPrestamo(LocalDate fechaPrestamo) { this.fechaPrestamo = fechaPrestamo; }

    public LocalDate getFechaDevolucion() { return fechaDevolucion; }
    public void setFechaDevolucion(LocalDate fechaDevolucion) { this.fechaDevolucion = fechaDevolucion; }

    // Punto 2.2: equals y hashCode 
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Prestamo prestamo = (Prestamo) o;
        return libro.equals(prestamo.libro) && estudiante.equals(prestamo.estudiante);
    }

    @Override
    public int hashCode() {
        int result = libro.hashCode();
        result = 31 * result + estudiante.hashCode();
        return result;
    }


    @Override
    public String toString() {
        return "Préstamo: " + libro.getTitulo() + " -> " + estudiante.getNombre();
    }
}