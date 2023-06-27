package Controlador;

import Modelo.Proveedor;
import Modelo.usuario;
import Programas.CRUD_proveedor;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ControladorProveedor", urlPatterns = {"/ControladorProveedor"})
public class ControladorProveedor extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String accion = request.getParameter("accion");
        try {
            if (accion != null) {
                switch (accion) {
                    case "listarProveedor":
                        listarProveedor(request, response);
                        break;
                    case "nuevo":
                        presentarFormulario(request, response);
                        break;
                    case "registrar":
                        registrarProveedor(request, response);
                        break;
                    case "leerProveedor":
                        presentarProveedor(request, response);
                        break;
                    case "actualizarProveedor":
                        actualizarProveedor(request, response);
                        break;
                    case "eliminarProveedor":
                        eliminarProveedor(request, response);
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

    private void listarProveedor(HttpServletRequest request, HttpServletResponse response) {
        try {
            CRUD_proveedor dao = new CRUD_proveedor();
            List<usuario> dats = null;
            dats = dao.listar();
            request.setAttribute("proveedor", dats);
            RequestDispatcher vista = request.getRequestDispatcher("/vistas/Proveedor/proveedor.jsp");
            vista.forward(request, response);
        } catch (ServletException ex) {
            Logger.getLogger(ControladorProveedor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ControladorProveedor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ControladorProveedor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void eliminarProveedor(HttpServletRequest request, HttpServletResponse response) {
        CRUD_proveedor dao = new CRUD_proveedor();
        Proveedor usus = new Proveedor();
        if (request.getParameter("cod") != null) {
            usus.setId(Integer.parseInt(request.getParameter("cod")));
            try {
                dao.eliminarProveedor(usus);
                response.sendRedirect("ControladorProveedor?accion=listarProveedor");
            } catch (Exception e) {
                request.setAttribute("msje", "No se pudo acceder a la base de datos" + e.getMessage());
            }
        } else {
            request.setAttribute("msje", "No se encontro Proveedor");
        }
    }

    private void presentarFormulario(HttpServletRequest request, HttpServletResponse response) {
        try {
            //this.cargarCargos(request);
            this.getServletConfig().getServletContext()
                    .getRequestDispatcher("/vistas/Proveedor/nuevoProveedor.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("msje", "No se pudo cargar la vista");
        }
    }

    private void registrarProveedor(HttpServletRequest request, HttpServletResponse response) {
        CRUD_proveedor dao = new CRUD_proveedor();
        Proveedor dat = null;

        if (request.getParameter("txtnom") != null && request.getParameter("txtdir") != null && request.getParameter("txttel") != null && request.getParameter("txtemail") != null) {
            {
                try {
                    dat = new Proveedor();
                    dat.setId(0);
                    dat.setNom(request.getParameter("txtnom"));
                    dat.setDir(request.getParameter("txtdir"));
                    dat.setTel(request.getParameter("txttel"));
                    dat.setEmail(request.getParameter("txtemail"));
                    dao.registrarProveedor(dat);
                    response.sendRedirect("ControladorProveedor?accion=listarProveedor");
                } catch (Exception e) {
                    request.setAttribute("msje", "No se pudo registrar" + e.getMessage());
                    this.presentarFormulario(request, response);
                }
            }
        }

    }

    private void presentarProveedor(HttpServletRequest request, HttpServletResponse response) {

        CRUD_proveedor dao;
        Proveedor dat;
        if (request.getParameter("cod") != null) {
            dat = new Proveedor();
            dat.setId(Integer.parseInt(request.getParameter("cod")));
            dao = new CRUD_proveedor();
            try {
                dat = dao.leerProveedor(dat);
                if (dat != null) {
                    request.setAttribute("proveedor", dat);
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
                    getRequestDispatcher("/vistas/Proveedor/actualizaProveedor.jsp"
                    ).forward(request, response);
        } catch (Exception e) {
            request.setAttribute("msje", "No se pudo realizar la operacion" + e.getMessage());
        }

    }

    private void actualizarProveedor(HttpServletRequest request, HttpServletResponse response) {
        CRUD_proveedor dao = new CRUD_proveedor();
        Proveedor dat = null;
        if (request.getParameter("txtnom") != null && request.getParameter("txtdir") != null && request.getParameter("txttel") != null && request.getParameter("txtemail") != null) {
            {
                try {
                    dat = new Proveedor();
                    dat.setId(Integer.parseInt(request.getParameter("hCodigo")));
                    dat.setNom(request.getParameter("txtnom"));
                    dat.setDir(request.getParameter("txtdir"));
                    dat.setTel(request.getParameter("txttel"));
                    dat.setEmail(request.getParameter("txtemail"));
                    dao.actualizarProveedor(dat);
                    response.sendRedirect("ControladorProveedor?accion=listarProveedor");
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
