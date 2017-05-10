/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema.financiero;

import Modelo.Resultados;
import Modelo.SituacionFinanciera;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author Diego EG
 */
public class SistemaFinanciero {

    /**
     * @param args the command line arguments
     */
    private static Map<String,ArrayList<String>> cuentas;
    
    public static void main(String[] args) {
        
        /*
        Comentada toda la parte pare crear un estado de situación finaciera balanceado "
        
        SituacionFinanciera edo1 = new SituacionFinanciera("Walmart.txt");
        edo1.agregarcuenta("Activo", "Circulante", "Caja", "200000");
        edo1.agregarcuenta("Activo", "Circulante", "Bancos", "250000");
       
        edo1.agregarcuenta("Activo", "Circulante", "Clientes", "300000");
        edo1.agregarcuenta("Activo", "Fijo", "Equipo de computo", "1000000");
        edo1.agregarcuenta("Activo", "Diferido", "Gastos de instalacion", "250000");
        
        edo1.agregarcuenta("Pasivo", "Circulante", "Proveedores", "200000");
        edo1.agregarcuenta("Pasivo", "Fijo", "Hipotecas por pagar", "500000");
        edo1.agregarcuenta("Capital", "Utilidad neta", "Utilidad neta", "300000");
        edo1.agregarcuenta("Capital", "Capital social", "Capital social", "1000000");
        
        
        if(edo1.verificardatosestado())
            edo1.crearEstadoDeSituacionFinanciera("Walmart.txt");
        else
            System.out.println("Los saldos de las cuentas no estan balanceados por favor revisa las cantidades rehistradas en las cuentas");
        
        //edo1.mostrarcuentas();

        */
        
        Resultados  edores1 = new Resultados("Estado de Resultados Wamlart de 2015.txt");
        /*
        edores1.agregarCuentas("Ventas", "150000");
        edores1.agregarCuentas("Costo de ventas", "25000");
        edores1.agregarCuentas("Gastos de venta", "10000");
        edores1.agregarCuentas("Gastos de administracion", "5000");
        edores1.agregarCuentas("Otros productos financieros", "5000");
        edores1.agregarCuentas("ISR", "12560");
        edores1.agregarCuentas("PTU", "8900");
        */
        
        edores1.leerEstadoFinanciero();
        edores1.mostrarCuentas();
        cuentas = edores1.importarCuentas();
        
    }
    
}
