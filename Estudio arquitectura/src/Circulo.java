public class Circulo extends figuras{
    double radio ;
    double diametro;

    public Circulo(int identificador, String nombre, String color, double area, double perimetro, String tipofigura, int numeroLineas, double radio, double diametro) {
        super(identificador, nombre, color, area, perimetro, tipofigura, numeroLineas);
        this.radio = radio;
        this.diametro = diametro;
    }
}
