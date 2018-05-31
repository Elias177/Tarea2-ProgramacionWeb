<!doctype html>
<html>
<title>Home</title>
<head>
    <h2>
       <a href="/est/agregar">Agregar Estudiante</a>
    </h2>
</head>
    <body>
        <h1>Estudiantes:</h1>
        <table border="1" align="left" width="100%">
            <tr>
                <thead>
                    <th>Matricula</th>
                    <th>Nombre</th>
                    <th>Apellido</th>
                    <th>Telefono</th>
                    <th>Opciones</th>
                </thead>
            </tr>
            <tbody>

            <#list estudiantes as est>
            <tr>
                <td>${est.matricula?string["0"]}</td>
                <td>${est.nombre}</td>
                <td>${est.apellido}</td>
                <td>${est.telefono}</td>
                <td>
                    <form style="display: inline;"  action="/est/editar/${est.matricula?string["0"]}">
                        <input type="submit" value="Mostrar/Editar">
                    </form>
                    <form style="display: inline;"  action="/est/borrar/${est.matricula?string["0"]}" method="post">
                     <button type="submit">Borrar </button>
                    </form>
                </td>
            </tr>
            </#list>

            </tbody>

        </table>

    </body>
    <br>

</html>
