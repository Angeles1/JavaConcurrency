package cyclicbarrier;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;

public class Carrera implements Runnable {
    private int id;
    private double inicio, total;
    private Random rand= new Random(System.nanoTime());
    //MATRIZ DE RESULTADOS
    private static double[][] tiempos;
    private static CyclicBarrier barrera;
    
    public Carrera(int id){
        this.id=id;
     
    } 

    public static void setCarrera(int participantes){
        tiempos = new double[participantes][4];
        barrera = new CyclicBarrier(participantes);
    }
    
    @Override
    public void run() {
        //Etapa1
        inicio= System.nanoTime();
        
        try{
            Thread.sleep(rand.nextInt(100)+100);
        }catch (Exception e){}
        
        total = System.nanoTime()-inicio;
        tiempos[id][0] = total;
        
        try{
            barrera.await();
        }catch(Exception e){}
        
        
    }
    public static double[][] getTiempos(){
        return tiempos;
    }

}
