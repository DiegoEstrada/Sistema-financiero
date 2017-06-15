package Modelo;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author Diego EG
 */



public class FlujoEfectivo implements EstadoFinanciero {
    
    private float valorAmortizacionDepresiacion;
    private File f;
    private Map<String,ArrayList<String>> cuentas = new LinkedHashMap();
    

    @Override
    public void crearEstadoFinanciero(boolean calcularamortizacionydepresiacion) {
        
    }

    @Override
    public void leerEstadoFinanciero() {
   
    }

    @Override
    public void agregarCuenta(String cuenta, String tipo, String nombre, String saldo) {
        //Este metodo no estara codificado debido a que solo se cuenta con un identifiacaro d
    }

    @Override
    public void agregarCuenta(String nombre, String saldo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminarCuenta(String cuenta) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mostrarCuentas() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public float obtenerSaldode(String cuentabuscada, String tipobuscado) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean verificarEstado() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<String> obtenerNombresdeCuentasOrdenadas() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<String> obtenerCuentasde(String cuentabuscada, String tipobuscado) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Map<String, ArrayList<String>> importarCuentas() {
       return this.cuentas;
    }
    
}
