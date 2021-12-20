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
                <a href="SvVerEmpleados" class="boton-menu">EMPLEADOS</a>
            </form>
            <form action="SvVerClientes" method="GET">
                <a href="SvVerClientes" class="boton-menu">CLIENTES</a>
            </form>
            <form action="SvLoginUsuario" method="GET">
                <a href="SvLoginUsuario" class="boton-menu seleccionado">SESIÓN</a>
            </form>
        </div>
        <div id="contenido-principal">
            <div class="contenedor-formulario">
                <form action="SvLoginUsuario" method="POST" class="flex-columna" id="form-login">
                    <h2 class="color-negro">Inicio de sesión</h2>
                    <div>
                        <p class="flex-fila">Nombre de usuario<input type="text" name="user" placeholder="Ingresar nombre de usuario"></p>
                        <p class="flex-fila">Contraseña<input type="password" name="contra" placeholder="Ingresar contraseña"></p> 
                    </div>
                    <button type="submit" class="boton-submit">INICIAR SESIÓN</button>
                </form>
            </div>
        </div>
    </div>
</body>
</html>