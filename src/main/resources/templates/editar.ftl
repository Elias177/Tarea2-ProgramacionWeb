<!doctype html>
<html>
<title>Editar Estudiante</title>
<h1>Editar estudiante </h1>
<form style="display: inline;" action="/est/editar" method="post">
    <div>
        <label for="matricula">Matricula</label>
        <div>
            <input type="text" name="matricula" value="${estudiante.matricula?string["0"]}">
        </div>
    </div>

    <div>
        <label for="nombre">Nombre</label>
        <div>
            <input type="text" name="nombre" value="${estudiante.nombre}">

        </div>
    </div>

    <div>
        <label for="apellido">Apellido</label>
        <div>
            <input type="text" name="apellido" value="${estudiante.apellido}">
        </div>
    </div>

    <div>
        <label for="telefono">Teléfono</label>
        <div>
            <input type="text" name="telefono" value="${estudiante.telefono}">
        </div>
    </div>

    <div>
        <button type="submit">Editar</button>
    </div>

</form>
<form style="display: inline;" action="/est/borrar/${estudiante.matricula?string["0"]}" method="post">
    <button type="submit">Borrar </button>
</form>

</html>
