
package Programas;

import Modelo.Proveedor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CRUD_proveedor  extends Conexion {

    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public List listar() throws Exception{
        List<Proveedor> lista = new ArrayList<>();
        String sql = "select * from proveedor";
        try {
            this.conectar(false);
            rs = this.ejecutarOrdenDatos(sql);
            while (rs.next()==true) {
                Proveedor dats = new Proveedor();
                dats.setId(rs.getInt("id"));
                dats.setNom(rs.getString("nom"));
                dats.setDir(rs.getString("dir"));
                dats.setTel(rs.getString("tel"));
                dats.setEmail(rs.getString("email"));
                lista.add(dats);

            }
            this.cerrar(true);
        } catch (Exception e) {
            throw e;
        } finally {
        }
        return lista;
    }

    public List buscarPorDesc(String bus) {
        Proveedor dats = null;
        List<Proveedor> lista = new ArrayList<>();
        String sql = "SELECT * FROM proveedor WHERE nom like ?";
        try {
            bus=bus+"%";
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, bus);
            rs = ps.executeQuery();
            while (rs.next()) {
                dats = new Proveedor();
                dats.setId(rs.getInt("id"));
                dats.setNom(rs.getString("nom"));
                dats.setDir(rs.getString("dir"));
                dats.setTel(rs.getString("tel"));
               dats.setEmail(rs.getString("email"));
               lista.add(dats);            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
 public void eliminarProveedor(Proveedor dat) throws Exception {
        String sql = "DELETE FROM proveedor"
                + " WHERE ID = " + dat.getId();
        try {
            this.conectar(false);
            this.ejecutarOrden(sql);
            this.cerrar(true);
        } catch (Exception e) {
            this.cerrar(false);
            throw e;
        }
    }
 public void registrarProveedor(Proveedor dat) throws Exception {
        String sql;
        sql = "INSERT INTO Proveedor (nom,dir,tel,email) VALUES ('"+dat.getNom()+"','"+dat.getDir()+"','"+dat.getTel()+"','"+dat.getEmail()+"')";
        try {
            this.conectar(false);
            this.ejecutarOrden(sql);
            this.cerrar(true);
        } catch (Exception e) {
            this.cerrar(false);
            throw e;
        }
    }
 
 public Proveedor leerProveedor(Proveedor dat) throws Exception {
        Proveedor dats = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM proveedor  WHERE id = " + dat.getId();

        try {            
            this.conectar(false);
            rs = this.ejecutarOrdenDatos(sql);
            if (rs.next() == true) {
                dats = new Proveedor();
                dats.setId(rs.getInt("id"));
                dats.setNom(rs.getString("nom"));
                dats.setDir(rs.getString("dir"));
                dats.setTel(rs.getString("tel"));
                dats.setEmail(rs.getString("email"));
                }
            this.cerrar(true);
        } catch (Exception e) {
            this.cerrar(false);
            throw e;
        } finally {
        }
        return dats;
    }
 
 public void actualizarProveedor(Proveedor dat) throws Exception {
    
     String sql = "UPDATE proveedor SET  nom='"+dat.getNom()+"', dir='"+dat.getDir()+"', tel='"+dat.getTel()+"', email='"+dat.getEmail()+"' where id ="+dat.getId()+" ";
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

