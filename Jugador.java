public abstract class Jugador {
    private String nombre;
    private String carrera;


    public Jugador () {}

    public Jugador(String nombre, String carrera) {
        this.nombre = nombre;
        this.carrera = carrera;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    //Metodo para mostrar los datos de cada tipo de jugador
    public abstract void mostrarDatos();
    public abstract void mostrarMateriasDesaprobadas();
    public abstract void mostrarMateriasAprobadas();
    public abstract void actualizarEstadoMateria(String materia);
}
