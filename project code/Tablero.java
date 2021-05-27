/**
 *
 * @author Camilo
 */
public class Tablero {
    
    private int numeroPalabrasTablero = 0;
    private int tamañoTablero = 15;
    private char[][] tablero = new char[15][15];
    
    /**
     * Constructor
     */
    public Tablero(){
        this.llenarTablero();
    }
    
    /**
     * Este metodo se encarga de agregar la palabra en el tablero en su respectivo
     * orden y direccion
     * @param fila
     * @param columna
     * @param sentidoDireccion
     * @param palabra 
     */
    public void agregarPalabra(int fila, int columna, String sentidoDireccion, String palabra){
        if(numeroPalabrasTablero == 0){
            System.out.println("\n\n\tLa primera palabra siempre va en el medio, perdón...");
            if(sentidoDireccion.equals("vertical")){
                fila = 7 - (palabra.length()/2);
                columna = 7;
            }else{
                columna = 7 - (palabra.length()/2);
                fila = 7;
            }
        }
        for(int i = 0; i  < palabra.length(); i++){
            tablero[fila][columna] = palabra.charAt(i);
            if(sentidoDireccion.equals("vertical")){
                fila++;
            }else{
                columna++;
            }    
        }
        numeroPalabrasTablero++;
    }
    
    /**
     * en este metodo nos encargamos de validar que la palabra no se salga del tablero
     * es decir que esten en los rangos correctos. Tambien se valida que la nueva palabra
     * no sobreescriba las palabras ya existentes en el tablero. Por ultimo revisamos
     * que la palabra ingresada coincida en alguna letra con las letras ya existentes en el tablero, 
     * es decir que conecte
     * @param fila
     * @param columna
     * @param sentidoDireccion
     * @param palabra
     * @return true en caso que todos los datos ingresados esten correctos y false de lo contrario
     */
    public boolean palabraUbicacionValida(int fila, int columna, String sentidoDireccion, String palabra){
        
        boolean contieneLetraTablero = false;
        
        if((sentidoDireccion.equals("vertical") && fila + palabra.length() > 15) || (sentidoDireccion.equals("horizontal") && columna + palabra.length() > 15)){
            System.out.println("\nIndica bien el punto de partida y la dirrecion, la palabra no cabe en el tablero. Se esta desbordando.");
            return false;
        }
        
        for(int i = 0; i  < palabra.length(); i++){
            if(palabra.charAt(i) == tablero[fila][columna] || tablero[fila][columna] == '-'){
                if(palabra.charAt(i) == tablero[fila][columna]){
                    contieneLetraTablero = true;
                }
                if(sentidoDireccion.equals("vertical")){
                    fila++;
                }else{
                    columna++;
                }      
            }else{
                System.out.println("\n La fila, columna, palabra escogida no encaja con las palabras del tablero intenta de nuevo con datos correctos!!!!");
                return false;
            }
        }    
        
        if(contieneLetraTablero || numeroPalabrasTablero == 0){
            return true;
        }else{
            System.out.println("\nLa palabra debe estar conectada con alguna que este en el diccionario, intenta de nuevo. Revisa bien!!");
            return false;
        }
        
    }
    
    /**
     * imprimimos el tablero con los indices respectivos de las filas y columnas
     */
    public void imprimirTablero(){
        System.out.println("El tablero del juego: ");
        System.out.println("           0  1  2  3  4  5  6  7  8  9  10 11 12 13 14");
        for(int i = 0; i<this.tamañoTablero; i++){
            if(i<10){
                System.out.print("        " + i + "  ");
            }else{
                System.out.print("        " + i + " ");
            } 
            for(int j = 0; j<this.tamañoTablero; j++){
                System.out.print(tablero[i][j] + "  ");
            }
            System.out.println("");
        }
    }
    
    /**
     * llenamos el tablero 
     */
    private void llenarTablero(){
        
        for(int i = 0; i<this.tamañoTablero; i++){
            for(int j = 0; j<this.tamañoTablero; j++){
                tablero[i][j] = '-';
            }
        }
    }
}
