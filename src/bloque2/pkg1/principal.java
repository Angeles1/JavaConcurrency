package bloque2.pkg1;

import java.util.Random;

public class principal extends Thread {
    //declaracion del vector estatico para que varios hilos accedan a el
    private static int tam=8;
    private static int[] vec = new int[tam];
    private int ini,fin;
    public principal(int ini, int fin){
        this.ini=ini;
        this.fin=fin;
    }
    public void run(){
        for(int i =ini; i< fin; i++){//ini =0, fin=4
            vec[i]*=10;
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Random rand=new Random(System.nanoTime());
        for (int i =0; i< vec.length;i++){
            vec[i]= rand.nextInt(10);
        }
        principal h1 = new principal(0,4);
        principal h2= new principal(4, vec.length);
        
        h1.start();
        h2.start();
        
        try{
            h1.join();
            h2.join();
        }catch (Exception e){}
        
        for (int i =0; i< vec.length; i++){
            System.out.print(vec[i]+" ");
        }
    }
    
}
