import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;


/**
 *En esta clase se maneja el diccionario con palabras válidas para el
 * juego Scrabble.
 * Las palabras se leen de un archivo texto, en el cual cada palabra
 * está en una línea diferente
 * 
 * @author Camilo
 */
public class Diccionario
{
    private ArrayList <String> diccionario = new ArrayList<>();

    
    public ArrayList<String> getDiccionario(){
        return diccionario;
    }
    
    /**
     * Este metodo lee el total de palabras del diccionario.txt
     * Tiene un contador para saber cuantas letras lee
     * Crea un array de las palabras que estan en el diccionario
     */ 

    public void leerDiccionario() {
        String nombreArchivo = "C:\\\\Users\\\\Camilo\\\\Documents\\\\NetBeansProjects\\\\Scrabble\\\\src\\\\main\\\\java\\\\diccionario.txt";
        BufferedReader br = null;
        int cont = 0;
        try {
           //Crear un objeto BufferedReader al que se le pasa un objeto FileReader con el nombre del fichero
           br = new BufferedReader(new FileReader(nombreArchivo));
           //Leer la primera línea, guardando en un String
           String texto = br.readLine();
           //Repetir mientras no se llegue al final del fichero
           while(texto != null)
           {
               //Guardar la linea en el diccionario
               diccionario.add(texto);
               cont++;
               //Leer la siguiente línea
               texto = br.readLine();
           }
        }
        catch (FileNotFoundException e) {
            System.out.println("Error: Fichero no encontrado");
            System.out.println(e.getMessage());
        }
        catch(Exception e) {
            System.out.println("Error de lectura del fichero");
            System.out.println(e.getMessage());
        }
        finally {
            try {
                if(br != null)
                    br.close();
            }
            catch (Exception e) {
                System.out.println("Error al cerrar el fichero");
                System.out.println(e.getMessage());
            }
        }
        
        System.out.println("Palabras leidas en total: " + cont);
    }
    
   
    /**
     * Este metodo obtiene el puntaje total de una palabra
     * @param palabra
     * @return acum. Este es el puntaje total de la palabra
     */
    public int puntajePalabra(String palabra){
        int acum = 0;
        for (int i = 0; i < palabra.length (); i++) { 
            char c = palabra.charAt (i); 
            
            if(String.valueOf(c).equals("a") || String.valueOf(c).equals("e") || String.valueOf(c).equals("o") || String.valueOf(c).equals("s") || String.valueOf(c).equals("i") || String.valueOf(c).equals("u") || String.valueOf(c).equals("n") || String.valueOf(c).equals("l") || String.valueOf(c).equals("r") || String.valueOf(c).equals("t")){
                acum += 1;
            }else if(String.valueOf(c).equals("c") || String.valueOf(c).equals("d") || String.valueOf(c).equals("g")){
                acum += 2;
            }else if(String.valueOf(c).equals("m") || String.valueOf(c).equals("b") || String.valueOf(c).equals("p")){
                acum += 3;
            }else if(String.valueOf(c).equals("f") || String.valueOf(c).equals("h") || String.valueOf(c).equals("v") || String.valueOf(c).equals("y")){
                acum += 4;
            }else if(String.valueOf(c).equals("j")){
                acum += 6;
            }else if(String.valueOf(c).equals("k") || String.valueOf(c).equals("q") || String.valueOf(c).equals("w") || String.valueOf(c).equals("x")){
                acum += 8;
            }else if(String.valueOf(c).equals("z")){
                acum += 10;
            }
        }
        
        return acum;
    }
}
