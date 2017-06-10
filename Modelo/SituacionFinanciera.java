package Modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


/**
 *
 * @author Diego EG
 */
public class SituacionFinanciera implements EstadoFinanciero {
    
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
    
    //private  Map<String,ArrayList<String>> cuentas;
    private String nomEdoFin;
    private static String cuentaaux;
    private static String tipoaux;
    private File f;
    private Map<String,ArrayList<String>> cuentas = new HashMap();
    
    public SituacionFinanciera(File archivo){
        //Inicializando el objeto cuentas como HashMap
        //cuentas = new HashMap<String, ArrayList<String>>();
        this.f = archivo;
        nomEdoFin = archivo.getName();
        
    }
    
    public Map<String,ArrayList<String>> importarNombreySaldo()
    {
        Map<String,ArrayList<String>> nombreySaldo = new HashMap();
        //ArrayList <String> saldos = new ArrayList();
        ArrayList <String> cuenta;
        int i,tam;
        String nombre;
        
        Iterator it = cuentas.keySet().iterator();
        
        while(it.hasNext()){
            ArrayList <String> saldos = new ArrayList();
            nombre = it.next().toString(); //Esta variable contiene el nombre de la llave de cada elemento del hashmap en las diferentes itraciones
            cuenta = (ArrayList<String>) cuentas.get(nombre); //Este objeto almacena los detalles de cada cuenta
            
                saldos.add(cuenta.get(2));
     
                nombreySaldo.put(nombre, saldos);
             
               //saldos.clear();
        }
        
        
        return nombreySaldo;
    }
    
    @Override
    public void crearEstadoFinanciero(boolean calcularamortizaconydepresiacion) {
        //System.out.println("Estoy creando el estado ");
        //File f;
        FileWriter w;
        BufferedWriter bw;
        PrintWriter wr;
        ArrayList<String> cuenta = new ArrayList<>(); 
        String datos[], aux; 
        String nombrear = f.getName();
        float suma=0;
        int t,i;
        
        //f = new File(nomEdoFin);
        try {
        
        w = new FileWriter(f);
        bw=new BufferedWriter(w);
        wr=new PrintWriter(bw);
        
        wr.println("\t\t\t\t\tEstado de situación financira de "+nombrear+ "\t\t\t\t\t");
        wr.println("Activo");
        //Se comienzan a escribir las cuentas de activo cieculante, sus saldo y su suma 
        wr.println("Circulante");
        cuenta = obtenerCuentasde( "Activo", "Circulante");
        suma = obtenerSaldode("Activo", "Circulante");
        t = cuenta.size();
            //System.out.println("Tamño "+t);
        for(i = 0; i<t; i++)
        {
          aux = cuenta.get(i);
          datos = aux.split(",");
            //System.out.println(datos[0] +"\t "+ datos[1]);
            wr.println(datos[0]+ " "+datos[1]);
            
        }
        
        wr.println("Suma activo circulante  "+suma); //se llama a la suma de activo circulante
        
        //Se comineza a escribir las cuentas de activo fijo, sus saldos y su suma
        
        wr.println("Fijo");
        
        float edificios = obtenerSaldodeLlave("Edificios");
        float mobiliario = obtenerSaldodeLlave("Mobiliario");
        float equiporeparto = obtenerSaldodeLlave("Equipo de reparto");
        float equipotransporte = obtenerSaldodeLlave("Equipo de transporte");
        float equipocomputo = obtenerSaldodeLlave("Equipo de computo");
        
        double depresiacionedificios = (edificios*0.05);
        double depresiacionmobiliario =  (mobiliario*0.1);
        double depresiacionequiporep =  (equiporeparto*0.25);
        double depresiacionequipotransp = (equipotransporte*0.25);
        double depresiacionequcomputo = (equipocomputo*0.33);
        
        double depresacionacumulada = depresiacionedificios + depresiacionmobiliario + depresiacionequiporep + depresiacionequipotransp + depresiacionequcomputo;
        
        
        cuenta = obtenerCuentasde("Activo", "Fijo");
        suma = obtenerSaldode("Activo", "Fijo");
        if(calcularamortizaconydepresiacion){
        suma = suma - (float)depresacionacumulada;
        }
        t = cuenta.size();
            //System.out.println("Tamño "+t);
        for(i = 0; i<t; i++)
        {
          aux = cuenta.get(i);
          datos = aux.split(",");
            //System.out.println(datos[0] +"\t "+ datos[1]);
            wr.println(datos[0]+" "+datos[1]);
            
        }
        if (calcularamortizaconydepresiacion){
        wr.println("Depreciacion acumulada "+(float) depresacionacumulada);
        }
        wr.println("Suma activo fijo  "+suma); //se llama a la suma de activo fijo 
        
        
        //Se comienza aescribir las cuentas de activo diferido, sus saldos y su suma 
        
        wr.println("Diferido");
        float gastosdeinstalacion = obtenerSaldodeLlave("Gastos de instalacion");
        float gastosdeorganizacion = obtenerSaldodeLlave("Gastos de organizacion");
        
        double amortizaciongastosinst = (gastosdeinstalacion*0.05);
        double amortizaciongastosorg =  (gastosdeorganizacion*0.05);
        double amoriizacionacumulada = amortizaciongastosinst + amortizaciongastosorg;
        
        cuenta = obtenerCuentasde("Activo", "Diferido");
        suma = obtenerSaldode("Activo", "Diferido");
        if(calcularamortizaconydepresiacion){
        suma  = suma - (float)amoriizacionacumulada;
        }
        t = cuenta.size();
            //System.out.println("Tamño "+t);
        for(i = 0; i<t; i++)
        {
          aux = cuenta.get(i);
          datos = aux.split(",");
            //System.out.println(datos[0] +"\t "+ datos[1]);
            wr.println(datos[0]+ " "+datos[1]);
            
        }
        
        if(calcularamortizaconydepresiacion){
        wr.println("Amortizacion acumulada "+(float)amoriizacionacumulada);
        }
        wr.println("Suma activo diferio  "+suma); //se llama a la suma de activo fijo 
        
        suma = obtenerSaldode("Activo", "Activo");
        if(calcularamortizaconydepresiacion){
        suma = suma - (float)depresacionacumulada - (float)amoriizacionacumulada;
        }
        
        wr.println("Suma de activo   "+suma); //se llama a la suma de activo total 
        
        //Se comeinzan a escribir las cuentas de pasivo circulante, sus saldos y su suma
        wr.println("Pasivo");
        
        wr.println("Circulante");
        cuenta = obtenerCuentasde("Pasivo", "Circulante");
        suma = obtenerSaldode("Pasivo", "Circulante");
        t = cuenta.size();
            //System.out.println("Tamño "+t);
        for(i = 0; i<t; i++)
        {
          aux = cuenta.get(i);
          datos = aux.split(",");
            //System.out.println(datos[0] +"\t "+ datos[1]);
            wr.println(datos[0]+ " "+datos[1]);
            
        }
        
        wr.println("Suma pasivo circulante  "+suma); //se llama a la suma de activo fijo 
        
        
        //Se comienza a escribir las cuentas de pasivo fijo, sus saldos y su suma
        
        wr.println("Fijo");
        cuenta = obtenerCuentasde("Pasivo", "Fijo");
        suma = obtenerSaldode("Pasivo", "Fijo");
        t = cuenta.size();
            //System.out.println("Tamño "+t);
        for(i = 0; i<t; i++)
        {
          aux = cuenta.get(i);
          datos = aux.split(",");
            //System.out.println(datos[0] +"\t "+ datos[1]);
            wr.println(datos[0]+ " "+datos[1]);
            
        }
        
        wr.println("Suma pasivo fijo  "+suma+""); //se llama a la suma de pasivo fijo 
        
        
        
        //Se cominezan a escribir las cuentas de capital, sus saldos y suma
        
        wr.println("Capital");
        
        float auxsuma=0;
        
        
        cuenta = obtenerCuentasde("Capital", "Capital social");
        suma = obtenerSaldode("Capital", "Capital social");
       
          aux = cuenta.get(0);
          datos = aux.split(",");
          wr.println(datos[0]+ " "+datos[1]);
           
          auxsuma += suma;
        
          
        cuenta = obtenerCuentasde("Capital", "Utilidad neta");
        suma = obtenerSaldode("Capital", "Utilidad neta");
        if(calcularamortizaconydepresiacion){
        suma = suma - (float)amoriizacionacumulada - (float)depresacionacumulada;
        }
          aux = cuenta.get(0);
          datos = aux.split(",");
          float s = Float.parseFloat(datos[1]);
            //System.out.println("--------------------------"+s);
            if(calcularamortizaconydepresiacion){
            s = s - (float)amoriizacionacumulada - (float)depresacionacumulada;
            }
          wr.println(datos[0]+ " "+s);
        
          
        auxsuma += suma;
        
        wr.println("Suma capital  "+auxsuma+""); //se llama a la suma de capital 
        
        suma = obtenerSaldode("Pasivo", "Pasivo");
        
        wr.println("Suma pasivo y capital  "+(suma+auxsuma)+""); //se llama a la suma de activo fijo 
        
        wr.close();
        bw.close();
        
        } catch (IOException ex) {
            System.out.println("Excepcion --->"+ex.getMessage());
        }
        
        System.out.println(nombrear+ " creado con exito");
        
    }

    @Override
    public void leerEstadoFinanciero() {
      
        FileReader r;
        System.out.println("Voy a leer "+this.f);
        BufferedReader br;
        String linea;
            //PrintWriter wr;
        
      
        
        try {
            
            r = new FileReader(this.f);
            br=new BufferedReader(r);
            
            while((linea=br.readLine())!=null)
            {
                asignarCuentas(linea);
            }
            
            
            r.close();
            br.close();
            
        } catch (FileNotFoundException ex) {
            System.out.println("Exepcion leer archivo Situcacion Financiera "+ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Excepcon IO leer archivo Situacion Financiera "+ex.getMessage());
        }
    }

    @Override
    public void agregarCuenta(String cuenta, String tipo, String nombre, String saldo) {
        ArrayList<String> datos = new ArrayList();
        datos.add(cuenta);      datos.add(tipo);    datos.add(saldo);
        cuentas.put(nombre,datos);
    }

    @Override
    public void agregarCuenta(String nombre, String saldo) {
       /*
        Este metdo no se implementa para esta clase debido a que un estado de situacionfinanciera requiere de mas 
        parametros de los que provee este metodo
        */
    }

    @Override
    public void eliminarCuenta(String cuenta) {
         cuentas.remove(cuenta);
    }

    @Override
    public void mostrarCuentas() {
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

    @Override
    public float obtenerSaldode(String cuentabuscada, String tipobuscado) {
        
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

    @Override
    public ArrayList<String> obtenerCuentasde(String cuentabuscada, String tipobuscado) {
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

    @Override
    public Map<String, ArrayList<String>> importarCuentas() {
        return cuentas;
    }

    @Override
    public boolean verificarEstado() {
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
    
    
     public void asignarCuentas(String linea)
    {
        /*
        Para este metodo de clase se debe considerar que si se quiere leer un estado de resultados hecho por el usuario
        en lugar de crearlo con el sistema se corre el riesgo de la perdida de fidelidad en la lectura de informacion
        debido a que la condicion para asignar los saldo admite todo lo que no sea en letras de a-z, A-Z o espacios
        */
        int t = linea.length();
        ArrayList<String> aux = new ArrayList();
        String nombre = "";
        String  saldo = "";
        String cuenta="";
        String tipo = "";
        
        /*
        Este ArrayList contiene las palabras que al momento de ser leidas por el archivo no forman parte de alguna cuenta
        del estado de situacion financiera, cada que se encuetre una de estas palabras se debe de saltar a la siguiente liena
        del estado financiero
        */
       
        
        
        
        if(linea.equals("Activo"))
            cuentaaux = "Activo";
        else{
            if (linea.equals("Pasivo")) 
                cuentaaux = "Pasivo";
            else
            {
                if (linea.equals("Capital")) 
                    cuentaaux = "Capital";
                else 
                {
                    if (linea.contains("Estado")) 
                        cuentaaux = "Activo";
                    else
                        cuentaaux = cuentaaux;
                }
                    
            }
        }
        
        
        if(cuentaaux.equals("Activo") || cuentaaux.equals("Pasivo"))
        {
            if(linea.contains("Circulante"))
                                    tipoaux = "Circulante";
                                else
                                {
                                    if(linea.contains("Fijo"))
                                        tipoaux = "Fijo";
                                    else
                                    {
                                        if(linea.contains("Diferido"))
                                        tipoaux = "Diferido";
                                        else
                                            tipoaux = tipoaux;
                                    }
                                }
        }
        else
        {
            if(cuentaaux.equals("Capital"))
            {
                if(linea.contains("Capital social"))
                                        tipoaux = "Capital social";
                                    else
                                    {
                                        if(linea.contains("Utilidad neta"))
                                        tipoaux = "Utilidad neta";
                                        else
                                          tipoaux = "Utilidad Neta"; 
                                    }
            }
            else
                System.out.println("Error en la elaboraion del Estado Financiero");
        }
        
        
        
        
if (!linea.equals("Capital") && !linea.contains("Activo") && !linea.contains("Pasivo") && !linea.contains("Circulante")  
     && !linea.contains("Fijo") && !linea.contains("Diferido")  && !linea.contains("Estado")
    ) 
    {
        for (int i = 0; i < t; i++) {            
            if(((linea.charAt(i)==32) ||(linea.charAt(i)>=65 && linea.charAt(i)<=90) || ((linea.charAt(i)>=97 && linea.charAt(i)<=122)))) //Mentras este leyendo un caracter en lugar de un numero en codigo ascii
                {
                    cuenta += linea.charAt(i);
                }
                else
                {
                    saldo += linea.charAt(i);
                }  
           
        }
     
     
      aux.clear();
            //System.out.println(cuenta);
            //System.out.println(saldo);
            aux.add(cuentaaux);
            aux.add(tipoaux);
            aux.add(saldo);
            
            cuentas.put(cuenta, aux);
    } 
}

    @Override
    public ArrayList<String> obtenerNombresdeCuentasOrdenadas() {
        ArrayList<String> nombresordenados = new ArrayList();
        
        String nombre;
        FileReader r;
        BufferedReader br;
        String linea;
            //PrintWriter wr;
        
        
        
        try {
            
            r = new FileReader(this.f);
            br=new BufferedReader(r);
            
            while((linea=br.readLine())!=null)
            {
                if (!linea.equals("Capital") && !linea.contains("Activo") && !linea.contains("Pasivo") && !linea.contains("Circulante")  
                && !linea.contains("Fijo") && !linea.contains("Diferido")  && !linea.contains("Estado")
                ) 
                { 
                    nombre = nombreOrdenado(linea);
                    nombresordenados.add(nombre);
                    //System.out.println(nombre);
                }
                
            }
            
            
            r.close();
            br.close();
            
        } catch (FileNotFoundException ex) {
            System.out.println("Excepcion Situacion financiera en obtenerNombresOrdenados "+ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Excepcion IO en situacion financiera obtenerNombresOrdenados "+ex.getMessage());
        }
        
        
        
        return nombresordenados;
    }
    
    public String nombreOrdenado(String linea)
    {
        int t = linea.length();
        String nombre = "";
        
        
            for (int i = 0; i < t; i++) {
            
                    if((linea.charAt(i)==32) ||(linea.charAt(i)>=65 && linea.charAt(i)<=90) || ((linea.charAt(i)>=97 && linea.charAt(i)<=122))) //Mentras este leyendo un caracter en lugar de un numero en codigo ascii
                     {
                        nombre += linea.charAt(i);
                     }
                    else
                        break;
                
            }
                
        
        return nombre;
    }
    
    
    public float obtenerSaldodeLlave(String nombrecuenta)
    {
        float saldo = 0;
        String nombre;
        Iterator it = cuentas.keySet().iterator();
        ArrayList <String> cuenta;
        
        while(it.hasNext()){
            nombre = it.next().toString();
            cuenta = (ArrayList<String>) cuentas.get(nombre);
            if(nombre.contains(nombrecuenta))
            {
                saldo += Float.valueOf(cuenta.get(2));
            }
            
        }
        
        return saldo;
    }
}
