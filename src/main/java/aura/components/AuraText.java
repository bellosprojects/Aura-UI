package src.main.java.aura.components;

import java.awt.*;
import javax.swing.JLabel;
import src.main.java.aura.core.AuraBox;

public class AuraText extends AuraBox<AuraText> {

    private final JLabel label;

    public AuraText(String text){
        label = new JLabel(text);
        label.setOpaque(false);
        add(label);
        background(new Color(0, 0, 0, 0));
        setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        addMouseEvents();
    }

    public AuraText text(String text){
        this.label.setText(text);
        repaint();
        if(getParent() != null) getParent().revalidate();
        return this;
    }

    public AuraText font(Font font){
        this.label.setFont(font);
        repaint();
        if(getParent() != null) getParent().revalidate();
        return this;
    }

    public AuraText textColor(Color color){
        this.label.setForeground(color);
        repaint();
        return this;
    }

    public String getText(){
        return this.label.getText();
    }

}