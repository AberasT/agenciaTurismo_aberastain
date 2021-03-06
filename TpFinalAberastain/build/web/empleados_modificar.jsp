<%@page import="java.text.SimpleDateFormat"%>
<%@page import="Logica.Usuario"%>
<%@page import="Logica.Empleado"%>
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
                <a href="SvRecaudaciones" class="boton-menu">GANANCIAS</a>
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
                <a href="SvVerEmpleados" class="boton-menu seleccionado">EMPLEADOS</a>
            </form>
            <form action="SvVerClientes" method="GET">
                <a href="SvVerClientes" class="boton-menu">CLIENTES</a>
            </form>
            <a href="sesion.jsp" class="boton-menu">SESIÓN</a>
        </div>
        <div class="contenido-principal">
            <div class="contenedor-formulario">
                <form class="flex-columna" action="SvModificarEmpleado" method="GET">
                    <%  Empleado emp = (Empleado) sesion.getAttribute("empleado");
                        Usuario usu = (Usuario) sesion.getAttribute("usuario");
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
                        String fechaString = sdf.format(emp.getFecha_nac());%>
                    <h2>Formulario de modificación</h2>
                    <div>
                        <p class="flex-fila "><label>Nombre</label><input type="text" name="nombre" value="<%=emp.getNombre()%>"></p>
                        <p class="flex-fila "><label>Apellido</label><input type="text" name="apellido" value="<%=emp.getApellido()%>"><p>
                        <p class="flex-fila "><label>Dirección</label><input type="text" name="direccion" value="<%=emp.getDireccion()%>"></p>
                        <p class="flex-fila "><label>DNI</label><input type="text" name="dni" value="<%=emp.getDni()%>"></p>
                        <p class="flex-fila "><label>Fecha de nacimiento</label><input type="date" name="fechaNacimiento" value="<%=fechaString%>"></p>
                        <p class="flex-fila "><label>Nacionalidad</label><input type="text" name="nacionalidad" value="<%=emp.getNacionalidad()%>"></p>
                        <p class="flex-fila "><label>Celular</label><input type="text" name="celular" value="<%=emp.getCelular()%>"></p>
                        <p class="flex-fila "><label>Email</label><input type="text" name="email" value="<%=emp.getEmail()%>"></p>
                        <p class="flex-fila "><label>Cargo</label><input type="text" name="cargo" value="<%=emp.getCargo()%>"></p>
                        <p class="flex-fila "><label>Sueldo</label><input type="number" name="sueldo" value="<%=emp.getSueldo()%>"></p>
                        <p class="flex-fila "><label>Nombre de usuario</label><input type="text" name="nombreUsuario" value="<%=usu.getNombre_usuario()%>"></p>
                        <p class="flex-fila "><label>Contraseña</label><input type="password" name="contrasenia" value="<%=usu.getContrasenia()%>"></p>
                    </div>
                    <input type="hidden" name="id" value="<%=emp.getId_empleado()%>">
                    <button class="boton-submit" type="submit">CARGAR</button>
                </form>
            </div>
        </div>
    </div>
    <%} %>
</body>
</html>