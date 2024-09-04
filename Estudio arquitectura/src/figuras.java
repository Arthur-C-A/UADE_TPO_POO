public class figuras extends Planos{
    int identificador;
    String nombre;
    String color;
    double area;
    double perimetro;
    String tipofigura;
    int numeroLineas;

    public figuras(int identificador, String nombre, String color, double area, double perimetro, String tipofigura, int numeroLineas) {
        this.identificador = identificador;
        this.nombre = nombre;
        this.color = color;
        this.area = area;
        this.perimetro = perimetro;
        this.tipofigura = tipofigura;
        this.numeroLineas = numeroLineas;
    }

    public figuras(int identificacionPlanos, String fechaEntrega, String arquitectos, int cantFiguras) {
        super(identificacionPlanos, fechaEntrega, arquitectos, cantFiguras);
    }

    public int getIdentificador() {
        return identificador;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public double getPerimetro() {
        return perimetro;
    }

    public void setPerimetro(double perimetro) {
        this.perimetro = perimetro;
    }

    public String getTipofigura() {
        return tipofigura;
    }

    public void setTipofigura(String tipofigura) {
        this.tipofigura = tipofigura;
    }

    public int getNumeroLineas() {
        return numeroLineas;
    }

    public void setNumeroLineas(int numeroLineas) {
        this.numeroLineas = numeroLineas;
    }
}
