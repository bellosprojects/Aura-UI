import java.awt.*;
import javax.swing.*;

import src.main.java.aura.animations.AnimateWobble;
import src.main.java.aura.components.*;
import src.main.java.aura.layouts.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Aura Engine - Sprint 1 Finale");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.setLocationRelativeTo(null);

        SRow searchBar = new SRow().gap(15).padding(20).content(row -> {
        // Icono fijo
        row.add(new SContainer().size(40, 40).background(Color.GRAY).radius(10));
        
        // Campo expandible (Weight 1)
        row.add(new SContainer().weight(1).background(Color.WHITE).radius(10));
        
        // Botón con sombra
        row.add(new SContainer().size(100, 40).background(Color.BLUE).shadow(Color.BLACK, 10));
    });

        frame.add(searchBar);
        frame.setVisible(true);
    }
}