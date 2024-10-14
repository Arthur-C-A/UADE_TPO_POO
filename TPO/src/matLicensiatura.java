import java.util.Map;

public class matLicensiatura extends Materias {
    public matLicensiatura() {
        super();
        Materias.put("ALGORITMOS Y ESTRUCTURAS DE DATOS I", false);
        Materias.put("ALGORITMOS Y ESTRUCTURAS DE DATOS II", false);
        Materias.put("APLICACIONES INTERACTIVAS", false);
        Materias.put("ARQUITECTURA DE COMPUTADORES", false);
        Materias.put("CIENCIA DE DATOS", false);
        Materias.put("DERECHO INFORMATICO", false);
        Materias.put("DESARROLLO DE APLICACIONES I", false);
        Materias.put("DESARROLLO DE APLICACIONES II", false);
        Materias.put("DIRECCIÓN DE PROYECTOS DE TECNOLOGÍA", false);
        Materias.put("DISEÑO Y ANÁLISIS DE ALGORITMOS", false);
        Materias.put("DISEÑO Y DESARROLLO WEB", false);
        Materias.put("ESTADÍSTICA AVANZADA", false);
        Materias.put("EVALUACIÓN DE PROYECTOS DE TECNOLOGÍA", false);
        Materias.put("EXAMEN DE INGLÉS", false);
        Materias.put("FUNDAMENTOS DE ECONOMIA", false);
        Materias.put("GESTIÓN DE PERSONAS EN ORGANIZACIONES DE TECNOLOGÍA", false);
        Materias.put("INGENIERÍA DE DATOS I", false);
        Materias.put("INGENIERÍA DE DATOS II", false);
        Materias.put("INGENIERÍA DE SOFTWARE", false);
        Materias.put("INTRODUCCIÓN A LA ALGORITMIA", false);
        Materias.put("LIDERAZGO Y NEGOCIACION", false);
        Materias.put("MARKETING", false);
        Materias.put("MATEMATICA DISCRETA", false);
        Materias.put("NEGOCIOS TECNOLÓGICOS", false);
        Materias.put("PARADIGMA ORIENTADO A OBJETOS", false);
        Materias.put("PROBABILIDAD Y ESTADÍSTICA", false);
        Materias.put("PROCESO DE DESARROLLO DE SOFTWARE", false);
        Materias.put("REDES DE DATOS", false);
        Materias.put("SEGURIDAD DE PROCESOS Y APLICACIONES", false);
        Materias.put("SEGURIDAD E INTEGRIDAD DE LA INFORMACION", false);
        Materias.put("SEMINARIO DE GESTIÓN DE TECNOLOGÍA", false);
        Materias.put("SISTEMAS DE INFORMACIÓN I", false);
        Materias.put("SISTEMAS DE INFORMACIÓN II", false);
        Materias.put("SISTEMAS OPERATIVOS", false);
        Materias.put("TECNOLOGÍA E INNOVACIÓN", false);
        Materias.put("TENDENCIAS TECNOLÓGICAS", false);
        Materias.put("TESTING DE APLICACIONES", false);
    }

    @Override
    public void mostrarMaterias() {
        for (Map.Entry<String, Boolean> entry : Materias.entrySet()) {
            System.out.println("Materia: " + entry.getKey() + " - Estado: " + (entry.getValue() ? "Aprobada" : "No Aprobada"));
        }
    }
}

