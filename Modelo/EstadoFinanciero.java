
package Modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Diego EG
 */
public interface EstadoFinanciero {
    public Map<String,ArrayList<String>> CUENTAS = new HashMap<>();
    
    public void crearEstadoFinanciero();
    public void leerEstadoFinanciero();
    public void agregarCuentas(String cuenta, String tipo, String nombre, String saldo);
    public void agregarCuentas(String nombre, String saldo);
    public void eliminarCuenta(String cuenta);
    public void mostrarCuentas();
    public float obtenerSaldode(String cuentabuscada, String tipobuscado);
    public ArrayList<String> obtenerCuentasde(String cuentabuscada, String tipobuscado);
    public Map<String,ArrayList<String>> importarCuentas();
    
}
