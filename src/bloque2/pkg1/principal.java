package bloque2.pkg1;

import java.util.Random;

public class principal implements Runnable{
    private int id;
    private static Random cerrojoA = new Random();
    private static Thread cerrojoB = new Thread();
    
    private int cont_private = 0; // no compartida!
    private static int cont=0;


    public principal(int id){
        this.id= id;
    }
 
    public static void main(String[] args) {
        Runtime runtime = Runtime.getRuntime();
        int nNucleos = runtime.availableProcessors();
        
        //Thread[] hilos = new Thread[nNucleos];
        Thread[] hilos = new Thread[8];
      
        for (int i =0; i<hilos.length;i++){
            Runnable runnable= new principal(i);
            hilos[i]=new Thread(runnable);
            hilos[i].start();
        }
      
        for(int i=0; i<hilos.length;i++){
            try{
                hilos[i].join(); 
            }catch (Exception e){}
        }
        System.out.println("Soy el hilo principal, la variable cont vale: "+cont);

    }

    @Override
    public void run() {
        for(int i =0; i<20000 ;i++){ //cada hilo hace el bucle simultaneamente incrementano su variable privada
            cont_private++; //private al final = 20.000
        }
  
        synchronized(cerrojoA){
            cont+=cont_private; //sumamos con sincronizacion la variables privada de cada hilo en orden!
        }
    }
    
}
