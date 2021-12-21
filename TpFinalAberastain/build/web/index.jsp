<%@page import="java.util.ArrayList"%>
<%@page import="java.util.GregorianCalendar"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.List"%>
<%@page import="Logica.Venta"%>
<%@page import="java.text.SimpleDateFormat"%>
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
        HttpSession sesion = request.getSession();
        String user = (String) sesion.getAttribute("user");
        if (user == null) {
            response.sendRedirect("login.jsp");
        } else { %>
    <div id="barra-info" class="flex-fila">
        <h2>AGENCIA DE TURISMO</h2>
        <a class="flex-fila" href="https://github.com/AberasT" target="_blank">github.com/AberasT<img id="imagen-gh" src="img/github_white.png"></a>
    </div>
    <div id="contenedor-general">
        <div id="barra-menu" class="flex-columna">
            <form action="SvRecaudaciones" method="GET">
                <a href="SvRecaudaciones" class="boton-menu seleccionado">GANANCIAS</a>
            </form>
            <form action="SvVerVentas" method="GET">
                <a href="SvVerVentas" class="boton-menu">VENTAS</a>
            </form>
            <form action="SvVerServicios" method="GET">
                <a href="SvVerServicios" class="boton-menu">SERVICIOS</a>
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
            <a href="sesion.jsp" class="boton-menu">SESIÓN</a>
        </div>
        <%  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
            Date fechaInf = (Date) sesion.getAttribute("fechaInf");
            Date fechaSup = (Date) sesion.getAttribute("fechaSup");
        %>
        <div id="contenido-principal">
            <div class="contenedor-formulario">
                <form class="flex-columna" action="SvRecaudaciones" method="POST">
                    <h2 class="color-negro">Consulta de ganancias</h2>
                    <p>Introducir fecha inferior<input type="date" name="fechaInf"></p>
                    <p>Introducir fecha superior<input type="date" name="fechaSup"></p>
                    <button type="submit" class="boton-submit">FILTRAR</button>
                </form>
            </div>
            <% if (fechaInf != null && fechaSup != null) { %>
            <div class="limiter">
                <div class="container-table100">
                    <div class="wrap-table100">
                        <div class="table100 ver1">
                            <div class="table100-firstcol">
                                <table>
                                    <thead>
                                        <tr class="row100 head">
                                            <th class="cell100 column1">NÚM. VENTA</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    <%  List <Venta> listaVentas = (List) sesion.getAttribute("listaVentas"); 
                                        List <Venta> listaVentasFiltradas = new ArrayList();
                                        for (Venta ven : listaVentas) {
                                            if ((sdf.format(ven.getFecha_venta()).compareTo(sdf.format(fechaInf)) >= 0) && (sdf.format(ven.getFecha_venta()).compareTo(sdf.format(fechaSup)) <= 0)) {
                                                listaVentasFiltradas.add(ven); }}%>
                                    <%
                                        for (Venta ven : listaVentasFiltradas) { %>
                                            
                                        <tr class="row100 body">
                                            <td class="column1"><%=ven.getNum_venta()%></td>
                                        </tr>
                                        <% } %>
                                    </tbody>
                                </table>
                            </div>
                            <div class="wrap-table100-nextcols js-pscroll">
                                <div class="table100-nextcols">
                                    <table>
                                        <thead>
                                            <tr class="row100 head">
                                                <th class="column7">Fecha de venta</th>
                                                <th class="column10">Cód. servicio vendido</th>
                                                <th class="column10">Cód. paquete vendido</th>
                                                <th class="column10">Medio de pago</th>
                                                <th class="column8">Cliente</th>
                                                <th class="column8">Empleado</th>
                                                <th class="column12">Costo</th>
                                            </tr>
                                        </thead>
                                            <tbody>
                                            <%  String fechaString, medioPago, cliente = "", empleado = "", codServicio, codPaquete;
                                                double costo = 0;
                                                for (Venta ven : listaVentasFiltradas) { %>
                                                <tr class="row100 body">
                                                    <%  if (ven.getServicio() == null) {
                                                            codPaquete = String.valueOf(ven.getPaquete().getCodigo_paquete());
                                                            codServicio = "-";
                                                        } else {
                                                            codServicio = String.valueOf(ven.getServicio().getCodigo());
                                                            codPaquete = "-";
                                                        }
                                                        fechaString = sdf.format(ven.getFecha_venta());
                                                        cliente = ven.getCliente().getNombre() + " " + ven.getCliente().getApellido() ;
                                                        cliente += " - ID " + ven.getCliente().getId_cliente();
                                                        empleado = ven.getUsuario().getEmpleado().getNombre() + " " + ven.getUsuario().getEmpleado().getApellido() ;
                                                        empleado += " - ID " + ven.getUsuario().getEmpleado().getId_empleado();
                                                        costo = ven.getCostoTotal();
                                                        switch(ven.getMedio_pago()) {
                                                            case "efectivo" : 
                                                                medioPago = "Efectivo";
                                                                break;
                                                            case "debito" : 
                                                                medioPago = "Tarjeta débito";
                                                                break;
                                                            case "credito" : 
                                                                medioPago = "Tarjeta crédito";
                                                                break;
                                                            case "monederoVirtual" : 
                                                                medioPago = "Monedero Virtual";
                                                                break;
                                                            default :
                                                                medioPago = "Transferencia";
                                                        }%>
                                                    <td class="column7"><%=fechaString%></td>
                                                    <td class="column10"><%=codServicio%></td>
                                                    <td class="column10"><%=codPaquete%></td>
                                                    <td class="column10"><%=medioPago%></td>
                                                    <td class="column8"><%=cliente%></td>
                                                    <td class="column8"><%=empleado%></td>
                                                    <td class="column12">$<%=costo%></td>
                                                </tr>
                                                    <%}%>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <%  double costoTotal = 0;%>
                <div class="contenedor-selectores flex-columna color-negro">
                    <h3>Recaudaciones en el plazo de tiempo indicado</h3>
                <%  for (Venta ven : listaVentasFiltradas) { %>
                        <p class="p-info">Venta Nro.<%=ven.getNum_venta()%> $<%=ven.getCostoTotal()%></p>
                    <%  costoTotal += ven.getCostoTotal(); }
                        costoTotal = costoTotal * Math.pow(10, 2);
                        costoTotal = Math.floor(costoTotal);
                        costoTotal = costoTotal / Math.pow(10, 2);%>
                    <h3>TOTAL: $<%=costoTotal%></h3>
                </div>
                <% } %>
            </div>
        </div>
    </div>
    <% } %>
</body>
</html>