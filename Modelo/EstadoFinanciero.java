
package Modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Diego EG
 */
public interface EstadoFinanciero {
    public Map<String,ArrayList<String>> cuentas = new HashMap<String, ArrayList<String>>();
    
    public void crearEstadoFinanciero(String nommbreEdoFin);
    public void leerEstadoFinanciero(String nombreEdo);
    public void agregarCuentas(String cuenta, String tipo, String nombre, String saldo);
    public void eliminarCuenta(String cuenta);
    public void mostrarCuentas();
    public float obtenerSaldode();
    public ArrayList<String> obtenerCuentasde();
    
}
