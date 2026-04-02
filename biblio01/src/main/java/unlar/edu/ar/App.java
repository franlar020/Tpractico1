package unlar.edu.ar;


import unlar.edu.ar.objets.Biblioteca;
import unlar.edu.ar.objets.Estudiante;
import unlar.edu.ar.objets.Libro;

public class App {
    public static void main(String[] args) {
        // 1. Creamos la biblioteca 
        Biblioteca miBiblioteca = new Biblioteca();

        // 2. Creación de estudiantes 
        Estudiante e1 = new Estudiante("101", "Juan Perez", "Sistemas", "juan@mail.com");
        Estudiante e2 = new Estudiante("102", "Ana Lopez", "Sistemas", "ana@mail.com");
        Estudiante e3 = new Estudiante("103", "Luis Gomez", "Licenciatura", "luis@mail.com");

        // Agregarlos al registro (HashMap) 
        miBiblioteca.registrarEstudiante(e1);
        miBiblioteca.registrarEstudiante(e2);
        miBiblioteca.registrarEstudiante(e3);

        // 3. Creación de libros 
        Libro l1 = new Libro("978-1", "Java POO", "Deitel", 2020, true);
        Libro l2 = new Libro("978-2", "Clean Code", "Robert Martin", 2008, true);
        Libro l3 = new Libro("978-3", "Algoritmos", "Sedgewick", 2011, true);
        Libro l4 = new Libro("978-4", "Patrones de Diseño", "GoF", 1994, true);
        Libro l5 = new Libro("978-5", "Maven Guide", "Apache", 2022, true);

        // Agregarlos al catálogo (ArrayList) 
        miBiblioteca.agregarLibro(l1);
        miBiblioteca.agregarLibro(l2);
        miBiblioteca.agregarLibro(l3);
        miBiblioteca.agregarLibro(l4);
        miBiblioteca.agregarLibro(l5);

        System.out.println("--- Sistema de Gestión de Biblioteca ---");
        System.out.println("Estudiantes registrados: " + miBiblioteca.getRegistroEstudiantes().size()); 
        System.out.println("Libros en catálogo: " + miBiblioteca.getCatalogoLibros().size());

        // --- PRUEBAS DE EXCEPCIONES ---
        System.out.println("\n--- Ejecutando Casos de Prueba ---");

        try {
            // Prueba exitosa
            miBiblioteca.registrarPrestamo("978-1", "101");
            
            // Forzar LibroNoDisponibleException (intentar el mismo libro)
            System.out.println("Re-intentando préstamo del mismo libro...");
            miBiblioteca.registrarPrestamo("978-1", "101");

        } catch (Exception e) {
            System.out.println("CAPTURA: " + e.getMessage());
        }

        try {
            // Forzar EstudianteNoEncontradoException 
            System.out.println("\nIntentando préstamo a legajo inexistente...");
            miBiblioteca.registrarPrestamo("978-2", "999");

        } catch (Exception e) {
            System.out.println("CAPTURA: " + e.getMessage());
        }


        //Calculo de multa con retraso de 15 días
        System.out.println("\n--- Probando Cálculo de Multa (Recursividad) ---");
        // Punto 5.6: Cálculo de multa con retraso de 15 días
        double valorLibro = 1000.0;
        int diasRetraso = 15;
        double multa = miBiblioteca.calcularMulta(diasRetraso, valorLibro);
        
        System.out.println("Valor del libro: $" + valorLibro);
        System.out.println("Días de retraso: " + diasRetraso);
        System.out.println("Multa calculada (1% por día): $" + multa);
    
    }
}