/**
 * La clase Proyecto representa un proyecto con un código y un nombre.
 * Proporciona métodos para acceder y modificar estos atributos.
 */
public class Proyecto {
    public int codigo;
    private String nombre;
    private String jefeAsignado;


    public Proyecto(int codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public int getCodigo() {
        return codigo;
    }
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void getJefeAsignado() {
        System.out.println(jefeAsignado);
    }

    public void asignarJefe(Jefe jefe) {
        if(this.jefeAsignado== null){
            this.jefeAsignado = jefe.getNombre();
        }
        else{
            System.out.println("Este proyecto ya tiene un jefe asignado. ");
        }

    }

}

