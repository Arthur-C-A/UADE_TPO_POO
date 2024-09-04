/**
 La clase poligono es una subclase de la clase "figuras" que representan un polígono con atributos adicionales relacionados con sus dimensiones y características.
 */
public class Poligonos extends figuras{
    double puntoFin;
    double puntoOrigen;
    double longitud;
    int area;
    double [] lados;

    public Poligonos(int identificacionPlanos, String fechaEntrega, String arquitectos, int cantFiguras, double puntoFin, double puntoOrigen, double longitud, int area, double[] lados) {
        super(identificacionPlanos, fechaEntrega, arquitectos, cantFiguras);
        this.puntoFin = puntoFin;
        this.puntoOrigen = puntoOrigen;
        this.longitud = longitud;
        this.area = area;

    }

    // En el metodo para calcular el perimetro de los poligonos tenemos que usar "super" ya que estamos accediendo a una variable de la clase padre, que en este caso es figuras.
    public double calcularPerimetroPoligonos() {
        super.perimetro = 0.0;
        if(lados != null) {
            for (double lado : lados) {
                super.perimetro += lado;
            }
        }
        return super.perimetro;

    }
    // Decidimos crear un metodo auxiliar para inicializar los lados ya que no sabemos de antemano cuantos lados va a tener
    public void inicializarLados(int numeroLados) {
        this.lados = new double[numeroLados];
    }



}
