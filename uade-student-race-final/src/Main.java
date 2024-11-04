package src;

import src.controller.Estudiante;
import src.controller.Ingenieria;
import src.controller.Licenciatura;
import src.controller.Tecnicatura;

public class Main {
    public static void main(String[] args) {
        Ingenieria tec = new Ingenieria();
        Estudiante estudiante = new Estudiante(tec);
        System.out.println(estudiante.getTotalMaterias());
        System.out.println(estudiante.getCarrera());

    }
}
