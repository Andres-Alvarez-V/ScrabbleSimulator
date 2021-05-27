
/**
 * Clase principal para el juego de Scrabble.
 * Esta clase crea el diccionario (las palabras se leen de un archivo)
 * Y luego invoca el método crearPalabras 
 * 
 * @author Camilo
 * @version 09/05/2021
 */
public class Scrabble
{
    public static void main(String [] args) {
        
        Diccionario diccionario = new Diccionario();//instancio la clase diccionario
        diccionario.leerDiccionario();//leo el documento
        Tablero tablero = new Tablero();//creo el tablero
        
        System.out.println("Bienvenido a Scrabble project!!\n");
        
        int numeroJugadores = Capturador.capturarNumeroJugadores();//Leo la cantidad de jugadores
        Jugador[] jugadores = new Jugador[numeroJugadores];// Creo un arreglo de jugadores
        //instancio los jugadores y los guardo en el arreglo
        for(int i = 0; i < numeroJugadores; i++){
            jugadores[i] = new Jugador(Capturador.leerString("\nIngrese el nombre del jugador " + (i+1) + ": "));
        }
        System.out.println("\n\n\n\n"); 
        Juego juego = new Juego(numeroJugadores, jugadores, diccionario, tablero);
        juego.iniciarJuego();//inicio el juego
        
    }
}
