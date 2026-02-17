package src.main.java.aura.components;

import java.awt.*;
import javax.swing.JTextField;
import src.main.java.aura.core.AuraBox;

public class AuraInput extends AuraBox<AuraInput> {

    private final JTextField input;

    public AuraInput(){
        input = new JTextField();
        input.setOpaque(false);
        input.setBorder(null);
        input.setBackground(new Color(0,0,0,0));
        setLayout(new BorderLayout());
        add(input, BorderLayout.CENTER);
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

    @Override
        public void setBounds(int x, int y, int width, int height) {
        super.setBounds(x, y, width, height);
        if (input != null) {
            input.revalidate(); 
        }
    }
    
}
