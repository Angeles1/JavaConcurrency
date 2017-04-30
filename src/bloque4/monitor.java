
package bloque4;


public class monitor {
    private int cont=0;
    
    public synchronized int inc(){ 
        for(int i =0; i<20000;i++){ //aseguramos la exclusion mutua!
            cont++; 
        }
        return cont;
    }


    
}
