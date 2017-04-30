
package monitorbuffer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

public class principal implements Runnable{
    private int id;
    private static buffer buf= new buffer();
    
    public principal(int id){
        this.id=id;
    }
    
    @Override
    public void run(){
        if(id==0){
            int elemento;
            while(true){
                elemento= buf.leer();
                
                if(elemento != -1){
                    System.out.println("El elemento extraido del buffer es: "+elemento);
                }
                try {
                    Thread.sleep(400);//dormir un segundo
                } catch (InterruptedException ex) {}
            }
        }
        else{
            while(true){
                buf.escribir();
                try{
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {}
            } 
            
        }
    }
  
    public static void main(String[] args) {
        Runtime runtime = Runtime.getRuntime();
        int nNucleos = runtime.availableProcessors();
        
        ExecutorService poolLector = Executors.newFixedThreadPool(nNucleos);
        for(int i =0; i<nNucleos;i++){
            Runnable runnable=new principal(0);//consumidores con id 0
            poolLector.execute(runnable);
        }
        poolLector.shutdown();
        ExecutorService poolEscritor = Executors.newFixedThreadPool(2);
        for(int i =0; i<2; i++){
            Runnable runnable= new principal(1); //productores con id 1
            poolEscritor.execute(runnable);
        }
        poolEscritor.shutdown();
        
        while(!poolLector.isTerminated());
    }
    
    
}
