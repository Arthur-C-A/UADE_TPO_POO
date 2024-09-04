public class Cuadrado extends figuras{
    int lados ;
    int area;

    public Cuadrado(int identificador, String nombre, String color, double area, double perimetro, String tipofigura, int numeroLineas, int lados, int area1) {
        super(identificador, nombre, color, area, perimetro, tipofigura, numeroLineas);
        this.lados = lados;
        this.area = area1;
    }
}
