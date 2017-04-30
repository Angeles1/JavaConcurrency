package cyclicbarrier;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*  En una carretera ciclista con 100 participantes hay 3 etapas, antes de comenzar una etapa hay que esperar
 *  a que lleguen todos los participantes, una vez que lleguen se dara la salida a la siguiente etapa.
 *  El ganador de la prueba, sera el que mejor tiempo haga en total y tambien se requiere saber el ganador
 *  de cada una de las etapas individuales
*/
public class principal {

    public static void main(String[] args) {
        int participantes =100;
        Carrera.setCarrera(participantes);
        Runtime runtime =Runtime.getRuntime();
        int nNucleos =runtime.availableProcessors();
        
        ExecutorService pool = Executors.newFixedThreadPool(nNucleos);
        for(int i =0; i< participantes; i++){
            Runnable runnable = new Carrera(i);
            pool.execute(runnable);
        }
        
        
        pool.shutdown();
        while(!pool.isTerminated());
        double[][] tiempos = Carrera.getTiempos();
        
        int idGanador =0;
        double ganadorTiempo= tiempos[0][3];
        
        int idGanador1 =0;
        double ganadorTiempo1= tiempos[0][0];
        
        int idGanador2 =0;
        double ganadorTiempo2= tiempos[0][1];
        
        int idGanador3 =0;
        double ganadorTiempo3= tiempos[0][2];
        
        for(int i =0; i<tiempos.length;i++){
            if(tiempos[i][3] < ganadorTiempo){
                ganadorTiempo= tiempos[i][3];
                idGanador=i;
            }
            if(tiempos[i][0] < ganadorTiempo1){
                ganadorTiempo1= tiempos[i][0];
                idGanador1=i;
            }
            if(tiempos[i][1] < ganadorTiempo2){
                ganadorTiempo2= tiempos[i][1];
                idGanador2=i;
            }
            if(tiempos[i][2] < ganadorTiempo3){
                ganadorTiempo3= tiempos[i][2];
                idGanador3=i;
            } 
        }
        System.out.println("");

    }
    
}
