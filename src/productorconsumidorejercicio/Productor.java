
package productorconsumidorejercicio;

import java.util.Date;


public class Productor extends Thread{
    private Buffer buffer;
    private int milisegundos;
    private int iteraciones;
    
    public Productor(Buffer b, int s, int n){
        this.buffer=b;
        this.milisegundos=s;
        this.iteraciones=n;
    }
    @Override
    public void run(){
        try{
            for (int i =0; i<iteraciones;i++){
                buffer.put(new Date().toString());
                Thread.sleep(milisegundos);
            }
        }catch(Exception ex){}
    }

}
