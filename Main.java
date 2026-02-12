import components.SuperBox;
import java.awt.*;
import javax.swing.*;

public class Main {
 
    public static void main(String[] args) {
        
        JFrame frame = new JFrame();
        frame.setSize(900, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());
        frame.setBackground(new Color(255, 255,255));

        SuperBox container = new SuperBox();
        container.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        container.setBg(new Color(2, 12, 25));

        SuperBox caja = new SuperBox();
        caja.setBg(new Color(0, 0, 0, 200));
        caja.setPadding(10, 20);
        caja.setRadius(0, 0, 15, 0);

        JLabel texto = new JLabel("Hola");
        texto.setForeground(Color.white);
        texto.setFont(new Font("SansSerif", Font.BOLD, 25));

        caja.add(texto);

        container.add(caja);

        frame.add(container, BorderLayout.CENTER);

        
        frame.setVisible(true);
        
    }

}