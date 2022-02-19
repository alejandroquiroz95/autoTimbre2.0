package controller;
import com.panamahitek.ArduinoException;
import com.panamahitek.PanamaHitek_Arduino;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import jssc.SerialPortException;
import model.modeloAgregarHora;
import model.modeloAgregarPuerto;
import view.vistaAgregarHora;

/**
 * Esta clase tipo interfaz se encarga de controlar de manera abstracta todos los eventos de la vista para ageregar las horas
 * @author Alejandro
 * @version AutoTimbre 2.0
 */
public class controladorAgregarHora implements ActionListener, Runnable{
    
    /**
     * Objeto para acceder a la vista Agregar Hora
     */
    private vistaAgregarHora objVistaAH;
    
    /**
     * Objeto para acceder al modelo Agregar Hora
     */
    private modeloAgregarHora objModeloAH;
    
    /**
     * Objeto para acceder al modelo Agegar Puerto
     */
    private modeloAgregarPuerto objModeloAP;
    
    /**
     * Objeto para acceder al controlador archivo de texto 
     */
    private controladorArchivoTexto objControladorAT;
    
    /**
     * Objeto para generar la conexion con el Arduino
     */
    private PanamaHitek_Arduino objConexionA; 
    
    /**
     * Objeto para acceder al controlador archivo de texto 
     */
    private DefaultTableModel modeloTabla;
    
    /**
     * Objeto para crear un nuevo hilo
     */
    private Thread hiloNuevo;
    
    //

    /**
     * Constructor de la clase para inicializar todos los objetos que se declararon
     * @throws IOException
     */
    public controladorAgregarHora() throws IOException{
        this.objVistaAH = new vistaAgregarHora();
        this.objModeloAH = new modeloAgregarHora(null, null);
        this.objModeloAP = new modeloAgregarPuerto(null, null, null);
        this.objControladorAT = new controladorArchivoTexto();
        this.objConexionA = new PanamaHitek_Arduino();
        this.modeloTabla = new DefaultTableModel();
        this.objVistaAH.getBtnSalir().addActionListener(this);
        this.objVistaAH.getBtnEliminar().addActionListener(this);
        this.objVistaAH.getBtnAgregarHora().addActionListener(this);
        this.objVistaAH.getBtnEncenderTimbre().addActionListener(this);
    }

    /**
     * Metodo abstracto que se ejecutara para iniciar la vista
     * @param puertoSeleccionado
     */
    public void IniciarVistaAH(String puertoSeleccionado){
        objVistaAH.setTitle("AutoTimbre version 2.0");
        objVistaAH.setLocationRelativeTo(null);
        objVistaAH.setVisible(true);
        objVistaAH.tblHoras.setModel(modeloTituloColumna());      
        objVistaAH.tblHoras.setModel(modeloTablaHoras());      
        hiloNuevo = new Thread(this);
        hiloNuevo.start();
        
        //inicia la conexion con el puerto serial donde esta el arduino
         try{
         objConexionA.arduinoTX(objModeloAP.getPuertoSeleccionado(), 9600);
        }catch(ArduinoException ex){
            Logger.getLogger(controladorAgregarHora.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Metodo abstracto para desencadenar los eventos que se realicen los componentes de la vista
     * @param e Parametro para agregar el evento
     */
    public void actionPerformed(ActionEvent e) {
        // Evento del boton salir
        if(objVistaAH.getBtnSalir() == e.getSource()){
            System.exit(0);
        }
        
        // Evento del boton eliminar
        if(objVistaAH.getBtnEliminar() == e.getSource()){
            objControladorAT.EliminarArchivo();
            objVistaAH.tblHoras.setModel(modeloTablaLimpiar());    
        }
        
        // Evento del boton agregar hora
        if(objVistaAH.getBtnAgregarHora()== e.getSource()){
            objControladorAT.CrearArchivo();
            objControladorAT.EscribirArchivo(objVistaAH.getSpHoras().getValue().toString() + ":" + objVistaAH.getSpMinutos().getValue().toString() + ":" + objVistaAH.getSpSegundos().getValue().toString() + ",");
            objVistaAH.tblHoras.setModel(modeloTablaLimpiar());   
            objVistaAH.tblHoras.setModel(modeloTablaHoras());
        }
        
        // Evento del boton encender timbre
         if(objVistaAH.getBtnEncenderTimbre()== e.getSource()){
            TocarTimbre();
        }
    }
    
    /**
     * Metodo adstracto que obtiene la hora actual tomada de la computadora
     */
    public void HoraActual() {
        String horaActual = null;
        Calendar calendario = new GregorianCalendar();
        Date fechaHoraActual = new Date();
        String horas, minutos, segundos;

        calendario.setTime(fechaHoraActual);
        horas= calendario.get(Calendar.HOUR_OF_DAY)>9?""+calendario.get(Calendar.HOUR_OF_DAY):"0"+calendario.get(Calendar.HOUR_OF_DAY); 
        minutos = calendario.get(Calendar.MINUTE)>9?""+calendario.get(Calendar.MINUTE):"0"+calendario.get(Calendar.MINUTE);
        segundos = calendario.get(Calendar.SECOND)>9?""+calendario.get(Calendar.SECOND):"0"+calendario.get(Calendar.SECOND);
        objModeloAH.setHoraActual(horas + ":" + minutos + ":" + segundos);
    }
    
    /**
     * Metodo abstracto que obtiene del archivo de texto todas las horas programadas para timbrar y las guarda en un arreglo
     */
    public void HorasTimbrar(){
        String[] horasTimbrar = null;
        String cadenaHoras = objControladorAT.LeerArchivo();
        if(!cadenaHoras.isEmpty() && cadenaHoras != null){
            cadenaHoras = cadenaHoras.substring(0, cadenaHoras.length()-1);
            objModeloAH.setHorasTimbrar(cadenaHoras.split(","));
        }
    }

    /**
     * Metodo abstracto que compara la hora actual con las del arreglo de horas programadas y verifica si son iguales
     */
    public void Timbrar(){
         HoraActual();
         HorasTimbrar();
         if(objModeloAH.getHorasTimbrar() != null){
            for(int i=0; i < objModeloAH.getHorasTimbrar().length; i++) {
               String horaTimbrar2 = objModeloAH.getHorasTimbrar()[i];
               if(horaTimbrar2.equals(objModeloAH.getHoraActual())){
                   TocarTimbre();
               }
            }
        }
    }   
        
    /**
     * Metodo abstracto que manda el valor 1 al Arduino para que active el timbre
     */
    public void TocarTimbre(){
        try {
            objConexionA.sendData("1");
        } catch (ArduinoException | SerialPortException ex) {
            Logger.getLogger(controladorAgregarHora.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Metodo que agrega y asigna el titulo de la columna a la tabla
     * @return Retorna el modelo de la tabla
     */
    public DefaultTableModel modeloTituloColumna(){
        this.modeloTabla.addColumn("Horarios");
        return this.modeloTabla;
    }
    
    /**
     * Metodo que agrega las filas con las horas programadas a la tabla
     * @return Retorna el modelo de la tabla
     */
    public DefaultTableModel modeloTablaHoras(){
        String a[] = new String[1];
        HorasTimbrar();
        if(objModeloAH.getHorasTimbrar() != null){
            for(int i=0; i < objModeloAH.getHorasTimbrar().length; i++) {
                a[0] = objModeloAH.getHorasTimbrar()[i];
                this.modeloTabla.addRow(a);
            }
        }
        return this.modeloTabla;
    }
    
    /**
     * Metodo que asigna un valor de 0 a las filas de las tablas
     * @return Retorna el modelo de la tabla
     */
    public DefaultTableModel modeloTablaLimpiar(){
        this.modeloTabla.setRowCount(0);
        return this.modeloTabla;
    } 
        
    /**
     * Metodo que verifica si el hilo nuevo sigue con vida y manda a llamar al metodo Timbrar cada segundo
     */
    public void run(){
        while(hiloNuevo.isAlive()){
            Timbrar();
            //lblHoraActual.setText(horaActual);
            try{
                Thread.sleep(1000);
            }catch(InterruptedException e){}
        }
    }
    
    
}
