public class Tecnico extends Jugador{
    private static final int cantMaterias = 18;
    private static final int nivelesMinimo = 2;
    private static final int aplazosDisp = 6;
    private Materias materias;

    public Tecnico() {}

    public Tecnico(String nombre, String carrera) {
        super(nombre, carrera);
        this.materias = new matTecnicatura();
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
