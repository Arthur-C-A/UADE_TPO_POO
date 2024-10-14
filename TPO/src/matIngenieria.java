import java.util.Map;

public class matIngenieria extends Materias {
    public matIngenieria() {
        super();
        Materias.put("APLICACIONES INTERACTIVAS", false);
        Materias.put("ARQUITECTURA DE APLICACIONES", false);
        Materias.put("ARQUITECTURA DE COMPUTADORES", false);
        Materias.put("CALIDAD DE SOFTWARE", false);
        Materias.put("CIENCIA DE DATOS", false);
        Materias.put("CÁLCULO I", false);
        Materias.put("CÁLCULO II", false);
        Materias.put("DERECHO INFORMATICO", false);
        Materias.put("DESARROLLO DE APLICACIONES I", false);
        Materias.put("DESARROLLO DE APLICACIONES II", false);
        Materias.put("DIRECCION DE PROYECTOS INFORMATICOS", false);
        Materias.put("ELEMENTOS DE ÁLGEBRA Y GEOMETRÍA", false);
        Materias.put("ESTADÍSTICA AVANZADA", false);
        Materias.put("EVALUACION DE PROYECTOS INFORMATICOS", false);
        Materias.put("EXAMEN DE INGLÉS", false);
        Materias.put("FUNDAMENTOS DE INFORMATICA", false);
        Materias.put("FUNDAMENTOS DE QUÍMICA", false);
        Materias.put("FUNDAMENTOS DE TELECOMUNICACIONES", false);
        Materias.put("FÍSICA I", false);
        Materias.put("FÍSICA II", false);
        Materias.put("INGENIERÍA DE DATOS I", false);
        Materias.put("INGENIERÍA DE DATOS II", false);
        Materias.put("INGENIERÍA DE SOFTWARE", false);
        Materias.put("INTELIGENCIA ARTIFICIAL", false);
        Materias.put("MATEMATICA DISCRETA", false);
        Materias.put("MODELADO Y SIMULACION", false);
        Materias.put("NEGOCIOS TECNOLÓGICOS", false);
        Materias.put("PARADIGMA ORIENTADO A OBJETOS", false);
        Materias.put("PENSAMIENTO CRITICO Y COMUNICACION", false);
        Materias.put("PROBABILIDAD Y ESTADÍSTICA", false);
        Materias.put("PROCESO DE DESARROLLO DE SOFTWARE", false);
        Materias.put("PROGRAMACION I", false);
        Materias.put("PROGRAMACION II", false);
        Materias.put("PROGRAMACIÓN III", false);
        Materias.put("PROYECTO FINAL DE INGENIERÍA EN INFORMÁTICA", false);
        Materias.put("PRÁCTICA PROFESIONAL SUPERVISADA", false);
        Materias.put("SEGURIDAD E INTEGRIDAD DE LA INFORMACION", false);
        Materias.put("SEMINARIO DE INTEGRACIÓN PROFESIONAL", false);
        Materias.put("SISTEMAS DE INFORMACIÓN I", false);
        Materias.put("SISTEMAS DE INFORMACIÓN II", false);
        Materias.put("SISTEMAS DE REPRESENTACION", false);
        Materias.put("SISTEMAS OPERATIVOS", false);
        Materias.put("TECNOLOGÍA E INNOVACIÓN", false);
        Materias.put("TECNOLOGÍA Y MEDIO AMBIENTE", false);
        Materias.put("TELEINFORMÁTICA Y REDES", false);
        Materias.put("TENDENCIAS TECNOLÓGICAS", false);
        Materias.put("TEORIA DE SISTEMAS", false);
        Materias.put("TEORÍA DE LA COMPUTACIÓN", false);
        Materias.put("ÁLGEBRA", false);
    }

    @Override
    public void mostrarMaterias() {
        for (Map.Entry<String, Boolean> entry : Materias.entrySet()) {
            System.out.println("Materia: " + entry.getKey() + " - Estado: " + (entry.getValue() ? "Aprobada" : "No Aprobada"));
        }
    }
}
