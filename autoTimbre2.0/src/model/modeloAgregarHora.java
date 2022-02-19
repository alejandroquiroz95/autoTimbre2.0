package model;

/**
 *
 * @author Alejandro Quiroz
 */
public class modeloAgregarHora {
    private String horaActual;
    private String[] horasTimbrar;   

    /**
     *
     * @param horaActual
     * @param horasTimbrar
     */
    public modeloAgregarHora(String horaActual, String[] horasTimbrar) {
        this.horaActual = horaActual;
        this.horasTimbrar = horasTimbrar;
    }
     
    /**
     *
     * @return
     */
    public String getHoraActual() {
        return horaActual;
    }

    /**
     *
     * @param ha
     */
    public void setHoraActual(String ha) {
        this.horaActual = ha;
    }

    /**
     *
     * @return
     */
    public String[] getHorasTimbrar() {
        return horasTimbrar;
    }

    /**
     *
     * @param ht
     */
    public void setHorasTimbrar(String[] ht) {
        this.horasTimbrar = ht;
    } 
}
