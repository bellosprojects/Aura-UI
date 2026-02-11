import components.SuperBox;
import components.Transition;
import components.Transition.AnimationType;

import java.awt.*;
import javax.swing.*;

public class Main {
 
    public static void main(String[] args) {
        
        JFrame frame = new JFrame();
        frame.setSize(900, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new FlowLayout());
        frame.setBackground(new Color(255, 255, 255));

        SuperBox item = new SuperBox(null);
        item.setBg(new Color(250, 213, 170));
        item.setMargin(20, 30, 20, 30);
        item.setPadding(10, 20);
        item.setRadius(15);
        item.addBg(new Color(210, 200, 150), 1f);
        item.setStroke(new Color(0, 0, 0, 50), 2);
        item.setBackgroundAngle(180);
        item.setDetectChildrensHover(true);
        item.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        JLabel text = new JLabel("Bello's Projects");
        text.setFont(new Font("SansfSerif", Font.BOLD, 20));

        item.add(text);

        item.addHoverAction(isHovered -> {


            float start = item.getOffset()[1];
            float end = isHovered? -4 : 0;

            item.addTimer(
                new Transition(start, end, 200, value -> {
                    item.setOffset(0, value);
                    item.setScale(value / -40 + 1);
                }),
                AnimationType.MOVE,
                true
            );
        });


        frame.add(item);
        
        frame.setVisible(true);
        
    }

}