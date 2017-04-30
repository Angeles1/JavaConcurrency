
package bloque4.pkg1;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class principal implements Runnable{
    private static int tam=8;
    private static int[][] matriz = new int[tam][tam];
    private int fila;
    public principal(int fila){
        this.fila=fila;
    }
    @Override
    public void run(){
        for(int i =0; i<tam;i++){
            matriz[fila][i]*=10;
        }
    }

    public static void main(String[] args) {
        Random rand = new Random(System.nanoTime());
        
        double tiempo_inicio, tiempo_final;
        
        for(int i =0; i<matriz.length;i++){
            for(int j =0; j< matriz[0].length;j++){
                matriz[i][j]= rand.nextInt(10);
            }
        }
        tiempo_inicio= System.nanoTime();
        
        ExecutorService pool = Executors.newCachedThreadPool();
        
        for(int i =0; i<tam; i++){
            Runnable runnable = new principal(i);
            pool.execute(runnable);
        }
        
        pool.shutdown();
        
        while(!pool.isTerminated()); //preguntamos si el pool ha terminado
            
        
        
        /*Thread[] hilos = new Thread [tam];
        
        for(int i =0; i<hilos.length;i++){
            Runnable runnable = new principal(i);
            hilos[i] = new Thread(runnable);
            hilos[i].start();
        }
        
        for(int i =0; i<hilos.length;i++){
            try{
                hilos[i].join();
            }catch(Exception e){}
        }*/
        tiempo_final= System.nanoTime() - tiempo_inicio;
        System.out.println((tiempo_final/1000)+" segundos");
        
       /* for(int i =0;i<matriz.length;i++){
            for(int j =0; j<matriz[0].length;j++){
                System.out.print(matriz[i][j]+ " ");
            }
            System.out.println();
        }*/

    }
    
}
