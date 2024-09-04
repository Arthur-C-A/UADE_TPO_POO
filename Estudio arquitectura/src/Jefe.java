public class Jefe {
  public int codigo;
  private String nombre;
  private String direccion;
  private int telefono;
  private int dni;



  public Jefe(int codigo, String nombre, String direccion, int telefono, int dni) {
    this.codigo = codigo;
    this.nombre = nombre;
    this.direccion = direccion;
    this.telefono = telefono;
    this.dni= dni;
  }

  public void setCodigo(int codigo) {
    this.codigo = codigo;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public void setDireccion(String direccion) {
    this.direccion = direccion;
  }

  public void setTelefono(int telefono) {
    this.telefono = telefono;
  }

  public void setDni(int dni) {
    this.dni = dni;
  }

  public int getCodigo() {
    return codigo;
  }

  public String getNombre() {
    return nombre;
  }

  public String getDireccion() {
    return direccion;
  }

  public int getTelefono() {
    return telefono;
  }

  public int getDni() {
    return dni;
  }
}
