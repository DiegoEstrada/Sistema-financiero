/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Analisis.Vertical;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author super
 */
public class PorcientosIntegrados{
    
    Map<String,ArrayList<String>> cuentasPorcientos;
    
    public PorcientosIntegrados(Map<String,ArrayList<String>> CUENTAS)
    {
        cuentasPorcientos=CUENTAS;
    }
    
    public ArrayList<String> obtenerNombresCuentas()
    {
        ArrayList<String> nombres=new ArrayList<>();
        ArrayList<String> saldos = new ArrayList<>();
        String nombre="";
        Iterator I = cuentasPorcientos.keySet().iterator();
        
        while(I.hasNext())
        {
            
            nombre=I.next().toString();     //Se guardan los nombres de las cuentas del map (las llave del map).
            saldos=(ArrayList<String>)cuentasPorcientos.get(nombre);        //En este array se guardan los saldos de cada una de las cuentas del map 'cuentasporcientos'
            nombres.add(nombre);        //Se agregan al arraylist el nombre de la cuenta y el saldo de cada una
        }
        //El metodo regresa el arraylist lleno con los datos que se necesitan de las cuentas.
        return nombres;
    }
    
    public Map<String,String> AnalisisPorcientosIntegrados(ArrayList<String> cuentas)
    {
        Map<String,String> porcientos=new HashMap<>();
        ArrayList<String> cuentaP = new ArrayList<>();
        String S;    //En esta cadena se guarda el saldo de cada una de las cuentas
        String CienS=null,Porcentaje;
        float cien;
        
        
        //Mediante este for solamente se busca la cuenta que represente el 100% del estado financiero, ya sea ventas o suma de activo
        for(int i=0;i<cuentas.size();i++)
        {
            if((cuentas.contains("Ventas")) || cuentas.contains("Suma de activo"))
            {
                CienS=cuentaP.get(0);              //Se obtiene el cien porciento
            }
        }
        
        cien=Float.parseFloat(CienS);
            
        //Con el segundo for se llama al metodo CalcularPorcentaje por cada iteracion
        for(int j=0;j<cuentas.size();j++)
        {
            cuentaP=cuentasPorcientos.get(cuentas.get(j));
            S=cuentaP.get(0);
            Porcentaje=String.valueOf(CalcularPorcentaje(cien,Float.parseFloat(S)));
            porcientos.put(cuentaP.get(j), Porcentaje); //Se guardan los nombres de las cuentas y los porcentajes calculados
        }
        
        return porcientos;
    }
    
    public float CalcularPorcentaje(float cien,float saldo)
    {
        float res;
        
        res=(saldo*100)/cien;
        
        return res;
    }
    
    public void imprimirAnalisisPorcientos(Map<String,String> PORCIENTOS)
    {
        Iterator it= PORCIENTOS.keySet().iterator();
        String nom,pct;
        
        while (it.hasNext()) {
            nom=it.next().toString();
            pct=PORCIENTOS.get(nom);
            
            System.out.println("Cuenta: "+nom+"---->Porcentaje: "+pct);
        }
    }
    
    public void ImprimirLlaves(Map<String,ArrayList<String>> Map)
    {
        Iterator a=Map.keySet().iterator();
        String n;
        
        while(a.hasNext())
        {
            n=a.next().toString();
            System.out.println(n);
        }
    }
}