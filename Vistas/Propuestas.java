/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import Modelo.Resultados;
import Modelo.SituacionFinanciera;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;

/**
 *
 * @author Diego EG
 */
public class Propuestas extends javax.swing.JFrame {

    private SituacionFinanciera sf;
    private Resultados er;
    private SituacionFinanciera sfProforma;
    private Resultados erProforma;
    private ArrayList<String> historia;
    private ArrayList<String> ingresos;
    private ArrayList<String> egresoas;
    private float cajaoriginal;
    private float bancooriginal;
    private float utilidadNeta;
    private File archivoHistoria;
    private File archivoFlujo;
    //private Map<String,ArrayList<String>> cuentasModificadasSF;
    //private Map<String,ArrayList<String>> cuentasModificadasER;
    private DefaultListModel lista;
    private float ad;
    
    public Propuestas(SituacionFinanciera esf, Resultados ers) {
        initComponents();
        
         //Colocando el icono del sistema 
        try{
            setIconImage(new ImageIcon(getClass().getResource("../Imagenes/accounting_icon.jpg")).getImage());
        }catch (Exception e){
            System.out.println("Error al cargar la imagen. Excepcion-> "+e.getMessage());
        }
        
        this.sf = esf;
        this.er = ers;
        
        this.sf.leerEstadoFinanciero();
        this.er.leerEstadoFinanciero();
        
        //cuentasModificadasER = er.getCuentas();
        //cuentasModificadasSF = sf.getCuentas();
        
        this.cajaoriginal = sf.obtenerSaldodeLlave("Caja");
        this.bancooriginal =sf.obtenerSaldodeLlave("Bancos");
        this.utilidadNeta =sf.obtenerSaldodeLlave("Utilidad neta");
        
        this.historia = new ArrayList();
        this.ingresos = new ArrayList();
        this.egresoas = new ArrayList();
        
        String rutaoriginal = this.sf.getF().getParent();
        String nombres [] = nombresNuevosArchivos();
        String nombreProformaSF = nombres[0];
        String nombresProformaER = nombres[1];
        File proformaSF = new File(rutaoriginal+"\\"+nombreProformaSF);
        File proformaER = new File(rutaoriginal+"\\"+nombresProformaER);
        archivoHistoria = new File(rutaoriginal+"\\MovimientosRealizados.txt");
        archivoFlujo = new File(rutaoriginal+"\\EstadoFlujoEfectivo.txt");
        
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
                //System.out.println("CUENTAS:: "+nombreactual+ " ->"+datos.get(2));
            }
        }
        
        
       
        
        return cuentasFiltradas;
    }
    
    public void escribirER()
    {
        try {
            FileWriter w;
            BufferedWriter bw;
            PrintWriter wr;
            float s = 0;
            float r = 0;
           
            //f = new File(nomEdoFin);
            w = new FileWriter(this.archivoFlujo);
            bw=new BufferedWriter(w);
            wr=new PrintWriter(bw);
            
            wr.println("\t\t\t Estado de flujos de efectivo  \t\t\t");
            //float caja = sfProforma.obtenerSaldodeLlave("Caja");
            //float bancos = sfProforma.obtenerSaldodeLlave("Bancos");
            float bc = this.cajaoriginal + this.bancooriginal;
            System.out.println("CAJA + BANCOS = "+bc);
            wr.println(formatocuentas("Suma inicial", ""+(long)bc));;
            wr.println("Ingresos");
            for (int i = 0; i < this.ingresos.size(); i++) {
            
                s = s + obtnerSaldoCadena(ingresos.get(i));
                
                wr.println(ingresos.get(i));
            }
            wr.println(formatocuentas("Suma ingresos", ""+(long)s));
            
            wr.println("Egresos");
            for (int i = 0; i < this.egresoas.size(); i++) {
                 r = r + obtnerSaldoCadena(egresoas.get(i));
                
                wr.println(egresoas.get(i));
            }
            wr.println(formatocuentas("Suma egresos", ""+(long)r));
            float u =bc+s-r;
            wr.println(formatocuentas("Saldo final", ""+(long)u));
            
            wr.close();
            bw.close();
        } catch (IOException ex) {
            System.out.println("Excepcion en EscribirER ->"+ex.getMessage());
        }
    }
    
    public float obtnerSaldoCadena(String linea)
    {
        String saldo = "";
        float retorno = 0;
        
        for (int i = 0; i < linea.length(); i++) {
            
            if(((linea.charAt(i)==32) ||(linea.charAt(i)>=65 && linea.charAt(i)<=90) || ((linea.charAt(i)>=97 && linea.charAt(i)<=122)))) //Mentras este leyendo un caracter en lugar de un numero en codigo ascii
                {
                    //cuenta += linea.charAt(i);
                }
                else
                {
                    saldo += linea.charAt(i);
                } 
            
        }
        
        retorno = Float.parseFloat(saldo);
        System.out.println("SALDO A RERTORNAR->"+retorno);
        return retorno;
    }
    
     public static double Redondear(double numero,int digitos)
    {
      int cifras=(int) Math.pow(10,digitos);
      return Math.rint(numero*cifras)/cifras;
    }
     
    public String quitarEspacios(String cadena)
    {
        String limpia = "";
        int espacios = 0;
        
        for (int i = 0; i < cadena.length(); i++) {
            if (espacios < 7)
            {
                limpia = limpia + cadena.charAt(i);
            }
            else{
                break;
            }
            
            if(cadena.charAt(i)==32)
            {
                espacios++;
            }
        }
        return limpia;
    }
     
    public void escribirHistoria() throws IOException
    {
            FileWriter w;
            BufferedWriter bw;
            PrintWriter wr;
            
            
           
            //f = new File(nomEdoFin);
            w = new FileWriter(this.archivoHistoria);
            bw=new BufferedWriter(w);
            wr=new PrintWriter(bw);
            
            wr.println("\t\t\t Movimientos Realizados  \t\t\t");
            
            for (int i = 0; i < this.historia.size(); i++) {
            
                wr.println(this.historia.get(i));
            }
            
        wr.close();
        bw.close();
    }
    
     public String formatocuentas(String cuenta, String saldo)
    {
        int espacios = 23;
        String formato = "";
        
        for (int i = 0; i < espacios; i++) {
            
            if(i<cuenta.length()){
                formato = formato + cuenta.charAt(i);
            }
            else{
                formato = formato + " ";
            }
        }
        formato = formato + " "+saldo;
        
        return formato;
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
        btCerrar = new javax.swing.JButton();
        jbRegistrar = new javax.swing.JButton();
        lbCuentaAMod = new javax.swing.JLabel();
        ComboBoxCuentasMod = new javax.swing.JComboBox<>();
        lbNuevoSaldo = new javax.swing.JLabel();

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

        btCerrar.setBackground(new java.awt.Color(255, 255, 255));
        btCerrar.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btCerrar.setText("Cerrar");
        btCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCerrarActionPerformed(evt);
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
        ComboBoxCuentasMod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboBoxCuentasModActionPerformed(evt);
            }
        });

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
                            .addGroup(FondoLayout.createSequentialGroup()
                                .addGroup(FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lbNuevoSaldo)
                                    .addComponent(lbCuentaAMod))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(ComboBoxCuentasMod, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(btModificar)
                                        .addComponent(txtNuevoSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jbRegistrar))
                        .addGap(18, 18, 18)
                        .addGroup(FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(FondoLayout.createSequentialGroup()
                                .addComponent(lbCuentasMod)
                                .addGap(0, 335, Short.MAX_VALUE))))
                    .addComponent(btCerrar, javax.swing.GroupLayout.Alignment.TRAILING))
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btCerrar)
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
        System.out.println("Utilidad Neta SFProforma--->"+sfProforma.obtenerSaldodeLlave("Utilidad neta"));
        //System.out.println("Utilidad Neta ERProforma--->"+erProforma.obtenerSaldodeLlave("Utilidad neta"));
        System.out.println("Utilidad Neta SF--->"+sf.obtenerSaldodeLlave("Utilidad neta"));
        //System.out.println("Utilidad Neta ER--->"+er.obtenerSaldodeLlave("Utilidad neta"));
        
        String cuentaACambiar = obtenerNombreSeleccionadaCB();
        String cuentaActual;
        float dif = 0;
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
                    
                    this.historia.add("Modificando "+ cuentaACambiar+ " de "+datos.get(0)+ " por  "+this.txtNuevoSaldo.getText());

                        
                    if(cuentaActual.contains("Proveedores") || cuentaActual.contains("Acreedores") || cuentaActual.contains("Deudores") || cuentaActual.contains("Documentos por pagar")){
                        dif = sf.obtenerSaldodeLlave(cuentaActual);
                        dif = dif -Float.parseFloat(this.txtNuevoSaldo.getText());
                        egresoas.add(formatocuentas(cuentaACambiar, String.valueOf(dif)));
                    }
                    else if(cuentaActual.contains("Ventas")){
                        
                        dif = er.obtenerSaldodeLlave(cuentaActual);
                        dif = dif-Float.parseFloat(this.txtNuevoSaldo.getText());
                        ingresos.add(formatocuentas(cuentaACambiar, String.valueOf(dif)));
                    }
                    
                    else if( cuentaActual.contains("Documentos por cobrar" ) || cuentaActual.contains("Clientes" )|| cuentaActual.contains("Deudores" )){
                        
                        dif = sf.obtenerSaldodeLlave(cuentaActual);
                        dif = dif-Float.parseFloat(this.txtNuevoSaldo.getText());
                        ingresos.add(formatocuentas(cuentaACambiar, String.valueOf(dif)));
                    }
                    else if(cuentaActual.contains("operacion")){
                        
                        dif = er.obtenerSaldodeLlave(cuentaActual);
                        dif = dif-Float.parseFloat(this.txtNuevoSaldo.getText());
                        if(dif<=0){
                            dif = dif *-1;
                            egresoas.add(formatocuentas("Intereses", String.valueOf(dif)));
                        }
                        else{
                            ingresos.add(formatocuentas("Inversion", String.valueOf(dif)));
                        }
                         
                        
                    }
                    
                    
                    erProforma.modificarValorCuenta(cuentaACambiar, this.txtNuevoSaldo.getText());
                    //erProforma.agregarCuenta(cuentaActual, this.txtNuevoSaldo.getText());
                    System.out.println(sf.obtenerSaldodeLlave("Ventas ER"));
                    
                    
                    agregarElemntoAJLista("Cuenta "+ cuentaACambiar+ " de Estado de Resultados modifcada con "+this.txtNuevoSaldo.getText());
                    System.out.println("Cuenta en ER "+ quitarEspacios(cuentaACambiar)+ " modificada con "+ this.txtNuevoSaldo.getText());
                    break;
                }
                //else
                //{
                  //  erProforma.eliminarCuenta(cuentaActual);
                   // erProforma.agregarCuenta(cuentaActual, datos.get(0));
                //}
            }
            
        }
        
        
        datos = null;
        
        Iterator itSF = cuentasModificadasSF.keySet().iterator();
        
        while(itSF.hasNext())
        {
            cuentaActual = itSF.next().toString(); // contiene la llave de la cuenta en iteracion 
            datos = cuentasModificadasSF.get(cuentaActual);
            
            if(cuentaActual.contains(cuentaACambiar))
            {
               
                if(!this.txtNuevoSaldo.getText().isEmpty())
                {
                    //System.out.println(datos.size());
                    
                    this.historia.add("Modificando "+ cuentaACambiar+ " de "+datos.get(2)+ " por  "+this.txtNuevoSaldo.getText());
                    
                    if(cuentaActual.contains("Proveedores") || cuentaActual.contains("Acreedores") || cuentaActual.contains("Deudores") || cuentaActual.contains("Documentos por pagar")){
                        dif = sf.obtenerSaldodeLlave(cuentaActual);
                        dif = dif -Float.parseFloat(this.txtNuevoSaldo.getText());
                        egresoas.add(formatocuentas(cuentaACambiar, String.valueOf(dif)));
                    }
                    else if(cuentaActual.contains("Ventas")){
                        
                        dif = er.obtenerSaldodeLlave(cuentaActual);
                        dif = dif-Float.parseFloat(this.txtNuevoSaldo.getText());
                        ingresos.add(formatocuentas(cuentaACambiar, String.valueOf(dif)));
                    }
                    
                    else if(cuentaActual.contains("Documentos por cobrar")){
                        
                        dif = sf.obtenerSaldodeLlave(cuentaActual);
                        dif = dif-Float.parseFloat(this.txtNuevoSaldo.getText());
                        ingresos.add(formatocuentas(cuentaACambiar, String.valueOf(dif)));
                    }
                   
                   
                    sfProforma.modificarValorCuenta(cuentaACambiar, this.txtNuevoSaldo.getText());
                    System.out.println(sf.obtenerSaldodeLlave("Ventas"));
                    //sfProforma.eliminarCuenta(cuentaActual);
                    //sfProforma.agregarCuenta(cuentaActual, datos.get(0), datos.get(1), this.txtNuevoSaldo.getText());
                    agregarElemntoAJLista("Cuenta "+ cuentaACambiar+ " de Estado de Situación  modifcada con "+this.txtNuevoSaldo.getText());
                    System.out.println("Cuenta en SF "+ quitarEspacios(cuentaACambiar)+ " modificada con "+ this.txtNuevoSaldo.getText());
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

        //System.out.println("VAL FINAL "+ sfProforma.obtenerSaldodeLlave("Utilidad neta"));
        
        if(this.sfProforma.verificarEstado())
        {
            try {
                System.out.println("Cuentas bien");
                
                if(this.sf.getF().getName().contains("Proforma") || this.sf.getF().getName().contains("proforma"))
                {
                    this.sfProforma.crearEstadoFinanciero(false); //No calcular amortizaciones ni depresiaciones
                    this.erProforma.crearEstadoFinanciero(false); //Estas fueron calculadas desde que se creeo el proforma
                }
                else{
                this.sfProforma.crearEstadoFinanciero(true);
                
                this.sfProforma.leerEstadoFinanciero();
                
                float a = sfProforma.obtenerSaldodeLlave("Amortizacion");
                float d = sfProforma.obtenerSaldodeLlave("Depresiacion");
                System.out.println("AMORTIZACION->>>>>"+a);
                System.out.println("DEPRESICACION->>>>"+d);
                
                
                this.erProforma.setAmprtizacionDepresiacion(a+d);
                this.erProforma.crearEstadoFinanciero(true);
                }
                
                escribirHistoria();
                escribirER();
                
                Ventana.ShowInformationMessage("¡Modificaciones realizadas con exito!");
                //File fSF= this.sf.getF();
                //File fER= this.er.getF();
                
                //AnalisisEF EF = new AnalisisEF(fSF, fER);
                //EF.setVisible(true);
                //this.setVisible(false);
            } catch (IOException ex) {
                System.out.println("Excepcion en el metodojbRegistrarAction ->" +ex.getMessage());;
            }
        }
    }//GEN-LAST:event_jbRegistrarActionPerformed

    private void btCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCerrarActionPerformed
        // TODO add your handling code here:
        File fSF= this.sf.getF();
        File fER= this.er.getF();
            
        AnalisisEF EF = new AnalisisEF(fSF, fER);
        EF.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btCerrarActionPerformed

    private void ComboBoxCuentasModActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboBoxCuentasModActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboBoxCuentasModActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> ComboBoxCuentasMod;
    private javax.swing.JPanel Fondo;
    private javax.swing.JButton btCerrar;
    private javax.swing.JButton btModificar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbRegistrar;
    private javax.swing.JList<String> jlCambios;
    private javax.swing.JLabel lbCuentaAMod;
    private javax.swing.JLabel lbCuentasMod;
    private javax.swing.JLabel lbNuevoSaldo;
    private javax.swing.JTextField txtNuevoSaldo;
    // End of variables declaration//GEN-END:variables
}
