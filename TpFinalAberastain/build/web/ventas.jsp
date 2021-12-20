<%@page import="java.text.SimpleDateFormat"%>
<%@page import="Logica.Venta"%>
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
    <div id="barra-info" class="flex-fila">
        <h2>AGENCIA DE TURISMO</h2>
        <a class="flex-fila" href="https://github.com/AberasT" target="_blank">github.com/AberasT<img id="imagen-gh" src="img/github_white.png"></a>
    </div>
    <div id="contenedor-general">
        <div id="barra-menu" class="flex-columna">
            <a href="index.jsp" class="boton-menu">INICIO</a>
            <a href="usuario.jsp" class="boton-menu">USUARIO</a>
            <a href="ventas.jsp" class="boton-menu seleccionado">VENTAS</a>
            <a href="servicios.jsp" class="boton-menu">SERVICIOS</a>
            <a href="paquetes.jsp" class="boton-menu">PAQUETES</a>
            <form action="SvVerEmpleados" method="GET">
                <a href="SvVerEmpleados" class="boton-menu">EMPLEADOS</a>
            </form>
            <form action="SvVerClientes" method="GET">
                <a href="SvVerClientes" class="boton-menu">CLIENTES</a>
            </form> 
        </div>
        <div id="contenido-principal" >
            <div id="menu-seccion" class="flex-fila">
                <a href="SvAltaVentasServicios">VENDER SERVICIO</a>
                <a href="SvAltaVentasPaquetes">VENDER PAQUETE</a>
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
                                            <th class="cell100 column1">NÚM. VENTA</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    <%  HttpSession miSesion = request.getSession();

                                        List <Venta> listaVentas = (List) miSesion.getAttribute("listaVentas");
                                        
                                        for (Venta ven : listaVentas) {%>
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
                                                <th class="column2">Modificar</th>
                                                <th class="column3">Eliminar</th>
                                                <th class="column10">Cód. servicio vendido</th>
                                                <th class="column10">Cód. paquete vendido</th>
                                                <th class="column7">Fecha de venta</th>
                                                <th class="column10">Medio de pago</th>
                                                <th class="column8">Cliente</th>
                                                <th class="column8">Empleado</th>
                                            </tr>
                                        </thead>
                                            <tbody>
                                            <%  String redireccionModificar, fechaString, medioPago, cliente = "", empleado = "", codServicio, codPaquete;
                                                int id;
                                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
                                                for (Venta ven : listaVentas) { %>
                                                <tr class="row100 body">
                                                <%  id = ven.getNum_venta();
                                                    if (ven.getServicio() == null) {
                                                        codPaquete = String.valueOf(ven.getPaquete().getCodigo_paquete());
                                                        redireccionModificar = "SvModificarVentaPaquete";
                                                        codServicio = "-";
                                                    } else {
                                                        codServicio = String.valueOf(ven.getServicio().getCodigo());
                                                        redireccionModificar = "SvModificarVentaServicio";
                                                        codPaquete = "-";
                                                    }
                                                    
                                                    fechaString = sdf.format(ven.getFecha_venta());
                                                    cliente = ven.getCliente().getNombre() + " " + ven.getCliente().getApellido() ;
                                                    cliente += " - ID " + ven.getCliente().getId_cliente();
                                                    empleado = ven.getUsuario().getEmpleado().getNombre() + " " + ven.getUsuario().getEmpleado().getApellido() ;
                                                    empleado += " - ID " + ven.getUsuario().getEmpleado().getId_empleado();
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
                                                    <td class="cell100 column2">
                                                        <form name="formModificarVenta" action="<%=redireccionModificar%>" method="POST">
                                                            <input type="hidden" name="id" value="<%=id%>">
                                                            <button class="boton-tabla boton-modif">
                                                                <img src="./img/modificar.png" alt="modif"/>
                                                            </button>
                                                        </form>
                                                    </td>
                                                    <td class="column3">
                                                        <form name="formEliminarVenta" action="SvEliminarVenta" method="POST" onsubmit="return confirm('Eliminar venta?')")>
                                                            <input type="hidden" name="id" value="<%=id%>">
                                                            <button type="submit" class="boton-tabla boton-elim">
                                                                <img src="./img/eliminar.png" alt="elim"/>
                                                            </button>
                                                        </form>
                                                    </td>
                                                    <td class="column10"><%=codServicio%></td>
                                                    <td class="column10"><%=codPaquete%></td>
                                                    <td class="column7"><%=fechaString%></td>
                                                    <td class="column10"><%=medioPago%></td>
                                                    <td class="column8"><%=cliente%></td>
                                                    <td class="column8"><%=empleado%></td>
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
            </div>
        </div>
    </div>
    <script src="./assets/script.js"></script>
</body>
</html>