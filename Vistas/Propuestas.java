/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import Modelo.Resultados;
import Modelo.SituacionFinanciera;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Diego EG
 */
public class Propuestas extends javax.swing.JFrame {

    private SituacionFinanciera sf;
    private Resultados er;
    private Map<String,ArrayList<String>> cuentasModificadasSF;
    private Map<String,ArrayList<String>> cuentasModificadasER;
    private DefaultListModel lista;
    private float ad;
    
    public Propuestas(SituacionFinanciera esf, Resultados ers) {
        initComponents();
        this.sf = esf;
        this.er = ers;
        
        this.sf.leerEstadoFinanciero();
        this.er.leerEstadoFinanciero();
        
        cuentasModificadasER = er.getCuentas();
        cuentasModificadasSF = sf.getCuentas();
        
        
        
        System.out.println(cuentasModificadasSF.containsKey("Depresiacion "));
        float d = sf.obtenerSaldodeLlave("Depreciacion acumulada");
        float a = sf.obtenerSaldodeLlave("Amortizacion acumulada");
        ad = a+d;
        System.out.println(ad);
         
        lista = new DefaultListModel();
        //lista.addElement("Ninguna cuenta modificada");
  
               
        this.jlCambios.setModel(lista);
        
        cargarCuentasCB();
    }

    public void cargarCuentasCB()
    {
        this.ComboBoxCuentasMod.removeAllItems();
        ArrayList<String> cuentasOrdenadasSF = sf.obtenerNombresdeCuentasOrdenadas();
        ArrayList<String> cuentasOrdenadasER = er.obtenerNombresdeCuentasOrdenadas();
        
        //Agregando las cuentas de SF
        for (int i = 0; i < cuentasOrdenadasSF.size(); i++) {
            this.ComboBoxCuentasMod.addItem(cuentasOrdenadasSF.get(i));   
        }
        
        //Agregando las cuentas de ER
        
            this.ComboBoxCuentasMod.addItem("---------");
        for (int j = 0; j < cuentasOrdenadasER.size(); j++) {
            this.ComboBoxCuentasMod.addItem(cuentasOrdenadasER.get(j));
        }
    }
    
    public String obtenerNombreSeleccionadaCB()
    {
        String cuenta  = "";
        int i = this.ComboBoxCuentasMod.getSelectedIndex();
        cuenta = this.ComboBoxCuentasMod.getItemAt(i);
        return cuenta;
    }
    
    public String[] nombresNuevosArchivos()
    {
        String nombreOriginalArchivoSFExtension = sf.getF().getName();
        String nombreOriginalArchivoERExtension = er.getF().getName();
        String nombreOriginaArchivoSF[] = nombreOriginalArchivoSFExtension.split("\\.");
        String nombreOriginaArchivoER[] = nombreOriginalArchivoERExtension.split("\\.");
        String nombreSF = nombreOriginaArchivoSF[0];
        String nombreER = nombreOriginaArchivoER[0];
        nombreSF = nombreSF + " Proforma.txt";
        nombreER = nombreER + " Proforma.txt";
        
        String nombres[] = new String[2] ;
        nombres[0] = nombreSF;
        nombres[1] = nombreER;
        
        return nombres;
        
    }
    
    public void agregarElemntoAJLista(String cambio)
    {
        lista.addElement(cambio);
        jlCambios.setModel(lista);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbCuentaAMod = new javax.swing.JLabel();
        ComboBoxCuentasMod = new javax.swing.JComboBox<>();
        lbNuevoSaldo = new javax.swing.JLabel();
        txtNuevoSaldo = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jlCambios = new javax.swing.JList<>();
        lbCuentasMod = new javax.swing.JLabel();
        btModificar = new javax.swing.JButton();
        btRegresar = new javax.swing.JButton();
        jbRegistrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lbCuentaAMod.setText("Seleccione las cuentas que desea modificar:");

        ComboBoxCuentasMod.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lbNuevoSaldo.setText("Nuevo Saldo:");

        jlCambios.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jlCambios);

        lbCuentasMod.setText("Cuentas modificadas:");

        btModificar.setText("Modificar");
        btModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btModificarActionPerformed(evt);
            }
        });

        btRegresar.setText("Regresar");
        btRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRegresarActionPerformed(evt);
            }
        });

        jbRegistrar.setText("Regsitrar cambios");
        jbRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbRegistrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbCuentaAMod, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ComboBoxCuentasMod, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lbNuevoSaldo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jbRegistrar)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtNuevoSaldo)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(btModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(0, 0, Short.MAX_VALUE))))))
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lbCuentasMod)
                                .addGap(0, 161, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btRegresar)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbCuentaAMod)
                    .addComponent(lbCuentasMod))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(ComboBoxCuentasMod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbNuevoSaldo)
                            .addComponent(txtNuevoSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(btModificar))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btRegresar)
                    .addComponent(jbRegistrar))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRegresarActionPerformed
        Inicio formularioprincipal = new Inicio();
        formularioprincipal.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btRegresarActionPerformed

    private void btModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btModificarActionPerformed
        
        //Vamos a modifiar en los dos estados el valor que modifique
        
        String cuentaACambiar = obtenerNombreSeleccionadaCB();
        String cuentaActual;
        ArrayList<String> datos;
        
        Iterator itER = cuentasModificadasER.keySet().iterator();
        
        while(itER.hasNext())
        {
            cuentaActual = itER.next().toString(); // contiene la llave de la cuenta en iteracion 
            
            if(cuentaActual.contains(cuentaACambiar))
            {
                datos = cuentasModificadasER.get(cuentaACambiar);
            
                if(!this.txtNuevoSaldo.getText().isEmpty())
                {
                    datos.set(0, txtNuevoSaldo.getText());
                    cuentasModificadasER.remove(cuentaActual);
                    cuentasModificadasER.put(cuentaActual, datos);
                     agregarElemntoAJLista("Cuenta "+ cuentaActual+ " de Estado de Resultados modifcada con "+datos.get(0));
                     System.out.println("Cuenta en ER "+ cuentaActual+ " modificada con "+ datos.get(0));
                }
                break;
            }
            
        }
        
        
        
        Iterator itSF = cuentasModificadasSF.keySet().iterator();
        
        while(itSF.hasNext())
        {
            cuentaActual = itSF.next().toString(); // contiene la llave de la cuenta en iteracion 
            
            if(cuentaActual.contains(cuentaACambiar))
            {
                datos = cuentasModificadasSF.get(cuentaACambiar);
            
                if(!this.txtNuevoSaldo.getText().isEmpty())
                {
                    datos.set(2, txtNuevoSaldo.getText());
                    cuentasModificadasSF.remove(cuentaActual);
                    cuentasModificadasSF.put(cuentaActual, datos);
                    agregarElemntoAJLista("Cuenta "+ cuentaActual+ " de Estado de Situación  modifcada con "+datos.get(2));
                    System.out.println("Cuenta en SF "+ cuentaActual+ " modificada con "+ datos.get(2));
                }
                break;
            }
            
        }
                
        
    }//GEN-LAST:event_btModificarActionPerformed

    private void jbRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbRegistrarActionPerformed
        
        
        String nombres[] = nombresNuevosArchivos();
        
        String nombreSF = nombres[0]; 
        String nombreER = nombres[1];
        
        
        JFileChooser abrredo = new JFileChooser();
        FileNameExtensionFilter extension = new FileNameExtensionFilter("Estados finacieros(*.txt)", "txt");
        abrredo.setDialogTitle("Selecciona un estado financiero");
        abrredo.setFileFilter(extension);
        abrredo.showDialog(this, "Seleccionar");
        
        //La posicion 0 del array es situacion financiera y la 1 resultados
        
        try {
            File archivo = abrredo.getCurrentDirectory();
            System.out.println("Resultado "+archivo);
            
            File archivoSF = new File(archivo.toString()+"\\"+nombreSF);
            File archivoER = new File(archivo.toString()+"\\"+nombreER);
            
            SituacionFinanciera sfProforma = new SituacionFinanciera(archivoSF);
            Resultados erProforma = new Resultados(archivoER);
            
            sfProforma.setCuentas(cuentasModificadasSF);
            erProforma.setCuentas(cuentasModificadasER);
            if (sfProforma.verificarEstado()){
            sfProforma.crearEstadoFinanciero(false); //Si quiero que calcule amortizaciones y depresiaciones
            }
            else
                System.out.println("Las modificaciones no estan balanceadas "); //JOPTION
            
            erProforma.crearEstadoFinanciero(false); //Si quiero que calcule amortizaciones y depresiaciones
            
            
            
        }catch(Exception e){
            System.out.println("Excepcion ->"+e.getMessage());
        }
    }//GEN-LAST:event_jbRegistrarActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> ComboBoxCuentasMod;
    private javax.swing.JButton btModificar;
    private javax.swing.JButton btRegresar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbRegistrar;
    private javax.swing.JList<String> jlCambios;
    private javax.swing.JLabel lbCuentaAMod;
    private javax.swing.JLabel lbCuentasMod;
    private javax.swing.JLabel lbNuevoSaldo;
    private javax.swing.JTextField txtNuevoSaldo;
    // End of variables declaration//GEN-END:variables
}
