/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import Modelo.Analisis.Horizontal.Diferencias;
import Modelo.Analisis.Vertical.PorcientosIntegrados;
import Modelo.SituacionFinanciera;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Diego EG
 */
public class AnalisisSF extends javax.swing.JFrame {

    SituacionFinanciera  sfX;
    SituacionFinanciera  sfY;
    
    public AnalisisSF(SituacionFinanciera acutual, SituacionFinanciera anterior) {
        initComponents();
        
        this.sfX = acutual; 
        this.sfY = anterior;
        
        this.sfX.leerEstadoFinanciero();
        this.sfY.leerEstadoFinanciero();
        
        cargarDatosTablaCuentas();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jtCuentas = new javax.swing.JTable();

        jtCuentas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Cuenta", "Periodo Actual", "Periodo Anterior", "Porcentaje", "Diferencia"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jtCuentas);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 651, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 397, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    public void cargarDatosTablaCuentas()
    {
        //Cuando se llama este metodo los 4 objetos de analisi deben estar creados
        DefaultTableModel modelo = (DefaultTableModel) this.jtCuentas.getModel();
        System.out.println("Vamos a llenar la tabla  de cuentas ");
        
        //Limpiando la tabla 
        try {
        int filas=this.jtCuentas.getRowCount();
            for (int i = 0;filas>i; i++) {
                modelo.removeRow(0);
            }
           
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al limpiar la tabla.");
        }
        
        
        String fila[] = new String[5];
        ArrayList<String> cuentasordenadasSF = sfX.obtenerNombresdeCuentasOrdenadas();
   
        Map<String,ArrayList<String>> cuentassfX = sfX.importarNombreySaldo();
        
        Map<String,ArrayList<String>> cuentassfY = sfY.importarNombreySaldo();
        
        
        
        PorcientosIntegrados porcientosIntegradosSFX = new PorcientosIntegrados(cuentassfX);
        
        //sfY.mostrarCuentas();
        Diferencias diferencias = new Diferencias(cuentassfY, cuentassfX);
        
        ArrayList<String> auxiliarparaDiferencias = diferencias.obtenerNombresCuentas();
        
        Map<String,String> analisisporcientosSFX = porcientosIntegradosSFX.AnalisisPorcientosIntegrados();
                
        Map<String, String> analisisDifernencias = diferencias.analisisDiferencias(auxiliarparaDiferencias);
        
        for (int i = 0; i < cuentasordenadasSF.size(); i++) {
            fila[0] = cuentasordenadasSF.get(i);
            //System.out.println("---> "+cuentasordenadasSF.get(i));
            
            fila[1] = obtenerSaldoCuentaenMap(cuentassfX, cuentasordenadasSF.get(i));
            fila[2] = obtenerSaldoCuentaenMap(cuentassfY, cuentasordenadasSF.get(i));
            fila[3] = analisisporcientosSFX.get(cuentasordenadasSF.get(i));
            fila[4] = analisisDifernencias.get(cuentasordenadasSF.get(i));
            fila[4] = analisisDifernencias.get(cuentasordenadasSF.get(i));
            
            modelo.addRow(fila);
            this.jtCuentas.setModel(modelo);
        }
        
    }
    
    public String obtenerSaldoCuentaenMap(Map<String, ArrayList<String>> cuentas, String cuentabuscada)
    {
        String saldo =  "";
        
        Iterator it = cuentas.keySet().iterator();
        
        while(it.hasNext())
        {
            String nombre = it.next().toString();
            //System.out.println("Nombre -->> "+nombre);
            ArrayList<String> datos = cuentas.get(nombre);
            if(cuentabuscada.contains(nombre))
            {
                if(datos.size()>0){
                    //System.out.println("Tam del arrlst "+datos.size());
                    //System.out.println("Encontre la cuenta "+ datos.get(0));
                    saldo = datos.get(0);
                }
            }
        }
                
        return saldo;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jtCuentas;
    // End of variables declaration//GEN-END:variables
}
