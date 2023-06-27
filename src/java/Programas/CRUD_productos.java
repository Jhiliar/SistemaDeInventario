package Programas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import Modelo.Producto;
import Modelo.cargo;
import Modelo.usuario;

public class CRUD_productos extends Conexion {

    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public List listar() throws Exception{
        List<Producto> lista = new ArrayList<>();
        String sql = "select * from productos";
        try {
            this.conectar(false);
            rs = this.ejecutarOrdenDatos(sql);
           
            while (rs.next()==true) {
                Producto prod = new Producto();
                prod.setId(rs.getInt("idprod"));
                prod.setDes(rs.getString("descrip"));
                prod.setMar(rs.getString("mar"));
                prod.setProc(rs.getString("proce"));
                prod.setCant(rs.getInt("cant"));
                prod.setPrec(rs.getDouble("prec"));
                prod.setStmax(rs.getInt("stockmax"));
                prod.setImg(rs.getString("ruta"));
                lista.add(prod);

            }
            this.cerrar(true);
        } catch (Exception e) {
            throw e;
        } finally {
        }
        return lista;
    }

    public List buscarPorDesc(String desc1) {
        Producto prod = null;
        List<Producto> lista = new ArrayList<>();
        String sql = "SELECT * FROM productos WHERE descrip like ?";
        try {
            desc1=desc1+"%";
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, desc1);
            rs = ps.executeQuery();
            if (rs.next()) {
                prod = new Producto();
                prod.setId(rs.getInt("idprod"));
                prod.setDes(rs.getString("descrip"));
                prod.setMar(rs.getString("mar"));
                prod.setProc(rs.getString("proce"));
                prod.setCant(rs.getInt("cant"));
                prod.setPrec(rs.getDouble("prec"));
                prod.setImg(rs.getString("ruta"));
                lista.add(prod);            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
    
    public Producto buscarIdProd(int id) {
        Producto prod = null;
        String sql = "SELECT * FROM productos WHERE idprod = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                prod = new Producto();
                prod.setId(rs.getInt("idprod"));
                prod.setDes(rs.getString("descrip"));
                prod.setMar(rs.getString("mar"));
                prod.setProc(rs.getString("proce"));
                prod.setCant(rs.getInt("cant"));
                prod.setPrec(rs.getDouble("prec"));
                prod.setImg(rs.getString("ruta"));
                            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return prod;
    }
    
 public void eliminarProducto(Producto dat) throws Exception {
        String sql = "DELETE FROM Productos"
                + " WHERE IDPROD = " + dat.getId();
        try {
            this.conectar(false);
            this.ejecutarOrden(sql);
            this.cerrar(true);
        } catch (Exception e) {
            this.cerrar(false);
            throw e;
        }
    }
 public void registrarProducto(Producto dat) throws Exception {
        String sql;
        sql = "INSERT INTO Productos (descrip, mar, proce, cant, prec, stockmax, stockmin, ruta) VALUES ('" + dat.getDes() + "', '" + dat.getMar() + "', '"+ dat.getProc() + "', "+dat.getCant()+","+dat.getPrec()+","+dat.getStmax()+","+dat.getStmin()+",'"+dat.getImg()+"')";
        try {
            this.conectar(false);
            this.ejecutarOrden(sql);
            this.cerrar(true);
        } catch (Exception e) {
            this.cerrar(false);
            throw e;
        }
    }
 
 public Producto leerProducto(Producto dat) throws Exception {
        Producto prod = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM productos  WHERE idprod = " + dat.getId();

        try {
            this.conectar(false);
            rs = this.ejecutarOrdenDatos(sql);
            if (rs.next() == true) {
                prod = new Producto();
                prod.setId(rs.getInt("idprod"));
                prod.setDes(rs.getString("descrip"));
                prod.setMar(rs.getString("mar"));
                prod.setProc(rs.getString("proce"));
                prod.setCant(rs.getInt("cant"));
                prod.setPrec(rs.getDouble("prec"));
                prod.setStmax(rs.getInt("stockmax"));
                prod.setImg(rs.getString("ruta"));
                
            }
            this.cerrar(true);
        } catch (Exception e) {
            this.cerrar(false);
            throw e;
        } finally {
        }
        return prod;
    }
 
 public void actualizarProductos(Producto dat) throws Exception {
    
     String sql = "UPDATE productos SET descrip='"+dat.getDes()+"', mar='"+dat.getMar()+"', proce='"+dat.getProc()+"', cant="+dat.getCant()+", prec="+dat.getPrec()+", stockmax="+dat.getStmax()+", stockmin="+dat.getStmin()+", ruta='"+dat.getImg()+"' where idprod ="+dat.getId()+" ";
           try {
            this.conectar(false);
            this.ejecutarOrden(sql);
            this.cerrar(true);
        } catch (Exception e) {
            this.cerrar(false);
            throw e;
        }
    }
 public void reduceStock(int id,int cantv)throws Exception
 {
   String sql = "UPDATE productos SET  cant= cant - "+cantv+" where idprod ="+id+" ";
           try {
            this.conectar(false);
            this.ejecutarOrden(sql);
            this.cerrar(true);
        } catch (Exception e) {
            this.cerrar(false);
            throw e;
        }  
 }     
        
}
