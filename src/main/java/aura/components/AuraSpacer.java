package src.main.java.aura.components;

import src.main.java.aura.core.AuraBox;
import java.awt.Color;

public class AuraSpacer extends AuraBox<AuraSpacer> {
    
    public AuraSpacer(){
        setLayout(null);
        weight(1f);
        background(new Color(0,0,0,0));
    }

}
