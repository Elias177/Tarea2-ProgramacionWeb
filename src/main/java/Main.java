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
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_28);
        configuration.setClassForTemplateLoading(Main.class, "/");

        ArrayList<Estudiante> estudiantes = new ArrayList<>();

        get("/", (req, res) -> {
            StringWriter writer = new StringWriter();
            Map<String, Object> atributos = new HashMap<>();
            Template template = configuration.getTemplate("templates/home.ftl");

            atributos.put("estudiantes", estudiantes);
            template.process(atributos, writer);

            return writer;
        });

        post("/agregar", (req, res) -> {
            int matricula = Integer.parseInt(req.queryParams("matricula"));
            String nombre = req.queryParams("nombre");
            String apellido = req.queryParams("apellido");
            String telefono = req.queryParams("telefono");

            estudiantes.add(new Estudiante(matricula, nombre, apellido, telefono));

            res.redirect("/");

            return null;
        });

        path("/est", () -> {
            get("/agregar", (req, res) -> {
                StringWriter writer = new StringWriter();
                Template template = configuration.getTemplate("templates/agregar.ftl");

                template.process(null, writer);

                return writer;
            });

            post("/editar", (req, res) -> {
                int matricula = Integer.parseInt(req.queryParams("matricula"));
                String nombre = req.queryParams("nombre");
                String apellido = req.queryParams("apellido");
                String telefono = req.queryParams("telefono");

                for (Estudiante est : estudiantes) {
                    if (est.getMatricula() == matricula) {
                        est.setNombre(nombre);
                        est.setApellido(apellido);
                        est.setTelefono(telefono);
                    }
                }

                res.redirect("/");

                return null;
            });

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
