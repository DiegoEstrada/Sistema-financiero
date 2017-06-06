package Vistas;

import Modelo.EstadoFinanciero;
import Modelo.SituacionFinanciera;
import java.io.File;
import java.io.FileWriter;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author super
 */

    /*
        -----ATENCION-----
        Un unico detalle que no es un error sino que puede confundir al usuaro es el jtextfield del nombre de archivo
        porque cuando da clic en crear se abre un JFChooser para seleccionar la ruta a guardar su archivo y aparte le
        vuelve a pedir un nombre, que si es distraido o no pone atenóm, puede poner tro diferente
        Lo que trae esto como consecuenica es que el archivo tenga un nombre diferente al que el piensa 
        El nombre con el que se guarda es con el que lo escribe en el JFChooser, el otro nobre sirve para crear el objeto
        de tipo Situacion Financiera
    */
public class Crear_Estado_Situacion_Financiera extends javax.swing.JFrame {

    /**
     * Creates new form Crear_Estado_Situacion_Financiera
     */
        private SituacionFinanciera sf;
        private File guardar;
        private DefaultListModel lista = new DefaultListModel();
        
    
    public Crear_Estado_Situacion_Financiera() {
        initComponents();
        DefaultListModel l = new DefaultListModel();
        l.addElement("");
        this.jcbTipoCuenta.removeAllItems();
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

        bts_TipoCuenta = new javax.swing.ButtonGroup();
        bts_Activo = new javax.swing.ButtonGroup();
        bts_Pasivo = new javax.swing.ButtonGroup();
        bts_Capital = new javax.swing.ButtonGroup();
        lbTitulo = new javax.swing.JLabel();
        lbExtension = new javax.swing.JLabel();
        lbNombreSF = new javax.swing.JLabel();
        txtNombreSF = new javax.swing.JTextField();
        lbCuentas = new javax.swing.JLabel();
        RdbActivo = new javax.swing.JRadioButton();
        RdbPasivo = new javax.swing.JRadioButton();
        RdbCapital = new javax.swing.JRadioButton();
        lbNombreCuenta = new javax.swing.JLabel();
        lbSaldoCuenta = new javax.swing.JLabel();
        txtSaldoCuenta = new javax.swing.JTextField();
        btAgregarCuenta = new javax.swing.JButton();
        btFinalizarSF = new javax.swing.JButton();
        btRegresar = new javax.swing.JButton();
        ComboBoxNombreCuenta = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jlCuentasAgregadas = new javax.swing.JList<>();
        jlbCuentasAgregadas = new javax.swing.JLabel();
        jbCrearEstado = new javax.swing.JButton();
        jcbTipoCuenta = new javax.swing.JComboBox<>();
        jbEliminarCuenta = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Crear Estado de Situación Financiera");

        lbTitulo.setFont(new java.awt.Font("Rockwell", 0, 18)); // NOI18N
        lbTitulo.setText("Crear nuevo Estado de Situacion Financiera");

        lbExtension.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        lbExtension.setText("Nombre del archivo ");

        lbNombreSF.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        lbNombreSF.setText("Ingresa el nombre:");

        lbCuentas.setFont(new java.awt.Font("Rockwell", 0, 16)); // NOI18N
        lbCuentas.setText("Cuentas");

        bts_TipoCuenta.add(RdbActivo);
        RdbActivo.setText("Activo");
        RdbActivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RdbActivoActionPerformed(evt);
            }
        });

        bts_TipoCuenta.add(RdbPasivo);
        RdbPasivo.setText("Pasivo");
        RdbPasivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RdbPasivoActionPerformed(evt);
            }
        });

        bts_TipoCuenta.add(RdbCapital);
        RdbCapital.setText("Capital");
        RdbCapital.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RdbCapitalActionPerformed(evt);
            }
        });

        lbNombreCuenta.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        lbNombreCuenta.setText("Nombre de la Cuenta:");

        lbSaldoCuenta.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        lbSaldoCuenta.setText("Saldo:");

        btAgregarCuenta.setFont(new java.awt.Font("Constantia", 0, 12)); // NOI18N
        btAgregarCuenta.setText("Agregar");
        btAgregarCuenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAgregarCuentaActionPerformed(evt);
            }
        });

        btFinalizarSF.setFont(new java.awt.Font("Constantia", 0, 12)); // NOI18N
        btFinalizarSF.setText("Guardar Estado");
        btFinalizarSF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btFinalizarSFActionPerformed(evt);
            }
        });

        btRegresar.setFont(new java.awt.Font("Constantia", 0, 12)); // NOI18N
        btRegresar.setText("Regresar");
        btRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRegresarActionPerformed(evt);
            }
        });

        ComboBoxNombreCuenta.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jlCuentasAgregadas.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jlCuentasAgregadas);

        jlbCuentasAgregadas.setText("Cuentas agregadas.");

        jbCrearEstado.setText("CrearEstado");
        jbCrearEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbCrearEstadoActionPerformed(evt);
            }
        });

        jcbTipoCuenta.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jcbTipoCuenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbTipoCuentaActionPerformed(evt);
            }
        });

        jbEliminarCuenta.setText("Eliminar cuenta");
        jbEliminarCuenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbEliminarCuentaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(79, 79, 79)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbExtension, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(651, 651, 651))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(RdbActivo)
                            .addComponent(lbNombreSF))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtNombreSF, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(81, 81, 81)
                                .addComponent(jbCrearEstado)
                                .addGap(166, 166, 166))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addComponent(ComboBoxNombreCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(RdbPasivo)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(RdbCapital)
                                        .addGap(224, 224, 224))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lbCuentas)
                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(161, 161, 161)
                                .addComponent(jbEliminarCuenta)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jlbCuentasAgregadas)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btFinalizarSF)))
                                .addContainerGap())))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(104, 104, 104)
                        .addComponent(btAgregarCuenta)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbSaldoCuenta)
                            .addComponent(lbNombreCuenta))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSaldoCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btRegresar))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(295, 295, 295)
                        .addComponent(lbTitulo)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jcbTipoCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(364, 364, 364))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(lbTitulo)
                .addGap(42, 42, 42)
                .addComponent(lbExtension)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbNombreSF)
                    .addComponent(txtNombreSF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbCrearEstado))
                .addGap(18, 18, 18)
                .addComponent(lbCuentas)
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(RdbPasivo)
                    .addComponent(RdbActivo)
                    .addComponent(RdbCapital))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(111, 111, 111)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbNombreCuenta)
                            .addComponent(ComboBoxNombreCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbSaldoCuenta)
                            .addComponent(txtSaldoCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jcbTipoCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(btAgregarCuenta)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btFinalizarSF)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btRegresar))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jlbCuentasAgregadas)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jbEliminarCuenta)
                                .addGap(29, 29, 29)))))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void RdbActivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RdbActivoActionPerformed
      asignarValoresCBTipo();
    }//GEN-LAST:event_RdbActivoActionPerformed

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
        
        
        if (this.RdbActivo.isSelected())
        {
            i = this.jcbTipoCuenta.getSelectedIndex();
            tipocuenta = this.jcbTipoCuenta.getItemAt(i);
            //System.out.println("Voy a ingresar Activo, "+ tipocuenta +" , " +nombrecuenta+ ", "+saldo);
            sf.agregarCuenta("Activo", tipocuenta, nombrecuenta, saldo);
            lista.addElement(nombrecuenta+"="+ saldo);
            jlCuentasAgregadas.setModel(lista);
            
        }
        else
         {
             if (this.RdbPasivo.isSelected())
             {
                  i = this.jcbTipoCuenta.getSelectedIndex();
                  tipocuenta = this.jcbTipoCuenta.getItemAt(i);
                  //System.out.println("Voy a ingresar Pasivo, "+ tipocuenta +" , " +nombrecuenta+ ", "+saldo);
                  sf.agregarCuenta("Pasivo", tipocuenta, nombrecuenta, saldo);
                  lista.addElement(nombrecuenta+"="+ saldo);
                  jlCuentasAgregadas.setModel(lista);
             }
             else
             {
                 if(this.RdbCapital.isSelected())
                 {
                       i = this.jcbTipoCuenta.getSelectedIndex();
                       tipocuenta = this.jcbTipoCuenta.getItemAt(i);
                       System.out.println("Voy a ingresar Capital, "+ tipocuenta +" , " +nombrecuenta+ ", "+saldo);
                       sf.agregarCuenta("Capital", tipocuenta, nombrecuenta, saldo);
                       lista.addElement(nombrecuenta+"="+ saldo);
                       jlCuentasAgregadas.setModel(lista);
                 }
                 else
                     System.out.println("Selecciona una opcion"); //JOPTION
             }
         }
        
        

        
    }//GEN-LAST:event_btAgregarCuentaActionPerformed

    private void jbCrearEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbCrearEstadoActionPerformed
        
        
        /*
        if(this.txtNombreSF.getText().isEmpty())
        {   
            System.out.println("Ingresa un nombre a tu sf"); //JOPTION
        }
        else
        {
        */
            String nombre="";
        
            
            JFileChooser guardarestado = new JFileChooser();
            FileNameExtensionFilter extension = new FileNameExtensionFilter("Estados finacieros(*.txt)", "txt");
            guardarestado.setFileFilter(extension);
            
            try{
                guardarestado.showSaveDialog(this);
                this.guardar = guardarestado.getSelectedFile();
                //nombre = this.guardar.getName();
                SituacionFinanciera situacionfinanciera = new SituacionFinanciera(guardar);
                this.sf =  situacionfinanciera;
                if (this.guardar != null)
                {
                    
                    //FileWriter  save=new FileWriter(guardar+".txt");
                    //save.write(areaDeTexto.getText());
                    //save.close();
                        JOptionPane.showMessageDialog(null,
                                "El archivo se ha generado correctamente",
                                                        "Información",JOptionPane.INFORMATION_MESSAGE);
                }
                
                
            }catch(Exception e){
            
            }
        //}
        
    }//GEN-LAST:event_jbCrearEstadoActionPerformed

    private void RdbPasivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RdbPasivoActionPerformed
        asignarValoresCBTipo();
    }//GEN-LAST:event_RdbPasivoActionPerformed

    private void RdbCapitalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RdbCapitalActionPerformed
        asignarValoresCBTipo();
    }//GEN-LAST:event_RdbCapitalActionPerformed

    private void jcbTipoCuentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbTipoCuentaActionPerformed
        asignarCuentasCB();
    }//GEN-LAST:event_jcbTipoCuentaActionPerformed

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
            sf.eliminarCuenta(nombre);
            lista.removeElementAt(i);
            jlCuentasAgregadas.setModel(lista);
        }
    }//GEN-LAST:event_jbEliminarCuentaActionPerformed

    private void btFinalizarSFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btFinalizarSFActionPerformed
        this.sf.mostrarCuentas();
        if (this.sf.verificarEstado()){
        Inicio forminicio = new Inicio();
        forminicio.setVisible(true);
        this.setVisible(false);
        this.sf.crearEstadoFinanciero(guardar);
        }
        else
            Ventana.ShowErrorMessage("Las cuentas ingresadas no estan balanceadas ");  //JOPION
        
        
    }//GEN-LAST:event_btFinalizarSFActionPerformed

    private void btRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRegresarActionPerformed
        Inicio forminicio = new Inicio();
        forminicio.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btRegresarActionPerformed

   public void asignarValoresCBTipo()
   {
         if (this.RdbActivo.isSelected())
        {
             this.jcbTipoCuenta.removeAllItems();
             this.jcbTipoCuenta.addItem("Circulante");
             this.jcbTipoCuenta.addItem("Fijo");
             this.jcbTipoCuenta.addItem("Diferido");
            
        }
        else
         {
             if (this.RdbPasivo.isSelected())
             {
                 this.jcbTipoCuenta.removeAllItems();
                 this.jcbTipoCuenta.addItem("Circulante");
                 this.jcbTipoCuenta.addItem("Fijo");
             }
             else
             {
                 if(this.RdbCapital.isSelected())
                 {
                      this.jcbTipoCuenta.removeAllItems();
                      this.jcbTipoCuenta.addItem("Capital social");
                      this.jcbTipoCuenta.addItem("Utilidad neta");
                 }
                 else
                     Ventana.ShowInformationMessage("Selecciona una opcion"); //JOPTION
             }
         }
             
        
           
   }
   

   public void asignarCuentasCB()
   {
       int i = this.jcbTipoCuenta.getSelectedIndex();
       
       if (this.RdbActivo.isSelected())
        {
             switch(i)
             {
                 case 0: //Caso Circulante
                      this.ComboBoxNombreCuenta.removeAllItems();
                      this.ComboBoxNombreCuenta.addItem("Caja");
                      this.ComboBoxNombreCuenta.addItem("Bancos");
                      this.ComboBoxNombreCuenta.addItem("Alamacen");
                      this.ComboBoxNombreCuenta.addItem("Clientes");
                      this.ComboBoxNombreCuenta.addItem("Deudores");
                      this.ComboBoxNombreCuenta.addItem("Documentos por cobrar");
                 break;
                 case 1: //Caso Fijo
                     this.ComboBoxNombreCuenta.removeAllItems();
                     this.ComboBoxNombreCuenta.addItem("Terrenos");
                     this.ComboBoxNombreCuenta.addItem("Edificios");
                     this.ComboBoxNombreCuenta.addItem("Mobiliario y equipo");
                     this.ComboBoxNombreCuenta.addItem("Equipo de reparto");
                     this.ComboBoxNombreCuenta.addItem("Equipo de transporte");
                     this.ComboBoxNombreCuenta.addItem("Equipo de computo");
                     break;
                 case 2: //Caso Diferido
                      this.ComboBoxNombreCuenta.removeAllItems();
                      this.ComboBoxNombreCuenta.addItem("Gastos de instalacion ");
                      this.ComboBoxNombreCuenta.addItem("Gastos de organizacion");
                      this.ComboBoxNombreCuenta.addItem("Papeleria y utiles ");
                      this.ComboBoxNombreCuenta.addItem("Propaganda y publicidad");
                     break;
                 default:
                     Ventana.ShowInformationMessage("Selecciona un tipo de cuenta"); //JOPTION
                     break;
             }
            
        }
        else
         {
             if (this.RdbPasivo.isSelected())
             {
                 switch(i)
                {
                 case 0: //Caso Circulante
                        this.ComboBoxNombreCuenta.removeAllItems();
                        this.ComboBoxNombreCuenta.addItem("Proveedores");
                        this.ComboBoxNombreCuenta.addItem("Deudores");
                        this.ComboBoxNombreCuenta.addItem("Acreedores");
                        this.ComboBoxNombreCuenta.addItem("Documentos por pagar");
                     break;
                 case 1:  //Caso Fijo
                        this.ComboBoxNombreCuenta.removeAllItems();
                        this.ComboBoxNombreCuenta.addItem("Documentos por pagar a largo plazo");
                        this.ComboBoxNombreCuenta.addItem("Acreedores hipotecarios");
                     break;
                 default:
                      Ventana.ShowInformationMessage("Selecciona una cuenta"); //JOPTION
                     break;
                }
                         
             }
             else
             {
                 if(this.RdbCapital.isSelected())
                 {
                       switch(i)
                        {
                        case 0: //Caso Capital social
                            this.ComboBoxNombreCuenta.removeAllItems();
                            this.ComboBoxNombreCuenta.addItem("Capital social");
                        
                            break;
                        case 1: // Caso Utilidad neta
                            this.ComboBoxNombreCuenta.removeAllItems();
                            this.ComboBoxNombreCuenta.addItem("Utilidad neta");
                            break;
                        default:
                             Ventana.ShowInformationMessage("Selecciona una cuenta"); //JOPTION
                            break;
                        }
                 }
                 else
                     Ventana.ShowInformationMessage("Selecciona un tipo de cuenta"); //JOPTION
             }
         }
   }
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> ComboBoxNombreCuenta;
    private javax.swing.JRadioButton RdbActivo;
    private javax.swing.JRadioButton RdbCapital;
    private javax.swing.JRadioButton RdbPasivo;
    private javax.swing.JButton btAgregarCuenta;
    private javax.swing.JButton btFinalizarSF;
    private javax.swing.JButton btRegresar;
    private javax.swing.ButtonGroup bts_Activo;
    private javax.swing.ButtonGroup bts_Capital;
    private javax.swing.ButtonGroup bts_Pasivo;
    private javax.swing.ButtonGroup bts_TipoCuenta;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbCrearEstado;
    private javax.swing.JButton jbEliminarCuenta;
    private javax.swing.JComboBox<String> jcbTipoCuenta;
    private javax.swing.JList<String> jlCuentasAgregadas;
    private javax.swing.JLabel jlbCuentasAgregadas;
    private javax.swing.JLabel lbCuentas;
    private javax.swing.JLabel lbExtension;
    private javax.swing.JLabel lbNombreCuenta;
    private javax.swing.JLabel lbNombreSF;
    private javax.swing.JLabel lbSaldoCuenta;
    private javax.swing.JLabel lbTitulo;
    private javax.swing.JTextField txtNombreSF;
    private javax.swing.JTextField txtSaldoCuenta;
    // End of variables declaration//GEN-END:variables
}
