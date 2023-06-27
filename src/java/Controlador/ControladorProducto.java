package Controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Modelo.Producto;
import Modelo.cargo;
import Modelo.usuario;
import Programas.CRUD_productos;
import Programas.CRUD_usuario;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;

@WebServlet(name = "ControladorProducto", urlPatterns = {"/ControladorProducto"})
public class ControladorProducto extends HttpServlet {

    CRUD_productos dao = new CRUD_productos();
    Producto prod = new Producto();

    PrintWriter out;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
         String accion = request.getParameter("accion");
        try {
            if (accion != null) {
                switch (accion) {
                    case "listarProductos":
                        listarProducto(request, response);
                        break;
                    case "nuevo":
                        presentarFormulario(request, response);
                        break;
                    case "registrar":
                        registrarProductos(request, response);

                        break;
                    case "leerProducto":
                        presentarProducto(request, response);
                        break;
                    case "actualizarProducto":
                         actualizarProducto(request, response);
                        break;
                    case "eliminarProducto":
                        eliminarProducto(request, response);
                        break;
                    default:
                        response.sendRedirect("IngresoSistema.jsp");
                }
            } else if (request.getParameter("cambiar") != null) {
                //  cambiarEstado(request, response);
            } else {
                response.sendRedirect("IngresoSistema.jsp");
            }
        } catch (Exception e) {
            try {
                this.getServletConfig().getServletContext().getRequestDispatcher("/mensaje.jsp").forward(request, response);

            } catch (Exception ex) {
                System.out.println("Error" + e.getMessage());
            }
        }

    }

    private void listarProducto(HttpServletRequest request, HttpServletResponse response) {
        try {
            CRUD_productos dao = new CRUD_productos();
            List<usuario> prods = null;

            prods = dao.listar();
            request.setAttribute("Productos", prods);
            RequestDispatcher vista = request.getRequestDispatcher("/vistas/productos/productos.jsp");
            vista.forward(request, response);
        } catch (ServletException ex) {
            Logger.getLogger(ControladorProducto.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ControladorProducto.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ControladorProducto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void eliminarProducto(HttpServletRequest request, HttpServletResponse response) {
        CRUD_productos dao = new CRUD_productos();
        Producto usus = new Producto();
        if (request.getParameter("cod") != null) {
            usus.setId(Integer.parseInt(request.getParameter("cod")));
            try {
                dao.eliminarProducto(usus);
                response.sendRedirect("ControladorProducto?accion=listarProductos");
            } catch (Exception e) {
                request.setAttribute("msje", "No se pudo acceder a la base de datos" + e.getMessage());
            }
        } else {
            request.setAttribute("msje", "No se encontro Producto");
        }
    }

    private void presentarFormulario(HttpServletRequest request, HttpServletResponse response) {
        try {
            //this.cargarCargos(request);
            this.getServletConfig().getServletContext()
                    .getRequestDispatcher("/vistas/productos/nuevoProducto.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("msje", "No se pudo cargar la vista");
        }
    }

    private void registrarProductos(HttpServletRequest request, HttpServletResponse response) {
        CRUD_productos dao = new CRUD_productos();
        Producto dat = null;

        if (request.getParameter("txtdesc") != null && request.getParameter("cbomar") != null && request.getParameter("cboproce") != null && request.getParameter("txtcant") != null && request.getParameter("txtprec") != null && request.getParameter("txtstmax") != null && request.getParameter("txtstmin") != null) {
            {
                try {
                    dat = new Producto();
                    dat.setId(0);
                    dat.setDes(request.getParameter("txtdesc"));
                    dat.setMar(request.getParameter("cbomar"));
                    dat.setProc(request.getParameter("cboproce"));
                    dat.setCant(Integer.parseInt(request.getParameter("txtcant")));
                    dat.setPrec(Double.parseDouble(request.getParameter("txtprec")));
                    dat.setStmax(Integer.parseInt(request.getParameter("txtstmax")));
                    dat.setStmin(Integer.parseInt(request.getParameter("txtstmin")));
                    dat.setImg(request.getParameter(""));
                    dao.registrarProducto(dat);
                    response.sendRedirect("ControladorProducto?accion=listarProductos");
                } catch (Exception e) {
                    request.setAttribute("msje", "No se pudo registrar" + e.getMessage());
                    this.presentarFormulario(request, response);
                }
            }
        }

    }

    private void presentarProducto(HttpServletRequest request, HttpServletResponse response) {

        CRUD_productos dao;
        Producto dat;
        if (request.getParameter("cod") != null) {
            dat = new Producto();
            dat.setId(Integer.parseInt(request.getParameter("cod")));
            dao = new CRUD_productos();
            try {
                dat = dao.leerProducto(dat);
                if (dat != null) {
                    request.setAttribute("productos", dat);
                } else {
                    request.setAttribute("msje", "No se encontró datos");
                }
            } catch (Exception e) {
                request.setAttribute("msje", "No se pudo acceder a la base de datos" + e.getMessage());
            }
        } else {
            request.setAttribute("msje", "No se tiene el parámetro necesario");
        }
        try {
            this.getServletConfig().getServletContext().
                    getRequestDispatcher("/vistas/productos/actualilzarProducto.jsp"
                    ).forward(request, response);
        } catch (Exception e) {
            request.setAttribute("msje", "No se pudo realizar la operacion" + e.getMessage());
        }

    }
private void actualizarProducto(HttpServletRequest request, HttpServletResponse response) {
         CRUD_productos dao = new CRUD_productos();
        Producto dat = null;

        if (request.getParameter("txtdesc") != null && request.getParameter("cbomar") != null && request.getParameter("cboproce") != null && request.getParameter("txtcant") != null && request.getParameter("txtprec") != null && request.getParameter("txtstmax") != null && request.getParameter("txtstmin") != null) {
            {
                try {
                    dat = new Producto();
                    dat.setId(Integer.parseInt(request.getParameter("hCodigo")));
                    dat.setDes(request.getParameter("txtdesc"));
                    dat.setMar(request.getParameter("cbomar"));
                    dat.setProc(request.getParameter("cboproce"));
                    dat.setCant(Integer.parseInt(request.getParameter("txtcant")));
                    dat.setPrec(Double.parseDouble(request.getParameter("txtprec")));
                    dat.setStmax(Integer.parseInt(request.getParameter("txtstmax")));
                    dat.setStmin(Integer.parseInt(request.getParameter("txtstmin")));
                    dat.setImg(request.getParameter(""));
                    dao.actualizarProductos(dat);
                    response.sendRedirect("ControladorProducto?accion=listarProductos");
                } catch (Exception e) {
                    request.setAttribute("msje", "No se pudo registrar" + e.getMessage());
                    this.presentarFormulario(request, response);
                }
            }
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
        /*   String accion = request.getParameter("accion");
        out = response.getWriter();
        out.print(accion);
        switch (accion) {
            case "Listar":
                List<Producto> lista = dao.listar();
                 out.print(accion);
                request.setAttribute("lista",lista);
                request.getRequestDispatcher("vistas/index.jsp").forward(request, response);
                break;
                
            case "Buscar":
                String desc1 = request.getParameter("codigo");
                List<Producto> lista1 = dao.buscarPorDesc(desc1);
                request.setAttribute("producto", lista1);
                request.getRequestDispatcher("/vistas/productos/buscar.jsp").forward(request, response);
                break;
                
            default:
                request.getRequestDispatcher("ControladorProducto?accion=Listar").forward(request, response);
                
                break;

        }*/
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
