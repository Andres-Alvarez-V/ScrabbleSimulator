import java.util.ArrayList;

/**
 * Esta clase maneja el objeto jugador el cual va a tener todas las funcionalidades
 * de un jugador
 * @author Camilo
 */
public class Jugador {
    
    /**
     * Letras disponibles del usuario para crear palabras
     */
    private ArrayList<UsedLetter> letrasMano = new ArrayList<UsedLetter>();
    private ArrayList<String> palabrasFormadas = new ArrayList<String>();
    private String nombre;
    private int puntaje;
    
    /**
     * Contstructor
     * @param nombre 
     */
    public Jugador(String nombre){ //Creamos el constructor que va a recibir el nombre del jugador
        this.nombre = nombre;
        this.puntaje = 0;
        this.entregarLetras();//Se crean las letras para el jugador
    }
    
    /**
     * 
     * @return letrasMano. Este es un array de un letras disponibles
     */
    public ArrayList<UsedLetter> getLetrasMano(){
        return letrasMano;
    }
    
    /**
     * 
     * @return palabrasFormadas. Devuelve las palabras que ha escogido el usuario
     */
    public ArrayList<String> getPalabrasFormadas(){
        return palabrasFormadas;
    }
    
    /**
     * Recibe la palabra que el usuario crea
     * @param palabra 
     */
    public void addPalabrasFormadas(String palabra){
        palabrasFormadas.add(palabra);
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public int getPuntaje(){
        return puntaje;
    }
    
    /**
     * Suma el puntaje para tener un total acumulado
     * @param puntaje 
     */
    public void sumarPuntaje(int puntaje){
        this.puntaje += puntaje;
    }

    /**
     * Este metodo generea 10 palabras aleatorias y las entrega al jugador
     * Aqui instanceo a usedLetter
     */
    public void entregarLetras() {
        
        for(; letrasMano.size() < 10;){
            if(letrasMano.size() % 3 == 0){//Aseguro 3 vocales en el total de las letrasEnMano
                int numero =(int)Math.ceil(Math.random()*5); //genero un numero aleatorio de 1 - 5
                switch(numero){//guardo el caracter con respecto al numero
                    case 1:
                        letrasMano.add(new UsedLetter('a', false));
                        continue;
                    case 2:
                        letrasMano.add(new UsedLetter('e', false));
                        continue;
                    case 3:
                        letrasMano.add(new UsedLetter('i', false));
                        continue;
                    case 4:
                        letrasMano.add(new UsedLetter('o', false));
                        continue;
                    case 5:
                        letrasMano.add(new UsedLetter('u', false));
                        continue;
                }
            }else{
                int codigoAscii = (int)Math.floor((Math.random()*(122 -97))+97);//genero un numero entre 97 - 122 equivalente a tabla ascii
                letrasMano.add(new UsedLetter((char)codigoAscii, false));//creo una instancia de UsedLetter con el caracter generado aleatoriamente
            }
        }
    }
    /**
     * imprime las letras disponibles del jugador
     */
    public void imprimirLetrasMano(){
        String letras = "";
        
        for(UsedLetter letra : letrasMano){
            letras+= letra.letter + ", ";
        }
        
        System.out.print("\nLas letras disponibles de " + this.nombre + " son: " + letras);
    }
    
}
