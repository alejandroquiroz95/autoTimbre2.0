package controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.vistaAgregarPuerto;
import model.modeloAgregarPuerto;
import com.panamahitek.PanamaHitek_Arduino;
import java.io.IOException;
import javax.swing.JOptionPane;
import view.vistaAgregarHora;

/**
 * Esta clase tipo interfaz se encarga de controlar de manera abstracta todos los eventos de la vista para ageregar un puerto
 * @author Alejandro Quiroz
 * @version AutoTimbre 2.0
 */
public  class controladorAgregarPuerto implements ActionListener{
    
    /**
     * Objeto para acceder a la vista Agegar Puerto
     */
    vistaAgregarPuerto objVistaAP;
    
    /**
     * Objeto para acceder al modelo Agegar Puerto
     */
    modeloAgregarPuerto objModeloAP;
    
    /**
     * Objeto para hacer uso de las clases de la libreria de Arduino
     */
    PanamaHitek_Arduino objConexionA;
    
    /**
     * Objeto para acceder a la vista Agregar Hora
     */
    vistaAgregarHora objVistaAH;
    
    /**
     * Objeto para acceder al modelo Agegar Hora
     */
    controladorAgregarHora objControladorAH;
    
    /**
     * Constructor de la clase para instanciar todos los objetos que seran utilizados
     * @throws IOException
     */
    public controladorAgregarPuerto() throws IOException{
        this.objVistaAP = new vistaAgregarPuerto();
        this.objModeloAP = new modeloAgregarPuerto(null, null, null);
        this.objConexionA = new PanamaHitek_Arduino();
        this.objControladorAH = new controladorAgregarHora();
        this.objVistaAP.getCbPuerto().addActionListener(this);
        this.objVistaAP.getBtnBuscar().addActionListener(this);
        this.objVistaAP.getBtnAgregarPuerto().addActionListener(this);
    }
    
    //metodo abstracto que se ejecutara al iniciar la clase principal

    /**
     *
     */
    public void IniciarVistaAP() {
        objVistaAP.setTitle("Agregar un puerto serie");
        objVistaAP.setLocationRelativeTo(null);
        objVistaAP.setVisible(true);
        BuscarPuertos();
    }

    /**
     * Metodo absracto para desencadenar los eventos que se realicen en los componentes de la vista
     * @param e Parametro que agrega el evento
     */
    public void actionPerformed(ActionEvent e) {
        //evento del boton buscar puerto
        if(objVistaAP.getBtnBuscar() == e.getSource()){         
            BuscarPuertos();
        }
        
        //evento del boton agregar puerto
        if(objVistaAP.getBtnAgregarPuerto() == e.getSource()){
            if(objVistaAP.getCbPuerto().getSelectedItem() == null){
                JOptionPane.showMessageDialog(null, "Debe seleccionar un puerto serie","Alerta",JOptionPane.WARNING_MESSAGE);
            }
            else{           
                objModeloAP.setPuertoSeleccionado((String)objVistaAP.cbPuerto.getSelectedItem());
                this.objVistaAH = new vistaAgregarHora();
                objControladorAH.IniciarVistaAH(objModeloAP.getPuertoSeleccionado());
                objVistaAP.dispose();
            }
        }
    }

    /**
     * Metodo abstracto que buscara los puertos disponibles y llenara el combo box con la lista de puertos
     */
    public void BuscarPuertos(){
        objVistaAP.getCbPuerto().removeAllItems();
        objModeloAP.setListaPuertos(objConexionA.getSerialPorts());
         
        for(int i = 0; i < objModeloAP.getListaPuertos().size(); i++){
            objModeloAP.setPuerto(objModeloAP.getListaPuertos().get(i));
            objVistaAP.getCbPuerto().addItem(objModeloAP.getPuerto()); 
        }
    }
}
