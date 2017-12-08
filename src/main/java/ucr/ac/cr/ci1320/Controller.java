package ucr.ac.cr.ci1320;

import ucr.ac.cr.ci1320.router.Router;

import java.util.Scanner;
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

public class Controller {
    private int hosts;

    public Controller(){}

    public void start(){
        int interfaces;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input the desired number of hosts");
        this.hosts = scanner.nextInt();
        while(this.hosts < 6 && this.hosts > 10){
            System.out.println("Input a valid host quantity, between 6 and 10");
            this.hosts = scanner.nextInt();
        }
        for(int i=0;i<this.hosts;i++) {
            System.out.println("Input the desired number of interfaces for "+i+" host");
            interfaces = scanner.nextInt();
            while (interfaces < 1 && interfaces > 5) {
                System.out.println("Input a valid number, between 1 and 5");
                interfaces = scanner.nextInt();
            }
            if (interfaces == 1) { //es un nodo terminal

            } else {
               // Thread thread = new Thread(new Router(interfaces,i, "localhost", 6666, listSize));
                //thread.start();
            }
        } //termina for
    }
}
