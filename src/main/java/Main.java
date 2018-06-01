import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.path;
import static spark.Spark.post;

public class Main {

    public static void main(String[] args) {
        //Cargando la configuracion de freemarker
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_28);
        configuration.setClassForTemplateLoading(Main.class, "/");

        ArrayList<Estudiante> estudiantes = new ArrayList<>();
        //Seteando el path principal
        get("/", (req, res) -> {
            StringWriter writer = new StringWriter();
            Map<String, Object> atr = new HashMap<>();
            Template template = configuration.getTemplate("templates/home.ftl");

            atr.put("estudiantes", estudiantes);
            template.process(atr, writer);

            return writer;
        });
        //Llamada post para agregar un estudiante nuevo
        post("/agregar", (req, res) -> {
            int mat = Integer.parseInt(req.queryParams("matricula"));
            String nom = req.queryParams("nombre");
            String ape = req.queryParams("apellido");
            String tel = req.queryParams("telefono");

            estudiantes.add(new Estudiante(mat, nom, ape, tel));

            res.redirect("/");

            return null;
        });
        //Rutas referente a la clase estudiante
        path("/est", () -> {
            //Ruta para agregar un estudiante
            get("/agregar", (req, res) -> {
                StringWriter writer = new StringWriter();
                Template temp = configuration.getTemplate("templates/agregar.ftl");

                temp.process(null, writer);

                return writer;
            });
        //Llamada para editar un estudiante
            post("/editar", (req, res) -> {
                int mat = Integer.parseInt(req.queryParams("matricula"));
                String nom = req.queryParams("nombre");
                String ape = req.queryParams("apellido");
                String tel = req.queryParams("telefono");

                for (Estudiante est : estudiantes) {
                    if (est.getMatricula() == mat) {
                        est.setNombre(nom);
                        est.setApellido(ape);
                        est.setTelefono(tel);
                    }
                }

                res.redirect("/");

                return null;
            });
            //Ruta para editar un estudiante tomando su matricula como parametro
            get("/editar/:matricula", (req, res) -> {
                try {
                    StringWriter writer = new StringWriter();
                    Map<String, Object> atributos = new HashMap<>();
                    Estudiante estudiante = null;
                    Template template = configuration.getTemplate("templates/editar.ftl");

                    for (Estudiante est : estudiantes) {
                        if (est.getMatricula() == Integer.parseInt(req.params("matricula"))) {
                            estudiante = est;
                        }
                    }

                    if (estudiante == null) {
                        throw new Exception();
                    }

                    atributos.put("estudiante", estudiante);
                    template.process(atributos, writer);

                    return writer;
                } catch (Exception error) {
                    error.printStackTrace();

                    return null;
                }
            });
            //Llamada para borrar un estudiante tomando su matricula como parametro
            post("/borrar/:matricula", (req, res) -> {
                int matricula = Integer.parseInt(req.params("matricula"));
                Estudiante estudiante = null;

                for (Estudiante est : estudiantes) {
                    if (est.getMatricula() == matricula) {
                        estudiante = est;
                    }
                }

                estudiantes.remove(estudiante);

                res.redirect("/");

                return null;
            });


        });
    }
}
