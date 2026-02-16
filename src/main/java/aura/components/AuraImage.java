package src.main.java.aura.components;

import javax.swing.ImageIcon;
import java.awt.Image;

import src.main.java.aura.core.AuraBox;

public class AuraImage extends AuraBox<AuraImage> {
    
    public AuraImage(String url){

        Image img = new ImageIcon(url).getImage();
        this.background(img);
        setOpaque(false);
        this.size(100, 100);

    }

    public AuraImage(Image img){

        this.background(img);
        setOpaque(false);
        this.size(100, 100);
        
    }

}
