
package Programas;

import Modelo.Venta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CRUD_venta  extends Conexion{
   Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    
    
    
    public int ObtenerNumeroDeFactura() {
        int numerodefactura =0;
        String sql = "SELECT MAX(numerofactura) FROM ventas";
        try {
            this.conectar(false);
            rs = this.ejecutarOrdenDatos(sql);
            while (rs.next()) {
                   
                numerodefactura = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CRUD_venta.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
           Logger.getLogger(CRUD_venta.class.getName()).log(Level.SEVERE, null, ex);
       }
        
        return numerodefactura;

    }

    
        public void RegistrarVenta(Venta venta)throws Exception{
         // String sql = "INSERT INTO ventas (numerofactura,idclienteventa,idempleadoventa,,fechaventa,totalventa,estado,id_user,id_clie) VALUES("+venta.getNumeroComprobante()+","+venta.getIdCliente()+","+venta.getIdEmpleado()+",'"+venta.getFecha()+"',"+venta.getMonto()+",'"+venta.getEstado()+"',"+venta.getIdEmpleado()+","+venta.getIdCliente()+")";
         String sql = "INSERT INTO ventas(numerofactura, idclienteventa, idempleadoventa, fechaventa, totalventa, estado, id_user, id_cli) VALUES ("+venta.getNumeroComprobante()+","+venta.getIdCliente()+","+venta.getIdEmpleado()+",'2023-01-01',"+venta.getMonto()+",'1',"+venta.getIdEmpleado()+","+venta.getIdCliente()+")";
        
          try {
            this.conectar(false);
            this.ejecutarOrden(sql);
            this.cerrar(true);
           
        } catch (Exception e) {
            this.cerrar(false);
            throw e;
        }
     }
    
    public void GuardarDetalleVenta(Venta venta)throws Exception{
          String sql = "INSERT INTO detalleventa (idventa,idproducto,cantidadproducto,precioventa) VALUES("+venta.getIdVenta()+","+venta.getIdProducto()+","+venta.getCantidad()+","+venta.getPrecio()+")";
       
        try {
           this.conectar(false);
            this.ejecutarOrden(sql);
            this.cerrar(true);
        } catch (Exception e) {
            this.cerrar(false);
            throw e;
        }
    }
    
     public int ObtenerMaximoIdVentas() throws Exception {
        int idVenta=0;
        String sql = "SELECT MAX(idVenta) FROM ventas";
        this.conectar(false);
         try {
            con = getConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
               idVenta=rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CRUD_venta.class.getName()).log(Level.SEVERE, null, ex);
        }
        return idVenta;
    }
   
}
