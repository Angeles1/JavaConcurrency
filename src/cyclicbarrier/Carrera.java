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
     etapa(0);
     etapa(1);
     etapa(2);
     
     tiempos[id][3]= tiempos[id][0] + tiempos[id][1] + tiempos[id][2];
    }
    
    private void etapa(int numEtapa){
        inicio= System.nanoTime();
        try{
            Thread.sleep(rand.nextInt(100)+100);
        }catch (Exception e){}
        
        total = System.nanoTime()-inicio;
        tiempos[id][numEtapa] = total;
        
        try{
            barrera.await();
            barrera.reset();
        }catch(Exception e){}
    }
    
    public static double[][] getTiempos(){
        return tiempos;
    }

}
