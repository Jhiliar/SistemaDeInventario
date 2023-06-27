package Programas;

import Modelo.Empleado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CRUD_empleado extends Conexion {

    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public List listar() throws Exception{
        List<Empleado> lista = new ArrayList<>();
        String sql = "select * from empleado";
        try {
            this.conectar(false);
            rs = this.ejecutarOrdenDatos(sql);
            while (rs.next()==true) {
                Empleado dats = new Empleado();
                dats.setId(rs.getInt("id"));
                dats.setCi(rs.getString("ci"));
                dats.setPat(rs.getString("pat"));
                dats.setMat(rs.getString("mat"));
                dats.setNom(rs.getString("nom"));
                dats.setDir(rs.getString("dir"));
                dats.setTel(rs.getString("tel"));
                dats.setEmail(rs.getString("email"));
                dats.setIdcar(rs.getInt("idcargo"));
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
        Empleado dats = null;
        List<Empleado> lista = new ArrayList<>();
        String sql = "SELECT * FROM empleado WHERE pat like ?";
        try {
            bus=bus+"%";
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, bus);
            rs = ps.executeQuery();
            while (rs.next()) {
                dats = new Empleado();
                dats.setId(rs.getInt("id"));
                dats.setCi(rs.getString("ci"));
                dats.setPat(rs.getString("pat"));
                dats.setMat(rs.getString("mat"));
                dats.setNom(rs.getString("nom"));
                dats.setDir(rs.getString("dir"));
                dats.setTel(rs.getString("tel"));
               dats.setEmail(rs.getString("email"));
                dats.setIdcar(rs.getInt("idcargo"));
               lista.add(dats);            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
 public void eliminarEmpleado(Empleado dat) throws Exception {
        String sql = "DELETE FROM empleado"
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
 public void registrarEmpleado(Empleado dat) throws Exception {
        String sql;
        sql = "INSERT INTO Empleado (ci,pat,mat,nom,dir,tel,email,idcargo) VALUES ('" + dat.getCi() + "', '" + dat.getPat() + "', '"+ dat.getMat() + "', '"+dat.getNom()+"','"+dat.getDir()+"','"+dat.getTel()+"','"+dat.getEmail()+"',"+dat.getIdcar()+")";
        try {
            this.conectar(false);
            this.ejecutarOrden(sql);
            this.cerrar(true);
        } catch (Exception e) {
            this.cerrar(false);
            throw e;
        }
    }
 
 public Empleado leerEmpleado(Empleado dat) throws Exception {
        Empleado dats = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM empleado  WHERE id = " + dat.getId();

        try {            
            this.conectar(false);
            rs = this.ejecutarOrdenDatos(sql);
            if (rs.next() == true) {
                dats = new Empleado();
                dats.setId(rs.getInt("id"));
                dats.setCi(rs.getString("ci"));
                dats.setPat(rs.getString("pat"));
                dats.setMat(rs.getString("mat"));
                dats.setNom(rs.getString("nom"));
                dats.setDir(rs.getString("dir"));
                dats.setTel(rs.getString("tel"));
                dats.setEmail(rs.getString("email"));
                dats.setIdcar(rs.getInt("idcargo"));
            }
            this.cerrar(true);
        } catch (Exception e) {
            this.cerrar(false);
            throw e;
        } finally {
        }
        return dats;
    }
 
 public void actualizarEmpleados(Empleado dat) throws Exception {
    
     String sql = "UPDATE empleado SET ci='"+dat.getCi()+"', pat='"+dat.getPat()+"', mat='"+dat.getMat()+"', nom='"+dat.getNom()+"', dir='"+dat.getDir()+"', tel='"+dat.getTel()+"', email='"+dat.getEmail()+"', idcargo="+dat.getIdcar()+" where id ="+dat.getId()+" ";
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

