import view.vistaAgregarPuerto;
import model.modeloAgregarPuerto;
import controller.controladorAgregarPuerto;
import java.io.IOException;

public class main {

    public static void main(String[] args) throws IOException {
        controladorAgregarPuerto objControlador = new controladorAgregarPuerto();
        objControlador.IniciarVistaAP();

    }
    
}
