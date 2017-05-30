package Modelo.Analisis.Horizontal;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Diego EG
 */
public class PuntoEquilibrio {
    
    private float utilidad;
    private float precioVentaU;
    private float costoVentasS;
    private Map<String,ArrayList<String>> cuentasER;
    private Map<String,ArrayList<String>> cuentasSF;
    
    public PuntoEquilibrio(float utilidad, float precioVentaU, Map<String,ArrayList<String>> cuentaser, Map<String,ArrayList<String>> cuentassf)
    {
        /*
        Este clase debe recibir cuentas del estado de resultados para que los calulos se realicen 
        de la manera correcta
        */
        this.utilidad = utilidad;
        this.precioVentaU = precioVentaU;
        this.cuentasER = cuentaser;
        this.cuentasSF = cuentassf;
        
    }
    
    public String calcularPE()
    {
        float pe=0;
        
        float cft = obtenerCostosFijosTotales();
        float cvu = obtenerCostosVariablesUnitarios();
        
        
        
        pe = (cft)/(1-((cvu*precioVentaU)/precioVentaU));
        
        return String.valueOf(pe);
    }
    
    public String calcularPEU()
    {
        float peu=0;
        
        float cft = obtenerCostosFijosTotales();
        float cvu = obtenerCostosVariablesUnitarios();
        
        
        System.out.println("porcriento \t \t"+cvu);
        System.out.println("costos fijos (gastos op) \t \t"+cft);
        System.out.println("costos variables (costo ventas) \t \t"+costoVentasS);
        System.out.println("Utilidad "+utilidad);
        
        
        peu = (cft+calcularUtlidad())/(1-(cvu*precioVentaU/precioVentaU));
        
        return String.valueOf(peu);
    }
    
    public  float obtenerCostosFijosTotales()
    {
        float cft=0;
        String c="";
        ArrayList<String> aux = new ArrayList();
        Set s = cuentasER.keySet();
        
        
        Iterator it = s.iterator();
        
        
        while(it.hasNext())
        {
           String nombre = it.next().toString();
           
            if(nombre.contains("Gastos de operacion"))
            {
                 aux =(ArrayList<String>) cuentasER.get(nombre);
                 //break;
            }
        }
        
        
        
        if (!aux.isEmpty()) 
        {
            
           c = aux.get(0);
           cft = Float.parseFloat(aux.get(0));
        }
        else
            System.out.println("Las cuentas recibidas para calcular PEU y PE no son las correctas");
        return cft;
    }
    
    
    public  float obtenerCostosVariablesUnitarios()
    {
        float cft=0, cien=0;
        ArrayList<String> aux;
        if (cuentasER.containsKey("Costo de ventas") && cuentasER.containsKey("Ventas")) 
        {
            aux = cuentasER.get("Costo de ventas");
            costoVentasS = Float.parseFloat(aux.get(0));
            
            aux = cuentasER.get("Ventas");
            cien = Float.parseFloat(aux.get(0));
            
            cft = (costoVentasS)/(cien);
        }
        else
            System.out.println("Las cuentas recibidas para calcular PEU y PE no son las correctas");
        return cft;
    }
    
    public float calcularUtlidad()
    {
        float capsocial=0;
        float ut;
        String saldo="";
        
        ArrayList<String> aux = new ArrayList();
        Set s = cuentasSF.keySet();
        
        
        Iterator it = s.iterator();
        
        
        while(it.hasNext())
        {
           String nombre = it.next().toString();
           
            if(nombre.contains("Capital social"))
            {
                 aux =(ArrayList<String>) cuentasSF.get(nombre);
                 saldo = aux.get(0);
                 break;
            }
        }
        
        capsocial = Float.parseFloat(saldo);
        
        ut = (utilidad*capsocial)/100;
        return ut;
    }
    
    public float numVentasPE()
    {
        String PEU = calcularPEU();
        return Float.valueOf(PEU)/precioVentaU;
    }
    
    public float numVentasPEU()
    {
        String PE = calcularPE();
        return Float.valueOf(PE)/precioVentaU;
    }
    
    public float cvtPE()
    {
        float cvu = obtenerCostosVariablesUnitarios();
        float nv = numVentasPE();
        return nv*cvu*precioVentaU;
    }
    
    public float cvtPEU()
    {
        float cvu = obtenerCostosVariablesUnitarios();
        float nv = numVentasPEU();
        return nv*cvu*precioVentaU;
    }
    
}
