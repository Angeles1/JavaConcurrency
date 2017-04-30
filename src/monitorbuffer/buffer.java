
package monitorbuffer;

import java.util.Random;
import java.util.Vector;


public class buffer {
    
    private Random random = new Random(System.nanoTime());
    
    private int tam=10;
    private int pos= -1;//VACIO
    private Vector<Integer> cola = new Vector();
    
    public synchronized int leer(){
        int elemento = -1;
        while(pos<0){
            try{
                System.out.println("El vector esta vacio y el consumidor se va a dormir");
                wait();
            }catch(Exception e){}
        }
        elemento = cola.get(pos);
        cola.remove(pos);
        pos--;
        
        return elemento;
    }
    
    public synchronized void escribir(){
        pos++;
        while(pos>=tam){
            try{
                System.out.println("El vector esta lleno");
                pos--;
            }catch(Exception e){}
        }
        cola.add(generar()); //comportamiento de reentrancia, no se interbloquea con generar
        notifyAll();
        
    }
    
    public synchronized int generar(){
        return random.nextInt(10);
    }

}
