
package Controlador;

import Modelo.Cliente;
import Modelo.Empleado;
import Modelo.usuario;
import Programas.CRUD_clientes;
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

@WebServlet(name = "ControladorCliente", urlPatterns = {"/ControladorCliente"})
public class ControladorCliente extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
         String accion = request.getParameter("accion");
        try {
            if (accion != null) {
                switch (accion) {
                    case "listarClientes":
                        listarClientes(request, response);
                        break;
                    case "nuevo":
                        presentarFormulario(request, response);
                        break;
                    case "registrar":
                        registrarClientes(request, response);
                        break;
                    case "leerCliente":
                        presentarClientes(request, response);
                        break;
                    case "actualizarCliente":
                         actualizarClientes(request, response);
                        break;
                    case "eliminarCliente":
                        eliminarClientes(request, response);
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

    private void listarClientes(HttpServletRequest request, HttpServletResponse response) {
        try {
            CRUD_clientes dao = new CRUD_clientes();
            List<Cliente> dats = null;

            dats = dao.listar();
            request.setAttribute("clientes", dats);
            RequestDispatcher vista = request.getRequestDispatcher("/vistas/Clientes/clientes.jsp");
            vista.forward(request, response);
        } catch (ServletException ex) {
            Logger.getLogger(ControladorEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ControladorEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ControladorEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void eliminarClientes(HttpServletRequest request, HttpServletResponse response) {
        CRUD_clientes dao = new CRUD_clientes();
         Cliente usus = new Cliente();
        if (request.getParameter("cod") != null) {
            usus.setId(Integer.parseInt(request.getParameter("cod")));
            try {
                dao.eliminarCliente(usus);
                response.sendRedirect("ControladorCliente?accion=listarClientes");
            } catch (Exception e) {
                request.setAttribute("msje", "No se pudo acceder a la base de datos" + e.getMessage());
            }
        } else {
            request.setAttribute("msje", "No se encontro Empleado");
        }
    }

    private void presentarFormulario(HttpServletRequest request, HttpServletResponse response) {
        try {
            //this.cargarCargos(request);
            this.getServletConfig().getServletContext()
                    .getRequestDispatcher("/vistas/Clientes/nuevoCliente.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("msje", "No se pudo cargar la vista");
        }
    }

    private void registrarClientes(HttpServletRequest request, HttpServletResponse response) {
        CRUD_clientes dao = new CRUD_clientes();
        Cliente dat = null;
        if (request.getParameter("txtnom") != null && request.getParameter("txtnit") != null && request.getParameter("txttel") != null && request.getParameter("txtemail") != null) {
            {
                try {
                    dat = new Cliente();
                    dat.setId(0);
                    dat.setNom(request.getParameter("txtnom"));
                    dat.setNit(request.getParameter("txtnit"));
                    dat.setTel(request.getParameter("txttel"));
                    dat.setEmail(request.getParameter("txtemail"));
                    dao.registrarCliente(dat);
                    response.sendRedirect("ControladorCliente?accion=listarClientes");
                } catch (Exception e) {
                    request.setAttribute("msje", "No se pudo registrar" + e.getMessage());
                    this.presentarFormulario(request, response);
                }
            }
        }

    }

    private void presentarClientes(HttpServletRequest request, HttpServletResponse response) {

        CRUD_clientes dao;
        Cliente dat;
        if (request.getParameter("cod") != null) {
            dat = new Cliente();
            dat.setId(Integer.parseInt(request.getParameter("cod")));
            dao = new CRUD_clientes();
            try {
                dat = dao.leerCliente(dat);
                if (dat != null) {
                    request.setAttribute("clientes", dat);
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
                    getRequestDispatcher("/vistas/Clientes/actualizaCliente.jsp"
                    ).forward(request, response);
        } catch (Exception e) {
            request.setAttribute("msje", "No se pudo realizar la operacion" + e.getMessage());
        }

    }
private void actualizarClientes(HttpServletRequest request, HttpServletResponse response) {
         CRUD_clientes dao = new CRUD_clientes();
         Cliente dat = null;

        if (request.getParameter("txtnom") != null && request.getParameter("txtnit") != null && request.getParameter("txttel") != null && request.getParameter("txtemail") != null) {
            {
                try {
                    dat = new Cliente();
                    dat.setId(Integer.parseInt(request.getParameter("hCodigo")));
                    dat.setNom(request.getParameter("txtnom"));
                    dat.setNit(request.getParameter("txtnit"));
                    dat.setTel(request.getParameter("txttel"));
                    dat.setEmail(request.getParameter("txtemail"));
                    dao.actualizarClientes(dat);
                    response.sendRedirect("ControladorCliente?accion=listarClientes");
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
