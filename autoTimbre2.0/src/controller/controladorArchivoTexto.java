package controller;
import controller.controladorAgregarHora;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class controladorArchivoTexto {
    private File objArchivo;
    private FileWriter objEscribir;
    private FileReader objLeer;

    public controladorArchivoTexto() {
        this.objArchivo = new File("C:\\Program Files\\Timbre\\ArchivoAutoTimbre.txt");
    }

    public void CrearArchivo(){
        if(!objArchivo.exists()){
            try {
                objArchivo.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(controladorAgregarHora.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void EliminarArchivo(){
        if(objArchivo.exists()){
            objArchivo.delete();
        }    
    }
    
    public void EscribirArchivo(String texto){
        if(objArchivo.exists()){
            try {
                this.objEscribir = new FileWriter(objArchivo, true);
                for(int i = 0; i < texto.length(); i++){
                    objEscribir.write(texto.charAt(i));
                }
                objEscribir.close();
            } catch (IOException ex) {
                Logger.getLogger(controladorAgregarHora.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public String LeerArchivo(){
        String resultado = "";
        if(objArchivo.exists()){
            try {
                this.objLeer = new FileReader(objArchivo);
                int valor = objLeer.read();
                while(valor != -1){
                    //System.out.print((char)valor);
                    resultado = resultado + (char)valor;
                    valor = objLeer.read();
                }
                objLeer.close();
            } catch (IOException ex) {
                Logger.getLogger(controladorAgregarHora.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return resultado;
    }
}
