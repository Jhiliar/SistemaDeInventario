
package Modelo;
public class Producto {
 public int id;
 public String des;
 public String mar;
 public String proc;
 public int cant;
 public double prec;
 public int stmax;
 public int stmin;
 public String img;

    public Producto() {
    }

    public Producto(int id, String des, String mar, String proc, int cant, double prec, int stmax, int stmin, String img) {
        this.id = id;
        this.des = des;
        this.mar = mar;
        this.proc = proc;
        this.cant = cant;
        this.prec = prec;
        this.stmax = stmax;
        this.stmin = stmin;
        this.img = img;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public void setMar(String mar) {
        this.mar = mar;
    }

    public void setProc(String proc) {
        this.proc = proc;
    }

    public void setCant(int cant) {
        this.cant = cant;
    }

    public void setPrec(double prec) {
        this.prec = prec;
    }

    public void setStmax(int stmax) {
        this.stmax = stmax;
    }

    public void setStmin(int stmin) {
        this.stmin = stmin;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public String getDes() {
        return des;
    }

    public String getMar() {
        return mar;
    }

    public String getProc() {
        return proc;
    }

    public int getCant() {
        return cant;
    }

    public double getPrec() {
        return prec;
    }

    public int getStmax() {
        return stmax;
    }

    public int getStmin() {
        return stmin;
    }

    public String getImg() {
        return img;
    }
 
}
