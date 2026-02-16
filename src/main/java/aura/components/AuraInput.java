package src.main.java.aura.components;

import java.awt.*;

import javax.swing.JTextField;
import src.main.java.aura.core.AuraBox;

public class AuraInput extends AuraBox<AuraInput> {

    private final JTextField input;

    public AuraInput(int width){
        input = new JTextField(width);
        input.setOpaque(false);
        input.setBorder(null);
        input.setBackground(new Color(0,0,0,0));
        setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        add(input);
    }

    public AuraInput font(Font font){
        this.input.setFont(font);
        repaint();
        if(getParent() != null) getParent().revalidate();
        return this;
    }

    public AuraInput textColor(Color color){
        this.input.setForeground(color);
        repaint();
        return this;
    }

    public String getText(){
        return this.input.getText();
    }
    
}
