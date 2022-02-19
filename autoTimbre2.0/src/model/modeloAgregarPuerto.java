package model;
import java.util.List;
/**
 * Esta clase contiene los atributos y metodos para agregar un puerto, lista de puertos y puerto seleccionado
 * @author Alejandro Quiroz
 * @version AutoTimbre2.0
 */
public class modeloAgregarPuerto {
    
     /**
     * Atributo que almacena un puerto
     */
    private String puerto;
    
     /**
     * Atributo para almacenar la lista de puertos disponibles
     */
    private List<String> listaPuertos; 
    
     /**
     * Atributo que almacena el puerto seleccionado de la lista
     */
    private static String puertoSeleccionado;

    /**
     * Constructor de la clase para inicializar los atributos declarados
     * @param puerto Se pasa el parametro al puerto cuando se instacia como objeto
     * @param listaPuertos Se pasa el parametro a la lista de puertos cuando se instacia como objeto
     * @param puertoSeleccionado Se pasa el parametro al puerto seleccionado cuando se instacia como objeto
     */
    public modeloAgregarPuerto(String puerto, List<String> listaPuertos, String puertoSeleccionado){
        this.puerto = puerto;
        //this.listaPuertos = new ArrayList<String>();
        this.listaPuertos = listaPuertos;
        this.puertoSeleccionado = puertoSeleccionado;
    }
    
    /**
     * Metodo que devuelve el valor del puerto
     * @return Regresa el valor del puerto
     */
    public String getPuerto(){
        return this.puerto;
    } 
        
    /**
     * Metodo que asigna un valor al puerto
     * @param p Se pasa el parametro p que asigna el valor al puerto
     */
    public void setPuerto(String p){
        this.puerto = p;
    }
    
    /**
     * Metodo que devuelve una lista de puertos
     * @return Regresa la lista de puertos
     */
    public List<String> getListaPuertos() {
        return listaPuertos;
    }

    /**
     * Metodo que asigna los valores a la lista de puertos
     * @param lp Parametro lp que asigna los valores a la lista de puertos
     */
    public void setListaPuertos(List<String> lp) {
        this.listaPuertos = lp;
    }
    
    /**
     * Metodo que devuelve el puerto seleccionado
     * @return Regresa el puerto seleccionado
     */
    public String getPuertoSeleccionado() {
        return puertoSeleccionado;
    }

    /**
     * Metodo que asigna el valor al puwerto seleccionado
     * @param ps Parametro ps que asigna el valor al puerto seleccionado
     */
    public void setPuertoSeleccionado(String ps) {
        this.puertoSeleccionado = ps;
    }
}
