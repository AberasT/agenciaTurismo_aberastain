<%@page import="java.text.SimpleDateFormat"%>
<%@page import="Logica.Servicio"%>
<%@page import="Logica.Cliente"%>
<%@page import="Logica.Empleado"%>
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
            <div>
                <div class="limiter">
                    <div class="container-table100">
                        <form class="wrap-table100" action="SvAltaVentasServicios" method="POST">
                            <div class="table100 ver1">
                                <div class="table100-firstcol">

                                    <table>
                                        <thead>
                                                <th class="column1">Servicios disponibles</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <%  HttpSession miSesion = request.getSession();
                                                List <Cliente> listaClientes = (List) miSesion.getAttribute("listaClientes");
                                                List <Empleado> listaEmpleados = (List) miSesion.getAttribute("listaEmpleados");
                                                
                                                List <Servicio> listaServicios = (List) miSesion.getAttribute("listaServicios");
                                                for (Servicio ser : listaServicios) {
                                                    if (ser.isHabilitado()) {
                                            %>
                                            <tr class="row100 body">
                                                <td class="column1"><%=ser.getCodigo()%></td>
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
                                                    <th class="column2">Incluir</th>
                                                    <th class="column10">Nombre</th>
                                                    <th class="column7">Fecha de servicio</th>
                                                    <th id="descripcion">Descripción</th>
                                                    <th class="column10">Destino</th>
                                                    <th class="column12">Costo</th>
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
                                                            <input type="checkbox" class="checkbox" name="<%=id%>" value="<%=id%>">
                                                        </td>
                                                        <td class="cell100 column10"><%=nombre%></td>
                                                        <td class="cell100 column7"><%=fechaString%></td>
                                                        <td class="cell100" id="descripcion"><%=descripcion%></td>
                                                        <td class="cell100 column10"><%=destino%></td>
                                                        <td class="cell100 column12">$<%=costo%></td>
                                                    </tr>
                                                    <%}}%>
                                                </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                            <div class="flex-columna contenedor-selectores">
                                <label class="color-negro">Seleccionar medio de pago
                                    <select name="medioPago" class="selector">
                                        <option value="efectivo">EFECTIVO</option>
                                        <option value="debito">TARJETA DE DÉBITO</option>
                                        <option value="credito">TARJETA DE CRÉDITO</option>
                                        <option value="monederoVirtual">MONEDERO VIRTUAL</option>
                                        <option value="transferencia">TRANSFERENCIA</option>
                                    </select>
                                </label>

                                <%  String nombreIdCli, nombreIdEmp; %>

                                <label class="color-negro">Seleccionar cliente
                                    <select name="clienteId" class="selector">
                                    <<%  for (Cliente cli : listaClientes) {
                                            if (cli.isHabilitado()) {
                                                nombreIdCli = cli.getNombre() +" "+ cli.getApellido();
                                                nombreIdCli += " - ID " + cli.getId_cliente();
                                        %>
                                        <option value="<%=cli.getId_cliente()%>"><%=nombreIdCli%></option>
                                    <% }} %>
                                    </select>
                                </label>
                                <label class="color-negro">Seleccionar empleado
                                    <select name="empleadoId" class="selector">
                                    <%  for (Empleado emp : listaEmpleados) {
                                            if (emp.isHabilitado()) {
                                                nombreIdEmp = emp.getNombre() +" "+ emp.getApellido();
                                                nombreIdEmp += " - ID " + emp.getId_empleado();
                                        %>
                                        <option value="<%=emp.getId_empleado()%>"><%=nombreIdEmp%></option>
                                    <% }} %>
                                    </select>
                                </label>
                                <label class="color-negro">Seleccionar fecha<input type="date" name="fechaVenta"></label>
                                <<button class="boton-submit" type="submit">CREAR VENTA</button>
                                <h3 class="color-negro">Debe seleccionarse un único servicio</h3>
                                <h3 class="color-negro">Recuerde seleccionar todos los campos</h3>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="./assets/script.js"></script>
</body>
</html>