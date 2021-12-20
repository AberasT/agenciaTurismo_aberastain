<%@page import="Logica.Paquete"%>
<%@page import="Logica.Servicio"%>
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
            <a href="index.jsp" class="boton-menu">INICIO</a>
            <a href="usuario.jsp" class="boton-menu">USUARIO</a>
            <a href="ventas.jsp" class="boton-menu">VENTAS</a>
            <a href="servicios.jsp" class="boton-menu">SERVICIOS</a>
            <a href="paquetes.jsp" class="boton-menu seleccionado">PAQUETES</a>
            <form action="SvVerEmpleados" method="GET">
                <a href="SvVerEmpleados" class="boton-menu">EMPLEADOS</a>
            </form>
            <form action="SvVerClientes" method="GET">
                <a href="SvVerClientes" class="boton-menu">CLIENTES</a>
            </form> 
        </div>
        <div id="contenido-principal" >
            <div id="menu-seccion" class="flex-fila">
                <form action="SvAltaPaquete" method="GET">
                    <a href="SvAltaPaquete">ALTA</a>
                </form>
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
                                            <th class="cell100 column1">Paquetes</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <%  List <Paquete> listaPaquetes = (List) miSesion.getAttribute("listaPaquetes");
                                            for (Paquete paq : listaPaquetes) {
                                                if (paq.isHabilitado()) {
                                        %>
                                        <tr class="row100 body">
                                            <td class="cell100 column1"><%=paq.getCodigo_paquete()%></td>
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
                                                                    <th class="column2">Modificar</th>
                                                                    <th class="column3">Eliminar</th>
                                                                    <th class="listaElementos">Servicios incluidos</th>
                                                                    <th class="column12">Costo total</th>
                                                            </tr>
                                                    </thead>
                                                    <tbody>
                                                        <%  String serviciosString = "";
                                                            double costo;
                                                            List<Servicio> listaServiciosIncluidos;
                                                            for (Paquete paq : listaPaquetes) { 
                                                                if (paq.isHabilitado()) {%>
                                                            <tr class="row100 body">
                                                                <%  listaServiciosIncluidos = paq.getLista_servicios_incluidos();
                                                                    serviciosString = "";
                                                                    for (Servicio ser : listaServiciosIncluidos) {
                                                                            serviciosString += ser.getNombre() + ", ";
                                                                        }
                                                                    serviciosString = serviciosString.substring(0,serviciosString.length()-2);
                                                                    costo = paq.getCosto_paquete(); %>
                                                                    <td class="column2">
                                                                        <form name="formModificarPaquete" action="SvModificarPaquete" method="POST">
                                                                            <input type="hidden" name="id" value="<%=paq.getCodigo_paquete()%>">
                                                                            <button class="boton-tabla boton-modif">
                                                                                <img src="./img/modificar.png" alt="modif"/>
                                                                            </button>
                                                                        </form>
                                                                    </td>
                                                                    <td class="column3">
                                                                        <form name="formEliminarPaquete" action="SvEliminarPaquete" method="GET" onsubmit="return confirm('Eliminar paquete?')")>
                                                                            <input type="hidden" name="id" value="<%=paq.getCodigo_paquete()%>">
                                                                            <button type="submit" class="boton-tabla boton-elim">
                                                                                <img src="./img/eliminar.png" alt="elim"/>
                                                                            </button>
                                                                        </form>
                                                                    </td>
                                                                    <td class="listaElementos"><%=serviciosString%></td>
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
    <% } %>
</body>
</html>