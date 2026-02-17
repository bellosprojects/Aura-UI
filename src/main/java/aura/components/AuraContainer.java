package src.main.java.aura.components;

import java.awt.FlowLayout;

import src.main.java.aura.core.AuraBox;

public class AuraContainer extends AuraBox<AuraContainer> {

    public AuraContainer(){
        setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        addMouseEvents();
    }

}
