
package Modelo;

import java.util.logging.Logger;

public class Empleado {
    public int id;
    public String ci,pat,mat,nom,dir,tel,email;
    public int idcar;

    public Empleado() {
    }

    public Empleado(int id, String ci, String pat, String mat, String nom, String dir, String tel, String email, int idcar) {
        this.id = id;
        this.ci = ci;
        this.pat = pat;
        this.mat = mat;
        this.nom = nom;
        this.dir = dir;
        this.tel = tel;
        this.email = email;
        this.idcar = idcar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCi() {
        return ci;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }

    public String getPat() {
        return pat;
    }

    public void setPat(String pat) {
        this.pat = pat;
    }

    public String getMat() {
        return mat;
    }

    public void setMat(String mat) {
        this.mat = mat;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

     public int getIdcar() {
        return idcar;
    }

    public void setIdcar(int idcar) {
        this.idcar = idcar;
    }
    
    
}
