
package bloque5.pkg1;

import java.util.concurrent.locks.ReentrantLock;


public class Reentrant implements Runnable {
    private static ReentrantLock cerrojo = new ReentrantLock();
    private static int cont=0;
    
        
    public void run(){
        cerrojo.lock();
        for(int i =0; i<100000;i++){
                cont++;
            }
        cerrojo.unlock();
    }
    
    public static int getCont(){
        return cont;
    }
}
