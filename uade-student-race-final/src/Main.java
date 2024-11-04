package src;

import javax.swing.*;
import src.controller.Estudiante;
import src.controller.Tecnicatura;

public class Main {
    public static void main(String[] args) {
        Tecnicatura tec = new Tecnicatura();
        Estudiante estudiante = new Estudiante(tec);


    }
}
