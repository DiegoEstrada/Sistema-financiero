package Modelo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;


/**
 *
 * @author Diego EG
 */
public class SituacionFinanciera {
    
    /* Declarando un map para guardar todas las cuentas del estado de resultados de manera que la llave de cada
       elemento se el nombre de la cuenta, su contenido es de tipo ArrayLista para alacenar varios indicadores
       adicionales neceaarios como el tipo de cuenta a la que pertenece y el saldo que tiene.
       Tomando la forma de 
        <cuenta,tipo,nombre,saldo> 
       Donde cuenta representa (activo,pasivo o capital)
             tipo representa (circulante, fijo o diferido) 
             nombre representa el nombre de la cuenta a utilizar (caja,bancos,almacen...)
             y saldo representa la cantidad en pesos con la cual cuenta la empresa en esa cuenta
    */
    
    private  Map<String,ArrayList<String>> cuentas;
    
    
    public SituacionFinanciera(String nombrearchivo){
        //Inicializando el objeto cuentas como HashMap
        cuentas = new HashMap<String, ArrayList<String>>();
        
        
    }
    
    public void crearEstadoDeSituacionFinanciera(String nombrearchivo)
    {
        //System.out.println("Estoy creando el estado ");
        File f;
        FileWriter w;
        BufferedWriter bw;
        PrintWriter wr;
        ArrayList<String> cuenta = new ArrayList<>(); 
        String datos[], aux; 
        float suma=0;
        int t,i;
        
        f = new File(nombrearchivo);
        try {
        
        w = new FileWriter(f);
        bw=new BufferedWriter(w);
        wr=new PrintWriter(bw);
        
        wr.println("\t\t\t\t\tEstado de situación financira de "+nombrearchivo+ "\t\t\t\t\t");
        wr.println("Activo");
        //Se comienzan a escribir las cuentas de activo cieculante, sus saldo y su suma 
        wr.println("\tCirculante");
        cuenta = obtenercuentasde(cuentas, "Activo", "Circulante");
        suma = obtenersaldode(cuentas, "Activo", "Circulante");
        t = cuenta.size();
            //System.out.println("Tamño "+t);
        for(i = 0; i<t; i++)
        {
          aux = cuenta.get(i);
          datos = aux.split(",");
            //System.out.println(datos[0] +"\t "+ datos[1]);
            wr.println("\t\t" +datos[0]+ "\t\t\t"+datos[1]);
            
        }
        
        wr.println("\tSuma citculante  "+suma+""); //se llama a la suma de activo circulante
        
        //Se comineza a escribir las cuentas de activo fijo, sus saldos y su suma
        
        wr.println("\tFijo");
        cuenta = obtenercuentasde(cuentas, "Activo", "Fijo");
        suma = obtenersaldode(cuentas, "Activo", "Fijo");
        t = cuenta.size();
            //System.out.println("Tamño "+t);
        for(i = 0; i<t; i++)
        {
          aux = cuenta.get(i);
          datos = aux.split(",");
            //System.out.println(datos[0] +"\t "+ datos[1]);
            wr.println("\t\t" +datos[0]+ "\t\t\t"+datos[1]);
            
        }
        
        wr.println("\tSuma fijo  "+suma+""); //se llama a la suma de activo fijo 
        
        
        //Se comienza aescribir las cuentas de activo diferido, sus saldos y su suma 
        
        wr.println("\tDiferido");
        cuenta = obtenercuentasde(cuentas, "Activo", "Diferido");
        suma = obtenersaldode(cuentas, "Activo", "Diferido");
        t = cuenta.size();
            //System.out.println("Tamño "+t);
        for(i = 0; i<t; i++)
        {
          aux = cuenta.get(i);
          datos = aux.split(",");
            //System.out.println(datos[0] +"\t "+ datos[1]);
            wr.println("\t\t" +datos[0]+ "\t\t\t"+datos[1]);
            
        }
        
        wr.println("\tSuma diferio  "+suma+""); //se llama a la suma de activo fijo 
        
        suma = obtenersaldode(cuentas, "Activo", "Activo");
        
        wr.println("Suma de activo   "+suma+""); //se llama a la suma de activo total 
        
        //Se comeinzan a escribir las cuentas de pasivo circulante, sus saldos y su suma
        wr.println("Pasivo");
        
        wr.println("\tCirculante");
        cuenta = obtenercuentasde(cuentas, "Pasivo", "Circulante");
        suma = obtenersaldode(cuentas, "Pasivo", "Circulante");
        t = cuenta.size();
            //System.out.println("Tamño "+t);
        for(i = 0; i<t; i++)
        {
          aux = cuenta.get(i);
          datos = aux.split(",");
            //System.out.println(datos[0] +"\t "+ datos[1]);
            wr.println("\t\t" +datos[0]+ "\t\t\t"+datos[1]);
            
        }
        
        wr.println("\tSuma circulante  "+suma+""); //se llama a la suma de activo fijo 
        
        
        //Se comienza a escribir las cuentas de pasivo fijo, sus saldos y su suma
        
        wr.println("\tFijo");
        cuenta = obtenercuentasde(cuentas, "Pasivo", "Fijo");
        suma = obtenersaldode(cuentas, "Pasivo", "Fijo");
        t = cuenta.size();
            //System.out.println("Tamño "+t);
        for(i = 0; i<t; i++)
        {
          aux = cuenta.get(i);
          datos = aux.split(",");
            //System.out.println(datos[0] +"\t "+ datos[1]);
            wr.println("\t\t" +datos[0]+ "\t\t\t"+datos[1]);
            
        }
        
        wr.println("\tSuma fijo  "+suma+""); //se llama a la suma de pasivo fijo 
        
        
        
        //Se cominezan a escribir las cuentas de capital, sus saldos y suma
        
        wr.println("Capital");
        
        float auxsuma=0;
        
        
        cuenta = obtenercuentasde(cuentas, "Capital", "Capital social");
        suma = obtenersaldode(cuentas, "Capital", "Capital social");
       
          aux = cuenta.get(0);
          datos = aux.split(",");
          wr.println("\t\t" +datos[0]+ "\t\t\t"+datos[1]);
           
          auxsuma += suma;
        
          
        cuenta = obtenercuentasde(cuentas, "Capital", "Utilidad neta");
        suma = obtenersaldode(cuentas, "Capital", "Utilidad neta");
       
          aux = cuenta.get(0);
          datos = aux.split(",");
          wr.println("\t\t" +datos[0]+ "\t\t\t"+datos[1]);
        
          
        auxsuma += suma;
        
        wr.println("\tSuma capital  "+auxsuma+""); //se llama a la suma de capital 
        
        suma = obtenersaldode(cuentas, "Pasivo", "Pasivo");
        
        wr.println("Suma pasivo y capital  "+(suma+auxsuma)+""); //se llama a la suma de activo fijo 
        
        wr.close();
        bw.close();
        
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
        System.out.println("Estado de resultados "+ nombrearchivo+ " creado con exito");
        
    }
    
    public void agregarcuenta(String cuenta, String tipo, String nombre, String saldo)
    {
        ArrayList<String> datos = new ArrayList();
        datos.add(cuenta);      datos.add(tipo);    datos.add(saldo);
        cuentas.put(nombre,datos);
    }
    
    public void eliminarcuenta(String cuenta)
    {
        cuentas.remove(cuenta);
    }
    
    public void mostrarcuentas()
    {
        //Declarando un iterador para recorrer todas las cuentas ingresadas e imprimirlas en consola
        Iterator it = cuentas.keySet().iterator();
        ArrayList <String> cuenta;
        int i,tam;
        String nombre;
        
        while(it.hasNext()){
            nombre = it.next().toString();
            cuenta = (ArrayList<String>) cuentas.get(nombre);
            
            tam = cuenta.size();
            System.out.print("Cuenta :"+nombre);
            for (i=0; i<tam;i++)
            {
                
                System.out.print(" "+cuenta.get(i));
            }
            System.out.println("");
        }
        
    }
    
    public  boolean verificardatosestado()
    {
        boolean edo= false; //ATENCION CAMBIAR A FALSE (CHECKED)
        Iterator it = cuentas.keySet().iterator();
        ArrayList <String> cuenta;
        int i,tam,sumaactivo=0,sumapasivo=0,sumacapital=0;
        String nombre;
        
        while(it.hasNext()){
            nombre = it.next().toString();
            cuenta = (ArrayList<String>) cuentas.get(nombre);
            
            /*Debido al orden de entrada de los datos en el metodo agregarcuenta() sabemos que los datos
              del objeto de tipo ArrayList se encuentean acomodados de la forma [0]cuenta [1]tipo [2]saldo
            */
            
            //En este caso en las condiciones podrian emplearse las posiciones el arraylist (la 0)
            
            if (cuenta.contains("Activo"))
                sumaactivo += Float.valueOf(cuenta.get(2));
            
            if (cuenta.contains("Pasivo"))
                sumapasivo += Float.valueOf(cuenta.get(2));
            
            
            if (cuenta.contains("Capital"))
                sumacapital += Float.valueOf(cuenta.get(2));

        }
        
        /*
        System.out.println("Suma de Activo = "+sumaactivo);
        System.out.println("Suma de Pasivo = "+sumapasivo);
        System.out.println("Suma de Capital = "+sumacapital);
        */
        
        edo = (sumaactivo==(sumapasivo+sumacapital))?true:false; //OK
        
        return edo;
    }

    
    public static ArrayList obtenercuentasde(Map cuentas, String cuentabuscada, String tipobuscado)
    {
        ArrayList<String> coincidencias = new ArrayList<>();
        
        Iterator it = cuentas.keySet().iterator();
        ArrayList <String> cuenta;
        String nombre, datos;
        
        while(it.hasNext()){
            nombre = it.next().toString();
            cuenta = (ArrayList<String>) cuentas.get(nombre);
            if (cuenta.contains(cuentabuscada) && cuenta.contains(tipobuscado))
            {
                //System.out.println(nombre +" = "+ cuenta.get(2));
               
                
                //coincidenciaencontrada[0] = nombre;     coincidenciaencontrada[1]=cuenta.get(2);
                datos = nombre +","+ cuenta.get(2);
               coincidencias.add(datos);
                //System.out.println(datos);
            }
            
        }
         
        return coincidencias;
    }
    
    public static float obtenersaldode(Map cuentas, String cuentabuscada, String tipobuscado)
    {
        float suma=0;
        String nombre;
        Iterator it = cuentas.keySet().iterator();
        ArrayList <String> cuenta;
        
        while(it.hasNext()){
            nombre = it.next().toString();
            cuenta = (ArrayList<String>) cuentas.get(nombre);
            if (cuenta.contains(cuentabuscada) && cuenta.contains(tipobuscado))
            {  
         
                suma += Float.valueOf(cuenta.get(2));
            }
            
        }
        
        return suma;
    }
    
   
    
}
