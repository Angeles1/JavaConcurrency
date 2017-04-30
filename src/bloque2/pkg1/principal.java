package bloque2.pkg1;

public class principal implements Runnable{
    private int id;
    
    public principal(int id){
        this.id= id;
    }
 
    public static void main(String[] args) {
        Runtime runtime = Runtime.getRuntime();
        int nNucleos = runtime.availableProcessors();
        
        Thread[] hilos = new Thread[nNucleos];
      
        for (int i =0; i<hilos.length;i++){
            Runnable runnable= new principal(i+1);
            hilos[i]=new Thread(runnable);
            hilos[i].start();
        }
      
        for(int i=0; i<hilos.length;i++){
            try{
                hilos[i].join(); 
            }catch (Exception e){}
        }
        System.out.println("Soy el hilo principal");

    }

    @Override
    public void run() {
        System.out.println("Soy el hilo: "+ id);
    }
    
}
