/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema.financiero;

import Modelo.SituacionFinanciera;

/**
 *
 * @author Diego EG
 */
public class SistemaFinanciero {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
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
    }
    
}
