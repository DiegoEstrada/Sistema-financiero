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
                System.out.println("La cuenta "+cuenta+ " buscada no existe en ningun estado financiero ");
        
        
     
        return saldo;
    }
    
    public Map<String,String>analisisRazonesFinancieras()
    {
        String nombre = "";
        float a,b,c,numerador,denominador,indicador=0;
        Map<String,String> rf = new HashMap();
        
        nombre = "Liquidez";
        a = ObtenerSaldo("Caja");
        b = ObtenerSaldo("Bancos");
        
        denominador = ObtenerSaldo("Suma pasivo circulante");
        numerador = a+b;
        
        indicador = numerador/denominador;
        
        
        rf.put(nombre, String.valueOf(Redondear(indicador, 2)));
        
        nombre = "Solvencia";
        
        numerador = ObtenerSaldo("Suma activo circulante");
        denominador = ObtenerSaldo("Suma pasivo circulante");
        
        indicador = numerador/denominador;
        
        //System.out.println(""+numerador+" / "+denominador);
        
        rf.put(nombre, String.valueOf(Redondear(indicador, 2)));
        
        nombre= "Estabilidad economica";
        a=ObtenerSaldo("Suma pasivo fijo");
        b=ObtenerSaldo("Suma pasivo circulante");
        c=ObtenerSaldo("Suma de activo");
        
        numerador=a + b;
        denominador=c;
        //System.out.println("Estabilidad");
        //System.out.println(""+a+" + "+b+ " / "+ denominador);
        
        indicador=numerador/denominador;
        
        rf.put(nombre, String.valueOf(Redondear(indicador, 2)));
        
        nombre="Inmovilizacion de capital";
        a=ObtenerSaldo("Suma activo fijo");
        b=ObtenerSaldo("Suma capital");
        
        indicador=a/b;
        
        rf.put(nombre, String.valueOf(Redondear(indicador, 2)));
        
        nombre="Rentabilidad en ventas";
        a=ObtenerSaldo("Utilidad neta");
        b=ObtenerSaldo("Ventas");
        
        indicador=a/b;
        
        rf.put(nombre, String.valueOf(Redondear(indicador, 2)));
        
        nombre="Rentabilidad en inversion";
        b=ObtenerSaldo("Capital social");
        
        indicador=a/b;
        
        rf.put(nombre, String.valueOf(Redondear(indicador, 2)));
        
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
    
    public static double Redondear(double numero,int digitos)
    {
      int cifras=(int) Math.pow(10,digitos);
      return Math.rint(numero*cifras)/cifras;
    }
}