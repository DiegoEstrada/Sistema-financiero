/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import Modelo.Resultados;
import Modelo.SituacionFinanciera;
import java.io.File;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author super
 */
public class Crear_Estado_Resultados extends javax.swing.JFrame {

        private Resultados er;
        private File guardar;
        private DefaultListModel lista = new DefaultListModel();
        
    public Crear_Estado_Resultados() {
        initComponents();
        DefaultListModel l = new DefaultListModel();
        l.addElement("");
        //this.jComboBox1.removeAllItems();
        this.ComboBoxNombreCuenta.removeAllItems();
        this.ComboBoxNombreCuenta.removeAllItems();
        this.jlCuentasAgregadas.setModel(l);
        this.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Fondo = new javax.swing.JPanel();
        PanelER = new javax.swing.JPanel();
        lbNombreCuenta = new javax.swing.JLabel();
        lbCuentas = new javax.swing.JLabel();
        ComboBoxNombreCuenta = new javax.swing.JComboBox<>();
        lbSaldoCuenta = new javax.swing.JLabel();
        txtSaldoCuenta = new javax.swing.JTextField();
        btAgregarCuenta = new javax.swing.JButton();
        jbEliminarCuenta = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jlCuentasAgregadas = new javax.swing.JList<>();
        jbCrearEstado = new javax.swing.JButton();
        PanelCrearER = new javax.swing.JPanel();
        lbExtension = new javax.swing.JLabel();
        jbCrearArchivo = new javax.swing.JButton();
        btRegresar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Crear Estado de Resultados");

        Fondo.setBackground(new java.awt.Color(153, 204, 255));

        PanelER.setBackground(new java.awt.Color(153, 204, 255));

        lbNombreCuenta.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        lbNombreCuenta.setText("Nombre de la Cuenta:");

        lbCuentas.setFont(new java.awt.Font("Rockwell", 0, 16)); // NOI18N
        lbCuentas.setText("Cuentas");

        ComboBoxNombreCuenta.setFont(new java.awt.Font("Times New Roman", 2, 11)); // NOI18N
        ComboBoxNombreCuenta.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lbSaldoCuenta.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        lbSaldoCuenta.setText("Saldo:");

        txtSaldoCuenta.setFont(new java.awt.Font("Consolas", 0, 11)); // NOI18N

        btAgregarCuenta.setBackground(new java.awt.Color(255, 255, 255));
        btAgregarCuenta.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btAgregarCuenta.setText("Agregar");
        btAgregarCuenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAgregarCuentaActionPerformed(evt);
            }
        });

        jbEliminarCuenta.setBackground(new java.awt.Color(255, 255, 255));
        jbEliminarCuenta.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jbEliminarCuenta.setText("Eliminar cuenta");
        jbEliminarCuenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbEliminarCuentaActionPerformed(evt);
            }
        });

        jlCuentasAgregadas.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jlCuentasAgregadas);

        jbCrearEstado.setBackground(new java.awt.Color(255, 255, 255));
        jbCrearEstado.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jbCrearEstado.setText("Guardar estado");
        jbCrearEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbCrearEstadoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelERLayout = new javax.swing.GroupLayout(PanelER);
        PanelER.setLayout(PanelERLayout);
        PanelERLayout.setHorizontalGroup(
            PanelERLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelERLayout.createSequentialGroup()
                .addGroup(PanelERLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelERLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(PanelERLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbSaldoCuenta)
                            .addComponent(lbNombreCuenta))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PanelERLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jbCrearEstado)
                            .addComponent(ComboBoxNombreCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbEliminarCuenta)
                            .addGroup(PanelERLayout.createSequentialGroup()
                                .addComponent(txtSaldoCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btAgregarCuenta)))
                        .addGap(10, 10, 10)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PanelERLayout.createSequentialGroup()
                        .addGap(190, 190, 190)
                        .addComponent(lbCuentas)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PanelERLayout.setVerticalGroup(
            PanelERLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelERLayout.createSequentialGroup()
                .addComponent(lbCuentas)
                .addGap(16, 16, 16)
                .addGroup(PanelERLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(PanelERLayout.createSequentialGroup()
                        .addGroup(PanelERLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbNombreCuenta)
                            .addComponent(ComboBoxNombreCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PanelERLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbSaldoCuenta)
                            .addComponent(txtSaldoCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btAgregarCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbEliminarCuenta)
                        .addGap(69, 69, 69)
                        .addComponent(jbCrearEstado))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        PanelCrearER.setBackground(new java.awt.Color(153, 204, 255));

        lbExtension.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        lbExtension.setText("Presiona el boton para generar un archivo ");

        jbCrearArchivo.setBackground(new java.awt.Color(255, 255, 255));
        jbCrearArchivo.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jbCrearArchivo.setText("Crear Archivo");
        jbCrearArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbCrearArchivoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelCrearERLayout = new javax.swing.GroupLayout(PanelCrearER);
        PanelCrearER.setLayout(PanelCrearERLayout);
        PanelCrearERLayout.setHorizontalGroup(
            PanelCrearERLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelCrearERLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(lbExtension)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jbCrearArchivo)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PanelCrearERLayout.setVerticalGroup(
            PanelCrearERLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelCrearERLayout.createSequentialGroup()
                .addGroup(PanelCrearERLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbExtension)
                    .addComponent(jbCrearArchivo))
                .addGap(0, 14, Short.MAX_VALUE))
        );

        btRegresar.setBackground(new java.awt.Color(255, 255, 255));
        btRegresar.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btRegresar.setText("Regresar");
        btRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRegresarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout FondoLayout = new javax.swing.GroupLayout(Fondo);
        Fondo.setLayout(FondoLayout);
        FondoLayout.setHorizontalGroup(
            FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FondoLayout.createSequentialGroup()
                .addGap(157, 157, 157)
                .addComponent(PanelCrearER, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, FondoLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btRegresar)
                .addGap(96, 96, 96))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, FondoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(PanelER, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(86, 86, 86))
        );
        FondoLayout.setVerticalGroup(
            FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, FondoLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(PanelCrearER, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PanelER, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btRegresar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Fondo, javax.swing.GroupLayout.PREFERRED_SIZE, 688, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Fondo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btAgregarCuentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAgregarCuentaActionPerformed
        String nombrecuenta,tipocuenta,saldo;
        int i;
        
        i = this.ComboBoxNombreCuenta.getSelectedIndex();
        saldo = this.txtSaldoCuenta.getText();
        nombrecuenta = this.ComboBoxNombreCuenta.getItemAt(i);
        
        //sf.agregarCuenta(saldo, saldo, saldo, saldo);
        /*
            Para obtener los nombres es mejor usar un combo bo para la clasificacion y usar ifs para el nombre de los 
            radiobuttns
        
        */
     
            //System.out.println("Voy a ingresar Activo, "+ tipocuenta +" , " +nombrecuenta+ ", "+saldo);
            er.agregarCuenta(nombrecuenta, saldo);
            lista.addElement(nombrecuenta+" = "+ saldo);
            jlCuentasAgregadas.setModel(lista);
            
        
        
    }//GEN-LAST:event_btAgregarCuentaActionPerformed

    private void btRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRegresarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btRegresarActionPerformed

    private void jbCrearArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbCrearArchivoActionPerformed
        JFileChooser guardarestado = new JFileChooser();
            FileNameExtensionFilter extension = new FileNameExtensionFilter("Estados finacieros(*.txt)", "txt");
            guardarestado.setFileFilter(extension);
            
            try{
                guardarestado.showSaveDialog(this);
                this.guardar = guardarestado.getSelectedFile();
                //nombre = this.guardar.getName();
                //System.out.println(this.guardar);
                Resultados estadodeResultados = new Resultados(guardar);
                this.er =  estadodeResultados;
                if (this.guardar != null)
                {
                    
                    //FileWriter  save=new FileWriter(guardar+".txt");
                    //save.write(areaDeTexto.getText());
                    //save.close();
                        JOptionPane.showMessageDialog(null,
                                "El archivo se ha generado correctamente",
                                                        "Información",JOptionPane.INFORMATION_MESSAGE);
                        
                        asignarCuentasCB();
                }
                
                
            }catch(Exception e){
                System.out.println("Excepcion atrapada -> "+e.getMessage());
            }
            
            
    }//GEN-LAST:event_jbCrearArchivoActionPerformed

    private void jbEliminarCuentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbEliminarCuentaActionPerformed
          int j,i = jlCuentasAgregadas.getSelectedIndex();
        String fila;
        String nombre = "";
        if(i<0)
        {
            Ventana.ShowInformationMessage("Selecciona la cuenta que deseas agregar");  //JOPTION
        }
        else
        {
            fila = jlCuentasAgregadas.getSelectedValue();
            
            for ( j = 0; j <fila.length(); j++) {
                if (!(fila.charAt(j)>=48 && fila.charAt(j)<=57) || fila.charAt(j)==32 )
                {
                    nombre = nombre + fila.charAt(j);
                }
            }
            //System.out.println("SELECCONE "+ nombre);
            er.eliminarCuenta(nombre);
            lista.removeElementAt(i);
            jlCuentasAgregadas.setModel(lista);
        }
    }//GEN-LAST:event_jbEliminarCuentaActionPerformed

    private void jbCrearEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbCrearEstadoActionPerformed
        Inicio forminicio = new Inicio();
        forminicio.setVisible(true);
        this.setVisible(false);
        this.er.crearEstadoFinanciero(false); //Envio falso porque no quiero caluclar depresiaciones ni amoritizaciones
    }//GEN-LAST:event_jbCrearEstadoActionPerformed

    public void asignarCuentasCB()
    {
        this.ComboBoxNombreCuenta.removeAllItems();
        this.ComboBoxNombreCuenta.addItem("Ventas");
        this.ComboBoxNombreCuenta.addItem("Costo de ventas");
        this.ComboBoxNombreCuenta.addItem("Gastos de operacion");
        //this.ComboBoxNombreCuenta.addItem("Gastos de administracion");
        this.ComboBoxNombreCuenta.addItem("Otros gastos financieros");
        this.ComboBoxNombreCuenta.addItem("Otros productos financieros");
    }
   
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> ComboBoxNombreCuenta;
    private javax.swing.JPanel Fondo;
    private javax.swing.JPanel PanelCrearER;
    private javax.swing.JPanel PanelER;
    private javax.swing.JButton btAgregarCuenta;
    private javax.swing.JButton btRegresar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbCrearArchivo;
    private javax.swing.JButton jbCrearEstado;
    private javax.swing.JButton jbEliminarCuenta;
    private javax.swing.JList<String> jlCuentasAgregadas;
    private javax.swing.JLabel lbCuentas;
    private javax.swing.JLabel lbExtension;
    private javax.swing.JLabel lbNombreCuenta;
    private javax.swing.JLabel lbSaldoCuenta;
    private javax.swing.JTextField txtSaldoCuenta;
    // End of variables declaration//GEN-END:variables
}
