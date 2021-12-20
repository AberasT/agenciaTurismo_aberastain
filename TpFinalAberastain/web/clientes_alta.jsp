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
                <a href="SvRecaudaciones" class="boton-menu ">GANANCIAS</a>
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
                <a href="SvVerClientes" class="boton-menu seleccionado">CLIENTES</a>
            </form>
            <a href="sesion.jsp" class="boton-menu">SESIÓN</a>
        </div>
        <div class="contenido-principal">
            <div class="contenedor-formulario">
                <form class="flex-columna" action="SvAltaCliente" method="POST">
                    <h2>Formulario de alta</h2>
                    <div>
                        <p class="flex-fila "><label>Nombre</label><input type="text" name="nombre"></p>
                        <p class="flex-fila "><label>Apellido</label><input type="text" name="apellido"></p>
                        <p class="flex-fila "><label>Dirección</label><input type="text" name="direccion"></p>
                        <p class="flex-fila "><label>DNI</label><input type="text" name="dni"></p>
                        <p class="flex-fila "><label>Fecha de nacimiento</label><input type="date" name="fechaNacimiento"></p>
                        <p class="flex-fila "><label>Nacionalidad</label><input type="text" name="nacionalidad"></p>
                        <p class="flex-fila "><label>Celular</label><input type="text" name="celular"></p>
                        <p class="flex-fila "><label>Email</label><input type="text" name="email"></p>
                    </div>
                    <button class="boton-submit" type="submit">CARGAR CLIENTE</button>
                </form>
            </div>
        </div>
    </div>
    <%}%>
</body>
</html>