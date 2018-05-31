<!doctype html>
<html>
<title>Agregar Estudiante</title>
<h1>Agregar estudiante</h1>
        <form action="/agregar" method="post" >
            <div>
                <label for="matricula">Matricula:
                    <div>
                        <input type="text" name="matricula">
                    </div>
            </div>
                    <div>
                        <label for="nombre">Nombre:   </label>
                        <div>
                            <input type="text" name="nombre">
                        </div>
                    </div>
                    <div>
                        <label for="apellido">Apellido:  </label>
                        <div>
                            <input type="text" name="apellido">
                        </div>
                    </div>
            <div>
                <label for="telefono">Teléfono: </label>
                <div>
                    <input type="text" name="telefono">
                </div>

            </div>
            <button type="submit">
                Agregar
            </button>
        </form>
</html>