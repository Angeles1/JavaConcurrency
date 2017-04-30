package bloque2.pkg1;

import java.util.Random;


public class principal extends Thread {
    private static int tam= 8;
    private static int[][]matriz = new int[tam][tam];
    private int ini, fin;
    
    public principal(int ini, int fin){
        this.ini=ini;
        this.fin=fin;
    }
    
    public void run(){
        for(int i = ini; i< fin; i++){
            for(int j = 0; j< matriz[0].length;j++){
                matriz[i][j] *=10;
            }
        }
    }
    
 
    public static void main(String[] args) {
        //informacion sobre el hardware
        Runtime runtime =Runtime.getRuntime();
        //numeros de procesadores logicos de mi computador
        int nNucleos = runtime.availableProcessors();
        
        Random rand= new Random(System.nanoTime());
        
        for (int i = 0; i< matriz.length; i++){
            for (int j =0; j <matriz[0].length;j++){
                matriz[i][j] = rand.nextInt(10);
            }
        }
        //vector de hilos para optimizar la creacion segun el numero de nuestros procesadores logicos
        Thread[] hilos = new Thread[nNucleos];
        
        int rango = tam/nNucleos; //rango en el que va a operar el hilo
        int start= 0;
        int finish = rango;
        
        for (int i =0; i< nNucleos ; i++){
            hilos[i] = new principal(start,finish);
            hilos[i].start();
            
            start= finish;
            finish+=rango;
        }
        
        for (int i =0; i<nNucleos; i++){
            try{
                hilos[i].join();
            }catch(Exception e){}
        }
        
        //imprimir matriz
        for(int i =0; i<matriz.length;i++){
            for(int j =0; j< matriz[0].length;j++){
                System.out.print(matriz[i][j]+" ");
            }
            System.out.println();
        }
        
        
        
       
    }
    
}
