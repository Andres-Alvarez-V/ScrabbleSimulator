import java.util.InputMismatchException;
import java.util.Scanner;
/**
 * Se encarga de leer las entradas
 * @author Camilo
 */
public class Capturador {
    
    static Scanner in = new Scanner(System.in) ;
    
    /**
     *Capturo un string y lo retorno
     * @return  entrada del string
    */
    public static String leerString(String mensaje){
        System.out.print(mensaje);
        return in.next();
    }
    
    /**
     * Captura la fila o columna del tablero
     * @param mensaje
     * @return n    n viene siendo el valor que captura
     */
    public static int capturarFilaColumna(String mensaje){
        
        System.out.print(mensaje);
        
        try{
            while(true){
                int n = in.nextInt();
                if(0<=n && n<15){
                    return n;
                }else{
                    System.out.print("Ingresa una posicion valida del 0 al 14: ");
                } 
            }
        }catch(InputMismatchException e){
            in.nextLine();
            System.out.println("\n\tSUMINISTRASTE UN CARÁCTER ERRONEO\n\tRECTIFICA TU RESPUESTA\n");
            return capturarFilaColumna(mensaje);
        }      
    }   
    
    /**
     * captura un numero como opcion
     * @return palabra vertical o horizontal para saber la orientacion
     */
    public static String capturarDireccionPalabraTablero(){
        System.out.print("Sentido de la palabra a poner: \n     1. vertical\n     2. horizontal\nIngrese una opcion: ");
        
        try{
            while(true){
                int n = in.nextInt();
                switch (n) {
                    case 1:
                        return "vertical";
                    case 2:
                        return "horizontal";
                    default:
                        System.out.print("Ingresa una opcion valida por favor.\nNumeros entre 1 - 2: ");
                       break;
                }
            }
            
        }catch(InputMismatchException e){
                in.nextLine();
                System.out.println("\n\tSUMINISTRASTE UN CARÁCTER ERRONEO\n\tRECTIFICA TU RESPUESTA\n");
                return capturarDireccionPalabraTablero();
        }   
    }
    
    /**
     *Capturo la cantidad de jugadores a jugar.
     *Solo pueden jugar entre 2 - 4
     * @return numero de jugadores ingresado por el usuario
    */
    public static int capturarNumeroJugadores(){
        System.out.print("Cantidad de jugadores (2 - 4).\nCuantos jugadores van a jugar: ");
        while(true){
            try{
                int n = in.nextInt();
                if(n>=2 && n<=4){
                  return n;
                }else{
                    System.out.print("Ingresa un numero valido por favor.\nNumeros entre 2 - 4: ");
                }  
            }catch(InputMismatchException e){
                in.nextLine();
                System.out.println("\n\tSUMINISTRASTE UN CARÁCTER ERRONEO\n\tRECTIFICA TU RESPUESTA\n");
                return capturarNumeroJugadores();
            }
             
        }
        
    }
    
    /**
     * captura la opcion de las palabras disponibles.
     * @return numero de la opcion elegida
     */
    public static int capturarPalabraElegida(){
        System.out.print("Ingrese el numero de la palabra que desea elegir y se puede poner en el tablero: ");
        while(true){
            try{
                int n = in.nextInt();
                if(n>=1 && n<=11){
                    return n-1;
                }else{
                    System.out.print("Ingresa un numero valido.\nNumeros entre 1 - 11: ");
                } 
            }catch(InputMismatchException e){
                in.nextLine();
                System.out.println("\n\tSUMINISTRASTE UN CARÁCTER ERRONEO\n\tRECTIFICA TU RESPUESTA\n");
                return capturarPalabraElegida();
            }
            
        }
    }
    
}
