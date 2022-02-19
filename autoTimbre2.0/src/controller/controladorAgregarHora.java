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

public class controladorAgregarHora implements ActionListener, Runnable{
    private vistaAgregarHora objVistaAH;
    private modeloAgregarHora objModeloAH;
    private modeloAgregarPuerto objModeloAP;
    private controladorArchivoTexto objModeloAT;
    private PanamaHitek_Arduino objConexionA; 
    private DefaultTableModel modeloTabla;
    Thread hilo1;
    
    //en el constructor se instancian todos los objetos
    public controladorAgregarHora() throws IOException{
        this.objVistaAH = new vistaAgregarHora();
        this.objModeloAH = new modeloAgregarHora(null, null);
        this.objModeloAP = new modeloAgregarPuerto(null, null, null);
        this.objModeloAT = new controladorArchivoTexto();
        this.objConexionA = new PanamaHitek_Arduino();
        this.modeloTabla = new DefaultTableModel();
        this.objVistaAH.getBtnSalir().addActionListener(this);
        this.objVistaAH.getBtnEliminar().addActionListener(this);
        this.objVistaAH.getBtnAgregarHora().addActionListener(this);
        this.objVistaAH.getBtnEncenderTimbre().addActionListener(this);
    }
    
    //metodo abstracto que se ejecutara para iniciar la vista
    public void IniciarVistaAH(String puertoSeleccionado){
        objVistaAH.setTitle("AutoTimbre version 2.0");
        objVistaAH.setLocationRelativeTo(null);
        objVistaAH.setVisible(true);
        objVistaAH.tblHoras.setModel(modeloTituloColumna());      
        objVistaAH.tblHoras.setModel(modeloTablaHoras());      
        hilo1 = new Thread(this);
        hilo1.start();
        
        //inicia la conexion con el puerto donde esta el arduino
         try{
         objConexionA.arduinoTX(objModeloAP.getPuertoSeleccionado(), 9600);
        }catch(ArduinoException ex){
            Logger.getLogger(controladorAgregarHora.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //metodo abstracto para desencadenar los eventos que se realicen
    public void actionPerformed(ActionEvent e) {
        // Evento del boton salir
        if(objVistaAH.getBtnSalir() == e.getSource()){
            System.exit(0);
        }
        
        // Evento del boton eliminar
        if(objVistaAH.getBtnEliminar() == e.getSource()){
            objModeloAT.EliminarArchivo();
            objVistaAH.tblHoras.setModel(modeloTablaLimpiar());    
        }
        
        // Evento del boton agregar hora
        if(objVistaAH.getBtnAgregarHora()== e.getSource()){
            objModeloAT.CrearArchivo();
            objModeloAT.EscribirArchivo(objVistaAH.getSpHoras().getValue().toString() + ":" + objVistaAH.getSpMinutos().getValue().toString() + ":" + objVistaAH.getSpSegundos().getValue().toString() + ",");
            objVistaAH.tblHoras.setModel(modeloTablaLimpiar());   
            objVistaAH.tblHoras.setModel(modeloTablaHoras());
        }
        
        // Evento del boton encender timbre
         if(objVistaAH.getBtnEncenderTimbre()== e.getSource()){
            TocarTimbre();
        }
    }
    
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
    
    public void HorasTimbrar(){
        String[] horasTimbrar = null;
        String cadenaHoras = objModeloAT.LeerArchivo();
        if(!cadenaHoras.isEmpty() && cadenaHoras != null){
            cadenaHoras = cadenaHoras.substring(0, cadenaHoras.length()-1);
            objModeloAH.setHorasTimbrar(cadenaHoras.split(","));
        }
    }

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
        
    public void TocarTimbre(){
        try {
            objConexionA.sendData("1");
        } catch (ArduinoException | SerialPortException ex) {
            Logger.getLogger(controladorAgregarHora.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public DefaultTableModel modeloTituloColumna(){
        this.modeloTabla.addColumn("Horarios");
        return this.modeloTabla;
    }
    
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
    
    public DefaultTableModel modeloTablaLimpiar(){
        this.modeloTabla.setRowCount(0);
        return this.modeloTabla;
    } 
        
    public void run(){
        while(hilo1.isAlive()){
            Timbrar();
            //lblHoraActual.setText(horaActual);
            try{
                Thread.sleep(1000);
            }catch(InterruptedException e){}
        }
    }
    
    
}
