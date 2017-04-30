package bloque2.pkg1;
//multiplicar todos los elementos de una matriz de forma concurrente y medir el tiempo
//emplear técnica en Matrices

import java.util.Random;

public class principal extends Thread {
    private static int tam = 4;
    private static int[][] matriz = new int[tam][tam];
    int ini, fin;
    //constructor
    public principal(int ini, int fin){
        this.ini=ini;
        this.fin=fin;
    }
    
    public void run(){
        for(int i = ini; i< fin;i++){ //bucle acotado para que no tener indeterminismo
            for(int j = 0; j<matriz[0].length; j++){
                matriz[i][j]*=10;
            }
        }
    }
 
    public static void main(String[] args) {
        //inicializar la matriz
        Random rand = new Random(System.nanoTime());
        double tiempo_inicio, tiempo_final;
        
        for(int i=0; i< matriz.length; i++){
            for(int j=0; j< matriz[0].length;j++){
                matriz[i][j] = rand.nextInt(10);
            }
        }
        
        //empiezo a medir el tiempo
        tiempo_inicio= System.nanoTime(); //obtenemos el hora actual en nanosegundos
        
        principal h1 = new principal(0,2);
        principal h2 = new principal(2,matriz.length);
        
        h1.start();
        h2.start();
        
        try{
            h1.join();
            h2.join();
        }catch(Exception e){}
        
        //tiempo que ha tardado en ejecutar la concurrencia
        tiempo_final = System.nanoTime()- tiempo_inicio; //hora final - hora comienzo
        System.out.println((tiempo_final/1000000)+" milisegundos");
        
        //mostrar la matriz con el hilo principal cuando ya se ha modificado con los otros hilos
        for(int i=0; i<matriz.length;i++){
            for (int j=0; j<matriz[0].length;j++){
                System.out.print(matriz[i][j]+" ");
            }
            System.out.println();
        }
                
    }
    
}
