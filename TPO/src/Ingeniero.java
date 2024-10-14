public class Ingeniero extends Jugador{
    private static final int cantMaterias = 50;
    private static final int nivelesMinimo = 5;
    private static final int aplazosDisp = 10;
    private Materias materias;

    public Ingeniero () {}

    public Ingeniero(String nombre, String carrera) {
        super(nombre,carrera);
        this.materias = new matIngenieria();

    }

    @Override
    public void mostrarDatos() {
        System.out.println("Nombre: " + getNombre() );
        System.out.println("Carrera: " + getCarrera());
        System.out.println("Materias de la carrera: " + cantMaterias);
        System.out.println("Niveles a superar: " + nivelesMinimo);
        System.out.println("Cantidad de aplazos disponibles: " + aplazosDisp);
        System.out.println();
        System.out.println("Materias de la carrera ");
        materias.mostrarMaterias();
    }

    public void actualizarEstadoMateria(String materia) {
        materias.actualizarEstadoMateria(materia);
    }

    public void mostrarMateriasAprobadas() {
        materias.mostrarMateriasAprobadas();
    }

    public void mostrarMateriasDesaprobadas() {
        materias.mostrarMateriasDesaprobadas();
    }


}
