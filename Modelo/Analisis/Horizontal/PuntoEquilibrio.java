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
        long peu=0;
        
        long cft = obtenerCostosFijosTotales();
        float cvu = obtenerCostosVariablesUnitarios();
        
        
        System.out.println("porciento \t \t"+cvu);
        System.out.println("costos fijos (gastos op) \t \t"+cft);
        System.out.println("costos variables (costo ventas) \t \t"+costoVentasS);
        System.out.println("Utilidad "+utilidad);
        
        
        peu = (long)  ( (cft+calcularUtlidad())/(1-(cvu*precioVentaU/precioVentaU)) );
        
        return String.valueOf(peu);
    }
    
    public  long obtenerCostosFijosTotales()
    {
        long cft=0;
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
           cft = Long.parseLong(aux.get(0));
        }
        else
            System.out.println("Las cuentas recibidas para calcular PEU y PE no son las correctas");
        return cft;
    }
    
    
    public  float obtenerCostosVariablesUnitarios()
    {
        float cft=0, cien=0;
        ArrayList<String> aux;
        //if (cuentasER.containsKey("Costo de ventas") && cuentasER.containsKey("Ventas")) 
        //{
            aux = obtenerDatos("Costo de ventas");
            costoVentasS = Long.parseLong(aux.get(0));
            
            aux = obtenerDatos("Ventas");
            cien = Long.parseLong(aux.get(0));
            
            cft = (costoVentasS)/(cien);
        //}
        //else
            //System.out.println("Las cuentas recibidas para calcular PEU y PE no son las correctas");
        return cft;
    }
    
    public float calcularUtlidad()
    {
        int capsocial=0;
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
        
        capsocial = Integer.parseInt(saldo);
        
        ut = (utilidad*capsocial)/100;
        return ut;
    }
    
    public float numVentasPE()
    {
        String PEU = calcularPEU();
        return Float.valueOf(PEU)/precioVentaU;
    }
    
    public long numVentasPEU()
    {
        String PE = calcularPEU();
        return (long) (Float.valueOf(PE)/precioVentaU);
    }
    
    public long cvtPE()
    {
        float cvu = obtenerCostosVariablesUnitarios();
        long nv = (long) numVentasPE();
        return (long) (nv*cvu*precioVentaU);
    }
    
    public long cvtPEU()
    {
        float cvu = obtenerCostosVariablesUnitarios();
        long nv = numVentasPEU();
        return (long)(nv*cvu*precioVentaU);
    }
    
    public ArrayList<String> obtenerDatos(String cuenta)
    {
        ArrayList<String> datos = new ArrayList();
        String llave;
        
        Iterator it = cuentasSF.keySet().iterator();
        
        while(it.hasNext())
        {
            llave = it.next().toString();
            
            if (llave.contains(cuenta))
            {
                datos = cuentasSF.get(llave);
                break;
            }
        }
        
        if(datos.isEmpty())
        {
            Iterator itr = cuentasER.keySet().iterator();
        
        while(itr.hasNext())
        {
            llave = itr.next().toString();
            
            if (llave.contains(cuenta))
            {
                datos = cuentasER.get(llave);
                break;
            }
        }
        }
              
        
        return datos;
    }
    
}
