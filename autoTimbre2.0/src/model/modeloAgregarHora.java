package model;

/**
 * Esta clase contiene los atributos y metodos para agregar una hora
 * @author Alejandro Quiroz
 * @version AutoTimbre2.0
 */
public class modeloAgregarHora {
    
     /**
     * Atributo que almacena la hora actual
     */
    private String horaActual;
    
     /**
     * Atributo que almacena en un arreglo las horas que se programaron
     */
    private String[] horasTimbrar;   

    /**
     * Constructor de la clase para inicializar los atributos declarados
     * @param horaActual Se pasa el valor del atributo hora actual al instanciar la clase
     * @param horasTimbrar Sepasa el valor del atributo horas en las que timbrara al instanciar la clase
     */
    public modeloAgregarHora(String horaActual, String[] horasTimbrar) {
        this.horaActual = horaActual;
        this.horasTimbrar = horasTimbrar;
    }
     
    /**
     *Metodo que devuelve el valor de la hora actual
     * @return regresa el valor de la hora actual
     */
    public String getHoraActual() {
        return horaActual;
    }

    /**
     * Metodo que asigna un valor a la hora actual
     * @param ha Parametro para asignarle un valor a la hora actual
     */
    public void setHoraActual(String ha) {
        this.horaActual = ha;
    }

    /**
     * Metodo que devuelve las horas en las que se timbrara
     * @return Retorna en un arreglo las horas en las que se timbrara
     */
    public String[] getHorasTimbrar() {
        return horasTimbrar;
    }

    /**
     * Metodo que asigna un valor a las horas en las que se timbrara
     * @param ht Parametro para asignarle un valor al arreglo de horas en las que se timbrara
     */
    public void setHorasTimbrar(String[] ht) {
        this.horasTimbrar = ht;
    } 
}
