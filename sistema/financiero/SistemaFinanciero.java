/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema.financiero;

import Modelo.Analisis.Horizontal.Diferencias;
import Modelo.Analisis.Horizontal.PuntoEquilibrio;
import Modelo.Analisis.Vertical.BenchMarking;
import Modelo.Analisis.Vertical.PorcientosIntegrados;
import Modelo.Analisis.Vertical.RazonesFinancieras;
import Modelo.Resultados;
import Modelo.SituacionFinanciera;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author Diego EG
 */
public class SistemaFinanciero {

    /**
     * @param args the command line arguments
     */
    private static Map<String,ArrayList<String>> cuentasSFX,cuentasSFY,cuentasERX,cuentasERY;
    
    public static void main(String[] args) {
        
        
        //Comentada toda la parte pare crear un estado de situación finaciera balanceado "
        
        SituacionFinanciera edo1 = new SituacionFinanciera("Estado de  Situacion Financiera  Walmart de 2015.txt");
        
        
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
        
       //edo1.mostrarCuentas();
        
        edo1.leerEstadoFinanciero();
        
        //edo1.mostrarCuentas();
       
       cuentasSFX = edo1.importarNombreySaldo();
       
        //System.out.println(cuentasSFX.get("Bancos").get(0));
       
        System.out.println("----------------------------------------------------------------------------------------------");
       
       // Esta parte genera otro estado de situacion financiera para realizar el analsis horizontal
       
        SituacionFinanciera edo2 = new SituacionFinanciera("Estado de  Situacion Financiera  Walmart de 2016.txt");
        
        
        
        
        edo2.agregarCuenta("Activo", "Circulante", "Caja", "250000");
        edo2.agregarCuenta("Activo", "Circulante", "Bancos", "310000");
       
        edo2.agregarCuenta("Activo", "Circulante", "Clientes", "250000");
        edo2.agregarCuenta("Activo", "Fijo", "Equipo de computo", "1000000");
        edo2.agregarCuenta("Activo", "Diferido", "Gastos de instalacion", "250000");
        
        edo2.agregarCuenta("Pasivo", "Circulante", "Proveedores", "120000");
        edo2.agregarCuenta("Pasivo", "Fijo", "Hipotecas por pagar", "400000");
        edo2.agregarCuenta("Capital", "Utilidad neta", "Utilidad neta", "540000");
        edo2.agregarCuenta("Capital", "Capital social", "Capital social", "1000000");
        
        
        if(edo2.verificarEstado())
            edo2.crearEstadoFinanciero();
        else
            System.out.println("Los saldos de las cuentas no estan balanceados por favor revisa las cantidades rehistradas en las cuentas");
        
       //edo2.mostrarCuentas();
       
        
       edo2.leerEstadoFinanciero();
       cuentasSFY = edo2.importarNombreySaldo();
       
        
        System.out.println("***************************************************************************************");
        
        //Esta parte genera un estado de resultados 
        Resultados  edores1 = new Resultados("Estado de Resultados Wamlart de 2015.txt");
        
        edores1.agregarCuenta("Ventas", "150000");
        edores1.agregarCuenta("Costo de ventas", "25000");
        edores1.agregarCuenta("Gastos de venta", "10000");
        edores1.agregarCuenta("Gastos de administracion", "5000");
        edores1.agregarCuenta("Otros productos financieros", "5000");
        edores1.agregarCuenta("ISR", "12560");
        edores1.agregarCuenta("PTU", "8900");
        
        
        edores1.crearEstadoFinanciero();
        //edores1.mostrarCuentas();
        edores1.leerEstadoFinanciero();
        cuentasERX = edores1.importarCuentas();
        
        System.out.println("-------------------------------------------------------------------------------------------");
 
        //Esta parte genera otro estado de resultados 
        
        Resultados  edores2 = new Resultados("Estado de Resultados Wamlart de 2016.txt");
        
        edores2.agregarCuenta("Ventas", "602070");
        edores2.agregarCuenta("Costo de ventas", "32000");
        edores2.agregarCuenta("Gastos de venta", "9000");
        edores2.agregarCuenta("Gastos de administracion", "5000");
        edores2.agregarCuenta("Otros productos financieros", "15000");
        edores2.agregarCuenta("ISR", "18600");
        edores2.agregarCuenta("PTU", "12470");
        
        edores2.crearEstadoFinanciero();
        
        
        
        edores2.leerEstadoFinanciero();
        
        
        cuentasERY = edores2.importarCuentas();
        
        //Iterator it = cuentasERY.keySet().iterator();
        
       //String nombre = it.next().toString();
        
        //System.out.println(cuentasERY.containsKey("Utilidad de operacion  "));
        //edores2.mostrarCuentas();
        
        
        
        Diferencias diferenciasSF = new Diferencias(cuentasSFX, cuentasSFY);
        Diferencias diferenciasER = new Diferencias(cuentasERX, cuentasERY);
        
//diferenciasSF.imprimirAnalisisDierencias(diferenciasSF.analisisDiferencias(diferenciasSF.obtenerNombresCuentas()));
        
      
    //Porcientos porcientosSF = new Porcientos (CuentasSFX);
    

    
        PuntoEquilibrio peedo1 = new PuntoEquilibrio(1, 300, cuentasERY,cuentasSFY);
        
        //Diferencias psdf = new Diferencias();
        
        
        PorcientosIntegrados pSF = new PorcientosIntegrados(cuentasSFX);
        
       //Map <String,String> analisisp = pSF.AnalisisPorcientosIntegrados();
       
       //pSF.imprimirAnalisisPorcientos(analisisp);
        //System.out.println( pSF.ObtenerCien());
        //ArrayList<String> n = pSF.obtenerNombresCuentas();
        //System.out.println(n.get(5));
        
        //System.out.println(peedo1.calcularPE());
        //System.out.println(peedo1.calcularPEU());
        //System.out.println(peedo1.obtenerCostosFijosTotales());
        //System.out.println(peedo1.obtenerCostosVariablesUnitarios());
        System.out.println(peedo1.calcularUtlidad());
        
        RazonesFinancieras rfX = new RazonesFinancieras(cuentasERY,cuentasSFY);
        System.out.println(rfX.ObtenerSaldo("Bancos"));
        
        //rfX.imprimirRazonesFinancieras(rfX.analisisRazonesFinancieras());
        
        BenchMarking bm = new BenchMarking(rfX, 1);
        
        
        bm.imprimirDiferenciasBenchMarking(bm.AnalisisBenchMarking());

    }
    
    
}
