package unlar.edu.ar.exception;

// Heredamos de Exception para que sea una excepción controlada
public class LibroNoDisponibleException extends Exception {
    public LibroNoDisponibleException(String mensaje) {
        super(mensaje);
    }
}