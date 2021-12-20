<%@page import="java.text.SimpleDateFormat"%>
<%@page import="Logica.Servicio"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <link href="./css/style.css" rel="stylesheet">
    <link href="./css/main.css" rel="stylesheet">
    <title>Agencia de Turismo</title>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300&display=swap" rel="stylesheet">
</head>
<body>
    <%
    HttpSession miSesion = request.getSession();
    String user = (String) miSesion.getAttribute("user");
    if (user == null) {
        response.sendRedirect("sesion.jsp");
    } else { %>
    <div id="barra-info" class="flex-fila">
        <h2>AGENCIA DE TURISMO</h2>
        <a class="flex-fila" href="https://github.com/AberasT" target="_blank">github.com/AberasT<img id="imagen-gh" src="img/github_white.png"></a>
    </div>
    <div id="contenedor-general">
        <div id="barra-menu" class="flex-columna">
            <form action="SvRecaudaciones" method="GET">
                <a href="SvRecaudaciones" class="boton-menu">INICIO</a>
            </form>
            <form action="SvVerVentas" method="GET">
                <a href="SvVerVentas" class="boton-menu">VENTAS</a>
            </form>
            <form action="SvVerServicios" method="GET">
                <a href="SvVerServicios" class="boton-menu seleccionado">SERVICIOS</a>
            </form>
            <form action="SvVerPaquetes" method="GET">
                <a href="SvVerPaquetes" class="boton-menu">PAQUETES</a>
            </form>
            <form action="SvVerEmpleados" method="GET">
                <a href="SvVerEmpleados" class="boton-menu">EMPLEADOS</a>
            </form>
            <form action="SvVerClientes" method="GET">
                <a href="SvVerClientes" class="boton-menu">CLIENTES</a>
            </form>
            <form action="SvLoginUsuario" method="GET">
                <a href="SvLoginUsuario" class="boton-menu">SESIÓN</a>
            </form>
        </div>
        <div id="contenido-principal" >
            <div id="menu-seccion" class="flex-fila">
                <a href="servicios_alta.jsp">ALTA</a>
                <label>BUSCAR<input type="text"></label>
            </div>
            <div>
              <div class="limiter">
		<div class="container-table100">
                    <div class="wrap-table100">
                        <div class="table100 ver1">
                            <div class="table100-firstcol">
                                <table>
                                    <thead>
                                        <tr class="row100 head">
                                            <th class="cell100 column1">Servicios</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <%  List <Servicio> listaServicios = (List) miSesion.getAttribute("listaServicios");
                                            for (Servicio ser : listaServicios) {
                                                if (ser.isHabilitado()) {
                                        %>
                                        <tr class="row100 body">
                                            <td class="cell100 column1"><%=ser.getNombre()%></td>
                                        </tr>
                                        <% }} %>
                                    </tbody>
                                </table>
                            </div>
                            <div class="wrap-table100-nextcols js-pscroll">
                                    <div class="table100-nextcols">
                                        <table>
                                            <thead>
                                                    <tr class="row100 head">
                                                    <th class="cell100 column2">Modificar</th>
                                                    <th class="cell100 column3">Eliminar</th>
                                                    <th class="cell100 column4">Código</th>
                                                    <th class="cell100 column10">Nombre</th>
                                                    <th class="cell100 column7">Fecha de servicio</th>
                                                    <th class="cell100" id="descripcion">Descripción</th>
                                                    <th class="cell100 column10">Destino</th>
                                                    <th class="cell100 column12">Costo</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                        <%  String nombre, descripcion, destino, fechaString;
                                                            int id;
                                                            double costo;
                                                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
                                                            for (Servicio ser : listaServicios) { 
                                                                if (ser.isHabilitado()) {%>
                                                <tr class="row100 body">
                                                <%  id = ser.getCodigo();
                                                    fechaString = sdf.format(ser.getFecha_servicio());
                                                    descripcion = ser.getDescripcion_breve();
                                                    destino = ser.getDestino_servicio();
                                                    nombre = ser.getNombre();
                                                    costo = ser.getCosto_servicio(); %>
                                                    <td class="column2">
                                                        <form name="formModificarServicio" action="SvModificarServicio" method="POST">
                                                            <input type="hidden" name="id" value="<%=id%>">
                                                            <button class="boton-tabla boton-modif">
                                                                <img src="./img/modificar.png" alt="modif"/>
                                                            </button>
                                                        </form>
                                                    </td>
                                                    <td class="column3">
                                                        <form name="formEliminarServicio" action="SvEliminarServicio" method="GET" onsubmit="return confirm('Eliminar servicio?')")>
                                                            <input type="hidden" name="id" value="<%=id%>">
                                                            <button type="submit" class="boton-tabla boton-elim">
                                                                <img src="./img/eliminar.png" alt="elim"/>
                                                            </button>
                                                        </form>
                                                    </td>
                                                    <td class="column4"><%=id%></td>
                                                    <td class="column10"><%=nombre%></td>
                                                    <td class="column7"><%=fechaString%></td>
                                                    <td id="descripcion"><%=descripcion%></td>
                                                    <td class="column10"><%=destino%></td>
                                                    <td class="column12">$<%=costo%></td>
                                                </tr>
                                                <%}}%>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="./assets/script.js"></script>
    <%}%>
</body>
</html>