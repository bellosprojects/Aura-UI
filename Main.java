import java.awt.*;
import javax.swing.*;

import src.main.java.aura.components.*;
import src.main.java.aura.layouts.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Aura Engine - Sprint 1 Finale");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setResizable(false);

        Image backgroundImg = new ImageIcon("C:\\Users\\bello\\Documents\\Programacion\\Java\\SGCU\\SGCU\\src\\main\\resources\\images\\comedor.png").getImage();

        AuraColumn main = new AuraColumn()
            .background(backgroundImg)
            .content(col -> {

                col.add(
                    new AuraImage("C:\\Users\\bello\\Documents\\Programacion\\Java\\SGCU\\SGCU\\src\\main\\resources\\images\\logoWhite.png")
                        .size(300, 300)
                );

                col.add(
                    new AuraText("SGCU")
                        .textColor(Color.white)
                        .font(new Font("Sans-Serif", Font.BOLD, 60))
                );

                col.add(
                    new AuraInput(22)
                        .font(new Font("Sans-serif", Font.PLAIN, 40))
                        .background(new Color(255, 255, 255, 200))
                        .radius(20, 20, 20, 20)
                );

            });

        frame.add(main);
        frame.setVisible(true);
    }
}