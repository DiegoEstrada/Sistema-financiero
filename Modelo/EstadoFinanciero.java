
package Modelo;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Diego EG
 */
public interface EstadoFinanciero {
    public Map<String,ArrayList<String>> CUENTAS = new HashMap<>();
    
    public void crearEstadoFinanciero(File f);
    public void leerEstadoFinanciero();
    public void agregarCuenta(String cuenta, String tipo, String nombre, String saldo);
    public void agregarCuenta(String nombre, String saldo);
    public void eliminarCuenta(String cuenta);
    public void mostrarCuentas();
    public float obtenerSaldode(String cuentabuscada, String tipobuscado);
    public boolean verificarEstado();
    public ArrayList<String> obtenerCuentasde(String cuentabuscada, String tipobuscado);
    public Map<String,ArrayList<String>> importarCuentas();
    
}
