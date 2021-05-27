/**
 * Esta clase se encarga de ejecutar todo el juego con su respectiva dinamica
 * y invoca los metodos e instanceas necesarias
 * @author Camilo
 */

public class Juego {
    
    private int numeroJugadores;
    private Jugador[] jugadores;
    private Diccionario diccionario;
    private Tablero tablero;
    private int turno = 0;
    private static int votosRendir = 0; //Esto es en el caso que se deseen rendir por falta de combinaciones
    
    /**
     * 
     * @param numeroJugadores
     * @param jugadores
     * @param diccionario
     * @param tablero 
     */
    public Juego(int numeroJugadores, Jugador[] jugadores, Diccionario diccionario, Tablero tablero){
        this.numeroJugadores = numeroJugadores;
        this.jugadores = jugadores;
        this.diccionario = diccionario;
        this.tablero = tablero;
    }
    
    /**
     * Este metodo inicia el juego y todas las interacciones
     */
    public void iniciarJuego(){
        while(true){
            
           Jugador jugador = jugadores[turno%numeroJugadores];//Guarda el jugador que esta en ese turno
           tablero.imprimirTablero();
           jugador.imprimirLetrasMano();//imprime letras del jugador
           Palabras.palabrasPosibles(diccionario, jugador, tablero);//invoco el metodo que va a formar las mejores palabras y me da a escoger una de esas palabras
           jugador.entregarLetras();//Vuelvo a llenar las letras del jugador con 10 palabras 
           turno++;
           
           if(votosRendir == numeroJugadores){
               System.out.println("\nGracias por jugar!!!!\nPuntaje global: ");
               imprimirPuntajeJugador();
               break;
           }
           if(turno%numeroJugadores == 0){//Si el turno corresponde al primer jugador se avanza de turno
               System.out.println("/_/_/    ROUNDDDD " + ((turno/numeroJugadores)+1) + "    /_/_/\nLos puntajes actuales son:");
               imprimirPuntajeJugador();
               System.out.println("\n\n _______________________________________________________________________\n\n");
           }
           
        }
    }
    
    /**
     * imprime el puntaje de los jugadores y las respectivas palabras creadas
     */
    public void imprimirPuntajeJugador(){
        for(Jugador j : jugadores){
            System.out.println("      --El jugador " + j.getNombre() + " tiene un puntaje acumuludo de " + j.getPuntaje() + " puntos y ha formado las siguientes palabras");
            System.out.println("        --" + j.getPalabrasFormadas().toString());
        } 
    }
    
    /**
     * aumenta el contador para rendirse
     */
    public static void votarRendir(){
        votosRendir++;
    }
    
    /**
     * reinicio el contador para rendirse
     */
    public static void ReiniciarVotosRendir(){
        votosRendir=0;
    }
    
    
    
}
