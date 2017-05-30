
package Modelo.Analisis.Vertical;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Diego EG
 */
public class BenchMarking {
    
    private String ramo;
    private RazonesFinancieras rf ;
    private final Map <String,ArrayList<String>> estandar = new HashMap();
    
    public BenchMarking(RazonesFinancieras razones, String ramo)
    {
        ArrayList<String> valores = new ArrayList();
        this.rf = razones;
        this.ramo = ramo;
        
        valores.add("0.79"); valores.add("3.32");  valores.add("0.46");
        valores.add("0.85"); valores.add("0.14");  valores.add("0.93");
      
        estandar.put("Industria", valores);
        
        valores.clear();
        
        valores.add("0.61"); valores.add("1.45");  valores.add("0.51");
        valores.add("1.14"); valores.add("0.06");  valores.add("2.58");
        estandar.put("Comercio", valores);
    }
    
}
