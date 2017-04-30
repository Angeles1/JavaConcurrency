
package productorconsumidorejercicio;

public class principal {

   
    public static void main(String[] args) {
        Buffer buffer = new Buffer(5);
        Productor miProductor;
        Consumidor miConsumidor;
        
        miProductor = new Productor(buffer, 1000, 20);
        miProductor.start();
        
        miConsumidor = new Consumidor(buffer,3000, 20);
        miConsumidor.start();

    }
    
}
