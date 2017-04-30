
package bloque4;


public class principal implements Runnable{
    private static monitor mon = new monitor();// es el cerrojo!
    
    @Override
    public void run() {
        mon.inc();
        System.out.println(mon.getCont());
    }
    public static void main(String[] args) {
        Runtime runtime= Runtime.getRuntime();
        int nNucleos = runtime.availableProcessors();
       // Thread[] hilos= new Thread[nNucleos];
        Thread[] hilos= new Thread[8];
        
        for(int i =0; i<hilos.length ; i++){
            Runnable runnable = new principal();
            hilos[i]  =new Thread(runnable);
            hilos[i].start();
        }
        
        for(int i =0; i<hilos.length;i++){
            try{
                hilos[i].join();
            }catch(Exception e){}
        }

    }


    
}
