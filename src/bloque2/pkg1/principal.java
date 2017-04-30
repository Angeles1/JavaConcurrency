package bloque2.pkg1;

public class principal implements Runnable{
    
 
    public static void main(String[] args) {
      Runnable runnable = new principal();
      Thread hilo1 = new Thread(runnable);
      hilo1.start();
      
      try{
          hilo1.join();
      }catch (Exception e){}

    }

    @Override
    public void run() {
        System.out.println("hola");
    }
    
}
