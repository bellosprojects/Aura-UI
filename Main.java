import java.awt.*;
import javax.swing.*;

import src.main.java.aura.animations.AnimateBackground;
import src.main.java.aura.animations.AnimateShake;
import src.main.java.aura.components.*;
import src.main.java.aura.layouts.*;
import src.main.java.aura.core.Transition;

public class Main {

    private static AuraInput ci;
    private static Transition invalidCi;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Aura Engine - Sprint 1 Finale");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());

        Image backgroundImg = new ImageIcon("C:\\Users\\bello\\Documents\\Programacion\\Java\\SGCU\\SGCU\\src\\main\\resources\\images\\comedor.png").getImage();

        Font labelFont = new Font("SansSerif", Font.PLAIN, 25);

        AuraGrid main = new AuraGrid(8, 10)
            .gap(0)
            .background(backgroundImg)
            .content(self -> {

                self.add(
                    new AuraSpacer()
                        .colSpan(3)
                        .rowSpan(7)
                );

                self.add(
                    new AuraColumn()
                        .colSpan(4)
                        .rowSpan(7)
                        .padding(30, 0, 0, 0)
                        .content(column -> {

                            column.add(
                                new AuraImage("C:\\Users\\bello\\Documents\\Programacion\\Java\\SGCU\\SGCU\\src\\main\\resources\\images\\logoWhite.png")
                                    .size(200, 200)
                            );

                            column.add(
                                new AuraText("SGCU - Iniciar secion")
                                    .textColor(Color.white)
                                    .font(new Font("SansSerif", Font.BOLD, 60))
                            );

                            column.add(new AuraSpacer());

                            column.add(
                                new AuraText("CEDULA")
                                    .alignSelf(AuraColumn.Alignment.LEFT)
                                    .textColor(Color.white)
                                    .font(labelFont)
                                    .margin(0, 50, 10, 0)
                            );

                            ci = new AuraInput(20)
                                    .background(new Color(255,255,255,200))
                                    .padding(10, 10)
                                    .font(new Font("SansSerif", Font.PLAIN, 45))
                                    .radius(15)
                                    .margin(0, 10);

                            column.add(ci);

                            invalidCi = new AnimateBackground(ci, Color.red, 300)
                                .pingPong()
                                .type(Transition.TransitionType.EASE_IN)
                                .parallel(
                                    new AnimateShake(ci, 5, 300)
                                ); 

                            column.add(
                                new AuraText("CONTRASEÑA")
                                    .alignSelf(AuraColumn.Alignment.LEFT)
                                    .textColor(Color.white)
                                    .font(labelFont)
                                    .margin(60, 50, 10, 0)
                            );

                            column.add(
                                new AuraInput(20)
                                    .background(new Color(255,255,255,200))
                                    .padding(10, 10)
                                    .font(new Font("SansSerif", Font.PLAIN, 45))
                                    .radius(15)
                            );

                            column.add(new AuraSpacer().weight(0.8f));

                        })
                );

                self.add(
                    new AuraSpacer()
                        .colSpan(3)
                        .rowSpan(7)
                );

                self.add(
                    new AuraRow()
                        .colSpan(10)
                        .align(AuraRow.Alignment.BOTTOM)
                        .content(footer -> {

                            footer.add(
                                new AuraText("© 2026 SGCU. Todos los derechos reservados.")
                                    .textColor(Color.white)
                                    .font(labelFont)
                                    .margin(20, 40)
                            );

                            footer.add(new AuraSpacer());

                            footer.add(
                                new AuraButton("Iniciar sesion")
                                    .textColor(Color.white)
                                    .font(new Font("SansSerif", Font.BOLD, 40))
                                    .margin(20, 40)
                                    .background(new Color(80, 100, 120))
                                    .onClick(button -> {
                                        invalidCi.stop(true);
                                        invalidCi.start();
                                    })
                            );

                        })
                );

            });

        frame.add(main, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}