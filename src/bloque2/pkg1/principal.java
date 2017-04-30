package bloque2.pkg1;
//multiplicar todos los elementos de una matriz de forma concurrente y medir el tiempo
//emplear técnica en Matrices

import java.util.Random;

public class principal extends Thread {
    private static int tam = 800;
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
        
        principal h1 = new principal(0,100);
        principal h2 = new principal(400,matriz.length);
        principal h3 = new principal(100,200);
        principal h4= new principal(200,300);
        principal h5= new principal(300,400);
        
        h1.start();
        h2.start();
        h3.start();
        h4.start();
        
        try{
            h1.join();
            h2.join();
            h3.join();
            h4.join();
        }catch(Exception e){}
        
        //tiempo que ha tardado en ejecutar la concurrencia
        tiempo_final = System.nanoTime()- tiempo_inicio; //hora final - hora comienzo
        System.out.println((tiempo_final/1000000)+" milisegundos");
        
        //mostrar la matriz con el hilo principal cuando ya se ha modificado con los otros hilos
        /*for(int i=0; i<matriz.length;i++){
            for (int j=0; j<matriz[0].length;j++){
                System.out.print(matriz[i][j]+" ");
            }
            System.out.println();
        }*/
                
    }
    
}
