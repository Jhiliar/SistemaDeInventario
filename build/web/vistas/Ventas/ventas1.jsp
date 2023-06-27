<%@page import="Programas.CRUD_clientes"%>
<%@page import="Modelo.Cliente"%>
<%@page import="java.util.List"%>
<%@page import="Programas.CRUD_productos"%>
<%@page import="Modelo.Producto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    if (session.getAttribute("usuario") != null) {
%>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
        <title>Sistema de Inventarios| Inicio</title>
        <!-- Tell the browser to be responsive to screen width -->
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
        <link rel="stylesheet" href="bower_components/bootstrap/dist/css/bootstrap.min.css">
        <!-- Font Awesome -->
        <link rel="stylesheet" href="bower_components/font-awesome/css/font-awesome.min.css">
        <!-- Ionicons -->
        <link rel="stylesheet" href="bower_components/Ionicons/css/ionicons.min.css">
        <!-- Theme style -->
        <link href="dist/css/AdminLTE.min.css" rel="stylesheet" type="text/css"/>

        <!-- AdminLTE Skins. We have chosen the skin-blue for this starter
              page. However, you can choose any other skin. Make sure you
              apply the skin class to the body tag so the changes take effect. -->
        <link rel="stylesheet" href="dist/css/skins/skin-blue.min.css">
        <link rel="stylesheet"
              href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">
        <style>

            @media print{
                .seccion1, .btn, .accion{
                    display: none;
                }
            }

        </style>
    </head>

    <body class="hold-transition skin-blue sidebar-mini">
        <div class="wrapper">

            <!-- Main Header -->
            <header class="main-header">
                <a href="#" class="logo">
                    <!-- mini logo for sidebar mini 50x50 pixels -->
                    <span class="logo-mini"><b>A</b>Dr</span>
                    <!-- logo for regular state and mobile devices -->
                    <span class="logo-lg"><b>Sistema de Inventario</b>La estrella</span>
                </a>

                <!-- Header Navbar -->
                <nav class="navbar navbar-static-top" role="navigation">
                    <!-- Sidebar toggle button-->
                    <a href="#" class="sidebar-toggle" data-toggle="push-menu" role="button">
                        <span class="sr-only">Toggle navigation</span>
                    </a>
                    <!-- Navbar Right Menu -->
                    <div class="navbar-custom-menu">
                        <ul class="nav navbar-nav">
                            <!-- User Account Menu -->
                            <li class="dropdown user user-menu">
                                <!-- Menu Toggle Button -->
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                    <!-- The user image in the navbar-->
                                    <img src="dist/img/user2-160x160.jpg" class="user-image" alt="User Image">
                                    <!-- hidden-xs hides the username on small devices so only the image appears. -->
                                    <span class="hidden-xs"> ${usuario.nombreUsuario}</span>
                                </a>
                                <ul class="dropdown-menu">
                                    <!-- The user image in the menu -->
                                    <li class="user-header">
                                        <img src="dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">

                                        <p>                    
                                            Bienvenido - ${usuario.nombreUsuario}
                                            <small>Usted es,${usuario.cargo.nombreCargo} </small>
                                        </p>
                                    </li>
                                    <!-- Menu Footer-->
                                    <li class="user-footer">
                                        <div class="pull-right">
                                            <a href="ControladorUsuario?accion=cerrar" class="btn btn-default btn-flat">Cerrar Session</a>
                                        </div>
                                    </li>
                                </ul>
                            </li>
                        </ul>
                    </div>
                </nav>
            </header>
            <!-- Left side column. contains the logo and sidebar -->
            <aside class="main-sidebar">

                <!-- sidebar: style can be found in sidebar.less -->
                <section class="sidebar">

                    <!-- Sidebar user panel (optional) -->
                    <div class="user-panel">
                        <div class="pull-left image">
                            <img src="dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">
                        </div>
                        <div class="pull-left info">
                            <p>Bienvenido,  ${usuario.nombreUsuario} </p>
                            <!-- Status -->
                            <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
                        </div>
                    </div>

                    <!-- search form (Optional) -->

                    <!-- /.search form -->

                    <!-- Sidebar Menu -->
                    <ul class="sidebar-menu" data-widget="tree">
                        <li class="header">INICIO</li>
                        <!-- Optionally, you can add icons to the links -->
                        <li><a href="#"><i class="fa fa-link"></i> <span>Panel Administrativo</span></a></li>
                        <li class="treeview active">
                            <a href="#"><i class="glyphicon glyphicon-th-large"></i> <span>Opciones Administrador</span>
                                <span class="pull-right-container">
                                    <i class="fa fa-angle-left pull-right"></i>
                                </span>
                            </a>
                            <ul class="treeview-menu">
                                <li><a href="ControladorUsuario?accion=listarUsuarios"><i class="fa fa-address-card"></i>Usuarios</a></li>
                                <li><a href="ControladorProducto?accion=listarProductos"><i class="fa fa-address-card"></i>Productos</a></li>
                                <li><a href="ControladorEmpleado?accion=listarEmpleados"><i class="fa fa-address-card"></i>Empleado</a></li>
                                <li><a href="ControladorProveedor?accion=listarProveedor"><i class="fa fa-address-card"></i>Provedores</a></li>
                                <li><a href="ControladorCliente?accion=listarClientes"><i class="fa fa-address-card"></i>Clientes</a></li>
                                <li><a href="ControladorVenta?accion=Ventas"><i class="fa fa-address-card"></i>Ventas</a></li>
                                <li><a href="ControladorCompras?accion=listarCompras"><i class="fa fa-address-card"></i>Compras</a></li>
                            </ul>
                    </ul>
                    </li>


                    </ul>
                    <!-- /.sidebar-menu -->
                </section>
                <!-- /.sidebar -->
            </aside>

            <!-- Content Wrapper. Contains page content -->
            <div class="content-wrapper">
                <section class="content-header">
                    <div class="row">
                        <div class="col-xs-12 col-md-3">
                        </div>
                        <div class="col-md-3 hidden-xs"></div>
                        <div class="col-xs-2 col-md-1">
                        </div>
                        <div class="col-xs-10 col-md-5 ">
                            <div class="btn-group pull-right">
                                <a href="ControaladorUsuario?accion=listarUsuarios" class="btn btn-default">
                                    <i class="fa fa-align-justify"></i> Ver listado</a>                                              
                            </div>
                        </div>
                    </div>
                </section>

                <section class="content">
                    <form method="post" action="ControladorVenta">
                        <div class="row">
                            <div class="col-md-5 seccion1">
                                <div class="card">
                                    <h5 class="card-header">Datos cliente</h5>
                                    <div class="card-body">

                                        <div class="row">
                                            <div class="col-md-6 d-flex">
                                                <%
                                                    Cliente datc = new Cliente();
                                                    CRUD_clientes dats = new CRUD_clientes();
                                                    List<Cliente> lista1 = dats.listar();
                                                %>
                                                <select name="clientes1">
                                                    <%
                                                        for (int i = 0; i < lista1.size(); i++) {
                                                            String dclie = lista1.get(i).getId() + "-" + lista1.get(i).getNom();
                                                    %>
                                                    <option><%=dclie%></option>
                                                    <%       }
                                                    %>
                                                </select> 
                                            </div>
                                            <div class="col-md-6 d-flex">
                                                <input type="submit" name="accion" value="BuscarCliente" class="btn btn-success ">
                                            </div>

                                            <div class="col-md-6 d-flex">
                                                <input type="number" name="documentocliente" class="form-control" placeholder="documento cliente" value="${cliente.getId()}">
                                            </div>
                                            <div class="col-md-6 d-flex">
                                                <input type="text" name="nombrecliente" class="form-control" placeholder="Nombre cliente" value="${cliente.getNom()}">
                                            </div>
                                        </div>
                                        <div class="row"></div>

                                    </div>
                                </div>
                                <div class="card">
                                    <h5 class="card-header">Datos producto</h5>
                                    <div class="card-body">

                                        <div class="row">
                                            <div class="col-md-6 d-flex form-group">
                                                <%
                                                    Producto prod = new Producto();
                                                    CRUD_productos dat = new CRUD_productos();
                                                    List<Producto> lista = dat.listar();

                                                %>
                                                <select name="productos1">
                                                    <%                                                        for (int i = 0; i < lista.size(); i++) {
                                                            String dprod = lista.get(i).getId() + "-" + lista.get(i).getDes();
                                                    %>
                                                    <option><%=dprod%></option>


                                                    <%       }
                                                    %>
                                                </select> 
                                                <input type="submit" name="accion" value="BuscarProducto" class="btn btn-danger">

                                            </div>
                                        </div>  
                                        <div class="row">        

                                            <div class="col-md-6 d-flex form-group">

                                                <input type="number" name="codigoproducto" class="form-control" placeholder="Codigo Producto" value="${productoseleccionado.getId()}">

                                            </div>
                                            <div class="col-md-6 d-flex form-group">
                                                <input type="text" name="nombreproducto" class="form-control" placeholder="Nombre Producto" value="${productoseleccionado.getDes()}">
                                            </div>
                                            <div class="col-md-4 d-flex form-group">
                                                <input type="text" name="precioproducto" class="form-control" placeholder="Bs. 0.0" value="${productoseleccionado.getPrec()}">
                                            </div>
                                            <div class="col-md-8 d-flex form-group">
                                                <input type="number" value="1" name="cantidadproducto" class="form-control" placeholder="Cantidad">
                                            </div>
                                        </div>
                                        <input type="submit" name="accion" value="AgregarProducto" class="btn btn-warning col text-center">
                                        <div class="row"></div>

                                        //  
                                    </div>
                                </div>
                            </div>

                            <div class="col-md-7">
                                <div class="card">
                                    <div class="card-header">
                                        <div class="form-group row">
                                            <label class="col-sm-2 col-form-label">Numero de factura</label>
                                            <input class="form-control col-md-4" type="text" name="txtnumerofactura" value="${numerofactura}">
                                        </div>

                                    </div>
                                    <div class="card-body">
                                        <table class="table">
                                            <thead class="thead-dark">
                                                <tr>
                                                    <th scope="col">#</th>
                                                    <th scope="col">Codigo</th>
                                                    <th scope="col">Producto</th>
                                                    <th scope="col">Precio</th>
                                                    <th scope="col">Cantidad</th>
                                                    <th scope="col">Total</th>
                                                    <th scope="col" class="columna">Acciones</th>
                                                </tr>
                                            </thead>
                                            <tbody>

                                                <c:forEach var="lista" items="${listaventas}">
                                                    <tr>
                                                        <th scope="row" style="width: 30px;">${lista.getItem()}</th>
                                                        <td style="width: 30px;">${lista.getIdProducto()}</td>
                                                        <td style="width: 350px;">${lista.getDescripcionProducto()}</td>
                                                        <td>$ ${lista.getPrecio()}</td>
                                                        <td style="width: 30px;">${lista.getCantidad()}</td>
                                                        <td>$ ${lista.getSubtotal()}</td>
                                                        <td class="columna">
                                                            <a class="btn btn-danger">Eliminar</a>
                                                            <a class="btn btn-warning">Editar</a>
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                    <div class="card-footer d-flex">
                                        <div class="col-md-8 text-center">

                                            <input class="btn btn-success" onclick="window.print()" type="submit" value="GrabarVenta" name="accion"/>
                                            <input class="btn btn-success" type="submit" value="NuevaVenta" name="accion"/>
                                            <input type="button" name="imprimir" value="Imprimir" onclick="window.print();">   


                                        </div>
                                        <div class="col-md-4">
                                            <input type=text" name="txttotalapagar" class="form-control" placeholder="bs 0.0" disabled="disabled" value="${totalapagar}">
                                        </div>
                                    </div>

                                </div>

                            </div>


                        </div>
                    </form>                   
                </section> 

            </div>
            <!-- /.content-wrapper -->

            <!-- Main Footer -->
            <footer class="main-footer">
                <!-- To the right -->
                <div class="pull-right hidden-xs">

                </div>
                <!-- Default to the left -->
                <strong>Copyright &copy; 2023 <a href="#">Sistema de Inventarios </a>.</strong> Todos los derechos reservados.
            </footer>

            <div class="control-sidebar-bg"></div>
        </div>
        <!-- ./wrapper -->

        <!-- REQUIRED JS SCRIPTS -->

        <!-- jQuery 3 -->
        <script src="bower_components/jquery/dist/jquery.min.js"></script>
        <!-- Bootstrap 3.3.7 -->
        <script src="bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
        <!-- AdminLTE App -->
        <script src="dist/js/adminlte.min.js"></script>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>

        <!-- Optionally, you can add Slimscroll and FastClick plugins.
             Both of these plugins are recommended to enhance the
             user experience. -->


    </body>
</html>
<%
    } else {
        response.sendRedirect("IngresoSistema.jsp");
    }
%>
