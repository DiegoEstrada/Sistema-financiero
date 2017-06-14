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
 * @author Diego EG
 */
public class PorcientosIntegrados {
    Map<String,ArrayList<String>> cuentas;
    public PorcientosIntegrados(Map<String,ArrayList<String>> cuentasrecibe)
    {
        this.cuentas = cuentasrecibe;
        
    }
    
    
    public ArrayList<String> obtenerNombresCuentas()
    {
        ArrayList<String> nombres=new ArrayList<>();
        ArrayList<String> saldos = new ArrayList<>();
        String nombre="";
        Iterator I = cuentas.keySet().iterator();
        
        while(I.hasNext())
        {
            
            nombre=I.next().toString();     //Se guardan los nombres de las cuentas del map (las llave del map).
            saldos=(ArrayList<String>)cuentas.get(nombre);        //En este array se guardan los saldos de cada una de las cuentas del map 'cuentasporcientos'
            nombres.add(nombre);        //Se agregan al arraylist el nombre de la cuenta y el saldo de cada una
        }
        //El metodo regresa el arraylist lleno con los datos que se necesitan de las cuentas.
        return nombres;
    }
    
    public Map<String,String> AnalisisPorcientosIntegrados()
    {
        Map<String,String> porcientos=new HashMap<>(); //Salida del metodo, es el resultado
        ArrayList<String> nombres = new ArrayList();
        ArrayList<String> aux = new  ArrayList();
        String s;    //En esta cadena se guarda el saldo de cada una de las cuentas
        //String CienS=null,Porcentaje;
        String nombrecuenta;
        float porciento,saldo,cien = ObtenerCien();
        
        nombres = obtenerNombresCuentas();
     
        for(int j=0;j<nombres.size();j++)
        {
            //cuentaP=cuentas.get(cuentas.get(j));
            
            nombrecuenta = nombres.get(j);
            aux = cuentas.get(nombres.get(j));
            s = aux.get(0);
            saldo = Float.parseFloat(s);
            porciento = CalcularPorcentaje(cien, saldo);
            if(porciento<0)
            {
                porciento=(-1)*porciento;
                porcientos.put(nombres.get(j), String.valueOf("("+Redondear(porciento, 2)+")"));
            }
            else
            {
                porcientos.put(nombres.get(j), String.valueOf(Redondear(porciento, 2)));
            }
            
            //Porcentaje=String.valueOf(CalcularPorcentaje(cien,Float.parseFloat(S)));
            //porcientos.put(nombres.get(j), String.valueOf(Redondear(porciento, 2))); //Se guardan los nombres de las cuentas y los porcentajes calculados
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
    
    public  float ObtenerCien()
    {
        float cien=0;
        Iterator it= cuentas.keySet().iterator();
        String nom, c;
        ArrayList<String> saldo;
        
        while (it.hasNext()) {
            nom=it.next().toString();
            
            if (nom.contains("Ventas"))
            {
                System.out.println("Estoy leyendo un edo de res");
                saldo=cuentas.get(nom);
                c = saldo.get(0);
                cien = Float.parseFloat(c);
                break;
            }
            else
            {
                if(nom.contains("Suma de activo"))
                {
                    System.out.println("Estoy leyendo un edo de sf");
                    saldo=cuentas.get(nom);
                    c = saldo.get(0);
                    cien = Float.parseFloat(c);
                    break;
                    
                }
            }
            
            
        }
        
        return cien;
    }
    
    public static double Redondear(double numero,int digitos)
    {
      int cifras=(int) Math.pow(10,digitos);
      return Math.rint(numero*cifras)/cifras;
    }
    
}
