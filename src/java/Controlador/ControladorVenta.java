
package Controlador;

import Modelo.Cliente;
import Modelo.Producto;
import Modelo.Venta;
import Modelo.usuario;
import Programas.CRUD_clientes;
import Programas.CRUD_productos;
import Programas.CRUD_usuario;
import Programas.CRUD_venta;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
//import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ControladorVenta", urlPatterns = {"/ControladorVenta"})
public class ControladorVenta extends HttpServlet {

    int factura;
    usuario usuario = new usuario();
    CRUD_usuario usuarioDAO = new CRUD_usuario();
    int idUsuario;
    int idProducto;
    Producto producto = new Producto();
    CRUD_productos productoDAO = new CRUD_productos();
    Venta venta = new Venta();
    int item, codProducto, cantidad;
    double precio;
    String descripcion;
    double subtotal, totalapagar = 0;
    List<Venta> listaVentas = new ArrayList();
    CRUD_venta ventaDAO = new CRUD_venta();
    int numfac = 0;
    NumberFormat formatoNumero1;
    String total1;
    String accion;
    CRUD_clientes cclie = new CRUD_clientes();
    Cliente dclie = new Cliente();
    String accesa = "";
    String partes[];
    String descrip, pr;
    int idp;
    String sclie, partes1[], prc;
  PrintWriter out;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        accion = request.getParameter("accion");
        RequestDispatcher vista;
        try {
            switch (accion) {
                case "Ventas":
                    factura = ventaDAO.ObtenerNumeroDeFactura();
                    if (factura == 0) {
                        numfac = 1;
                    } else {
                        numfac = factura + 1;
                    }
                    request.setAttribute("numerofactura", numfac);
                    accesa = "/vistas/Ventas/ventas1.jsp";
                    // vista = request.getRequestDispatcher(accesa);
                    // vista.forward(request, response);
                    break;

                case "BuscarCliente":

                    dclie = new Cliente();
                    sclie = request.getParameter("clientes1");
                    partes1 = sclie.split("-");
                    prc = partes1[0].trim();
                    int documentoCliente = Integer.parseInt(prc);
                    dclie = cclie.BuscarCliente(documentoCliente);
                    request.setAttribute("cliente", dclie);
                    accesa = "/vistas/Ventas/ventas1.jsp";
                    break;
                case "BuscarProducto":
                    descrip = request.getParameter("productos1");
                    partes = descrip.split("-");
                    pr = partes[0].trim();
                    request.setAttribute("pr", pr);
                    idp = Integer.parseInt(pr);
                    //int codigoProducto = Integer.parseInt(request.getParameter("codigoproducto"));
                    int codigoProducto = idp;
                    producto = productoDAO.buscarIdProd(codigoProducto);
                    // producto = productoDAO.buscarIdProd(idp);
                    request.setAttribute("productoseleccionado", producto);
                    request.setAttribute("cliente", dclie);
                    for (int i = 0; i < listaVentas.size(); i++) {
                        totalapagar += listaVentas.get(i).getSubtotal();
                    }
                    formatoNumero1 = NumberFormat.getNumberInstance();
                    total1 = formatoNumero1.format(totalapagar);
                    request.setAttribute("totalapagar", total1);
                    request.setAttribute("listaventas", listaVentas);
                    accesa = "/vistas/Ventas/ventas1.jsp";
                    break;

                case "AgregarProducto":
                    request.setAttribute("numerofactura", numfac);
                    totalapagar = 0;
                    venta = new Venta();
                    item++;
                    codProducto = Integer.parseInt(request.getParameter("codigoproducto"));
                    descripcion = request.getParameter("nombreproducto");
                    precio = Double.parseDouble(request.getParameter("precioproducto"));
                    cantidad = Integer.parseInt(request.getParameter("cantidadproducto"));
                    subtotal = precio * cantidad;
                    venta.setItem(item);
                    venta.setDescripcionProducto(descripcion);
                    venta.setCantidad(cantidad);
                    venta.setPrecio(precio);
                    venta.setSubtotal(subtotal);
                    venta.setIdProducto(codProducto);
                    listaVentas.add(venta);
                    //  System.err.println("error venta");
                    request.setAttribute("listaventas", listaVentas);
                    for (int i = 0; i < listaVentas.size(); i++) {
                        totalapagar += listaVentas.get(i).getSubtotal();
                    }
                    formatoNumero1 = NumberFormat.getNumberInstance();
                    total1 = formatoNumero1.format(totalapagar);
                    request.setAttribute("totalapagar", total1);
                    accesa = "/vistas/Ventas/ventas1.jsp";
                    break;
                case "NuevaVenta":
                    accesa = ("/vistas/index.jsp");
                    break;
                case "GrabarVenta":
                   out=response.getWriter();
                    // accesa = ("/vistas/index.jsp");
                    String dclies = request.getParameter("clientes1");
                     
                  
                  String partirclie[]=dclies.split("-");
                    int dclie1=Integer.parseInt(partirclie[0]);
                
                   int nfac=Integer.parseInt(request.getParameter("txtnumerofactura"));
                   // request.setAttribute("totalapagar", nfac);
                    venta = new Venta();
                    venta.setNumeroComprobante(nfac);
                   // request.setAttribute("totalapagar",dclie.getId());
                    venta.setIdCliente(dclie.getId());
                    venta.setIdEmpleado(1);
                    String fec="2023-10-10";
                    venta.setFecha(fec);
                    request.setAttribute("totalapagar",totalapagar);
                    venta.setMonto(totalapagar);
                    venta.setEstado("1");
                    venta.setIdEmpleado(1);
                    venta.setIdCliente(dclie.getId());
                    ventaDAO.RegistrarVenta(venta);
                    
                    // int idv = ventaDAO.ObtenerMaximoIdVentas();
                     for (int i = 0; i < listaVentas.size(); i++) {
                        venta = new Venta();
                        venta.setIdVenta(nfac);
                        venta.setIdProducto(listaVentas.get(i).getIdProducto());
                        venta.setCantidad(listaVentas.get(i).getCantidad());
                        venta.setPrecio(listaVentas.get(i).getPrecio());
                        ventaDAO.GuardarDetalleVenta(venta);
                        productoDAO.reduceStock(listaVentas.get(i).getIdProducto(),listaVentas.get(i).getCantidad());
                    }
                    listaVentas = new ArrayList();
                    accesa = "/vistas/Ventas/ventas1.jsp";
                    break;

                default:
                    factura = ventaDAO.ObtenerNumeroDeFactura();
                    if (factura == 0) {
                        numfac = 1;
                    } else {
                        numfac = factura + 1;
                    }
                    request.setAttribute("numerofactura", numfac);
                    accesa = ("/vistas/Ventas/ventas1.jsp");

            }
            this.getServletConfig().getServletContext().getRequestDispatcher(accesa).forward(request, response);

        } catch (Exception ex) {
            Logger.getLogger(ControladorVenta.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        /* accion = request.getParameter("accion");
        RequestDispatcher vista;
     
            switch (accion) {
                case "Ventas":
                     factura = ventaDAO.ObtenerNumeroDeFactura();
                    if (factura == 0) {
                        numfac = 1;
                    } else {
                        numfac = factura + 1;
                    }
                    request.setAttribute("numerofactura", numfac);
                    accesa=("/vistas/Ventas/ventas1.jsp");
                    break;
                case "GenerarVenta" :  
                    accesa=("/vistas/index.jsp");
                    break;
            }   
       
          this.getServletConfig().getServletContext().getRequestDispatcher(accesa).forward(request, response);
         */
        processRequest(request, response);

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
