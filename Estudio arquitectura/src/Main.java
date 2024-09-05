import java.util.ArrayList;

public class Main {
    public static void asignarJefeAProyecto(Proyecto proyecto, Jefe jefe, ArrayList<String> jefeYaAsignado,  ArrayList<Integer> proyConJefeYaAsignado) {
        /* Paso por parametro:
        - Proyecto al cual le quiero asignaar un jefe
        - El jefe a asignar
        - Array que contiene los jefes ya asignaados
        - Array que contiene el codigo del proyecto
        */
        String jefeNombre = jefe.getNombre();
        int codigoProyecto = proyecto.getCodigo();

        if (!jefeYaAsignado.contains(jefeNombre) && !proyConJefeYaAsignado.contains(codigoProyecto)) { //Verifico si no esta en la lista. Si no esta, procedo
            proyecto.asignarJefe(jefe); // asigno
            jefeYaAsignado.add(jefeNombre); // agrego a lista jefe
            proyConJefeYaAsignado.add(codigoProyecto); //agrego a lista proyectos
            System.out.println("Jefe -> " + jefeNombre + " asignado al proyecto " + proyecto.getNombre());
        } else
            System.out.println("El jefe/proyecto " + jefeNombre + " ya fue asignado.");

    }

    public static boolean verificarExistenciaNombre(String jefe, ArrayList<String> nombreEnUso) {
        if(nombreEnUso.contains(jefe)) {
            System.out.println("El nombre ya existe. Dar otro nombre");
            return true;
        }
        else {
            nombreEnUso.add(jefe);
            return false;
        }
    }


    public static void main(String[] args) {
        //Lista con nombres de jefes
        ArrayList<String> nombreEnUso = new ArrayList<>();
        //Lista con jefes asignadaos
        ArrayList<String> jefeYaAsignado = new ArrayList<>();
        //Lista con proyectos con jefes asignadaos
        ArrayList<Integer> proyConJefeYaAsignado = new ArrayList<>();

        Proyecto proyecto1 = new Proyecto(123, "Proyecto1");
        Proyecto proyecto2 = new Proyecto(456, "Proyecto2");
        Proyecto proyecto3 = new Proyecto(789, "Proyecto3");

        //Paso de creacion de jefes.
        // Verificación antes de crear el jefe
        if (!verificarExistenciaNombre("Javier", nombreEnUso)) {
            Jefe jefe1 = new Jefe(1, "Javier", "Monserrat", 12354687, 45798658);
            nombreEnUso.add(jefe1.getNombre()); // Agregar nombre a la lista
            //asignacion del jefe
            asignarJefeAProyecto(proyecto1, jefe1, jefeYaAsignado,proyConJefeYaAsignado);

        }
        if (!verificarExistenciaNombre("Lucas", nombreEnUso)) {
            Jefe jefe2 = new Jefe(2, "Lucas", "Lima", 1234589, 45879658);
            nombreEnUso.add(jefe2.getNombre()); // Agregar nombre a la lista
            //asignacion del jefe
            asignarJefeAProyecto(proyecto2, jefe2, jefeYaAsignado,proyConJefeYaAsignado);

        }

        // Creación de figuras (circulo y cuadrado)
        Circulo circulo = new Circulo(1, "Círculo", "Rojo", 78.5, 25.12, "Circulo", 0, 5.0, 10.0);
        Cuadrado cuadrado = new Cuadrado(2, "Cuadrado", "Azul", 25.0, 20.0, "Cuadrado", 4, 4, 16);

        // Creación de un plano con figuras
        Planos plano1 = new Planos(111, "12/12/2024", "Arquitecto A", 2);

        System.out.println("Figuras en el plano 1:");
        System.out.println("Figura 1: " + circulo.getNombre() + ", Área: " + circulo.getArea() + ", Perímetro: " + circulo.getPerimetro());
        System.out.println("Figura 2: " + cuadrado.getNombre() + ", Área: " + cuadrado.getArea() + ", Perímetro: " + cuadrado.getPerimetro());

        // Creación del polígono y asignación de lados
        Poligonos poligono = new Poligonos(1, "2024-09-04", "Arquitecto Agustin y Pepo", 5, 0, 0, 0, 100, null);

        // Inicializamos el número de lados (ej: polígono de 4 lados)
        poligono.inicializarLados(4);

        // Asignamos valores a los lados (en este caso, 4 lados de longitud 5.0)
        poligono.lados[0] = 5.0;
        poligono.lados[1] = 5.0;
        poligono.lados[2] = 5.0;
        poligono.lados[3] = 5.0;

        // Calculamos el perímetro del polígono
        System.out.println("Perímetro del polígono: " + poligono.calcularPerimetroPoligonos());
    }
}
