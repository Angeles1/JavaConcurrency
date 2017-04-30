
package productorconsumidorejercicio;


public class Buffer {
    private String[] buffer;
    private int in=-1;
    private int out = -1;
    private int count = 0;
    
    public Buffer(int size){
        this.buffer= new String[size];
    }
    public synchronized void put(String s){
        while(count >= buffer.length){
            try{
                wait();
            }catch(Exception ex){}
        }
        count++;
        buffer[++in %buffer.length]=s;
        notifyAll();
    }
    
    public synchronized String get(){
        while(count ==0){
            try{
                wait();
            }catch (Exception ex){}
        }
        count--;
        String s= buffer[++out %buffer.length];
        notifyAll();
        return s;
    }
    
    
}
