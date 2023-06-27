
package Programas;

import Modelo.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class CRUD_clientes extends Conexion {

    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public List listar() throws Exception{
        List<Cliente> lista = new ArrayList<>();
        String sql = "select * from cliente";
        try {
            this.conectar(false);
            rs = this.ejecutarOrdenDatos(sql);
            while (rs.next()==true) {
                Cliente dats = new Cliente();
                dats.setId(rs.getInt("id"));
                dats.setNom(rs.getString("nombre"));
                dats.setNit(rs.getString("nit"));
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

    public Cliente BuscarCliente(int id) throws Exception{
        Cliente dats = null;
        String sql = "SELECT * FROM Cliente WHERE id = ?";
        this.conectar(false);
        con = getConexion(); 
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while(rs.next()){
                dats = new Cliente();
                dats.setId(rs.getInt("id"));
                dats.setNom(rs.getString("nombre"));
                dats.setNit(rs.getString("nit"));
                dats.setTel(rs.getString("tel"));
                dats.setEmail(rs.getString("email"));
              }
        } catch (Exception e) {
        }
        return dats;
    }
    
    
    public List buscarPorDesc(String bus) {
        Cliente dats = null;
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT * FROM cliente WHERE nombre like ?";
        try {
            bus=bus+"%";
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, bus);
            rs = ps.executeQuery();
            while (rs.next()) {
                dats = new Cliente();
                dats.setId(rs.getInt("id"));
                dats.setNom(rs.getString("nombre"));
                dats.setNit(rs.getString("nit"));
                dats.setTel(rs.getString("tel"));
               dats.setEmail(rs.getString("email"));
              
               lista.add(dats);            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
 public void eliminarCliente(Cliente dat) throws Exception {
        String sql = "DELETE FROM cliente"
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
 public void registrarCliente(Cliente dat) throws Exception {
        String sql;
        sql = "INSERT INTO Cliente (nombre,nit,tel,email) VALUES ('"+dat.getNom()+"','"+dat.getNit()+"','"+dat.getTel()+"','"+dat.getEmail()+"')";
        try {
            this.conectar(false);
            this.ejecutarOrden(sql);
            this.cerrar(true);
        } catch (Exception e) {
            this.cerrar(false);
            throw e;
        }
    }
 
 public Cliente leerCliente(Cliente dat) throws Exception {
        Cliente dats = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM cliente  WHERE id = " + dat.getId();

        try {            
            this.conectar(false);
            rs = this.ejecutarOrdenDatos(sql);
            if (rs.next() == true) {
                dats = new Cliente();
                dats.setId(rs.getInt("id"));
                dats.setNom(rs.getString("nombre"));
                dats.setNit(rs.getString("nit"));
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
 
 public void actualizarClientes(Cliente dat) throws Exception {
    
     String sql = "UPDATE cliente SET nombre='"+dat.getNom()+"', nit='"+dat.getNit()+"', tel='"+dat.getTel()+"', email='"+dat.getEmail()+"' where id ="+dat.getId()+" ";
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

