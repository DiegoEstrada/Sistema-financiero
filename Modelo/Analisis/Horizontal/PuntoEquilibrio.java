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
    private Map<String,ArrayList<String>> cuentas;
    
    public PuntoEquilibrio(float utilidad, float precioVentaU, Map<String,ArrayList<String>> cuentas)
    {
        /*
        Este clase debe recibir cuentas del estado de resultados para que los calulos se realicen 
        de la manera correcta
        */
        this.utilidad = utilidad;
        this.precioVentaU = precioVentaU;
        this.cuentas = cuentas;
        
    }
    
    public String calcularPE()
    {
        float pe=0;
        
        float cft = obtenerCostosFijosTotales();
        float cvu = obtenerCostosVariablesUnitarios();
        
        
        
        pe = (cft)/(1-(cvu*costoVentasS/precioVentaU));
        
        return String.valueOf(pe);
    }
    
    public String calcularPEU()
    {
        float peu=0;
        
        float cft = obtenerCostosFijosTotales();
        float cvu = obtenerCostosVariablesUnitarios();
        
        
        //System.out.println("porcriento \t \t"+cvu);
        //System.out.println("costos fijos (gastos op) \t \t"+cft);
        //System.out.println("costos variables (costo ventas) \t \t"+costoVentasS);
        //System.out.println("Utilidad "+utilidad);
        
        
        peu = (cft+utilidad)/(1-(cvu*costoVentasS/precioVentaU));
        
        return String.valueOf(peu);
    }
    
    public  float obtenerCostosFijosTotales()
    {
        float cft=0;
        String c="";
        ArrayList<String> aux = new ArrayList();
        Set s = cuentas.keySet();
        
        
        Iterator it = s.iterator();
        
        
        while(it.hasNext())
        {
           String nombre = it.next().toString();
           
            if(nombre.contains("Utilidad de operacion"))
            {
                 aux =(ArrayList<String>) cuentas.get(nombre);
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
        if (cuentas.containsKey("Costo de ventas") && cuentas.containsKey("Ventas")) 
        {
            aux = cuentas.get("Costo de ventas");
            costoVentasS = Float.parseFloat(aux.get(0));
            
            aux = cuentas.get("Ventas");
            cien = Float.parseFloat(aux.get(0));
            
            cft = (costoVentasS)/(cien);
        }
        else
            System.out.println("Las cuentas recibidas para calcular PEU y PE no son las correctas");
        return cft;
    }
    
}
