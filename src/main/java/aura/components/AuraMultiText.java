package aura.components;

import java.awt.*;
import javax.swing.JTextArea;
import aura.core.AuraBox;

public class AuraMultiText extends AuraBox<AuraMultiText> {

    private final JTextArea textArea;

    public AuraMultiText(String text) {
        this.textArea = new JTextArea(text);
        this.textArea.setLineWrap(true);
        this.textArea.setWrapStyleWord(true);
        this.textArea.setOpaque(false);
        this.textArea.setBackground(new Color(0,0,0,0));
        
        this.setLayout(new BorderLayout());
        this.padding(10).add(textArea, BorderLayout.CENTER);
    }

    public AuraMultiText text(String t){
        textArea.setText(t);
        return this;
    }

    public AuraMultiText rows(int rows){
        this.textArea.setRows(rows);
        return this;
    }

    public AuraMultiText block(){
        this.textArea.setEditable(false);
        this.textArea.setFocusable(false);
        return this;
    }

    public void setText(String text){
        this.textArea.setText(text);
        repaint();
        if(this.getParent() != null) this.getParent().revalidate();
    }

}