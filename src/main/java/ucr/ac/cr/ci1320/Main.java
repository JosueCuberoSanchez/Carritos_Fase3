package ucr.ac.cr.ci1320;
import ucr.ac.cr.ci1320.router.Router;

/**
 * Universidad de Costa Rica
 * Facultad de Ingeniería
 * Escuela de Ciencias de la Computación e Informática
 * Profesora: Gabriela Barrantes
 * Autores:
 * Abellán Jiménez Mariana B50031
 * Brenes Solano Silvia B41133
 * Cubero Sánchez Josué B42190
 */
public class Main {

    public static void main(String[] args) {
       // Controller controller = new Controller();
        //controller.start();

        Router router = new Router(3, 1, "10.1.130.141", 7777);
        router.startController();
    }
}
