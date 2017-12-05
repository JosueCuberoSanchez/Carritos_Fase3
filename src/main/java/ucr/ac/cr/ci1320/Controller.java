package ucr.ac.cr.ci1320;

import ucr.ac.cr.ci1320.router.RouterController;

import java.util.Scanner;

/**
 * Created by josue on 05/12/17.
 */
public class Controller {

    public Controller(){

    }

    public void start(){
        int interfaces;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input the desired number of interfaces");
        interfaces = scanner.nextInt();
        while(1 <= interfaces && interfaces <= 5){
            System.out.println("Input a valid number, between 1 and 5");
            interfaces = scanner.nextInt();
        }
        if(interfaces == 1){ //es un nodo terminal

        } else {
            RouterController routerController = new RouterController(interfaces);
            try {
                routerController.startController();
            } catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
