package bloque2.pkg1;

import java.util.Random;

public class principal implements Runnable{
    private int id;
    private static Random cerrojoA = new Random();
    private static Thread cerrojoB = new Thread();


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
        System.out.println("Soy el hilo principal");

    }

    @Override
    public void run() {
        //interbloqueo
        if( id % 2== 0){
            synchronized(cerrojoA){ //Activo: h0 Cola: h1 //50% de pos que ocurra interbloqueo
                mostrarA();
            }
        }
        else{
            synchronized(cerrojoB){ //Activo:h1 Cola:  h3 h0
                mostrarB();
            }
        }
    }
    
    private void mostrarA(){
        synchronized(cerrojoB){
            System.out.println("Soy el hilo: "+id); //h2
        }
    }
    
    private void mostrarB(){
        synchronized(cerrojoA){
            System.out.println("Soy el hilo: " +id);
        }
    }
}
