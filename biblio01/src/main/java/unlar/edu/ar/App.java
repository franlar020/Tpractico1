package unlar.edu.ar;


import unlar.edu.ar.objets.Estudiante;
import unlar.edu.ar.objets.Libro;

public class App {
    public static void main(String[] args) {
        // Creación de estudiantes 
        Estudiante e1 = new Estudiante("101", "Juan Perez", "Sistemas", "juan@mail.com");
        Estudiante e2 = new Estudiante("102", "Ana Lopez", "Sistemas", "ana@mail.com");
        Estudiante e3 = new Estudiante("103", "Luis Gomez", "Licenciatura", "luis@mail.com");

        // Creación de libros 
        Libro l1 = new Libro("978-1", "Java POO", "Deitel", 2020, true);
        Libro l2 = new Libro("978-2", "Clean Code", "Robert Martin", 2008, true);
        Libro l3 = new Libro("978-3", "Algoritmos", "Sedgewick", 2011, true);
        Libro l4 = new Libro("978-4", "Patrones de Diseño", "GoF", 1994, true);
        Libro l5 = new Libro("978-5", "Maven Guide", "Apache", 2022, true);

        System.out.println("--- Sistema de Gestión de Biblioteca ---");
        System.out.println(e1);
        System.out.println(l1);
    }
}*/

/*public class App 
{
    public static void main( String[] args )
    {
       System.out.println( "Hello World!" );

        System.out.println("probando xd ");

        System.out.println("probando xd 2   ");

        System.out.println("probando xd 3   "   );

        System.out.println("isma es un crack   "   );

         System.out.println("isma es un crack 2   "   );

          System.out.println("isma es un crack 3   "   );
    }
}*/
