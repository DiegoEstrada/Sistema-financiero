package Modelo.Analisis.Horizontal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author Diego EG
 */
public class Diferencias {
    
    Map<String,ArrayList<String>>cuentasEdoX;
    Map<String,ArrayList<String>>cuentasEdoY;
    
    /*  
    Los objetos de tpo Map que llevan por nombre cuentasEdoX y cuentasEdoY se refieren a X para el periodo
    contable actual y Y para el periodo contable anterior
    X Periodo actual
    Y Periodo anterior
    */
    
    public Diferencias(Map<String,ArrayList<String>>cuentasEdoX, Map<String,ArrayList<String>>cuentasEdoY)
    {
        this.cuentasEdoX = cuentasEdoX;
        this.cuentasEdoY = cuentasEdoY;
        
        //ArrayList<String> nombres = obtenerNombresCuentas();
        //String c;
        
        //System.out.println("Este estado tiene " +nombres.size()+" cuentas ");
        //c  = (!nombres.isEmpty())?"Cuentas iguales":"Cuentas diferentes";
        
        //System.out.println(c);
        
        
        
        
    }
    
    
    public ArrayList<String> obtenerNombresCuentas()
    {
        
        int numcuentas=0;
        ArrayList<String> nombres = new ArrayList();
            
        /*
        Para encontrar los nombres de las cuentas que se usan en ambos estados, partiremos del hecho que ambos estados
        deben de tener el mismo numero de cuentas y las mismas cuentas.
        Para cubrir el primer punto se compara la longitud de ambas cuentas y asi determianr si se debe o no proseguir 
        con el analisis.
        Para el segundo punto tomaremos las cuentas del estado X para ir iterandolas y buscandolas 
        */
        
        if(cuentasEdoX.size()==cuentasEdoY.size())
        {
            Iterator it = cuentasEdoX.keySet().iterator();
        
            ArrayList <String> cuenta;
            String nombre;
        
                while(it.hasNext()){
                    nombre = it.next().toString(); //Esta variable contiene el nombre de la llave de cada elemento del hashmap en las diferentes itraciones
                    cuenta = (ArrayList<String>) cuentasEdoX.get(nombre); //Este objeto almacena los detalles de cada cuenta
                        if(cuentasEdoY.containsKey(nombre)){
                            nombres.add(nombre);
                            numcuentas++;
                        }
                        else{
                            System.out.println("Hay una cuenta que no existe en ambos estados financieros");
                        }
                }
        }
        else
            System.out.println("En alguno de los estados financieross las cuentas son diferentes o no existen las mismas cuentas");
        
        
       
        
        //System.out.println("valor de numcuentas"+numcuentas);
        if (numcuentas==cuentasEdoX.size())
            return nombres;
        else
        {
            System.out.println("No se ha podido generar el metodo de diferencias por problemas en las cuentas de los estados financieros");
           return null; 
        }
       
    }
    
    public Map<String,String> analisisDiferencias(ArrayList<String> nombres)
    {
        Map<String,String> diferencias =  new HashMap();
        ArrayList<String> cuentaX = new ArrayList();
        ArrayList<String> cuentaY = new ArrayList();
        String cX, cY;
        float saldoX, saldoY,saldoDif;
        
        
        for (int i = 0; i < nombres.size(); i++) {
            
            //System.out.println("Tam de "+nombres.get(i)+" : "+cuentaX.size());
            
            cuentaX = cuentasEdoX.get(nombres.get(i));
            cuentaY = cuentasEdoY.get(nombres.get(i));
            
            
            //System.out.println("Tamano del arreglo "+cuentaX.size());
            cX = cuentaX.get(0);
            cY = cuentaY.get(0);
            
            saldoX = Float.parseFloat(cX);
            //System.out.println("Saldo I "+saldoX);
            saldoY = Float.parseFloat(cY);
            //System.out.println("Saldo F "+saldoY);
             saldoDif = saldoX - saldoY;
             //System.out.println("SALDO "+saldoDif);
            diferencias.put(nombres.get(i),String.valueOf(saldoDif));
            
        }
              
        return diferencias;
    }
    
    public void imprimirAnalisisDierencias(Map<String,String> analisis)
    {
        Iterator it = analisis.keySet().iterator();
       
            String nombre,saldo;
        
                while(it.hasNext()){
                    nombre = it.next().toString();
                    saldo = analisis.get(nombre);
                    
                    System.out.println("Cuenta : "+ nombre +" Diferencia = "+saldo );
                }
    }
   
    
}
