package bloque2.pkg1;

import java.util.Random;

public class principal implements Runnable{
    private int id;
    private static Random cerrojo = new Random();
    private static int cont=0;

    public principal(int id){
        this.id= id;
    }
 
    public static void main(String[] args) {
        Runtime runtime = Runtime.getRuntime();
        int nNucleos = runtime.availableProcessors();
        
        Thread[] hilos = new Thread[nNucleos];
      
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
        synchronized(cerrojo){
            while(id!= cont){
                try{
                    cerrojo.wait();//dormimos un hilo
                }catch (Exception e){}
            }
            System.out.println("Soy el hilo: "+id);
            cont++;
            cerrojo.notifyAll();//se despiertan los hilos
            
        }
    }
    
}
