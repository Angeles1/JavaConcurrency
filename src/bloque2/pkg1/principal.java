package bloque2.pkg1;

public class principal implements Runnable{
    private static int cont=0;
    private static Object object= new Object();//cerrojo para synchronized
 
    public static void main(String[] args) {
        Runtime runtime = Runtime.getRuntime();
        int nNucleos = runtime.availableProcessors();
        
        Thread[] hilos = new Thread[nNucleos];
      
        for (int i =0; i<hilos.length;i++){
            Runnable runnable= new principal();
            hilos[i]=new Thread(runnable);
            hilos[i].start();
        }
      
        for(int i=0; i<hilos.length;i++){
            try{
                hilos[i].join(); 
            }catch (Exception e){}
        }
        System.out.println(cont);

    }

    @Override
    public void run() {
        for(int i =0; i<20000;i++){
            //Activo: h1 Cola: h4, h3, h2 synchronized los despierta cuando h1 sale vuelve a la cola y el mas rapido entra y bloquea
            synchronized(object){ //resuelve el problema de la exclusion mutua, region critica controlada
               cont++; 
            }
        }
        System.out.println("hola");
    }
    
}
