package Modelo.Analisis.Vertical;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Diego EG
 */
public class RazonesFinancieras {
    
    private Map <String, ArrayList<String>> cuentasSF;
    private Map <String, ArrayList<String>> cuentasER;
 
    public RazonesFinancieras(Map <String, ArrayList<String>> cuentasEdoRes,Map <String, ArrayList<String>> cuentasSitFin)
    {
        this.cuentasER = cuentasEdoRes;
        this.cuentasSF = cuentasSitFin;
        
    }
    
    public float ObtenerSaldo(String cuenta)
    {
        float saldo = 0;
        String s="";
        
        ArrayList<String> aux = new ArrayList();
        Set setSF = cuentasSF.keySet();
        Set setER = cuentasER.keySet();
        
        
        Iterator itSF = setSF.iterator();
        Iterator itER = setER.iterator();
        
       
        
        while(itSF.hasNext())
        {
           String nombre = itSF.next().toString();
           
            if(nombre.contains(cuenta))
            {
                 aux = (ArrayList<String>) cuentasSF.get(nombre);
                 break;
            }
        }
        
        
         if (aux.isEmpty())
        {
            while(itER.hasNext())
            {
                String nombre = itER.next().toString();
           
                if(nombre.contains(cuenta))
                {
                    aux = (ArrayList<String>) cuentasER.get(nombre);
                    break;
                }
            }
        }
        
            if (!aux.isEmpty()) 
            {
            
            s = aux.get(0);
            saldo = Float.parseFloat(s);
            }
            else
                System.out.println("La cuenta buscada no existe en ningun estado financiero");
        
        
     
        return saldo;
    }
    
    public Map<String,String>analisisRazonesFinancieras()
    {
        String nombre = "";
        float a,b,numerador,denominador,indicador=0;
        Map<String,String> rf = new HashMap();
        
        nombre = "Liquidez";
        a = ObtenerSaldo("Caja");
        b = ObtenerSaldo("Bancos");
        
        denominador = ObtenerSaldo("Suma pasivo circulante");
        numerador = a+b;
        
        indicador = numerador/denominador;
        
        rf.put(nombre, String.valueOf(indicador));
        
        nombre = "Solvencia";
        
        numerador = ObtenerSaldo("Suma activo circulante");
        denominador = ObtenerSaldo("Suma pasivo circulante");
        
        indicador = numerador/denominador;
        
        rf.put(nombre, String.valueOf(indicador));
        
        
        
        return rf;
    }
    
    public void imprimirRazonesFinancieras(Map <String,String> razones)
    {
        Iterator it= razones.keySet().iterator();
        String nom,val;
        
        while (it.hasNext()) {
            nom=it.next().toString();
            val=razones.get(nom);
            
            System.out.println(nom +" = "+val );
        }
    }
}

