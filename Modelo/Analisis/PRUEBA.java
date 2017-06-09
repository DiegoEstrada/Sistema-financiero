/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Analisis;

import Modelo.Analisis.Horizontal.Diferencias;
import Modelo.Resultados;
import Modelo.SituacionFinanciera;
import java.io.File;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author Diego EG
 */
public class PRUEBA {
    File f = new File("C:\\Users\\Diego EG\\Documents\\EmpresaG.txt");
    
    
    
    public PRUEBA()
    {
        /*
        SituacionFinanciera df = new SituacionFinanciera(f);
        df.agregarCuenta("Activo", "Fijo", "Terrenos", "2350");
        df.agregarCuenta("Activo", "Fijo", "Edificios", "1860");
        df.agregarCuenta("Activo", "Fijo", "Mobiliario", "344");
        df.agregarCuenta("Activo", "Fijo", "Equipo de reparto", "234");
        df.mostrarCuentas();
        
        System.out.println(df.obtenerSaldodeLlave("Mo"));
    
        
        Resultados r = new Resultados(f);
        r.agregarCuenta("Ventas", "77");
        r.agregarCuenta("Costo de ventas", "25");
        r.agregarCuenta("Gastos de administracion", "31");
        r.agregarCuenta("Otros productos fnancieros", "11");
        
        r.crearEstadoFinanciero();
        
        r.mostrarCuentas();

        */
        
        File a = new File("C:\\Users\\Diego EG\\Documents\\EmpresaG.txt");
        
     SituacionFinanciera edo1 = new SituacionFinanciera(a);
        
        
        edo1.agregarCuenta("Activo", "Circulante", "Caja", "200000");
        edo1.agregarCuenta("Activo", "Circulante", "Bancos", "250000");
       
        edo1.agregarCuenta("Activo", "Circulante", "Clientes", "300000");
        edo1.agregarCuenta("Activo", "Fijo", "Equipo de computo", "1000000");
        edo1.agregarCuenta("Activo", "Diferido", "Gastos de instalacion", "250000");
        
        edo1.agregarCuenta("Pasivo", "Circulante", "Proveedores", "200000");
        edo1.agregarCuenta("Pasivo", "Fijo", "Hipotecas por pagar", "500000");
        edo1.agregarCuenta("Capital", "Utilidad neta", "Utilidad neta", "300000");
        edo1.agregarCuenta("Capital", "Capital social", "Capital social", "1000000");
        
        
        if(edo1.verificarEstado())
            edo1.crearEstadoFinanciero();
        else
            System.out.println("Los saldos de las cuentas no estan balanceados por favor revisa las cantidades rehistradas en las cuentas");
        
        ArrayList<String> nomOrd = edo1.obtenerNombresdeCuentasOrdenadas();
        
        
        for (int i = 0; i < nomOrd.size(); i++) {
            System.out.println(nomOrd.get(i));
        }
        
        Map<String,ArrayList<String>> cuentas = edo1.importarNombreySaldo();
         
        
        Diferencias df = new Diferencias(cuentas, cuentas);
        ArrayList<String> aux = df.obtenerNombresCuentas();
        Map<String,String> analisidiferencias = df.analisisDiferencias(aux);
        df.imprimirAnalisisDierencias(analisidiferencias);
    }
    public static void main(String[] args) {
        PRUEBA P = new PRUEBA();
    }
            
}
