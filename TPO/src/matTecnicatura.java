import java.util.Map;

public class matTecnicatura extends Materias {
    public matTecnicatura() {
        super();
        Materias.put("ALGORITMOS Y ESTRUCTURAS DE DATOS I", false);
        Materias.put("ALGORITMOS Y ESTRUCTURAS DE DATOS II", false);
        Materias.put("APLICACIONES INTERACTIVAS", false);
        Materias.put("ARQUITECTURA DE COMPUTADORES", false);
        Materias.put("DESARROLLO DE APLICACIONES I", false);
        Materias.put("DISEÑO Y ANÁLISIS DE ALGORITMOS", false);
        Materias.put("DISEÑO Y DESARROLLO WEB", false);
        Materias.put("INGENIERÍA DE DATOS I", false);
        Materias.put("INGENIERÍA DE DATOS II", false);
        Materias.put("INTRODUCCIÓN A LA ALGORITMIA", false);
        Materias.put("PARADIGMA ORIENTADO A OBJETOS", false);
        Materias.put("PROCESO DE DESARROLLO DE SOFTWARE", false);
        Materias.put("REDES DE DATOS", false);
        Materias.put("SISTEMAS DE INFORMACIÓN I", false);
        Materias.put("SISTEMAS DE INFORMACIÓN II", false);
        Materias.put("SISTEMAS OPERATIVOS", false);
        Materias.put("TESTING DE APLICACIONES", false);
        Materias.put("TRABAJO INTEGRADOR FINAL", false);
    }

    @Override
    public void mostrarMaterias() {
        for (Map.Entry<String, Boolean> entry : Materias.entrySet()) {
            System.out.println("Materia: " + entry.getKey() + " - Estado: " + (entry.getValue() ? "Aprobada" : "No Aprobada"));
        }
    }
}