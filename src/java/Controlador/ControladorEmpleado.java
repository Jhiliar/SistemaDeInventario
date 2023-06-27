/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Empleado;
import Modelo.usuario;
import Programas.CRUD_empleado;
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

@WebServlet(name = "ControladorEmpleado", urlPatterns = {"/ControladorEmpleado"})
public class ControladorEmpleado extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String accion = request.getParameter("accion");
        try {
            if (accion != null) {
                switch (accion) {
                    case "listarEmpleados":
                        listarEmpleado(request, response);
                        break;
                    case "nuevo":
                        presentarFormulario(request, response);
                        break;
                    case "registrar":
                        registrarEmpleados(request, response);

                        break;
                    case "leerEmpleado":
                        presentarEmpleado(request, response);
                        break;
                    case "actualizarEmpleado":
                        actualizarEmpleado(request, response);
                        break;
                    case "eliminarEmpleado":
                        eliminarEmpleado(request, response);
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

    private void listarEmpleado(HttpServletRequest request, HttpServletResponse response) {
        try {
            CRUD_empleado dao = new CRUD_empleado();
            List<usuario> prods = null;

            prods = dao.listar();
            request.setAttribute("Empleados", prods);
            RequestDispatcher vista = request.getRequestDispatcher("/vistas/Empleados/empleados.jsp");
            vista.forward(request, response);
        } catch (ServletException ex) {
            Logger.getLogger(ControladorEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ControladorEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ControladorEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void eliminarEmpleado(HttpServletRequest request, HttpServletResponse response) {
        CRUD_empleado dao = new CRUD_empleado();
        Empleado usus = new Empleado();
        if (request.getParameter("cod") != null) {
            usus.setId(Integer.parseInt(request.getParameter("cod")));
            try {
                dao.eliminarEmpleado(usus);
                response.sendRedirect("ControladorEmpleado?accion=listarEmpleados");
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
                    .getRequestDispatcher("/vistas/Empleados/nuevoEmpleado.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("msje", "No se pudo cargar la vista");
        }
    }

    private void registrarEmpleados(HttpServletRequest request, HttpServletResponse response) {
        CRUD_empleado dao = new CRUD_empleado();
        Empleado dat = null;

        if (request.getParameter("txtci") != null && request.getParameter("txtpat") != null && request.getParameter("txtmat") != null && request.getParameter("txtnom") != null && request.getParameter("txtdir") != null && request.getParameter("txttel") != null && request.getParameter("txtemail") != null && request.getParameter("txtcar") != null) {
            {
                try {
                    dat = new Empleado();
                    dat.setId(0);
                    dat.setCi(request.getParameter("txtci"));
                    dat.setPat(request.getParameter("txtpat"));
                    dat.setMat(request.getParameter("txtmat"));
                    dat.setNom(request.getParameter("txtnom"));
                    dat.setDir(request.getParameter("txtdir"));
                    dat.setTel(request.getParameter("txttel"));
                    dat.setEmail(request.getParameter("txtemail"));
                    dat.setIdcar(Integer.parseInt(request.getParameter("txtcar")));
                    dao.registrarEmpleado(dat);
                    response.sendRedirect("ControladorEmpleado?accion=listarEmpleados");
                } catch (Exception e) {
                    request.setAttribute("msje", "No se pudo registrar" + e.getMessage());
                    this.presentarFormulario(request, response);
                }
            }
        }

    }

    private void presentarEmpleado(HttpServletRequest request, HttpServletResponse response) {

        CRUD_empleado dao;
        Empleado dat;
        if (request.getParameter("cod") != null) {
            dat = new Empleado();
            dat.setId(Integer.parseInt(request.getParameter("cod")));
            dao = new CRUD_empleado();
            try {
                dat = dao.leerEmpleado(dat);
                if (dat != null) {
                    request.setAttribute("empleados", dat);
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
                    getRequestDispatcher("/vistas/Clientes/actualizaEmpleado.jsp"
                    ).forward(request, response);
        } catch (Exception e) {
            request.setAttribute("msje", "No se pudo realizar la operacion" + e.getMessage());
        }

    }

    private void actualizarEmpleado(HttpServletRequest request, HttpServletResponse response) {
        CRUD_empleado dao = new CRUD_empleado();
        Empleado dat = null;

        if (request.getParameter("txtci") != null && request.getParameter("txtpat") != null && request.getParameter("txtmat") != null && request.getParameter("txtnom") != null && request.getParameter("txtdir") != null && request.getParameter("txttel") != null && request.getParameter("txtemail") != null && request.getParameter("txtcar") != null) {
            {
                try {
                    dat = new Empleado();
                    dat.setId(Integer.parseInt(request.getParameter("hCodigo")));
                    dat.setCi(request.getParameter("txtci"));
                    dat.setPat(request.getParameter("txtpat"));
                    dat.setMat(request.getParameter("txtmat"));
                    dat.setNom(request.getParameter("txtnom"));
                    dat.setDir(request.getParameter("txtdir"));
                    dat.setTel(request.getParameter("txttel"));
                    dat.setEmail(request.getParameter("txtemail"));
                    dat.setIdcar(Integer.parseInt(request.getParameter("txtcar")));
                    dao.actualizarEmpleados(dat);
                    response.sendRedirect("ControladorEmpleado?accion=listarEmpleados");
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
