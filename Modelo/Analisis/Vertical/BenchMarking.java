
package Modelo.Analisis.Vertical;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author Diego EG
 */
public class BenchMarking {
    
    private int ramo;
    private RazonesFinancieras rf ;
    //private Map<String,String> indemp = new HashMap();  //indicadores de una empresa determinada
    private final Map <String,ArrayList<String>> estandar = new HashMap();
    
    /*
        Para este caso el atributo ramo representa si la empresa pertence al ramo industrial, de comercio,
        tecnologia o servicios. Estos estan definidos por:
        1.  Indutria
        2.  Comercio
        3.  Servicios
        4.  Tecnologia
        
    */
    
    public BenchMarking(RazonesFinancieras razones, int ramo)
    {
        ArrayList<String> valores1 = new ArrayList();
        ArrayList<String> valores2 = new ArrayList();
        ArrayList<String> valores3 = new ArrayList();
        ArrayList<String> valores4 = new ArrayList();
        
        this.rf = razones;
        this.ramo = ramo;
        
        valores1.add("0.79"); valores1.add("3.32");  valores1.add("0.46");
        valores1.add("0.85"); valores1.add("0.14");  valores1.add("0.93");
        estandar.put("Industria", valores1);
        
        //valores.clear();
        
        valores2.add("0.61"); valores2.add("1.45");  valores2.add("0.51");
        valores2.add("1.14"); valores2.add("0.06");  valores2.add("2.58");
        estandar.put("Comercio", valores2);
        
        //valores.clear();
        
        valores3.add("0.22"); valores3.add("0.99");  valores3.add("0.49");
        valores3.add("1.5");  valores3.add("0.08");  valores3.add("0.53");
        estandar.put("Servicios", valores3);
        
        //valores.clear();
        
        valores4.add("0.45");  valores4.add("1.18");  valores4.add("0.66");
        valores4.add("1.92");  valores4.add("0.11");  valores4.add("2.93");
        estandar.put("Tecnologia", valores4);
    }
    
    public Map<String,String> AnalisisBenchMarking()
    {
        String nom="";
        String ramoempresa = "";
        float dif,a,b;
        ArrayList<String> indicadores = new ArrayList();
        Map<String, String> razonesEmpresa = rf.analisisRazonesFinancieras();
        Map<String,String> bm = new HashMap();
         
        
        switch(ramo)
        {
            case 1:
                    ramoempresa = "Industria";
                break;
            case 2 :
                    ramoempresa = "Comercio";
                break;
            case 3:
                    ramoempresa = "Servicios";
                break;
            case 4:
                    ramoempresa = "Tecnologia";
                break;
            default:
                System.out.println("El identificador del ramo no existe ");
                break;
        }
        //System.out.println(ramoempresa);
        indicadores = obtenerIndicadoresdeRamo(ramoempresa);
        
         nom="Liquidez";
        a=Float.parseFloat(razonesEmpresa.get("Liquidez"));
        b=Float.parseFloat(indicadores.get(0));
        System.out.println("-----------------"+b);
        dif=a-b;
        bm.put(nom, String.valueOf(dif));
        
        
        nom="Solvencia";
        a=Float.parseFloat(razonesEmpresa.get("Solvencia"));
        b=Float.parseFloat(indicadores.get(1));
        dif=a-b;
        bm.put(nom, String.valueOf(dif));
        
        nom="Estabilidad economica";
        a=Float.parseFloat(razonesEmpresa.get("Estabilidad economica"));
        b=Float.parseFloat(indicadores.get(2));
        dif= a<b ? b-a:a-b;
        bm.put(nom, String.valueOf(dif));
        
        nom="Inmovilizacion de capital";
        a=Float.parseFloat(razonesEmpresa.get("Inmovilizacion de capital"));
        b=Float.parseFloat(indicadores.get(3));
        dif= a<b ? b-a:a-b;
        bm.put(nom, String.valueOf(dif));
        
        nom="Rentabilidad en ventas";
        a=Float.parseFloat(razonesEmpresa.get("Rentabilidad en ventas"));
        b=Float.parseFloat(indicadores.get(4));
        dif=a-b;
        bm.put(nom, String.valueOf(dif));
        
        nom="Rentabilidad en inversion";
        a=Float.parseFloat(razonesEmpresa.get("Rentabilidad en inversion"));
        b=Float.parseFloat(indicadores.get(5));
        dif=a-b;
        bm.put(nom, String.valueOf(dif));
        
        
        
        return bm;
    }
    
    public ArrayList<String> obtenerIndicadoresdeRamo(String nombre)
    {
        String llave = "";
        ArrayList<String> indicadores = new ArrayList();
        Map<String, String> rEmpresa = rf.analisisRazonesFinancieras();
        Iterator it = estandar.keySet().iterator();
       
        while (it.hasNext()) {
            llave=it.next().toString();
            if(llave.contains(nombre))
            {
                indicadores = estandar.get(llave);
                break;
            }
        }
       
        
        
        return indicadores;
    }
    
    public void imprimirDiferenciasBenchMarking(Map <String,String> diferencias)
    {
        Iterator it= diferencias.keySet().iterator();
        String nom,val;
        
        while (it.hasNext()) {
            nom=it.next().toString();
            val=diferencias.get(nom);
            
            System.out.println(nom +" diferencia = "+val );
        }
    }
    
    
    
    
}
