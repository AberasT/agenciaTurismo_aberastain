<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="Logica.Empleado"%>
<%@page import="java.util.List"%>
<%@page import="Logica.ControladoraLogica"%>
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
            <a href="paquetes.jsp" class="boton-menu">PAQUETES</a>
            <form action="SvVerEmpleados" method="GET">
                <a href="SvVerEmpleados" class="boton-menu seleccionado">EMPLEADOS</a>
            </form>
            <form action="SvCliente" method="GET">
                <a href="SvCliente" class="boton-menu">CLIENTES</a>
            </form>
        </div>
        <div id="contenido-principal" >
            <div id="menu-seccion" class="flex-fila">
                <a href="empleados_alta.jsp">ALTA</a>
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
                                                <th class="column1">Empleados</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <%  List <Empleado> listaEmpleados = (List) miSesion.getAttribute("listaEmpleados");
                                                String nombreComp;
                                                for (Empleado emp : listaEmpleados) {
                                                    if (emp.isHabilitado()) {
                                                        nombreComp = emp.getNombre() + " " + emp.getApellido();
                                            %>
                                            <tr class="row100 body">
                                                <td class="column1"><%=nombreComp%></td>
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
                                                    <th class="cell100 column3">Eliminar</th>
                                                    <th class="cell100 column4">ID</th>
                                                    <th class="cell100 column5">DNI</th>
                                                    <th class="cell100 column6">Cargo</th>
                                                    <th class="cell100 column7">Fecha de nacimiento</th>
                                                    <th class="cell100 column8">Email</th>
                                                    <th class="cell100 column9">Celular</th>
                                                    <th class="cell100 column10">Direccion</th>
                                                    <th class="cell100 column11">Nacionalidad</th>
                                                    <th class="cell100 column12">Sueldo</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <%  String dni, cargo, email, celular, direccion, nacionalidad, nombreUsuario;
                                                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
                                                    double sueldo;
                                                    int id;
                                                    for (Empleado emp : listaEmpleados) { %>
                                                <tr class="row100 body">
                                                <%  if (emp.isHabilitado()) {
                                                        id = emp.getId_empleado();
                                                        dni = emp.getDni();
                                                        cargo = emp.getCargo();
                                                        String fechaString = sdf.format(emp.getFecha_nac());
                                                        email = emp.getEmail();
                                                        celular = emp.getCelular();
                                                        direccion = emp.getDireccion();
                                                        nacionalidad = emp.getNacionalidad();
                                                        sueldo = emp.getSueldo();%>
                                                    <td class="cell100 column2">
                                                        <form name="formModificarEmpleado" action="SvModificarEmpleado" method="POST">
                                                            <input type="hidden" name="id" value="<%=id%>">
                                                            <button class="boton-tabla boton-modif">
                                                                <img src="./img/modificar.png" alt="modif"/>
                                                            </button>
                                                        </form>
                                                    </td>
                                                    <td class="cell100 column3">
                                                        <form name="formEliminarEmpleado" action="SvEliminarEmpleado" method="GET" onsubmit="return confirm('Eliminar empleado?')")>
                                                            <input type="hidden" name="id" value="<%=id%>">
                                                            <button type="submit" class="boton-tabla boton-elim">
                                                                <img src="./img/eliminar.png" alt="elim"/>
                                                            </button>
                                                        </form>
                                                    </td>
                                                    <td class="cell100 column4"><%=id%></td>
                                                    <td class="cell100 column5"><%=dni%></td>
                                                    <td class="cell100 column6"><%=cargo%></td>
                                                    <td class="cell100 column7"><%=fechaString%></td>
                                                    <td class="cell100 column8"><%=email%></td>
                                                    <td class="cell100 column9"><%=celular%></td>
                                                    <td class="cell100 column10"><%=direccion%></td>
                                                    <td class="cell100 column11"><%=nacionalidad%></td>
                                                    <td class="cell100 column12">$<%=sueldo%></td>
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
