import java.awt.*;
import javax.swing.*;
import src.main.java.aura.components.AuraButton;
import src.main.java.aura.components.AuraImage;
import src.main.java.aura.components.AuraInput;
import src.main.java.aura.layouts.*;

public class Main {


    public static void main(String[] args) {
        JFrame frame = new JFrame("Aura Engine - Sprint 1 Finale");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());

        AuraRow searchBar = new AuraRow()
            .gap(20)
            .background(new Color(0, 0, 0, 100))
            .padding(10)
            .radius(20)
            .margin(10)
            .content(row -> {

                row.add(
                    new AuraImage("lupa.png")
                        .size(40, 40)
                );

                row.add(
                    new AuraInput()
                    .weight(1f)
                    .textColor(Color.white)
                    .background(new Color(0,0,0,0))
                    .font(new Font("Arial", Font.PLAIN, 25))
                );

                row.add(
                    new AuraButton("Buscar")
                );

            });

        frame.add(searchBar, BorderLayout.NORTH);
        frame.setVisible(true);
    }
}