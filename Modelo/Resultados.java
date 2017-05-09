package Modelo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Diego EG
 */
public class Resultados implements EstadoFinanciero {
    
    private String nomEdoFin;
    
    public Resultados(String nombreEdoFin)
    {
        nomEdoFin = nombreEdoFin;
    }
   
    @Override
    public void crearEstadoFinanciero() {
        try {
            File f;
            FileWriter w;
            BufferedWriter bw;
            PrintWriter wr;
            ArrayList<String> cuenta = new ArrayList<>();
            ArrayList<String> utilidades = new ArrayList<>();
            String datos[], aux;
            float a,b,gastos,suma=0;
            int i,t;
            
           
            f = new File(nomEdoFin);
            w = new FileWriter(f);
            bw=new BufferedWriter(w);
            wr=new PrintWriter(bw);
            
            wr.println("\t\t\t\t\t" +nomEdoFin+ "\t\t\t\t\t");
            
            cuenta = obtenerCuentasde("Ventas", "Ventas");
            aux = cuenta.get(0); //Obteniendo los valores de la cuenta de ventas
            datos = aux.split(",");
            a = Float.valueOf(datos[1]);
            wr.println(datos[0] +"\t"+ datos[1] );
            
            wr.println("Menos");
            
            cuenta = obtenerCuentasde("Costo", "Costo");
            aux = cuenta.get(0); //Obteniendo los valores de la cuenta de Costo de ventas
            datos = aux.split(",");
            b = Float.valueOf(datos[1]);
            wr.println(datos[0] +"\t"+ datos[1] );
            
            suma = a-b;
            
            wr.println("Utilidad bruta \t "+String.valueOf(suma));
            aux = String.valueOf(suma);
            utilidades.add(aux);
            CUENTAS.put("Utilidad bruta", utilidades);
            
            wr.println("Menos");
            
            cuenta = obtenerCuentasde("Gastos", "Gastos");
            suma = obtenerSaldode("Gastos", "Gastos");
            t = cuenta.size();
            //System.out.println("Tamño "+t);
            for(i = 0; i<t; i++)
            {
                aux = cuenta.get(i);
                datos = aux.split(",");
                //System.out.println(datos[0] +"\t "+ datos[1]);
                wr.println(datos[0]+ "\t"+datos[1]);
            
            }
            
            gastos = a-b-suma;
            
            wr.println("Utilidad de operacion \t "+String.valueOf(a-b-suma));
            aux = String.valueOf(a-b-suma);
            utilidades.clear();
            utilidades.add(aux);
            CUENTAS.put("Utilidad de operacion", utilidades);
            
            a=0; b =0;
            
            if ((cuenta = obtenerCuentasde("Otros", "Otros")).size()>0)
            {
                if ((cuenta = obtenerCuentasde("gastos", "gastos")).size()>0)
                {
                cuenta = obtenerCuentasde("gastos", "gastos");
                aux = cuenta.get(0); //Obteniendo los valores de la cuenta de otros gastos finacieros
                datos = aux.split(",");
                a = Float.valueOf(datos[1]);
                wr.println("Menos");
                wr.println(datos[0] +"\t"+ datos[1] );
                }
                if ((cuenta = obtenerCuentasde("productos", "productos")).size()>0)
                {
                cuenta = obtenerCuentasde("productos", "productos");
                aux = cuenta.get(0); //Obteniendo los valores de la cuenta de otros gastos finacieros
                datos = aux.split(",");
                b = Float.valueOf(datos[1]);
                wr.println("Mas");
                wr.println(datos[0] +"\t"+ datos[1] );
                }
                suma = (gastos - a ) +b; 
                wr.println("Utilidad de operacion \t "+suma);
            }
            else
            {
                wr.println("Utilidad de operacion \t" +suma);
            }
            
            
         wr.println("Menos");
         
        cuenta = obtenerCuentasde("ISR", "ISR");
        aux = cuenta.get(0); //Obteniendo los valores de la cuenta de ISR
        datos = aux.split(",");
        a = Float.valueOf(datos[1]);;
        wr.println(datos[0] +"\t"+ datos[1] );
        
        cuenta = obtenerCuentasde("PTU", "PTU");
        aux = cuenta.get(0); //Obteniendo los valores de la cuenta de PTU
        datos = aux.split(",");
        b = Float.valueOf(datos[1]);;
        wr.println(datos[0] +"\t"+ datos[1] );
        
        float utilidad;
        
        utilidad = suma - (a+b);
        utilidades.clear();
        utilidades.add(String.valueOf(a+b));
        CUENTAS.put("Impuestos por pagar",utilidades);
        
        wr.println("Impuestos por pagar \t"+ (a+b));
        
        utilidades.clear();
        utilidades.add(String.valueOf(utilidad));
        CUENTAS.put("Utilidad neta",utilidades);
        
       wr.println("Utilidad neta \t"+ utilidad);
       
            
            
        wr.close();
        bw.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
        
        
    }

    @Override
    public void agregarCuentas(String nombre, String saldo) {
        ArrayList<String> datos = new ArrayList<>();
        
        /*
            Para el caso del estado de resultados no hay una clasificacion para el tipo de cuenta
            Lo unico que hay que idientificar es si la cuenta pertenece a la suma/resta de la utilidad
            bruta, de operacion o neta. Por lo cual la forma de estas cuentas tendria la siguiente forma
            de acuerdo con los parametros recibidos 
                                <nombre, saldo>
        */
        datos.add(saldo);
        CUENTAS.put(nombre, datos);
       
    }

    @Override
    public void eliminarCuenta(String cuenta) {
      CUENTAS.remove(cuenta);
    }

    @Override
    public void mostrarCuentas() {
        Iterator it = CUENTAS.keySet().iterator();
        ArrayList <String> cuenta;
        int i,tam;
        String nombre;
        
        while(it.hasNext()){
            nombre = it.next().toString(); //Esta variable contiene el nombre de la llave de cada elemento del hashmap en las diferentes itraciones
            cuenta = (ArrayList<String>) CUENTAS.get(nombre); //Este objeto almacena los detalles de cada cuenta
            
            tam = cuenta.size();
            
            System.out.print("Cuenta :"+nombre);
            for (i=0; i<tam;i++)
            {
                
                System.out.print(" "+cuenta.get(i));
            }
            System.out.println("");
        }
        
    }

    @Override
    public void leerEstadoFinanciero() {
        
    }

    @Override
    public float obtenerSaldode(String cuentabuscada, String tipobuscado) {
        float suma=0;
        
        Iterator it = CUENTAS.keySet().iterator();
        ArrayList <String> cuenta;
        String nombre;
        
       
         while(it.hasNext()){
            nombre = it.next().toString();
            cuenta = (ArrayList<String>) CUENTAS.get(nombre);
            if (nombre.contains(tipobuscado))
             suma += Float.valueOf(cuenta.get(0));
        }
        
        return suma;
    }
    
    /*
    La importancia de tener en el nombre de la forma Abc dfg, es decir que solo la pimer letra comienza con mayuscula
    permite identifica de manera precisa la distincion entre las cuentas Ventas y Costo de ventas que contienen la palabra
    ventas pero de diferente manera
    */

    @Override
    public ArrayList<String> obtenerCuentasde(String cuentabuscada, String tipobuscado) {
        
        ArrayList<String> coincidencias = new ArrayList();
        
        Iterator it = CUENTAS.keySet().iterator();
        ArrayList <String> cuenta;
        String nombre, datos;
        
       
         while(it.hasNext()){
            nombre = it.next().toString();
            cuenta = (ArrayList<String>) CUENTAS.get(nombre);
            if (nombre.contains(cuentabuscada))
            {
               datos = nombre +","+ cuenta.get(0);
               coincidencias.add(datos);
               
            }
            
        }
        
        return coincidencias;
    }

    @Override
    public void agregarCuentas(String cuenta, String tipo, String nombre, String saldo) {
        //Este metodo esta vacio con la intencion de implementar mas detalles al HashMapal agregar cuentas 
    }
    
    
}
