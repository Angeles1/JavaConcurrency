package bloque2.pkg1;

public class principal implements Runnable{
    
 
    public static void main(String[] args) {
      Thread[] hilos = new Thread[2];
      
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
    }

    @Override
    public void run() {
        System.out.println("hola");
    }
    
}
