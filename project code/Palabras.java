import java.util.ArrayList;

/**
 * En esta clase se hara todo lo relacionado con el manejo de letras y palabras
 * @author Camilo
 */
public class Palabras {
    
    /**
     * se utiliza para saber si es valida o no la palabra
     */
    private static boolean palabraValida = false;
    private static ArrayList<String> mejoresPalabras = new ArrayList<String>();
    private static ArrayList<Integer> puntajeMejoresPalabras = new ArrayList<Integer>();
    
    
    /**
     * Recorre la palabra y verifica si esta palabra se puede formar
     * con las letras en mano que tiene el jugador
     * Metodo recursivo
     * @param palabra
     * @param index
     * @param letrasMano 
     */
    private static void validarPalabra(String palabra, int index, ArrayList<UsedLetter> letrasMano){
        if(index < palabra.length()){//valido que el indice de la palabra sea menor
            for(UsedLetter letra : letrasMano){//recorro las letras disponibles
                if(palabra.charAt(index) == letra.letter && !letra.used){//se valida que la letra coincida con el caracter de la palabra y ademas que no se ha usado la letra
                    letra.used = true;//asignamos que esa letra ya esta en uso
                    validarPalabra(palabra, index+1, letrasMano);//invocamos recursivamente el metodo y avanzamos una posicion para validar la otra letra
                    letra.used = false;//reinicializamos el estado de la letra
                    break;//rompemos el ciclo porque ya encontramos lo que necesitabamos que es la letra
                }
            }
        }else{
            Palabras.palabraValida = true;//si la longitud de la palabra es igual a la longitud nos da a entender que si se puede formar la palabra
        }
    }
    
    
    /**
     * sobreescribe el array si la palabra que recibio tiene mayor puntaje que la que ya existia
     *guarda la palabra que se va a reemplazar en un auxiliar para verificar si las posiciones
     *de abajo son menor que la que fue reemplaza, en ese caso tambien la reemplaza
     * @param puntajePalabra
     * @param palabra 
     */
    private static void ordenarArrayPalabrasValidas(int puntajePalabra, String palabra){
        String auxPalabra;
        int auxPuntaje;
        for(int i = 0; i < mejoresPalabras.size(); i++){
            if(puntajeMejoresPalabras.get(i) < puntajePalabra){
                
                auxPalabra = mejoresPalabras.get(i);
                auxPuntaje = puntajeMejoresPalabras.get(i);
                mejoresPalabras.set(i, palabra);
                puntajeMejoresPalabras.set(i, puntajePalabra);
                puntajePalabra = auxPuntaje;
                palabra = auxPalabra;
            }
        }
    }
    
    
    /**
     * Este metodo recibe el diccionario el cual recorre y recibe jugador 
     * para obtener las letras en mano, formar las letras posibles con sus mejores opciones
     * y al final modifica el jugador con su nuevo puntaje, palabra escogida
     * @param diccionario
     * @param jugador 
     */
    public static void palabrasPosibles(Diccionario diccionario, Jugador jugador, Tablero tablero){
        
        for(String palabraDiccionario : diccionario.getDiccionario()){// recorro el diccionario palabra por palabra
            
            validarPalabra(palabraDiccionario, 0, jugador.getLetrasMano());//se invoca el metodo para validar si la palabra puede ser formada
            if(Palabras.palabraValida){//si la palabra es valida la utilizamos como posible opcion
                
                //System.out.println(palabraDiccionario);
                Palabras.palabraValida = false;//reinicializo la variable para la otra iteracion de la palabra    
                int puntajePalabra = diccionario.puntajePalabra(palabraDiccionario);//Obtengo el puntaje de la palabra valida
                
                if(mejoresPalabras.size() < 10){//Me aseguro que en el array hayan 10 palabras
                    mejoresPalabras.add(palabraDiccionario);
                    puntajeMejoresPalabras.add(puntajePalabra);
                }else{
                    ordenarArrayPalabrasValidas(puntajePalabra, palabraDiccionario);// invoco el metodo para ordenar las palabras
                }  
            }
        }
        
        
        //Hago un ciclo para que se agregue la palabra correctamente al tablero
        while(true){
            
            
            imprimirMejoresPalabras(jugador.getNombre());
            int indicePalabraEscogida = Capturador.capturarPalabraElegida();
            
            //si la persona no tiene jugadas disponibles entonces se agrega al puntaje de rendicion se limpian los datos de la
            //clase palabras y pasa al siguiente turno evitando el resto de procesos
            if(indicePalabraEscogida == 10){
                Juego.votarRendir();
                mejoresPalabras.clear();
                puntajeMejoresPalabras.clear();
                System.out.println("\n\n _______________________________________________________________________\n\n");
                break;
            }
            
            
            String palabraEscogida = mejoresPalabras.get(indicePalabraEscogida);
            int fila = Capturador.capturarFilaColumna("Ingrese la fila en la que desea que inicie la palabra: ");
            int columna = Capturador.capturarFilaColumna("Ingrese la columna en la que desea que inicie la palabra: ");
            String direccionPalabraTablero = Capturador.capturarDireccionPalabraTablero();
            
            //luego de verificar que todos los datos ingresados sean correctos entonces procede a agregarlos al tablero
            //guarda la palabra en el jugador y reinicia la votacion a rendir
            if(tablero.palabraUbicacionValida(fila, columna, direccionPalabraTablero, palabraEscogida)){
                tablero.agregarPalabra(fila, columna, direccionPalabraTablero, palabraEscogida);
                guardarPalabraFormada(jugador, indicePalabraEscogida, diccionario);
                Juego.ReiniciarVotosRendir();
                break;
            }
        }        
    }
    
    /**
     * Obtengo la opcion de la palabra escogida, la guardo en el jugador, 
     * guardo su nuevo puntaje y elimino las letras que uso para formar la palabra
     * @param jugador 
     */
    private static void guardarPalabraFormada(Jugador jugador, int indicePalabraEscogida, Diccionario diccionario){
        
        String palabraEscogida = mejoresPalabras.get(indicePalabraEscogida);
        jugador.addPalabrasFormadas(palabraEscogida);//Guardo la palabra escogida por el jugador 
        jugador.sumarPuntaje(puntajeMejoresPalabras.get(indicePalabraEscogida));//guardo el nuevo puntaje en el jugador
        ArrayList<UsedLetter> letrasMano = jugador.getLetrasMano(); //obtengo las letras en mano
        
        for (int n = 0; n < palabraEscogida.length (); n++) { 
            char letraPalabraEscogida = palabraEscogida.charAt(n);
            for(int m = 0; m < letrasMano.size(); m++){
                if(letrasMano.get(m).letter == letraPalabraEscogida){
                    letrasMano.remove(m);
                    break;
                }
            }
        }
        
        diccionario.getDiccionario().remove(palabraEscogida);//elimino la palabra del diccionario
        mejoresPalabras.clear();
        puntajeMejoresPalabras.clear();
        System.out.println("\n\n _______________________________________________________________________\n\n");
        
    }
    
    /**
     * Recorro las mejores palabras y las imprimo con su respectivo puntaje
     * @param nombre 
     */
    private static void imprimirMejoresPalabras(String nombre){
        System.out.println("\nLas mejores palabras que puede formar " + nombre + " con su respectivo puntaje son: ");
        for(int i = 0; i < mejoresPalabras.size(); i++){
            System.out.println("     " + (i+1) + ". Palabra: " + mejoresPalabras.get(i) + ", Puntaje: " + puntajeMejoresPalabras.get(i));
        }
        System.out.println("     11. En caso de no tener jugadas y desea pasar el turno");
    }
}   
