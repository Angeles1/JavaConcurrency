
package bloque4;


public class monitor {
    private int cont=0;
    
    public synchronized void inc(){ 
        for(int i =0; i<20000;i++){ //aseguramos la exclusion mutua!
            cont++; 
        }
    }
    
    public synchronized int getCont(){
        return cont;
    }

    
}
