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
import java.util.HashMap;
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
    private SituacionFinanciera sfProforma;
    private Resultados erProforma;
    //private Map<String,ArrayList<String>> cuentasModificadasSF;
    //private Map<String,ArrayList<String>> cuentasModificadasER;
    private DefaultListModel lista;
    private float ad;
    
    public Propuestas(SituacionFinanciera esf, Resultados ers) {
        initComponents();
        this.sf = esf;
        this.er = ers;
        
        this.sf.leerEstadoFinanciero();
        this.er.leerEstadoFinanciero();
        
        //cuentasModificadasER = er.getCuentas();
        //cuentasModificadasSF = sf.getCuentas();
        
        
        
        String rutaoriginal = this.sf.getF().getParent();
        String nombres [] = nombresNuevosArchivos();
        String nombreProformaSF = nombres[0];
        String nombresProformaER = nombres[1];
        File proformaSF = new File(rutaoriginal+"\\"+nombreProformaSF);
        File proformaER = new File(rutaoriginal+"\\"+nombresProformaER);
        
        //System.out.println(proformaSF);
        //System.out.println(proformaER);
        
        this.sfProforma = new SituacionFinanciera(proformaSF);
        this.erProforma = new Resultados(proformaER);
        
        Map<String,ArrayList<String>> cuentasFiltradasSF  = filtrarCuentasSuma();
        Map<String,ArrayList<String>> cuentasOriginalesER  = filtrarCuentasUtilidad();
        
        sfProforma.setCuentas(cuentasFiltradasSF);
        erProforma.setCuentas(cuentasOriginalesER);
        
        
        
        sfProforma.mostrarCuentas();
        
                
        //System.out.println(cuentasModificadasSF.containsKey("Depresiacion "));
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
        
        ArrayList<String> cuentasOrdenadasSFFiltradas = filtrarCuentasCBSF(cuentasOrdenadasSF);
        ArrayList<String> cuentasOrdenadasERFiltradas = filtrarCuentasCBER(cuentasOrdenadasER);
        
        //Agregando las cuentas de SF
        for (int i = 0; i < cuentasOrdenadasSFFiltradas.size(); i++) {
            this.ComboBoxCuentasMod.addItem(cuentasOrdenadasSFFiltradas.get(i));   
        }
        
        //Agregando las cuentas de ER
        
            this.ComboBoxCuentasMod.addItem("---------");
            
        for (int j = 0; j < cuentasOrdenadasERFiltradas.size(); j++) {
            this.ComboBoxCuentasMod.addItem(cuentasOrdenadasERFiltradas.get(j));
        }
    }
    
    public String obtenerNombreSeleccionadaCB()
    {
        String cuenta  = "";
        int i = this.ComboBoxCuentasMod.getSelectedIndex();
        cuenta = this.ComboBoxCuentasMod.getItemAt(i);
        return cuenta;
    }
    
    public ArrayList<String> filtrarCuentasCBSF(ArrayList<String> cuentas)
    {
        String palabraAFiltrar = "Suma";
        ArrayList<String> cuentasordendas = new ArrayList();
        
        for (int i = 0; i < cuentas.size(); i++) {
            if (!cuentas.get(i).contains(palabraAFiltrar)){
                cuentasordendas.add(cuentas.get(i));
            }
        }
        
        return cuentasordendas;
    }
    public ArrayList<String> filtrarCuentasCBER(ArrayList<String> cuentas)
    {
        String palabraAFiltrar = "Utilidad";
        ArrayList<String> cuentasordendas = new ArrayList();
        
        for (int i = 0; i < cuentas.size(); i++) {
            if (!cuentas.get(i).contains(palabraAFiltrar)){
                cuentasordendas.add(cuentas.get(i));
            }
        }
        
        return cuentasordendas;
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
    
    public Map<String,ArrayList<String>> filtrarCuentasSuma()
    {
        String palabraAquitarSF = "Suma";
        
        String nombreactual;
        ArrayList<String> datos = new ArrayList();
        Map<String,ArrayList<String>> cuentasOriginalesSF = sf.getCuentas();
        Map<String,ArrayList<String>> cuentasFiltradas = new HashMap();
        
        Iterator itSF = cuentasOriginalesSF.keySet().iterator();
        
        while(itSF.hasNext())
        {
            nombreactual = itSF.next().toString();
            if(!nombreactual.contains(palabraAquitarSF))
            {
                datos = cuentasOriginalesSF.get(nombreactual);
                cuentasFiltradas.put(nombreactual, datos);
            }
        }
        
        
       
        
        return cuentasFiltradas;
    }
    
    public Map<String, ArrayList<String>> filtrarCuentasUtilidad()
    {
        String nombreactual;
        String palabraAquitarER = "Utilidad";
        Map<String,ArrayList<String>> cuentasOriginalesER = er.getCuentas();
        Map<String,ArrayList<String>> cuentasFiltradas = new HashMap();
        ArrayList<String> datos = new ArrayList();
        
        Iterator itER = cuentasOriginalesER.keySet().iterator();
        
        while(itER.hasNext())
        {
            nombreactual = itER.next().toString();
            if(!nombreactual.contains(palabraAquitarER))
            {
                datos = cuentasOriginalesER.get(nombreactual);
                cuentasFiltradas.put(nombreactual, datos);
            }
        }
        
        return cuentasFiltradas;
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Fondo = new javax.swing.JPanel();
        txtNuevoSaldo = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jlCambios = new javax.swing.JList<>();
        lbCuentasMod = new javax.swing.JLabel();
        btModificar = new javax.swing.JButton();
        btRegresar = new javax.swing.JButton();
        jbRegistrar = new javax.swing.JButton();
        lbCuentaAMod = new javax.swing.JLabel();
        ComboBoxCuentasMod = new javax.swing.JComboBox<>();
        lbNuevoSaldo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Realizar Propuestas");
        setResizable(false);

        Fondo.setBackground(new java.awt.Color(153, 204, 255));

        txtNuevoSaldo.setFont(new java.awt.Font("Consolas", 0, 11)); // NOI18N

        jlCambios.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jlCambios.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jlCambios);

        lbCuentasMod.setFont(new java.awt.Font("Rockwell", 0, 12)); // NOI18N
        lbCuentasMod.setText("Cuentas modificadas:");

        btModificar.setBackground(new java.awt.Color(255, 255, 255));
        btModificar.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btModificar.setText("Modificar");
        btModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btModificarActionPerformed(evt);
            }
        });

        btRegresar.setBackground(new java.awt.Color(255, 255, 255));
        btRegresar.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btRegresar.setText("Regresar");
        btRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRegresarActionPerformed(evt);
            }
        });

        jbRegistrar.setBackground(new java.awt.Color(255, 255, 255));
        jbRegistrar.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jbRegistrar.setText("Registrar cambios");
        jbRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbRegistrarActionPerformed(evt);
            }
        });

        lbCuentaAMod.setFont(new java.awt.Font("Rockwell", 0, 12)); // NOI18N
        lbCuentaAMod.setText("Seleccione las cuentas que desea modificar:");

        ComboBoxCuentasMod.setFont(new java.awt.Font("Times New Roman", 2, 11)); // NOI18N
        ComboBoxCuentasMod.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lbNuevoSaldo.setFont(new java.awt.Font("Rockwell", 0, 12)); // NOI18N
        lbNuevoSaldo.setText("Nuevo Saldo:");

        javax.swing.GroupLayout FondoLayout = new javax.swing.GroupLayout(Fondo);
        Fondo.setLayout(FondoLayout);
        FondoLayout.setHorizontalGroup(
            FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FondoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, FondoLayout.createSequentialGroup()
                        .addGroup(FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btModificar)
                            .addGroup(FondoLayout.createSequentialGroup()
                                .addGroup(FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lbNuevoSaldo)
                                    .addComponent(lbCuentaAMod))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(ComboBoxCuentasMod, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtNuevoSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jbRegistrar))
                        .addGap(18, 18, 18)
                        .addGroup(FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(FondoLayout.createSequentialGroup()
                                .addComponent(lbCuentasMod)
                                .addGap(0, 379, Short.MAX_VALUE))))
                    .addComponent(btRegresar, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        FondoLayout.setVerticalGroup(
            FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FondoLayout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addGroup(FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbCuentaAMod)
                    .addComponent(ComboBoxCuentasMod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbCuentasMod))
                .addGroup(FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(FondoLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbNuevoSaldo)
                            .addComponent(txtNuevoSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(btModificar)
                        .addGap(46, 46, 46)
                        .addComponent(jbRegistrar)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, FondoLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btRegresar)
                        .addGap(6, 6, 6))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Fondo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Fondo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btModificarActionPerformed
        
        //Vamos a modifiar en los dos estados el valor que modifique
        
        String cuentaACambiar = obtenerNombreSeleccionadaCB();
        String cuentaActual;
        ArrayList<String> datos;
      
        Map<String, ArrayList<String>> cuentasModificadasER = erProforma.getCuentas();
        Map<String, ArrayList<String>> cuentasModificadasSF = sfProforma.getCuentas();
        
        System.out.println(cuentasModificadasSF.size());
        
        Iterator itER = cuentasModificadasER.keySet().iterator();
        
        while(itER.hasNext())
        {
            cuentaActual = itER.next().toString(); // contiene la llave de la cuenta en iteracion 
            
            if(cuentaActual.contains(cuentaACambiar))
            {
               datos = cuentasModificadasER.get(cuentaActual);
            
                if(!this.txtNuevoSaldo.getText().isEmpty())
                {
                    //nuevosDatos.add(this.txtNuevoSaldo.getText());
                    //datos.set(0, txtNuevoSaldo.getText());
                    //erProforma.eliminarCuenta(cuentaActual);
                    erProforma.agregarCuenta(cuentaActual, this.txtNuevoSaldo.getText());
                    agregarElemntoAJLista("Cuenta "+ cuentaActual+ " de Estado de Resultados modifcada con "+this.txtNuevoSaldo.getText());
                    System.out.println("Cuenta en ER "+ cuentaActual+ " modificada con "+ this.txtNuevoSaldo.getText());
                    break;
                }
                //else
                //{
                  //  erProforma.eliminarCuenta(cuentaActual);
                   // erProforma.agregarCuenta(cuentaActual, datos.get(0));
                //}
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
                    sf.modificarValorCuenta(cuentaACambiar, this.txtNuevoSaldo.getText());
                    //sfProforma.eliminarCuenta(cuentaActual);
                    //sfProforma.agregarCuenta(cuentaActual, datos.get(0), datos.get(1), this.txtNuevoSaldo.getText());
                    agregarElemntoAJLista("Cuenta "+ cuentaActual+ " de Estado de Situación  modifcada con "+this.txtNuevoSaldo.getText());
                    System.out.println("Cuenta en SF "+ cuentaActual+ " modificada con "+ this.txtNuevoSaldo.getText());
                    break;
                }
                
                //else
                //{
                  ///  sfProforma.eliminarCuenta(cuentaActual);
                    //sfProforma.agregarCuenta(cuentaActual, datos.get(0), datos.get(1), datos.get(2));
               // }
               
            }
            
        }
    }//GEN-LAST:event_btModificarActionPerformed

    private void jbRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbRegistrarActionPerformed

        if(this.sfProforma.verificarEstado())
        {
           System.out.println("Cuentas bien");
            this.sfProforma.crearEstadoFinanciero(true);
            
            this.sfProforma.leerEstadoFinanciero();
                    
            float a = sfProforma.obtenerSaldodeLlave("Amortizacion");
            float d = sfProforma.obtenerSaldodeLlave("Depreciacion");
            System.out.println("AMORTIZACION->>>>>"+a);
            System.out.println("DEPRESICACION->>>>"+d);
        
        
            this.erProforma.setAmprtizacionDepresiacion(a+d);
            this.erProforma.crearEstadoFinanciero(true);
        
            
            
            Ventana.ShowInformationMessage("¡Modificaciones realizadas con exito!");
            File fSF= this.sf.getF();
            File fER= this.er.getF();
            
            AnalisisEF EF = new AnalisisEF(fSF, fER);
            EF.setVisible(true);
            this.setVisible(false);
        }
    }//GEN-LAST:event_jbRegistrarActionPerformed

    private void btRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRegresarActionPerformed
        // TODO add your handling code here:
        File fSF= this.sf.getF();
        File fER= this.er.getF();
            
        AnalisisEF EF = new AnalisisEF(fSF, fER);
        EF.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btRegresarActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> ComboBoxCuentasMod;
    private javax.swing.JPanel Fondo;
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
