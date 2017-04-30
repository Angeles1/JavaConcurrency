
package bloque5.pkg1;


public class Synchro implements Runnable {
    private static int cont=0;
    private static Object cerrojo= new Object();
    
    @Override
    public void run(){
        synchronized(cerrojo){
            for(int i =0; i<100000;i++){
                cont++;
            }
        }
    }
    
    public static int getCont(){
        return cont;
    }

}
