import java.util.HashMap;
import java.util.Map;

public class Materias {
    public Map<String, Boolean> Materias;

    public Materias() {
        this.Materias = new HashMap<>(); // Copia inicial para evitar modificar el original
    }
    /*
    Método para agregar una materia. consideramos que no sea posible ya que la cantidad de materias son valores final.
    public void agregarMateria(String nombre) {
        Materias.put(nombre, false);  // Agrega las materias con estado false por defecto
    }
*/
    // Método para actualizar el estado de una materia (aprobada o no)
    public void actualizarEstadoMateria(String materia) {
        if (Materias.containsKey(materia)) {
            boolean estadoActual = Materias.get(materia);
            Materias.put(materia, !estadoActual);  // Cambia el estado: si es true -> false, si es false -> true
            System.out.println("El estado de la materia " + materia + " se ha actualizado a " + (!estadoActual ? "Aprobada" : "No Aprobada"));
        }
    }
    // Método para mostrar solo materias desaprobadas
    public void mostrarMateriasDesaprobadas() {
        System.out.println("Materias pendientes (no aprobadas):");
        for (Map.Entry<String, Boolean> entry : Materias.entrySet()) {
            if (!entry.getValue()) {  // Solo muestra materias que no están aprobadas (false)
                System.out.println(entry.getKey());
            }
        }
    }
    // Método para mostrar solo materias aprobadas
    public void mostrarMateriasAprobadas() {
        System.out.println("Materias aprobadas:");
        for (Map.Entry<String, Boolean> entry : Materias.entrySet()) {
            if (entry.getValue())  // Solo muestra materias que están aprobadas (true)
                System.out.println(entry.getKey());
            }
    }
    // Mostrar todas las materias y su estado
    public void mostrarMaterias() {}

}


