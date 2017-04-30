
package productorconsumidorejercicio;

import java.util.Date;


public class Consumidor extends Thread{
    private Buffer buffer;
    private int milisegundos;
    private int iteraciones;
    
    public Consumidor(Buffer b, int s, int n){
        this.buffer=b;
        this.milisegundos=s;
        this.iteraciones=n;
    }
    @Override
    public void run(){
        try{
            for (int i =0; i<iteraciones;i++){
                System.out.println(buffer.get());
                Thread.sleep(milisegundos);
            }
        }catch(Exception ex){}
    }
}
