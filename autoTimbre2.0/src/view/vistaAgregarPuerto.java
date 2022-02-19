package view;
import com.panamahitek.PanamaHitek_Arduino;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.panamahitek.ArduinoException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;

public class vistaAgregarPuerto extends javax.swing.JFrame {
//    PanamaHitek_Arduino ca = new PanamaHitek_Arduino();
//    List<String> lp = new ArrayList<String>();
//    
    public vistaAgregarPuerto() {
        initComponents();
        //lp = ca.getSerialPorts();
        //BuscarPuertos();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblPuerto = new javax.swing.JLabel();
        cbPuerto = new javax.swing.JComboBox<>();
        btnBuscar = new javax.swing.JButton();
        btnAgregarPuerto = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblPuerto.setText("Puerto:");

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        btnAgregarPuerto.setText("Agregar puerto");
        btnAgregarPuerto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarPuertoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(lblPuerto)
                .addGap(18, 18, 18)
                .addComponent(cbPuerto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnBuscar)
                .addGap(18, 18, 18)
                .addComponent(btnAgregarPuerto)
                .addContainerGap(50, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnAgregarPuerto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblPuerto)
                        .addComponent(cbPuerto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnBuscar)))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarPuertoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarPuertoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAgregarPuertoActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBuscarActionPerformed

//    public void BuscarPuertos(){
//        String p;
//        cbPuerto.removeAllItems();
//        lp = ca.getSerialPorts();
//        for(int i = 0; i < lp.size(); i++){
//            p = lp.get(i);
//            cbPuerto.addItem(p);          
//        }
//    }
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(vistaAgregarPuerto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(vistaAgregarPuerto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(vistaAgregarPuerto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(vistaAgregarPuerto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new vistaAgregarPuerto().setVisible(true);
            }
        });
    }

    public JButton getBtnAgregarPuerto() {
        return btnAgregarPuerto;
    }

    public void setBtnAgregarPuerto(JButton btnAgregarPuerto) {
        this.btnAgregarPuerto = btnAgregarPuerto;
    }

    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    public void setBtnBuscar(JButton btnBuscar) {
        this.btnBuscar = btnBuscar;
    }

    public JComboBox<String> getCbPuerto() {
        return cbPuerto;
    }

    public void setCbPuerto(JComboBox<String> cbPuerto) {
        this.cbPuerto = cbPuerto;
    }

    public JLabel getLblPuerto() {
        return lblPuerto;
    }

    public void setLblPuerto(JLabel lblPuerto) {
        this.lblPuerto = lblPuerto;
    }
 
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnAgregarPuerto;
    public javax.swing.JButton btnBuscar;
    public javax.swing.JComboBox<String> cbPuerto;
    public javax.swing.JLabel lblPuerto;
    // End of variables declaration//GEN-END:variables
}
